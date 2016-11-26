package com.akhgupta.easylocation.demo.activity;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import com.akhgupta.easylocation.demo.MainActivity;
import com.akhgupta.easylocation.demo.db.ExpanseAdapter;
import com.akhgupta.easylocation.demo.model.ExpenseInfo;
import com.akhgupta.easylocation.demo.db.ExpenseManager;
import com.akhgupta.easylocation.demo.R;
import com.akhgupta.easylocation.demo.util.BdTourmateAlertDialog;
import com.akhgupta.easylocation.demo.util.BdTourmatePreferenceManager;

import java.util.ArrayList;

public class ExpenseMaster extends AppCompatActivity {
    EditText expenseparticularsET,expenseET;
    Button btnSave;
    ExpenseManager expenseManager;
    Context context = ExpenseMaster.this;
    ExpenseInfo expenseInfos;
    BdTourmatePreferenceManager memory;
    ExpanseAdapter expanseAdapter;
    ListView expenseLV;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_expense_master);
        expenseparticularsET= (EditText) findViewById(R.id.expenseparticularsET);
        expenseET= (EditText) findViewById(R.id.expenseET);
        btnSave = (Button) findViewById(R.id.btnSave);
        expenseManager=new ExpenseManager(this);
        expenseLV= (ListView) findViewById(R.id.expenseLV);
        /*Toast.makeText(context, tourID , Toast.LENGTH_LONG).show();*/


        expenseInfos = expenseManager.getSingleExpenseInfo(Home.costID);
        if (String.valueOf(expenseInfos.getExpenseParticulars()).equals("No data found")) {
            expenseparticularsET.setText("");

            expenseET.setText(String.valueOf("0"));

            btnSave.setText("Save");
        }
        else
        {
            expenseparticularsET.setText(expenseInfos.getExpenseParticulars());
            expenseET.setText(String.valueOf(expenseInfos.getExpense()));
            btnSave.setText("Update");

        }



        ExpenseManager expenseManager=new ExpenseManager(this);


        final ArrayList<ExpenseInfo> expenseInfo=expenseManager.getAllExpenseInfo();


        expanseAdapter = new ExpanseAdapter(this,expenseInfo);
        expenseLV.setAdapter(expanseAdapter);

    }
    public void SaveExpenseInfo(View view) {

        ExpenseInfo expenseInfo=new ExpenseInfo();
        expenseInfo.tourID= Home.tourID;

        expenseInfo.ExpenseParticulars=expenseparticularsET.getText().toString();
        expenseInfo.Expense=Double.valueOf(expenseET.getText().toString());

        long result=   expenseManager.addExpenseInfo(expenseInfo);

        if(result>0)
        {
            BdTourmateAlertDialog.showSuccessAlertDialogs(context, "Congratulation", "Expense added Successfully", false, new ExpenseMaster());
        }
        else
        {
            BdTourmateAlertDialog.showSuccessAlertDialogs(context, "Failed", "Failed to create expense", false, new ExpenseMaster());
        }

    }

    public void UpdateInfo(View view) {
        ExpenseInfo expenseInfo=new ExpenseInfo();
        expenseInfo.ExpenseParticulars=expenseparticularsET.getText().toString();
        expenseInfo.Expense=Double.valueOf(expenseET.getText().toString());

        long result=   expenseManager.updateExpenseInfo(expenseInfo, Home.costID);

        if(result>0)
        {
            BdTourmateAlertDialog.showSuccessAlertDialogs(context, "Congratulation", "Expense updated Successfully", false, new ExpenseMaster());
        }
        else

        {
            BdTourmateAlertDialog.showSuccessAlertDialogs(context, "Failed", "Failed to update Expense", false, new ExpenseMaster());
        }
    }

    public void deleteInfo(String view) {
        long result=   expenseManager.deleteExpense(Home.costID);

        if(result>0)
        {
            clear();
            BdTourmateAlertDialog.showSuccessAlertDialogs(context, "Congratulation", "Expense deleted Successfully", false, new ExpenseMaster());
        }
        else
        {
            clear();
            BdTourmateAlertDialog.showSuccessAlertDialogs(context, "Sorry", "Failed to delete expense", false, new ExpenseMaster());

        }
    }
    private void clear()
    {
        expenseparticularsET.setText("");
        expenseET.setText("0");
        btnSave.setText("Save");

    }
    public void signAddExpenseinfo(View view) {
        if(new String(btnSave.getText().toString()).equals("Save"))
        {
            SaveExpenseInfo(view);
        }
        else
        {
            UpdateInfo(view);
        }
    }
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater=getMenuInflater();
        menuInflater.inflate(R.menu.mastermenu,menu);
        return true;
    }

    public void home(MenuItem item) {
        Home.tourID="";
        Home.costID="0";
        Intent intent=new Intent(ExpenseMaster.this,Home.class);
        startActivity(intent);
    }

    public void logOut(MenuItem item) {
        Home.tourID="";
        Home.costID="0";
        Intent intent=new Intent(ExpenseMaster.this,MainActivity2.class);
        startActivity(intent);
    }

    public void locationUpdate(MenuItem item) {
        Intent intent=new Intent(ExpenseMaster.this,MainActivity.class);
        startActivity(intent);
    }

    public void viewExpenselist(View view) {
        Intent intent=new Intent(ExpenseMaster.this,ExpenseList.class);
        startActivity(intent);
    }

    public void deleteExpense(View view) {
        deleteInfo(Home.costID);
    }
}
