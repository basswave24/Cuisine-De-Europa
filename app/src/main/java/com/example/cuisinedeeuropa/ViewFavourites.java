//This activity displays a list to the user of all their favourite restaurants

package com.example.cuisinedeeuropa;


import android.app.AlertDialog;
import android.app.ListActivity;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.Toast;

//IMPORTING DATABASE NAMES
import static com.example.cuisinedeeuropa.DatabaseHelper.KEY_NAME;
import static com.example.cuisinedeeuropa.DatabaseHelper.KEY_NOTE;
import static com.example.cuisinedeeuropa.DatabaseHelper.KEY_PRATING;
import static com.example.cuisinedeeuropa.DatabaseHelper.KEY_FAVOURITE_RESTAURANT_ID;

public class ViewFavourites extends ListActivity {
    DatabaseManager myDatabase;
    Cursor myCursor;


    String[] columns = {KEY_NAME, KEY_NOTE, KEY_PRATING}; //Listview will have these 3 columns
    int[] textIDsRow = {R.id.favouriteName, R.id.personalNoteList, R.id.personalRatingList};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_restaurants);


        myDatabase = new DatabaseManager(this);
        myDatabase.open();

        myCursor = myDatabase.getAllFavouritesRestaurants();
        //Creating the list
        SimpleCursorAdapter myAdapter = new SimpleCursorAdapter(this, R.layout.rowfavourites, myCursor, columns, textIDsRow);
        setListAdapter(myAdapter);
        myDatabase.close();
    }


    public void onListItemClick(ListView l, View V, int position, long id) {
        super.onListItemClick(l, V, position, id);
        final Cursor selectedChoice = (Cursor) l.getItemAtPosition(position);
        /*REFERENCE :
            https://stackoverflow.com/questions/2115758/how-do-i-display-an-alert-dialog-on-android
            Used a little bit of this code, however I had to do it my own way since I had to incorporate it in the listview onlistItemClick's method
         */
        final AlertDialog.Builder myBuilder = new AlertDialog.Builder(this);
        myBuilder.setMessage("Update or Delete this entry?");
        myBuilder.setCancelable(true);

        myBuilder.setPositiveButton(
                "Update", //IF USER PRESSES UPDATE
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        int updateID = selectedChoice.getInt(selectedChoice.getColumnIndex(KEY_FAVOURITE_RESTAURANT_ID));
                        String name = selectedChoice.getString(selectedChoice.getColumnIndex(KEY_NAME));

                        Intent swapScreens = new Intent(ViewFavourites.this,UpdateFavourite.class);
                        swapScreens.putExtra("updateID",updateID); //We pass the ID and name of the favourite restaurant
                        swapScreens.putExtra("name",name);
                        startActivity(swapScreens); //swap screens to update screen
                    }
                });

        myBuilder.setNegativeButton(
                "Delete", //IF USER PRESSES DELETE
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        myDatabase = new DatabaseManager(getApplicationContext());
                        myDatabase.open();
                        int deleteid = selectedChoice.getInt(selectedChoice.getColumnIndex(KEY_FAVOURITE_RESTAURANT_ID)); //GETS ID OF FAVOURITE
                        String name = selectedChoice.getString(selectedChoice.getColumnIndex(KEY_NAME)); //GETS NAME OF FAVOURITE
                        myDatabase.deleteFavourite(deleteid); //Delete chosen restaurant
                        Toast.makeText(getApplicationContext(),"You have deleted "+name+" from favourites!",Toast.LENGTH_LONG).show();
                        dialog.cancel();
                        finish();
                        startActivity(getIntent());
                        myDatabase.close();
                    }
                });

        myBuilder.create().show(); //show the alertDialog
        //REFERENCE COMPLETE
    }
}