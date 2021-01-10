//This activity prompts the user to select a city from a dropdown menu

package com.example.cuisinedeeuropa;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

import java.util.List;

public class SelectCity extends AppCompatActivity{

    Spinner dropdown;
    Button submitCity, backButton;
    DatabaseManager myDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_city);

        addItemsOnSpinner(); //calling function to populate the dropdown spinner

        //Setting up buttons
        submitCity =findViewById(R.id.submit);
        backButton = findViewById(R.id.backButtonSelectCity);


        //Using anon classes
        submitCity.setOnClickListener(new View.OnClickListener(){
            public void onClick(View view){ //USER PRESS SUBMITS
                String choice = dropdown.getSelectedItem().toString();
                Intent swapScreens = new Intent(SelectCity.this,Restaurants.class);
                swapScreens.putExtra("choice",choice); //We pass the chosen city
                startActivity(swapScreens); //Change screens to list of restaurants
            }
        });

        backButton.setOnClickListener(new View.OnClickListener(){
            public void onClick(View view){
                finish();
            }
        }); //USER PRESS BACKS, we finsih activity


    }

    //method to populate spinner
    public void addItemsOnSpinner(){
        dropdown = findViewById(R.id.spinner); //set up the spinner
        myDatabase = new DatabaseManager(this);
        myDatabase.open();

        List<String> cities = myDatabase.getAllCities(); //Get all the cities from database

        //Creating adapter to display the cities in a spinner
        ArrayAdapter<String> myArrayAdapter = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item,cities);
        myArrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_item);
        dropdown.setAdapter(myArrayAdapter);

        myDatabase.close();

    }
}