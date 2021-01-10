//Activity for adding a brand new restaurant to the RESTAURANTS_TABLE

package com.example.cuisinedeeuropa;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class AddRestaurant extends AppCompatActivity implements View.OnClickListener {

    EditText restaurantName,cityName,cuisineStyle,priceRange;
    Button addRestaurant,backButton;
    DatabaseManager myDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_restaurant);


        //Setting up variables
        restaurantName = findViewById(R.id.addRestaurantName);
        cityName = findViewById(R.id.addRestaurantCity);
        cuisineStyle = findViewById(R.id.addRestaurantCuisine);
        priceRange = findViewById(R.id.addRestaurantPriceRange);
        addRestaurant = findViewById(R.id.addNewRestaurant);
        backButton = findViewById(R.id.backButtonAddRestaurant);

        //Setting up listeners
        addRestaurant.setOnClickListener(this);
        backButton.setOnClickListener(this);

    }

    public void onClick(View view){
        if(view.getId()==addRestaurant.getId()){ //USER PRESS ADD RESTAURANT
            String rName = restaurantName.getText().toString();
            String cName = cityName.getText().toString();   //WE GET VALUES FROM THE EDIT FIELDS
            String cStyle = cuisineStyle.getText().toString();
            String pRange = priceRange.getText().toString();


            if(rName.isEmpty()){
                Toast.makeText(this,"Restaurant name must not be empty",Toast.LENGTH_LONG).show();
            }else if(cName.isEmpty()){
                Toast.makeText(this,"City name must not be empty",Toast.LENGTH_LONG).show();
            }else{
                myDatabase = new DatabaseManager(this);
                myDatabase.open();
                myDatabase.insertRestaurant(rName,cName,cStyle,"User added restaurant","User added restaurant",pRange,"User added restaurant",null,null);
                myDatabase.close();
                Toast.makeText(this,"Restaurant Added", Toast.LENGTH_LONG).show();
                finish();
            }
        }else if(view.getId()==backButton.getId()){//USER PRESSES BACK BUTTON
            finish();
        }
    }
}