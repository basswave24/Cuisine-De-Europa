//Creates the 2 tables we have as well as defining the variables

package com.example.cuisinedeeuropa;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper
{

    //Restaurant table
    public static final String KEY_RESTAURANT_ID = "_id";
    public static final String KEY_NAME = "Name";
    public static final String KEY_CITY = "City";
    public static final String KEY_CUISINESTYLE = "CuisineStyle";
    public static final String KEY_RANKING = "Ranking";
    public static final String KEY_RATING ="Rating" ;
    public static final String KEY_PRICERANGE = "PriceRange";
    public static final String KEY_REVIEWS = "Reviews";
    public static final String KEY_LONGITUDE = "Longitude";
    public static final String KEY_LATITUDE = "Latitude";

    //Favourites table
    public static final String KEY_NOTE = "PersonalNote";
    public static final String KEY_PRATING = "PersonalRating";
    public static final String KEY_FAVOURITE_RESTAURANT_ID = "_id";



    public static final String DATABASE_NAME = "RestaurantDataset";
    public static final String RESTAURANT_TABLE = "Restaurant";
    public static final String FAVOURITES_TABLE = "Favourites";
    public static final int DATABASE_VERSION = 1;

    //CREATING RESTAURANTS TABLE
    private static final String CREATE_RESTAURANTS =
            "create table " + RESTAURANT_TABLE  + "(" +
                    KEY_RESTAURANT_ID + " integer primary key autoincrement, " +
                    KEY_NAME + " text not null, " +
                    KEY_CITY + " text not null, " +
                    KEY_CUISINESTYLE + " text, " +
                    KEY_RANKING + " integer, " +
                    KEY_RATING + " integer, " +
                    KEY_PRICERANGE + " text, " +
                    KEY_REVIEWS + " text, " +
                    KEY_LONGITUDE + " real, "+
                    KEY_LATITUDE + " real);";

    //CREATING FAVOURITES TABLE
    private static final String CREATE_FAVOURITES =
            "create table " + FAVOURITES_TABLE + "(" +
                    KEY_FAVOURITE_RESTAURANT_ID + " integer primary key, "+
                    KEY_NOTE + " text, " +
                    KEY_PRATING + " integer, " +
                    KEY_NAME + " text);";

    public DatabaseHelper(Context context)
    {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }


    @Override
    public void onCreate(SQLiteDatabase db)
    {
        db.execSQL(CREATE_RESTAURANTS);
        db.execSQL(CREATE_FAVOURITES);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion,
                          int newVersion)
    {
        // If you want to change the structure of your database, e.g.

    }
}

