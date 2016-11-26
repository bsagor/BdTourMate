package com.akhgupta.easylocation.demo.activity;
import android.app.DatePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

import com.akhgupta.easylocation.demo.MainActivity;
import com.akhgupta.easylocation.demo.R;
import com.akhgupta.easylocation.demo.db.EventManager;
import com.akhgupta.easylocation.demo.model.EventInfo;
import com.akhgupta.easylocation.demo.model.TourInfo;
import com.akhgupta.easylocation.demo.util.BdTourmateAlertDialog;
import com.akhgupta.easylocation.demo.util.BdTourmatePreferenceManager;

import java.util.Calendar;

import static com.akhgupta.easylocation.demo.activity.Home.tourID;
import static com.akhgupta.easylocation.demo.activity.Home.visitorID;


public class TravelMaster extends AppCompatActivity {
    EditText traveleventET;
    EditText estimatedbudgetET;
    EditText fromdateET;
    EditText todateET;


    Button btnSave;
    EventManager eventManager;
    Context context = TravelMaster.this;
    BdTourmatePreferenceManager memory;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_travel_master);
        eventManager = new EventManager(this);
        traveleventET = (EditText) findViewById(R.id.traveleventET);
        estimatedbudgetET = (EditText) findViewById(R.id.estimatedbudgetET);
        fromdateET = (EditText) findViewById(R.id.fromdateET);
        todateET = (EditText) findViewById(R.id.todateET);
        btnSave = (Button) findViewById(R.id.btnSave);

        fromdateET.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                Calendar calendar=Calendar.getInstance();

                int year =calendar.get(Calendar.YEAR);
                int month=calendar.get(Calendar.MONTH);
                int day=calendar.get(Calendar.DAY_OF_MONTH);
                DatePickerDialog datePickerDialog=new DatePickerDialog(TravelMaster.this, new DatePickerDialog.OnDateSetListener() {

                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        fromdateET.setText(String.valueOf(dayOfMonth)+"/"+String.valueOf(month)+"/"+String.valueOf(year));
                    }
                },year,month,day);
                datePickerDialog.getDatePicker().setMinDate(calendar.getTimeInMillis());
                datePickerDialog.show();
            }
        });

        todateET.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                Calendar calendar=Calendar.getInstance();

                int year =calendar.get(Calendar.YEAR);
                int month=calendar.get(Calendar.MONTH);
                int day=calendar.get(Calendar.DAY_OF_MONTH);
                DatePickerDialog datePickerDialog=new DatePickerDialog(TravelMaster.this, new DatePickerDialog.OnDateSetListener() {

                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        todateET.setText(String.valueOf(dayOfMonth)+"/"+String.valueOf(month)+"/"+String.valueOf(year));
                    }
                },year,month,day);
                datePickerDialog.getDatePicker().setMinDate(calendar.getTimeInMillis());
                datePickerDialog.show();
            }
        });

        TourInfo tourInfo = eventManager.getSingleEventInfo(tourID);
        if (String.valueOf(tourInfo.getTourID()).equals("No data found")) {
            traveleventET.setText("");
            fromdateET.setText("");

            todateET.setText("");
            estimatedbudgetET.setText(String.valueOf("0"));

            btnSave.setText("Save");
        }
        else
        {
            traveleventET.setText(tourInfo.DESTINATION);
            fromdateET.setText(tourInfo.FROMDATE);
            btnSave.setText("Update");
            todateET.setText(tourInfo.TODATE);
            estimatedbudgetET.setText(String.valueOf(tourInfo.BUDGET));
        }
    }



        /*if(tourID!=null)
        {
            TourInfo eventInfo=eventManager.getSingleEventInfo(tourID,visitorID);
            if(eventInfo!=null){
                traveleventET.setText(eventInfo.DESTINATION);
                estimatedbudgetET.setText(String.valueOf(eventInfo.BUDGET));

                fromdateET.setText(eventInfo.FROMDATE);
                todateET.setText(eventInfo.TODATE);
                btnSave.setText("Update");
            }
        }*/


    public void locationUpdate(MenuItem item) {
        Intent intent=new Intent(TravelMaster.this,MainActivity.class);
        startActivity(intent);
    }
    public void SaveEventInfo(View view) {

        EventInfo eventInfo=new EventInfo();
        eventInfo.tourVisitorID=visitorID;
        eventInfo.destination=traveleventET.getText().toString();
        eventInfo.budget=Double.valueOf(estimatedbudgetET.getText().toString());
        eventInfo.fromDate=fromdateET.getText().toString();
        eventInfo.toDate=todateET.getText().toString();

        long result=   eventManager.addTour(eventInfo);

        if(result>0)
        {
            BdTourmateAlertDialog.showSuccessAlertDialogs(context, "Congratulation", "Event added Successfully", false, new TravelList());
        }
        else
        {
            BdTourmateAlertDialog.showSuccessAlertDialogs(context, "Failed", "Failed to create event", false, new TravelMaster());
        }

    }

    public void UpdateInfo(View view) {
        EventInfo eventInfo=new EventInfo();
        eventInfo.tourVisitorID=visitorID;
        eventInfo.destination=traveleventET.getText().toString();
        eventInfo.budget=Double.valueOf(estimatedbudgetET.getText().toString());
        eventInfo.fromDate=fromdateET.getText().toString();
        eventInfo.toDate=todateET.getText().toString();


        long result=   eventManager.updateEventInfo(eventInfo,visitorID);

        if(result>0)
        {
            BdTourmateAlertDialog.showSuccessAlertDialogs(context, "Congratulation", "Data updated Successfully", false, new TravelList());
        }
        else

        {
            BdTourmateAlertDialog.showSuccessAlertDialogs(context, "Failed", "Failed to update informationy", false, new TravelMaster());
        }
    }

    public void deleteInfo(View view) {
        long result=   eventManager.deleteEvent(tourID);

        if(result>0)
        {
            clear();
            BdTourmateAlertDialog.showSuccessAlertDialogs(context, "Congratulation", "Event deleted Successfully", false, new TravelList());
        }
        else
        {
            clear();
            BdTourmateAlertDialog.showSuccessAlertDialogs(context, "Sorry", "Failed to delete data", false, new TravelMaster());

        }
    }
    private void clear()
    {
        traveleventET.setText("");
        fromdateET.setText("");

        todateET.setText("");
        estimatedbudgetET.setText(String.valueOf("0"));

        btnSave.setText("Save");

    }
    public void signAddTravelinfo(View view) {
        if(new String(btnSave.getText().toString()).equals("Save"))
        {
            if(edtIsNull(traveleventET)){
                if(edtIsNull(estimatedbudgetET))
                {
                    if(edtIsNull(estimatedbudgetET))
                    {
                        if(edtIsNull(estimatedbudgetET))
                        {
                            SaveEventInfo(view);
                        }
                        else
                        {
                            Toast.makeText(this, "Enter travel to date", Toast.LENGTH_SHORT).show();
                        }
                    }
                    else
                    {
                        Toast.makeText(this, "Enter travel from date", Toast.LENGTH_SHORT).show();
                    }
                }
                else
                {
                    Toast.makeText(this, "Enter travel budget", Toast.LENGTH_SHORT).show();
                }
            }
            else
            {
                Toast.makeText(this, "Enter travel event name", Toast.LENGTH_SHORT).show();
            }

        }
        else
        {
            if(edtIsNull(traveleventET)){
                if(edtIsNull(estimatedbudgetET))
                {
                    if(edtIsNull(estimatedbudgetET))
                    {
                        if(edtIsNull(estimatedbudgetET))
                        {
                            UpdateInfo(view);
                        }
                        else
                        {
                            Toast.makeText(this, "Enter travel to date", Toast.LENGTH_SHORT).show();
                        }
                    }
                    else
                    {
                        Toast.makeText(this, "Enter travel from date", Toast.LENGTH_SHORT).show();
                    }
                }
                else
                {
                    Toast.makeText(this, "Enter travel budget", Toast.LENGTH_SHORT).show();
                }
            }
            else
            {
                Toast.makeText(this, "Enter travel event name", Toast.LENGTH_SHORT).show();
            }

        }
    }
    private boolean edtIsNull(EditText editText) {
        boolean edtIsNull = true;
        if(String.valueOf(editText.getText()).equals("")){
            edtIsNull = false;
        }
        return edtIsNull;
    }
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater=getMenuInflater();
        menuInflater.inflate(R.menu.mastermenu,menu);
        return true;
    }

    public void home(MenuItem item) {
        Home.tourID="";
        Home.costID="0";
        Intent intent=new Intent(TravelMaster.this,Home.class);
        startActivity(intent);
    }

    public void logOut(MenuItem item) {
        Home.tourID="";
        Home.costID="0";
        Intent intent=new Intent(TravelMaster.this,MainActivity2.class);
        startActivity(intent);
    }

    public void createEvent(View view) {
        Intent intent=new Intent(TravelMaster.this,ExpenseMaster.class);
        startActivity(intent);
    }

    public void deleteevent(View view) {
        deleteInfo(view);
    }

    public void viewTravellist(View view) {
        Intent intent=new Intent(TravelMaster.this,TravelList.class);
        startActivity(intent);
    }
}
