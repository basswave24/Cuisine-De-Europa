//This activity implements a list that displays all the restaurants from the city chosen in the dropdown menu

package com.example.cuisinedeeuropa;


import android.app.ListActivity;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;

//IMPORTING DATABSE NAMES
import static com.example.cuisinedeeuropa.DatabaseHelper.KEY_NAME;

public class Restaurants extends ListActivity { //Extending listactivity since we want to make a list
    DatabaseManager myDatabase;
    Cursor myCursor;

    String[] columns = {KEY_NAME}; //List will only have 1 column, the name column
    int[] textIDsRow = {R.id.restaurantName};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_restaurants);

        String city =getIntent().getStringExtra("choice"); //we get the city choice from previous Intent

        myDatabase = new DatabaseManager(this);
        myDatabase.open();

        myCursor = myDatabase.getRestaurants(city); //We get all the restaurants based on dropdown menu choice

        //Setting up the list to display the restaurants
        SimpleCursorAdapter myAdapter = new SimpleCursorAdapter(this,R.layout.row,myCursor,columns,textIDsRow);
        setListAdapter(myAdapter);
        myDatabase.close();
    }


    public void onListItemClick(ListView l, View V, int position, long id){ //WHEN USER CLICKS A RESTAURANT
        super.onListItemClick(l,V,position,id);
        Cursor selectedChoice =(Cursor) l.getItemAtPosition(position);
        String restaurantName = selectedChoice.getString(1); //we store the name of the restaurant
        Intent swapScreens = new Intent(Restaurants.this,RestaurantDetails.class);
        swapScreens.putExtra("restaurantName",restaurantName); //we pass it to next activty
        startActivity(swapScreens); //We change the screen to details of the restaurant the user pressed on
    }
}