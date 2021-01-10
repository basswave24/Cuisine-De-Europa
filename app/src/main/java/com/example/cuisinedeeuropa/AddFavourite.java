//This activity adds the chosen restaurant to the user's favourites

package com.example.cuisinedeeuropa;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class AddFavourite extends AppCompatActivity implements View.OnClickListener {

    TextView confirmation;
    Button submit,back;
    DatabaseManager myDatabase;
    EditText personalNote, personalRating;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_favourite);

        //Setting up variables
        confirmation = findViewById(R.id.confirmation);
        submit = findViewById(R.id.submitFavourite);
        personalNote = findViewById(R.id.personalNote);
        personalRating = findViewById(R.id.personalRating);
        back = findViewById(R.id.backButtonAddFavourite);


        String rName = getIntent().getStringExtra("restaurantName");
        confirmation.setText("You are about to add "+rName+" to your favourites");


        //Setting up listeners
        submit.setOnClickListener(this);
        back.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        if(view.getId()==submit.getId()) { //IF USER PRESS SUBMIT
            myDatabase = new DatabaseManager(getApplicationContext());
            myDatabase.open();
            String pNote = personalNote.getText().toString();//gets text user inputted from edittext
            String pRating = personalRating.getText().toString();//gets text user inputted from eddittext
            String name = getIntent().getStringExtra("restaurantName");//gets the name of restaurant from previous intent
            Integer id = getIntent().getIntExtra("id", 0);//gets the ID of the restaurant from previous intent
            myDatabase.insertFavourite(id, pNote, pRating, name); //inserts a favourite
            Toast.makeText(getApplicationContext(),"You have added "+name+" to your favourites",Toast.LENGTH_LONG).show();//displays a message
            myDatabase.close();
            finish();
        }else if(view.getId()==back.getId()){ //IF USER PRESSES BACK
            finish();
        }
    }
}