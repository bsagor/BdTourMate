package com.akhgupta.easylocation.demo.db;

/**
 * Created by FARUQ on 11/14/2016.
 */
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;
import android.widget.Toast;

import com.akhgupta.easylocation.demo.model.EventInfo;
import com.akhgupta.easylocation.demo.model.TourInfo;

import java.util.ArrayList;

public class EventManager {
    SQLiteDatabase sqLiteDatabase;
    Context context;
    DbTour dbTour;

    public EventManager(Context context) {
        this.context = context;
        this.dbTour = new DbTour(context);
    }

    public ArrayList<TourInfo> getAllEventInfo(String VisitorID) {
        ArrayList<TourInfo> objTenantInfos = new ArrayList<>();

        String selectQuery = "select cost,* from " + dbTour.TABLE_TOURINFO +
                " e LEFT JOIN ( select "+DbTour.C_COST_TOURID+",sum("+DbTour.C_COST+")cost from "+ DbTour.TABLE_COST_INFO +" group by "+DbTour.C_COST_TOURID+" ) c on c."+DbTour.C_COST_TOURID+"=e."+DbTour.C_TOUR_ID+" where "+dbTour.C_TOUR_VISITORID+" = '"+VisitorID+"'";
        /*Toast.makeText(context, selectQuery, Toast.LENGTH_LONG).show();*/
        Log.e("EM","Query: "+selectQuery);
        sqLiteDatabase = dbTour.getReadableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery(selectQuery, null);
        if (cursor.moveToFirst()) {
            for (int i = 0; i < cursor.getCount(); i++) {


                String tourId = cursor.getString(cursor.getColumnIndex(dbTour.C_TOUR_ID));
                String DESTINATION = cursor.getString(cursor.getColumnIndex(dbTour.C_TOUR_DESTINATION));
                String FROMDATE = cursor.getString(cursor.getColumnIndex(dbTour.C_TOUR_FROMDATE));
               /* Log.e("EM","des : "+DESTINATION);*/
                String TODATE = cursor.getString(cursor.getColumnIndex(dbTour.C_TOUR_TODATE));
                Double BUDGET = cursor.getDouble(cursor.getColumnIndex(dbTour.C_TOUR_BUDGET));
                Double expendedCost = cursor.getDouble(cursor.getColumnIndex("cost"));

                /*Toast.makeText(context, String.valueOf(expendedCost), Toast.LENGTH_LONG).show();*/
                TourInfo objTourinfo=new TourInfo(tourId,DESTINATION,FROMDATE,TODATE,"",BUDGET,expendedCost);
                objTenantInfos.add(objTourinfo);
                cursor.moveToNext();
            }

        }
        cursor.close();
        sqLiteDatabase.close();
        return objTenantInfos;
    }
    public TourInfo getSingleEventInfo(String id) {
        TourInfo objEventinfo=new TourInfo();

        String selectQuery = "select * from " + dbTour.TABLE_TOURINFO +
                " where " + dbTour.C_TOUR_ID + " = '" + id+"' ";
        sqLiteDatabase = dbTour.getReadableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery(selectQuery, null);
        if (cursor.moveToFirst()) {

            objEventinfo.TourID = cursor.getString(cursor.getColumnIndex(dbTour.C_TOUR_ID));
            objEventinfo.DESTINATION = cursor.getString(cursor.getColumnIndex(dbTour.C_TOUR_DESTINATION));
            objEventinfo.BUDGET = cursor.getDouble(cursor.getColumnIndex(dbTour.C_TOUR_BUDGET));
            objEventinfo.FROMDATE = cursor.getString(cursor.getColumnIndex(dbTour.C_TOUR_FROMDATE));
            objEventinfo.TODATE = cursor.getString(cursor.getColumnIndex(dbTour.C_TOUR_TODATE));

            // TenantInfo objTenantInfo=new TenantInfo(tenantId,flatNo,fatherName,motherName,tenantAddress,tenantName,tenantNID,allotedDate,houseRent,gasBill,maintenanceBill);

            cursor.moveToNext();
        }
        else
        {
            objEventinfo.TourID = "No data found";
        }


        return objEventinfo;
    }
    public long updateEventInfo(EventInfo objEventinfo, String id) {
        sqLiteDatabase = dbTour.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(dbTour.C_TOUR_VISITORID, objEventinfo.getTourVisitorID());
        contentValues.put(dbTour.C_TOUR_DESTINATION, objEventinfo.getDestination());
        contentValues.put(dbTour.C_TOUR_BUDGET, objEventinfo.getBudget());
        contentValues.put(dbTour.C_TOUR_FROMDATE, objEventinfo.getFromDate());
        contentValues.put(dbTour.C_TOUR_TODATE, objEventinfo.getToDate());

        long data = sqLiteDatabase.update(dbTour.TABLE_TOURINFO, contentValues, dbTour.C_TOUR_ID
                + "=?", new String[]{String.valueOf(id)});


        return data;
    }
    public long deleteEvent(String id) {
        int i=0;
        try {
            sqLiteDatabase = dbTour.getWritableDatabase();
            sqLiteDatabase.delete(dbTour.TABLE_TOURINFO, dbTour.C_TOUR_ID + "=?", new String[]{String.valueOf(id)});
            i=1;
        }
        catch (Exception e) {
            i=0;
        }
        return i;
    }
    public EventInfo checkEvent(String visitorID,String destination, String fromDate, String todate) {
        EventInfo objEventInfo=new EventInfo();

        String selectQuery = "select * from " + dbTour.TABLE_TOURINFO +
                " where " + dbTour.C_TOUR_VISITORID + " = '" + visitorID +"' and "+dbTour.C_TOUR_DESTINATION+" = '"+destination+"' and " +dbTour.C_TOUR_FROMDATE+" between'"+fromDate+"' and '"+todate+"'";

       /* Toast.makeText(context, selectQuery, Toast.LENGTH_LONG).show();*/
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
    public long addTour(EventInfo objeventInfo)
    {
        long data = 0;
      /*  EventInfo eventInfo=checkEvent(objeventInfo.getTourVisitorID().toString().trim(),objeventInfo.getDestination().toString().trim(),objeventInfo.getFromDate().toString().trim(),objeventInfo.getToDate().toString().trim());

        if(Integer.valueOf(eventInfo.getTourVisitorID())<=0) {*/
            try {
                sqLiteDatabase = dbTour.getWritableDatabase();
                ContentValues contentValues = new ContentValues();
                contentValues.put(dbTour.C_TOUR_VISITORID, objeventInfo.getTourVisitorID());
                contentValues.put(dbTour.C_TOUR_DESTINATION, objeventInfo.getDestination());
                contentValues.put(dbTour.C_TOUR_BUDGET, objeventInfo.getBudget());
                contentValues.put(dbTour.C_TOUR_FROMDATE, objeventInfo.getFromDate());
                contentValues.put(dbTour.C_TOUR_TODATE, objeventInfo.getToDate());
                data = sqLiteDatabase.insert(DbTour.TABLE_TOURINFO, null, contentValues);
            } catch (Exception ex) {
                Toast.makeText(context, ex.getMessage(), Toast.LENGTH_SHORT).show();
            }
       /* }
        else
        {
            Toast.makeText(context, "Event name already exists", Toast.LENGTH_SHORT).show();
        }*/
        return data;
    }
}
