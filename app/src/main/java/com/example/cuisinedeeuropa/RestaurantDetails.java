//This activity displays all the details for the restaurant chosen

package com.example.cuisinedeeuropa;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

//IMPORTING DATABASE NAMES
import static com.example.cuisinedeeuropa.DatabaseHelper.KEY_CUISINESTYLE;
import static com.example.cuisinedeeuropa.DatabaseHelper.KEY_LATITUDE;
import static com.example.cuisinedeeuropa.DatabaseHelper.KEY_LONGITUDE;
import static com.example.cuisinedeeuropa.DatabaseHelper.KEY_PRICERANGE;
import static com.example.cuisinedeeuropa.DatabaseHelper.KEY_RANKING;
import static com.example.cuisinedeeuropa.DatabaseHelper.KEY_RATING;
import static com.example.cuisinedeeuropa.DatabaseHelper.KEY_RESTAURANT_ID;
import static com.example.cuisinedeeuropa.DatabaseHelper.KEY_REVIEWS;

public class RestaurantDetails extends AppCompatActivity implements View.OnClickListener {
    //DECLARING VARIABLES
    DatabaseManager myDatabase;
    Cursor myCursor;
    TextView name, cuisineStyle, ranking, rating, priceRange, reviews;
    Button backButton, addFavourites;
    ImageView maps;
    Integer id;
    Double longitude, latitude;
    String restaurantName;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_restaurant_details);

        //Setting up textviews
        cuisineStyle = findViewById(R.id.CuisineStyle);
        ranking = findViewById(R.id.Ranking);
        rating = findViewById(R.id.Rating);
        priceRange = findViewById(R.id.PriceRange);
        reviews = findViewById(R.id.Reviews);
        name = findViewById(R.id.RestaurantName);
        maps = findViewById(R.id.maps);
        backButton = findViewById(R.id.backButton);
        addFavourites = findViewById(R.id.addFavourites);

        restaurantName = getIntent().getStringExtra("restaurantName"); //getting restaurantName from previous intent

        myDatabase = new DatabaseManager(this);
        myDatabase.open();

        myCursor = myDatabase.getRestaurantDetails(restaurantName); //getting details of restaurant user clicked on in the listactivity intent previously

        name.setText(restaurantName); //Set the name of the restaurant to the passed string

        if(myCursor.moveToFirst()){
            do{
                cuisineStyle.setText(myCursor.getString(myCursor.getColumnIndex(KEY_CUISINESTYLE)));
                ranking.setText(myCursor.getString(myCursor.getColumnIndex(KEY_RANKING)));
                rating.setText(myCursor.getString(myCursor.getColumnIndex(KEY_RATING)));
                priceRange.setText(myCursor.getString(myCursor.getColumnIndex(KEY_PRICERANGE)));
                reviews.setText(myCursor.getString(myCursor.getColumnIndex(KEY_REVIEWS)));
                id = myCursor.getInt(myCursor.getColumnIndex(KEY_RESTAURANT_ID));
                longitude = myCursor.getDouble(myCursor.getColumnIndex(KEY_LONGITUDE));
                latitude = myCursor.getDouble(myCursor.getColumnIndex(KEY_LATITUDE));
            }while(myCursor.moveToNext());
        } //Setting up the variables while also getting longitude/lat and id for further methods


        //Setting up listeners
        addFavourites.setOnClickListener(this);
        backButton.setOnClickListener(this);
        maps.setOnClickListener(this);

        myDatabase.close();
        myCursor.close();


    }

    public void onClick(View view){
        if(view.getId()==backButton.getId()){ //IF USER PRESSES BACK
            Intent intent = new Intent(this, MainMenu.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent); //Clear all intents and go back to Main Menu
        }else if(view.getId()==addFavourites.getId()){ //IF USER PRESSES Add to favourite
            Intent swapScreens = new Intent(RestaurantDetails.this,AddFavourite.class);
            swapScreens.putExtra("id",id);
            swapScreens.putExtra("restaurantName",restaurantName);
            startActivity(swapScreens);//Pass in the id and name of the restaurant and change screens to addFavourite
        }else if(maps.getId()==view.getId()){ //If user taps the google maps icon
            if(latitude!=0) { //IF LATITUDE EXISTS(HENCE THE RESTAURANT COMES FROM THE DATASET)
                Intent swapScreens = new Intent(RestaurantDetails.this, Map.class);
                swapScreens.putExtra("latitude", latitude);
                swapScreens.putExtra("longitude", longitude);
                swapScreens.putExtra("restaurantName", restaurantName);
                startActivity(swapScreens); //pass in name, long and lat to Map activity
            }else{ //IF LATITUDE DOESN'T EXIST, THEN ITS A USER ADDED RESTAURANT, SO WE WON'T DISPLAY IT
                Toast.makeText(this,"Can't display maps, user added restaurant",Toast.LENGTH_LONG).show();
            }
        }
    }
}