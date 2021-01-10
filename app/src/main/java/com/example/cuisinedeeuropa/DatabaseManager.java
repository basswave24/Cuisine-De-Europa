//This class holds all the methods to do with databases queries, which are used throughout the application

package com.example.cuisinedeeuropa;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

//IMPORTING THE NAMES
import static com.example.cuisinedeeuropa.DatabaseHelper.FAVOURITES_TABLE;
import static com.example.cuisinedeeuropa.DatabaseHelper.KEY_CITY;
import static com.example.cuisinedeeuropa.DatabaseHelper.KEY_CUISINESTYLE;
import static com.example.cuisinedeeuropa.DatabaseHelper.KEY_LATITUDE;
import static com.example.cuisinedeeuropa.DatabaseHelper.KEY_LONGITUDE;
import static com.example.cuisinedeeuropa.DatabaseHelper.KEY_NAME;
import static com.example.cuisinedeeuropa.DatabaseHelper.KEY_NOTE;
import static com.example.cuisinedeeuropa.DatabaseHelper.KEY_PRATING;
import static com.example.cuisinedeeuropa.DatabaseHelper.KEY_PRICERANGE;
import static com.example.cuisinedeeuropa.DatabaseHelper.KEY_RANKING;
import static com.example.cuisinedeeuropa.DatabaseHelper.KEY_RATING;
import static com.example.cuisinedeeuropa.DatabaseHelper.KEY_RESTAURANT_ID;
import static com.example.cuisinedeeuropa.DatabaseHelper.KEY_FAVOURITE_RESTAURANT_ID;
import static com.example.cuisinedeeuropa.DatabaseHelper.KEY_REVIEWS;
import static com.example.cuisinedeeuropa.DatabaseHelper.RESTAURANT_TABLE;


public class DatabaseManager
{
    Context context;
    private DatabaseHelper myDatabaseHelper;
    private SQLiteDatabase myDatabase;


    public DatabaseManager(Context context)
    {
        this.context = context;

    }

    public DatabaseManager open() throws SQLException {
        myDatabaseHelper = new DatabaseHelper(context);
        myDatabase = myDatabaseHelper.getWritableDatabase();
        return this;
    }

    public void close()
    {
        myDatabaseHelper.close();
    }


    public Cursor getRestaurantDetails(String restaurantName){ //gets the details of whatever restaurant was passed in
        return myDatabase.query(RESTAURANT_TABLE, new String[] {
                        KEY_RESTAURANT_ID,
                        KEY_NAME,
                        KEY_CUISINESTYLE,
                        KEY_RANKING,
                        KEY_RATING,
                        KEY_PRICERANGE,
                        KEY_REVIEWS,
                        KEY_LATITUDE,
                        KEY_LONGITUDE},
                KEY_NAME + "=?",
                new String[]{restaurantName},
                null,
                null,
                null,
                null);
    }


    public boolean deleteFavourite(int id){
        return myDatabase.delete(FAVOURITES_TABLE, KEY_FAVOURITE_RESTAURANT_ID + "=" + id,null) >0;
    } //deletes a favourite restaurant based on the ID

    public List<String> getAllCities(){
        List<String> cities = new ArrayList<String>();
        Cursor myCursor = myDatabase.query(true,RESTAURANT_TABLE,new String[]{
                KEY_CITY},
                null,
                null,
                null,
                null,
                null,
                 null
            );


        if(myCursor.moveToFirst()){
            do{
                cities.add(myCursor.getString(0));
            } while(myCursor.moveToNext());
        }

        return cities;
    } //gets a List of cities of all the cities in the RESTAURANT_TABLE. Needed for the spinner(dropdown menu)

    public Cursor getAllFavouritesRestaurants(){
        Cursor myCursor = myDatabase.query(FAVOURITES_TABLE,new String[]{
                        KEY_FAVOURITE_RESTAURANT_ID,KEY_NAME,KEY_NOTE,KEY_PRATING},
                null,
                null,
                null,
                null,
                null,
                null
        );
        return myCursor;
    } //gets details of all the favourites restaurants

    public void updateFavourites(Integer id,String personalNote,String personalRating){
        ContentValues updateData = new ContentValues();
        updateData.put(KEY_NOTE,personalNote);
        updateData.put(KEY_PRATING,personalRating);

        myDatabase.update(FAVOURITES_TABLE,updateData, KEY_FAVOURITE_RESTAURANT_ID +"="+id,null);
    } //Updates the Personal Note and Personal Rating of the favourite restaurant selected(found from ID)


    public Cursor getRestaurants(String choice){
        return myDatabase.query(RESTAURANT_TABLE, new String[] {
                    KEY_RESTAURANT_ID,
                    KEY_NAME,
                    KEY_CITY},
                KEY_CITY + "=?",
                new String[]{choice},
                null,
                null,
                null,
                null);
    }//Gets all the restaurants from the city the user chose(from the dropdown spinner)




    public long insertRestaurant(String Name, String City, String CuisineStyle, String Ranking, String Rating,String PriceRange,String Reviews,Double Longitude, Double Latitude){
        ContentValues initialValues = new ContentValues();
        initialValues.put(KEY_NAME, Name);
        initialValues.put(KEY_CITY, City);
        initialValues.put(KEY_CUISINESTYLE, CuisineStyle);
        initialValues.put(KEY_RANKING, Ranking);
        initialValues.put(KEY_RATING, Rating);
        initialValues.put(KEY_PRICERANGE,PriceRange);
        initialValues.put(KEY_REVIEWS,Reviews);
        initialValues.put(KEY_LONGITUDE,Longitude);
        initialValues.put(KEY_LATITUDE,Latitude);
        return myDatabase.insert(RESTAURANT_TABLE,null,initialValues);
    } //Used to add a new restaurant to the dataset

    public long insertFavourite(Integer id,String PersonalNote,String PersonalReview,String Name){
        ContentValues initialValues = new ContentValues();
        initialValues.put(KEY_FAVOURITE_RESTAURANT_ID,id);
        initialValues.put(KEY_PRATING,PersonalReview);
        initialValues.put(KEY_NOTE,PersonalNote);
        initialValues.put(KEY_NAME,Name);
        return myDatabase.insert(FAVOURITES_TABLE,null,initialValues);
    } //Used to add a new favourite restaurant to the dataset

}
