package com.akhgupta.easylocation.demo.activity;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.ListView;

import com.akhgupta.easylocation.demo.MainActivity;
import com.akhgupta.easylocation.demo.db.ExpanseAdapter;
import com.akhgupta.easylocation.demo.model.ExpenseInfo;
import com.akhgupta.easylocation.demo.db.ExpenseManager;
import com.akhgupta.easylocation.demo.R;
import com.akhgupta.easylocation.demo.util.BdTourmatePreferenceManager;

import java.util.ArrayList;

public class ExpenseList extends AppCompatActivity {

    ExpenseManager expenseManager;
    Context context = ExpenseList.this;
    BdTourmatePreferenceManager memory;
    ExpanseAdapter expanseAdapter;
    ListView lstExpense;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_expense_list);
        lstExpense= (ListView) findViewById(R.id.lstExpense);
        final ArrayList<String> stringArrayList=new ArrayList<>();
        ExpenseManager expenseManager=new ExpenseManager(this);


        final ArrayList<ExpenseInfo> expenseInfo=expenseManager.getAllExpenseInfo();

        Home.tourID="";
        Home.costID="0";
        expanseAdapter = new ExpanseAdapter(this,expenseInfo);
        lstExpense.setAdapter(expanseAdapter);

    }
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater=getMenuInflater();
        menuInflater.inflate(R.menu.mastermenu,menu);
        return true;
    }

    public void home(MenuItem item) {
        Home.tourID="";
        Home.costID="0";
        Intent intent=new Intent(ExpenseList.this,Home.class);
        startActivity(intent);
    }
    public void locationUpdate(MenuItem item) {
        Intent intent=new Intent(ExpenseList.this,MainActivity.class);
        startActivity(intent);
    }
    public void logOut(MenuItem item) {
        Home.tourID="";
        Home.costID="0";
        Intent intent=new Intent(ExpenseList.this,MainActivity2.class);
        startActivity(intent);
    }
}
