package com.akhgupta.easylocation.demo.db;

/**
 * Created by faruq on 11/17/2016.
 */
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;
import android.widget.Toast;

import com.akhgupta.easylocation.demo.model.EventInfo;
import com.akhgupta.easylocation.demo.model.ExpenseInfo;

import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Date;

import static com.akhgupta.easylocation.demo.activity.Home.tourID;


public class ExpenseManager {
    SQLiteDatabase sqLiteDatabase;
    Context context;
    DbTour dbTour;
    String currentDateTimeString = DateFormat.getDateTimeInstance().format(new Date());
    public ExpenseManager(Context context) {
        this.context = context;
        this.dbTour = new DbTour(context);
    }
    public ArrayList<ExpenseInfo> getAllExpenseInfo() {

        ArrayList<ExpenseInfo> objTenantInfos = new ArrayList<>();

        String selectQuery = "select * from " + dbTour.TABLE_COST_INFO +
                " where "+dbTour.C_COST_TOURID+" = '"+tourID+"'";

        Log.e("EM","Query: "+selectQuery);
        sqLiteDatabase = dbTour.getReadableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery(selectQuery, null);
        if (cursor.moveToFirst()) {
            for (int i = 0; i < cursor.getCount(); i++) {
                String tourID = cursor.getString(cursor.getColumnIndex(dbTour.C_COST_TOURID));
                String expenseID = cursor.getString(cursor.getColumnIndex(dbTour.C_COST_ID));
                String Particulars = cursor.getString(cursor.getColumnIndex(dbTour.C_PARTICULARS));
                Double Expense = cursor.getDouble(cursor.getColumnIndex(dbTour.C_COST));
                String CostDate = cursor.getString(cursor.getColumnIndex(dbTour.C_COSTDATE));
                ExpenseInfo objTourinfo=new ExpenseInfo(expenseID,tourID,Particulars,Expense,CostDate);
                objTenantInfos.add(objTourinfo);
              /*  Toast.makeText(context, tourID, Toast.LENGTH_LONG).show();*/
                cursor.moveToNext();
            }

        }
        cursor.close();
        sqLiteDatabase.close();

        return objTenantInfos;
    }
    public ExpenseInfo getSingleExpenseInfo(String id) {
        ExpenseInfo objexpenseInfo=new ExpenseInfo();

        String selectQuery = "select * from " + dbTour.TABLE_COST_INFO +
                " where " + dbTour.C_COST_ID + " = '" + id+"' ";
        sqLiteDatabase = dbTour.getReadableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery(selectQuery, null);
        if (cursor.moveToFirst()) {

            objexpenseInfo.ExpenseParticulars = cursor.getString(cursor.getColumnIndex(dbTour.C_PARTICULARS));

            objexpenseInfo.Expense = cursor.getDouble(cursor.getColumnIndex(dbTour.C_COST));

            // TenantInfo objTenantInfo=new TenantInfo(tenantId,flatNo,fatherName,motherName,tenantAddress,tenantName,tenantNID,allotedDate,houseRent,gasBill,maintenanceBill);

            cursor.moveToNext();
        }
        else
        {
            objexpenseInfo.ExpenseParticulars = "No data found";
        }


        return objexpenseInfo;
    }
    public long updateExpenseInfo(ExpenseInfo objexpenseInfo, String id) {
        sqLiteDatabase = dbTour.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(dbTour.C_PARTICULARS, objexpenseInfo.getExpenseParticulars());
        contentValues.put(dbTour.C_COST, Double.valueOf(objexpenseInfo.getExpense()));

        long data = sqLiteDatabase.update(dbTour.TABLE_COST_INFO, contentValues, dbTour.C_COST_ID
                + "=?", new String[]{String.valueOf(id)});


        return data;
    }
    public long deleteExpense(String id) {
        int i=0;
        try {
            sqLiteDatabase = dbTour.getWritableDatabase();
            sqLiteDatabase.delete(dbTour.TABLE_COST_INFO, dbTour.C_COST_ID + "=?", new String[]{String.valueOf(id)});
            i=1;
        }
        catch (Exception e) {
            i=0;
        }
        return i;
    }
    public EventInfo checkExpense(String destination, String fromDate, String todate) {
        EventInfo objEventInfo=new EventInfo();

        String selectQuery = "select * from " + dbTour.TABLE_COST_INFO +
                " where  "+dbTour.C_TOUR_DESTINATION+" = '"+destination+"' and " +dbTour.C_TOUR_FROMDATE+" between'"+fromDate+"' and '"+todate+"'";

        Toast.makeText(context, selectQuery, Toast.LENGTH_LONG).show();
        Cursor cursor = sqLiteDatabase.rawQuery(selectQuery, null);
        if (cursor.moveToFirst()) {

            objEventInfo.tourID = cursor.getString(cursor.getColumnIndex(dbTour.C_TOUR_ID));
            objEventInfo.destination = cursor.getString(cursor.getColumnIndex(dbTour.C_TOUR_DESTINATION));
            objEventInfo.budget = cursor.getDouble(cursor.getColumnIndex(dbTour.C_TOUR_BUDGET));
            objEventInfo.fromDate = cursor.getString(cursor.getColumnIndex(dbTour.C_TOUR_FROMDATE));
            objEventInfo.toDate = cursor.getString(cursor.getColumnIndex(dbTour.C_TOUR_TODATE));

            // TenantInfo objTenantInfo=new TenantInfo(tenantId,flatNo,fatherName,motherName,tenantAddress,tenantName,tenantNID,allotedDate,houseRent,gasBill,maintenanceBill);

            cursor.moveToNext();
        }
        return objEventInfo;
    }
    public long addExpenseInfo(ExpenseInfo expenseInfo)
    {
        long data = 0;
        try {
            sqLiteDatabase = dbTour.getWritableDatabase();
            ContentValues contentValues = new ContentValues();
            contentValues.put(dbTour.C_COST_TOURID, expenseInfo.getTourID());
            contentValues.put(dbTour.C_PARTICULARS, expenseInfo.getExpenseParticulars());
            contentValues.put(dbTour.C_COST, Double.valueOf(expenseInfo.getExpense()));
            contentValues.put(dbTour.C_COSTDATE, currentDateTimeString);
            data = sqLiteDatabase.insert(DbTour.TABLE_COST_INFO, null, contentValues);

        } catch (Exception ex) {
            Log.e("","Error"+ex.getMessage().toString());
            Toast.makeText(context, ex.getMessage(), Toast.LENGTH_LONG).show();
        }

        return data;
    }

}
