//This activity updates the personal note and personal rating of the chosen favourite restaurant

package com.example.cuisinedeeuropa;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class UpdateFavourite extends AppCompatActivity {

    TextView confirmation;
    Button submit;
    DatabaseManager myDatabase;
    EditText personalNote,personalRating;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_favourite);

        String name = getIntent().getStringExtra("name"); //getting name

        //Setting up various elements
        submit = findViewById(R.id.submitFavouriteUpdate);
        personalNote = findViewById(R.id.personalNoteUpdate);
        personalRating = findViewById(R.id.personalRatingUpdate);
        confirmation = findViewById(R.id.confirmationUpdate);

        confirmation.setText("You are about to update information for "+name);

        //Anonymous class
        submit.setOnClickListener(new View.OnClickListener(){
            public void onClick(View view) {
                if(view.getId()==submit.getId()){ //USER PRESSES SUBMIT
                    myDatabase = new DatabaseManager(getApplicationContext());
                    myDatabase.open();

                    Integer id = getIntent().getIntExtra("updateID",0);//Get ID from previous intent
                    String pNote = personalNote.getText().toString(); //Get updated personal Note
                    String pRating = personalRating.getText().toString(); //Get updated personal Rating
                    String name = getIntent().getStringExtra("name"); //Get name from previous intent

                    myDatabase.updateFavourites(id, pNote, pRating);//update using the ID and the new personalNote and personalRating
                    Toast.makeText(getApplicationContext(),"You have updated "+name+" to favourites",Toast.LENGTH_LONG).show();
                    myDatabase.close();

                    //Go back to main menu
                    Intent intent = new Intent(getApplicationContext(), MainMenu.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                    startActivity(intent);
                }
        }});
    }
}
