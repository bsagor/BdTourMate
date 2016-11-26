package com.akhgupta.easylocation.demo.activity;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.akhgupta.easylocation.demo.MainActivity;
import com.akhgupta.easylocation.demo.R;
import com.akhgupta.easylocation.demo.gallery.GalleryActivity;
import com.akhgupta.easylocation.demo.util.BdTourmateAlertDialog;
import com.akhgupta.easylocation.demo.util.BdTourmatePreferenceManager;

public class Home extends AppCompatActivity {
    Context context = Home.this;
    BdTourmatePreferenceManager memory;
    TextView tvUsename;
    public static String visitorID;
    public static String tourID;
    public static String placeID;
    public static String costID;
    public static String currentPlace="Dhaka";
    public static String parameter="Bank";
    public static String la;
    public static String lo;
    public static String mapTitle="Dhaka";
    public static String parentparameter="Dhaka, Bangladesh";

    public static String sla="23.8103";
    public static String slo="90.4125";
    public static String isplaceUpdate="No";



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        tvUsename= (TextView) findViewById(R.id.tvUsername);
        memory=new BdTourmatePreferenceManager(context);

        tvUsename.setText("Welcome: "+memory.getPref(memory.KEY_VISITOR_NAME));
        visitorID=memory.getPref(memory.KEY_VISITOR_ID);


    }
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater=getMenuInflater();
        menuInflater.inflate(R.menu.mastermenu1,menu);
        return true;
    }



    public void logOut(MenuItem item) {
        memory.deletePreferences(memory.KEY_VISITOR_LOGIN);
        Intent intent=new Intent(Home.this,MainActivity2.class);
        startActivity(intent);
    }

    public void nearByplace(View view) {
        Intent intent=new Intent(Home.this,NearbyPlaceMaster.class);
        startActivity(intent);
    }

    public void viewEventList(View view) {
        Intent intent=new Intent(Home.this,TravelList.class);
        startActivity(intent);
    }

    public void weatherMaster(View view) {
        Intent intent=new Intent(Home.this,WeatherMaster.class);
        startActivity(intent);
    }

    public void updateInfo(View view) {
        BdTourmateAlertDialog.showYesNoAlertDialog(context, "Update Info", "Do you want to update information?", false, new SignUpvisitor());
    }



    public void travel(View view) {
//        Intent intent=new Intent(Home.this,GalleryActivity.class);
        Intent intent=new Intent(Home.this,TravelMomentActivity.class);
        startActivity(intent);
    }

    public void tour(View view) {
        Intent intent=new Intent(Home.this,TravelMaster.class);
        startActivity(intent);
    }

    public void locationUpdate(MenuItem item) {
        Intent intent=new Intent(Home.this,MainActivity.class);
        startActivity(intent);
    }
}
