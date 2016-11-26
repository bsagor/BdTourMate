package com.akhgupta.easylocation.demo.activity;

import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.widget.Toast;

import com.akhgupta.easylocation.demo.R;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;
import com.google.android.gms.maps.model.MarkerOptions;

import static com.akhgupta.easylocation.demo.activity.Home.la;
import static com.akhgupta.easylocation.demo.activity.Home.lo;
import static com.akhgupta.easylocation.demo.activity.Home.mapTitle;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }


    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    private LatLngBounds ADELAIDE = new LatLngBounds(
            new LatLng(-35.0, 138.58), new LatLng(-34.9, 138.61));
// Constrain the camera target to the Adelaide bounds.

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        
        // Add a marker in Sydney and move the camera
        double lat,lon;
        lat=Double.valueOf(la);
        lon=Double.valueOf(lo);
        /*Toast.makeText(this, "la: "+String.valueOf(lat), Toast.LENGTH_SHORT).show();*/
        LatLng sydney = new LatLng(lat, lon);
        mMap.addMarker(new MarkerOptions().position(sydney).title(mapTitle.toString()));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney));
        mMap.setMinZoomPreference(10.0f);
        mMap.setMaxZoomPreference(20.0f);




    }
}
