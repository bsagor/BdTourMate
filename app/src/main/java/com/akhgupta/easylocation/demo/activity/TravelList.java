package com.akhgupta.easylocation.demo.activity;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.akhgupta.easylocation.demo.MainActivity;
import com.akhgupta.easylocation.demo.db.EventManager;
import com.akhgupta.easylocation.demo.R;
import com.akhgupta.easylocation.demo.db.TourAdapter;
import com.akhgupta.easylocation.demo.model.TourInfo;
import com.akhgupta.easylocation.demo.util.BdTourmatePreferenceManager;

import java.util.ArrayList;

import static com.akhgupta.easylocation.demo.activity.Home.visitorID;

public class TravelList extends AppCompatActivity {
    ArrayList<TourInfo> tourArrayList;
    ListView lstTravel;
    TourInfo tourInfo;
    TourAdapter tourAdapter;

    BdTourmatePreferenceManager memory;
    Context context = TravelList.this;
    TextView tourIDtv;
    TextView tvStatus;
    ImageView editTour;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_travel_list);
        lstTravel= (ListView) findViewById(R.id.lstTravel);
        final ArrayList<String> stringArrayList=new ArrayList<>();
        EventManager eventManager=new EventManager(this);
        editTour= (ImageView) findViewById(R.id.editTour);
        tvStatus = (TextView) findViewById(R.id.tvStatus);

        final ArrayList<TourInfo> tourInfo=eventManager.getAllEventInfo(visitorID);
        tourIDtv= (TextView) findViewById(R.id.tourID);

        if(tourInfo.size() > 0){
            tvStatus.setText("Found Total " + tourInfo.size() + " Event/s.");
            tvStatus.setTextColor(Color.WHITE);
        } else {
            tvStatus.setText("No Event Created Yet.");
            tvStatus.setTextColor(Color.RED);
        }

       /* tourID="";
        costID="0";*/
        tourAdapter = new TourAdapter(this,tourInfo);
        lstTravel.setAdapter(tourAdapter);
    }
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater=getMenuInflater();
        menuInflater.inflate(R.menu.mastermenu,menu);
        return true;
    }

    public void home(MenuItem item) {
        Home.tourID="";
        Home.costID="0";
        Intent intent=new Intent(TravelList.this,Home.class);
        startActivity(intent);
    }
    public void locationUpdate(MenuItem item) {
        Intent intent=new Intent(TravelList.this,MainActivity.class);
        startActivity(intent);
    }
    public void logOut(MenuItem item) {
        Home.tourID="";
        Home.costID="0";
        Intent intent=new Intent(TravelList.this,MainActivity2.class);
        startActivity(intent);
    }

    public void travelExpense(View view) {
        /*Intent intent=new Intent(TravelList.this,TravelMaster.class);
        startActivity(intent);*/
    }
}
