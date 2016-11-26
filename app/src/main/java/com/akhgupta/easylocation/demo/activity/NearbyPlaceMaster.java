package com.akhgupta.easylocation.demo.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;

import com.akhgupta.easylocation.demo.MainActivity;
import com.akhgupta.easylocation.demo.R;

import static com.akhgupta.easylocation.demo.activity.Home.parameter;

public class NearbyPlaceMaster extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nearby_place_master);
    }
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater=getMenuInflater();
        menuInflater.inflate(R.menu.mastermenu,menu);
        return true;
    }

    public void home(MenuItem item) {
        Home.tourID="";
        Home.costID="0";
        Intent intent=new Intent(NearbyPlaceMaster.this,Home.class);
        startActivity(intent);
    }

    public void logOut(MenuItem item) {
        Home.tourID="";
        Home.costID="0";
        Intent intent=new Intent(NearbyPlaceMaster.this,MainActivity2.class);
        startActivity(intent);
    }

    public void locationUpdate(MenuItem item) {
        Intent intent=new Intent(NearbyPlaceMaster.this,MainActivity.class);
        startActivity(intent);
    }

    public void bank(View view) {
        Intent intent=new Intent(NearbyPlaceMaster.this,PlacesViewMaster.class);
        parameter="Bank";
        startActivity(intent);
    }

    public void atm(View view) {
        Intent intent=new Intent(NearbyPlaceMaster.this,PlacesViewMaster.class);
        parameter="ATM";
        startActivity(intent);
    }

    public void Hospital(View view) {Intent intent=new Intent(NearbyPlaceMaster.this,PlacesViewMaster.class);
        parameter="Hospital";
        startActivity(intent);
    }

    public void hotel(View view) {Intent intent=new Intent(NearbyPlaceMaster.this,PlacesViewMaster.class);
        parameter="Hotel";
        startActivity(intent);
    }

    public void shopingmall(View view) {Intent intent=new Intent(NearbyPlaceMaster.this,PlacesViewMaster.class);
        parameter="Shopping Mall";
        startActivity(intent);
    }
}
