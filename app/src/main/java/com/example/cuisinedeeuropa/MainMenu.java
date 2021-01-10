/*This activity is the main menu activity, this is the first screen the user will see
    PROJECT MADE BY : DAN ANDREI PIRLEA
    STUDENT NUMBER : C18444682
 */

package com.example.cuisinedeeuropa;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainMenu extends AppCompatActivity implements View.OnClickListener {

    DatabaseManager myDatabase;
    Button selectCity;
    Button addRestaurant;
    Button viewFavourites;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        startService(new Intent(MainMenu.this, BGM.class)); //starts up the Background music which will play till app is closed


        //Setting up variables
        selectCity = findViewById(R.id.selectCity);
        addRestaurant = findViewById(R.id.addRestaurant);
        viewFavourites = findViewById(R.id.browseFavourites);

        //Setting up listeners
        selectCity.setOnClickListener(this);
        addRestaurant.setOnClickListener(this);
        viewFavourites.setOnClickListener(this);


        //This is used just once to populate the data with restaurants
        //insertData();

    }

    public void onClick(View view){
        if(view.getId()==selectCity.getId()){ //SELECT CITY BUTTON
            Intent swapScreens = new Intent(MainMenu.this,SelectCity.class);
            startActivity(swapScreens);
        }else if(view.getId()==addRestaurant.getId()){ //ADD RESTAURANT BUTTON
            Intent swapScreens = new Intent(MainMenu.this,AddRestaurant.class);
            startActivity(swapScreens);
        }else if(view.getId()==viewFavourites.getId()){ //VIEWFAVOURITES BUTTON
            Intent swapScreens = new Intent(MainMenu.this,ViewFavourites.class);
            startActivity(swapScreens);
        }//Starting a different activity depending what button was chosen
    }


    /*DATASET NAME: "TripAdvisor Restaurants Info for 31 Euro-Cities"
    MADE BY: Damien Beneschi
    Link : https://www.kaggle.com/damienbeneschi/krakow-ta-restaurans-data-raw
    I have reduced the dataset significantly as it had over 10000 rows
    I also got the latitude and longitude for each restaurant myself as the original dataset didn't contain this
     */

    public void insertData(){
        myDatabase = new DatabaseManager(this);
        myDatabase.open();
        myDatabase.insertRestaurant("Martine of Martine's Table", "Amsterdam", " 'French', 'Dutch', 'European' ", "1", "5", "$$ - $$$", "  'Just like home', 'A Warm Welcome to Wintry Amsterdam'", 52.37500838, 4.900006254);
        myDatabase.insertRestaurant("De Silveren Spiegel", "Amsterdam", " 'Dutch', 'European', 'Vegetarian Friendly', 'Gluten Free Options' ", "2", "4.5", "$$$$", "  'Great food and staff', 'just perfect'", 52.37786457, 4.894156033);
        myDatabase.insertRestaurant("La Rive", "Amsterdam", " 'Mediterranean', 'French', 'International', 'European', 'Vegetarian Friendly', 'Vegan Options' ", "3", "4.5", "$$$$", "  'Satisfaction', 'Delicious old school restaurant'", 52.36005337, 4.905597034);
        myDatabase.insertRestaurant("Librije's Zusje Amsterdam", "Amsterdam", " 'Dutch', 'European', 'International', 'Vegetarian Friendly', 'Vegan Options', 'Gluten Free Options' ", "5", "4.5", "$$$$", "  'Best meal.... EVER', 'super food experience'", 52.36477276, 4.897405294);
        myDatabase.insertRestaurant("Ciel Bleu Restaurant", "Amsterdam", " 'Contemporary', 'International', 'Vegetarian Friendly', 'Vegan Options', 'Gluten Free Options' ", "6", "4.5", "$$$$", "  'A treat!', 'Wow just Wow'", 52.34898529, 4.894564626);
        myDatabase.insertRestaurant("Simul Gastronomic Situ", "Athens", " 'European', 'Vegetarian Friendly', 'Vegan Options', 'Gluten Free Options' ", "1", "5", "$$ - $$$", "  'Excellent experience', 'A nice surprise'", 37.97802752, 23.75069532);
        myDatabase.insertRestaurant("Cinque Wine & Deli Bar", "Athens", " 'Wine Bar', 'Greek', 'Delicatessen', 'Gluten Free Options' ", "2", "5", "$", "  'One of the best restaurants we ever visite...', 'A truly delightful evening'", 37.97855506, 23.72376478);
        myDatabase.insertRestaurant("Dinner in the Sky", "Athens", " 'Mediterranean', 'European', 'Greek', 'Vegetarian Friendly', 'Vegan Options', 'Gluten Free Options' ", "3", "5", "$$$$", "  'Great sensorial experience', 'Perfect Way to Watch the Sunset in Athens'", 37.97798626, 23.71361387);
        myDatabase.insertRestaurant("Aleria", "Athens", " 'Greek', 'Mediterranean', 'European', 'Vegetarian Friendly', 'Vegan Options', 'Gluten Free Options' ", "4", "5", "$$$$", "  'Surprise Find!', 'Fabulous evening'", 37.98325536, 23.71796532);
        myDatabase.insertRestaurant("Feyrouz", "Athens", " 'Lebanese', 'Fast Food', 'Mediterranean', 'Middle Eastern', 'Vegetarian Friendly', 'Vegan Options' ", "5", "5", "$", "  'A hidden gem in Athens', 'Not just great - this is awesome food.'", 37.97833763, 23.72781467);
        myDatabase.insertRestaurant("Funky Gourmet", "Athens", " 'Mediterranean', 'European', 'Greek', 'Contemporary', 'Vegetarian Friendly', 'Vegan Options', 'Gluten Free Options' ", "6", "5", "$$$$", "  'Anniversary Dinner', 'Puerto Rico meets the Brits in Funky Gourm...'", 37.98335596, 23.71644824);
        myDatabase.insertRestaurant("Uma", "Barcelona", " 'International', 'Mediterranean', 'Fusion', 'European', 'Spanish' ", "1", "5", "$$$$", "  'Perfect atmosphere and location', 'Perfection'", 41.39610192, 2.163783797);
        myDatabase.insertRestaurant("Viana", "Barcelona", " 'International', 'Mediterranean', 'Spanish', 'Vegetarian Friendly', 'Vegan Options', 'Gluten Free Options' ", "2", "5", "$$ - $$$", "  'Wow! Best ever!', 'Small menu-- GET A RESERVATION'", 41.38004129, 2.177273855);
        myDatabase.insertRestaurant("Blavis", "Barcelona", " 'Mediterranean', 'European', 'Spanish', 'Vegetarian Friendly', 'Vegan Options', 'Gluten Free Options' ", "3", "5", "$$ - $$$", "  'The whole package', 'Incredible Service and Tapas!'", 41.40350227, 2.147554791);
        myDatabase.insertRestaurant("FACIL", "Berlin", " 'International', 'European', 'Gluten Free Options' ", "1", "4.5", "$$$$", "  'What a way to turn 60!', 'Only to recommend'", 52.50911635, 13.37380593);
        myDatabase.insertRestaurant("La Gondola Due", "Berlin", " 'Italian', 'Mediterranean', 'European', 'Vegetarian Friendly', 'Vegan Options', 'Gluten Free Options' ", "2", "4.5", "$$ - $$$", "  'Great food!', 'Very good'", 52.43778263, 13.23356727);
        myDatabase.insertRestaurant("Ristorante A Mano", "Berlin", " 'Italian', 'Mediterranean', 'European', 'Vegetarian Friendly', 'Vegan Options' ", "3", "4.5", "$$ - $$$", "  'Sensational!', 'Delicious food and lovely service'", 52.51948516, 13.42771184);
        myDatabase.insertRestaurant("Comme Chez Soi", "Budapest", " 'Italian', 'Mediterranean', 'European', 'Hungarian', 'Vegetarian Friendly', 'Gluten Free Options' ", "1", "5", "$$ - $$$", "  'Great place!', 'Best best best'", 47.49472616, 19.05134963);
        myDatabase.insertRestaurant("Bors GasztroBar", "Budapest", " 'Fast Food', 'International', 'European', 'Street Food', 'Soups', 'Hungarian', 'Vegetarian Friendly', 'Vegan Options', 'Gluten Free Options' ", "2", "5", "$", "  'The best of soup & sandwich', 'DO YOURSELF A SOLID, eat here.'", 47.49686441, 19.0635487);
        myDatabase.insertRestaurant("Local Korner", "Budapest", " 'Pizza', 'Vegan Options' ", "3", "4.5", "$", "  'Best pizza from Budapest...!!!', 'Very good'", 47.4953852, 19.05794374);
        myDatabase.insertRestaurant("Mulberry Garden", "Dublin", " 'Irish', 'European', 'Contemporary', 'Vegetarian Friendly', 'Vegan Options', 'Gluten Free Options' ", "1", "4.5", "$$$$", "  'Great Experience on Food and Service - Str...', 'Great food and super professional staff!'", 53.32284518, -6.236829131);
        myDatabase.insertRestaurant("Bloom Brasserie", "Dublin", " 'Irish', 'European', 'Wine Bar', 'Central European', 'Vegetarian Friendly', 'Vegan Options', 'Gluten Free Options' ", "2", "4.5", "$$ - $$$", "  'Good but no wow factor', 'Great Brunch'", 53.33376611, -6.244968289);
        myDatabase.insertRestaurant("The Vintage Kitchen", "Dublin", " 'Irish', 'European', 'Soups', 'Vegetarian Friendly', 'Vegan Options', 'Gluten Free Options' ", "3", "4.5", "$$ - $$$", "  'Plan on spending a while, for a truly fabu...', 'Wow'", 53.34704168, -6.255673144);
        myDatabase.insertRestaurant("Estamine Art Food Drink", "Lisbon", " 'Bar', 'Pub', 'Healthy', 'Brazilian', 'Vegetarian Friendly', 'Vegan Options', 'Gluten Free Options' ", "1", "5", "$", "  'Fabulous food and people - book in advance...', 'Best mojitos in Lisbon!'", 38.71882514, -9.131824218);
        myDatabase.insertRestaurant("BA Wine Bar do Bairro Alto", "Lisbon", " 'Portuguese', 'Wine Bar', 'Gluten Free Options' ", "2", "4.5", "$$ - $$$", "  'This place should be required for all trav...', 'A journey through Portuguese wines in a co...'", 38.71268516, -9.145429918);
        myDatabase.insertRestaurant("La Buvette da Mae d'Agua", "Lisbon", " 'French', 'Mediterranean', 'Healthy', 'Portuguese', 'European', 'Vegetarian Friendly', 'Vegan Options', 'Gluten Free Options' ", "3", "5", "$$ - $$$", "  'A little gem in Lisbon', 'Unexpected gastronomic experience'", 38.71743901, -9.146288029);
        myDatabase.insertRestaurant("R & H cafe gallery", "London", " 'Cafe', 'Middle Eastern', 'Persian', 'Vegetarian Friendly', 'Vegan Options', 'Gluten Free Options' ", "1", "5", "$$ - $$$", "  'Amazing cakes', 'Breakfatst'", 51.45671883, -0.303088602);
        myDatabase.insertRestaurant("Osteria Romana", "London", " 'Italian', 'Mediterranean', 'Vegetarian Friendly' ", "2", "5", "$$$$", "  'Small restaurant with the touch of Italian...', 'One of the best'", 51.5023085, -0.162978231);
        myDatabase.insertRestaurant("The Oystermen Seafood Bar & Kitchen", "London", " 'Seafood', 'British', 'Gluten Free Options' ", "3", "5", "$$ - $$$", "  'Fantasic!!', 'Superb Food, Warm and Welcoming Hospitalit...'", 51.51144971, -0.123655433);
        myDatabase.insertRestaurant("La Meduse", "Paris", " 'French', 'Seafood', 'European', 'Vegetarian Friendly', 'Gluten Free Options', 'Vegan Options' ", "1", "5", "$$ - $$$", "  'You can't figure out from the outside the...', 'Cosy little restaurant on the border of Ca...'", 48.86158217, 2.378029784);
        myDatabase.insertRestaurant("Le Cappiello", "Paris", " 'French', 'Mediterranean', 'European', 'Contemporary', 'Vegetarian Friendly', 'Vegan Options', 'Gluten Free Options' ", "2", "5", "$$ - $$$", "  'Incredible!', 'Amazing food, wonderful atmosphere!'", 48.8470636, 2.2989848);
        myDatabase.insertRestaurant("ASPIC", "Paris", " 'French', 'European', 'Contemporary' ", "3", "5", "$$$$", "  'Second time and just as good', 'Best dinner in Paris by far'", 48.87937247, 2.3437044399999998);
        myDatabase.insertRestaurant("Federica & Barbara of BB Kitchen", "Rome", " 'Italian' ", "1", "5", "$$ - $$$", "  'Unmissable experience', 'Magical evening'", 41.89003589, 12.47094503);
        myDatabase.insertRestaurant("Pane e Salame", "Rome", " 'Italian', 'Street Food', 'Vegetarian Friendly' ", "2", "5", "$", "  'Very nice panini', 'The best'", 41.90087037, 12.4817728);
        myDatabase.insertRestaurant("Two Sizes", "Rome", " 'Fast Food', 'Italian', 'Cafe', 'Vegetarian Friendly' ", "3", "5", "$", "  'Worth a stop!', 'Delicious!'", 41.8982286, 12.47149305);
        myDatabase.insertRestaurant("Casino Restaurant Wien", "Vienna", " 'Austrian', 'European', 'Central European', 'International', 'Vegetarian Friendly', 'Vegan Options' ", "1", "5", "$$$$", "  'Fine dining at it's best', 'Xmas dinner'", 48.20548337, 16.37090312);
        myDatabase.insertRestaurant("Die Metzgerei", "Vienna", " 'Austrian', 'European', 'Central European', 'Vegetarian Friendly', 'Gluten Free Options' ", "2", "4.5", "$$ - $$$", "  'Dinner in Vienna', 'Great food with a comfortable and warm atm...'", 48.19487429, 16.29514128);
        myDatabase.insertRestaurant("Vinothek W-Einkehr", "Vienna", " 'Austrian' ", "3", "4.5", "$$ - $$$", "  'Excellent', 'Worth the ratings'", 48.20960807, 16.37841416);
        myDatabase.insertRestaurant("San Thai", "Warsaw", " 'Thai', 'Asian', 'Vegetarian Friendly', 'Gluten Free Options', 'Vegan Options' ", "1", "5", "$", "  'Great experience', 'Best Thai food out of Thailand!'", 52.23678974, 21.00497);
        myDatabase.insertRestaurant("Soto Sushi - Warynskiego", "Warsaw", " 'Japanese', 'Sushi', 'Asian', 'Vegetarian Friendly', 'Vegan Options', 'Gluten Free Options' ", "2", "4.5", "$$ - $$$", "  'Best sushi I've ever tried', 'One of best sushi bars in the city'", 52.22103663, 21.01606701);
        myDatabase.insertRestaurant("Amber Room", "Warsaw", " 'Polish', 'European', 'Central European', 'International', 'Vegetarian Friendly', 'Vegan Options', 'Gluten Free Options' ", "3", "4.5", "$$$$", "  'Lovely building and tasty food', 'Great experience!'", 52.22154904, 21.02402997);
        myDatabase.close();
    }
}