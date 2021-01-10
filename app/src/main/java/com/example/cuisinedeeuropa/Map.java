/*REFERENCE:

Most of this code was gotten from https://developers.google.com/maps/documentation/android-sdk/start
and https://developers.google.com/maps/documentation/android-sdk/map-with-marker
However I had to do my own twist on it since I didn't hardcode the longitude and latitude
And I had to get the intents
 */

//This activty implements a google maps with a marker for the restaurant passed as an intent


package com.example.cuisinedeeuropa;

import androidx.fragment.app.FragmentActivity;

import android.os.Bundle;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class Map extends FragmentActivity implements OnMapReadyCallback {

    Double latitude;
    Double longitude;
    String restaurantName;
    GoogleMap map;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map);

        //Getting the values from RestaurantDetails intent
        latitude = getIntent().getDoubleExtra("latitude",0);
        longitude = getIntent().getDoubleExtra("longitude",0);
        restaurantName = getIntent().getStringExtra("restaurantName");

        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);

        mapFragment.getMapAsync(this);

    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        map = googleMap;
        LatLng Restaurant = new LatLng(longitude,latitude);//These are the values gotten from Restaurant Details intent
        map.addMarker(new MarkerOptions().position(Restaurant).title(restaurantName));//Name gotten from Restaurant Details intent
        map.animateCamera(CameraUpdateFactory.newLatLngZoom(Restaurant,10),1500,null);//We want to zoom in a tiny bit so we are doing 10 zoom level
                                                                                                            //We also want it to zoom in closely over 1.5 seconds, hence the 1500 value
    }
}
//REFERENCE COMPLETE