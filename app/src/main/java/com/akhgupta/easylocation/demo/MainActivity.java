package com.akhgupta.easylocation.demo;

import android.content.Intent;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.akhgupta.easylocation.EasyLocationAppCompatActivity;
import com.akhgupta.easylocation.EasyLocationRequest;
import com.akhgupta.easylocation.EasyLocationRequestBuilder;
import com.akhgupta.easylocation.demo.activity.Home;
import com.akhgupta.easylocation.demo.activity.MainActivity2;
import com.akhgupta.easylocation.demo.activity.NearbyPlaceMaster;
import com.google.android.gms.location.LocationRequest;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

import static com.akhgupta.easylocation.demo.activity.Home.isplaceUpdate;
import static com.akhgupta.easylocation.demo.activity.Home.la;
import static com.akhgupta.easylocation.demo.activity.Home.lo;
import static com.akhgupta.easylocation.demo.activity.Home.parentparameter;

public class MainActivity extends EasyLocationAppCompatActivity {
    @BindView(R.id.requestSingleLocationButton)
    Button requestSingleLocationButton;
    @BindView(R.id.requestLocationUpdatesButton)
    Button requestLocationUpdatesButton;
    private Unbinder unbinder;
    TextView txtlocation;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        unbinder = ButterKnife.bind(this);
        txtlocation= (TextView) findViewById(R.id.txtlocation);
    }
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater=getMenuInflater();
        menuInflater.inflate(R.menu.mastermenu,menu);
        return true;
    }

    public void home(MenuItem item) {
        Home.tourID="";
        Home.costID="0";
        Intent intent=new Intent(MainActivity.this,Home.class);
        startActivity(intent);
    }

    public void logOut(MenuItem item) {
        Home.tourID="";
        Home.costID="0";
        Intent intent=new Intent(MainActivity.this,MainActivity2.class);
        startActivity(intent);
    }

    @Override
    public void onLocationPermissionGranted() {
        showToast("Location permission granted");
    }

    private void showToast(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onLocationPermissionDenied() {
        showToast("Location permission denied");
    }

    @Override
    public void onLocationReceived(Location location) {
        showToast(location.getProvider() + "," + location.getLatitude() + "," + location.getLongitude());
        la= String.valueOf(location.getLatitude());
        lo= String.valueOf(location.getLongitude());
        Geocoder gcd = new Geocoder(MainActivity.this, Locale.getDefault());
        List<Address> addresses = null;
        try {
            addresses = gcd.getFromLocation(Double.valueOf(la), Double.valueOf(lo), 1);
        } catch (IOException e) {
            e.printStackTrace();
        }
        if (addresses.size() > 0)
            System.out.println(addresses.get(0).getThoroughfare()+", "+addresses.get(0).getLocality()+", "+addresses.get(0).getCountryName());
        txtlocation.setText(addresses.get(0).getThoroughfare()+", "+addresses.get(0).getLocality()+", "+addresses.get(0).getCountryName());
        parentparameter=txtlocation.getText().toString();
        Toast.makeText(this, "Parent: "+parentparameter , Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onLocationProviderEnabled() {
        showToast("Location services are now ON");
    }

    @Override
    public void onLocationProviderDisabled() {
        showToast("Location services are still Off");
    }

    @OnClick({R.id.requestSingleLocationButton, R.id.requestLocationUpdatesButton})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.requestSingleLocationButton: {
                LocationRequest locationRequest = new LocationRequest()
                        .setPriority(LocationRequest.PRIORITY_BALANCED_POWER_ACCURACY)
                        .setInterval(5000)
                        .setFastestInterval(5000);
                EasyLocationRequest easyLocationRequest = new EasyLocationRequestBuilder()
                        .setLocationRequest(locationRequest)
                        .build();
                requestSingleLocationFix(easyLocationRequest);
                isplaceUpdate="Yes";
            }
            break;
            case R.id.requestLocationUpdatesButton: {
               /* LocationRequest locationRequest = new LocationRequest()
                        .setPriority(LocationRequest.PRIORITY_BALANCED_POWER_ACCURACY)
                        .setInterval(5000)
                        .setFastestInterval(5000);
                EasyLocationRequest easyLocationRequest = new EasyLocationRequestBuilder()
                        .setLocationRequest(locationRequest)
                        .build();
                requestLocationUpdates(easyLocationRequest);*/
                Toast.makeText(this, "Current Location Has been Cleared", Toast.LENGTH_LONG).show();
                isplaceUpdate="No";
                parentparameter="Dhaka, Bangladesh";
            }
            break;
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unbinder.unbind();
    }
}