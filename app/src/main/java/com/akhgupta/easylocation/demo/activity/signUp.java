package com.akhgupta.easylocation.demo.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import com.akhgupta.easylocation.demo.R;

public class signUp extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
    }
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater=getMenuInflater();
        menuInflater.inflate(R.menu.mastermenu,menu);
        return true;
    }

    public void home(MenuItem item) {
        Home.tourID="";
        Home.costID="0";
        Intent intent=new Intent(signUp.this,MainActivity2.class);
        startActivity(intent);
    }

    public void logOut(MenuItem item) {
        Home.tourID="";
        Home.costID="0";
        Intent intent=new Intent(signUp.this,MainActivity2.class);
        startActivity(intent);
    }
}
