package com.akhgupta.easylocation.demo.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.telephony.SmsManager;
import android.util.Log;
import android.widget.Toast;

import com.akhgupta.easylocation.demo.model.VisitorInfo;

public class VisitorManager {
    SQLiteDatabase sqLiteDatabase;
    Context context;
    DbTour dbTour;
    public VisitorManager(Context context)
    {
        this.context = context;
        this.dbTour = new DbTour(context);
    }
    public VisitorInfo getSingleVisitorInfo(String id) {
        VisitorInfo objTenantInfo=new VisitorInfo();

        String selectQuery = "select * from " + dbTour.TABLE_VISITORINFO +
                " where " + dbTour.C_VISITOR_ID + " = " + id;
        sqLiteDatabase = dbTour.getReadableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery(selectQuery, null);
        if (cursor.moveToFirst()) {

            objTenantInfo.VisitorID = cursor.getInt(cursor.getColumnIndex(dbTour.C_VISITOR_ID));
            objTenantInfo.VisitorName = cursor.getString(cursor.getColumnIndex(dbTour.C_VISITOR_NAME));
            objTenantInfo.LoginName = cursor.getString(cursor.getColumnIndex(dbTour.C_VISITOR_LOGINNAME));
            objTenantInfo.Password = cursor.getString(cursor.getColumnIndex(dbTour.C_VISITOR_PASSWORD));
            objTenantInfo.Address = cursor.getString(cursor.getColumnIndex(dbTour.C_VISITOR_ADDRESS));
            objTenantInfo.ContactNo= cursor.getString(cursor.getColumnIndex(dbTour.C_VISITOR_CONTACTNO));
            // TenantInfo objTenantInfo=new TenantInfo(tenantId,flatNo,fatherName,motherName,tenantAddress,tenantName,tenantNID,allotedDate,houseRent,gasBill,maintenanceBill);

            cursor.moveToNext();
        }
        return objTenantInfo;
    }
    public VisitorInfo getSingleVisitorInfobyMobileno(String mobileNo) {
        VisitorInfo objTenantInfo=new VisitorInfo();

        String selectQuery = "select * from " + dbTour.TABLE_VISITORINFO +
                " where " + dbTour.C_VISITOR_CONTACTNO + " = '" + mobileNo+"'";
        sqLiteDatabase = dbTour.getReadableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery(selectQuery, null);
        if (cursor.moveToFirst()) {

            objTenantInfo.VisitorID = cursor.getInt(cursor.getColumnIndex(dbTour.C_VISITOR_ID));
            objTenantInfo.VisitorName = cursor.getString(cursor.getColumnIndex(dbTour.C_VISITOR_NAME));
            objTenantInfo.LoginName = cursor.getString(cursor.getColumnIndex(dbTour.C_VISITOR_LOGINNAME));
            objTenantInfo.Password = cursor.getString(cursor.getColumnIndex(dbTour.C_VISITOR_PASSWORD));
            objTenantInfo.Address = cursor.getString(cursor.getColumnIndex(dbTour.C_VISITOR_ADDRESS));
            objTenantInfo.ContactNo= cursor.getString(cursor.getColumnIndex(dbTour.C_VISITOR_CONTACTNO));
            // TenantInfo objTenantInfo=new TenantInfo(tenantId,flatNo,fatherName,motherName,tenantAddress,tenantName,tenantNID,allotedDate,houseRent,gasBill,maintenanceBill);
            SmsManager smsManager=SmsManager.getDefault();
            smsManager.sendTextMessage(Uri.encode(mobileNo),null,"Welcome "+objTenantInfo.VisitorName+", \nYour password is: "+objTenantInfo.Password+"\nPlease keep it secreet",null,null);
            cursor.moveToNext();
        }
        return objTenantInfo;
    }
    public VisitorInfo loginMaster(String loginName,String password) {
        VisitorInfo objTenantInfo=new VisitorInfo();

        String selectQuery = "select * from " + dbTour.TABLE_VISITORINFO +
                " where " + dbTour.C_VISITOR_LOGINNAME + " = '" + loginName +"' and " + dbTour.C_VISITOR_PASSWORD + " = '"+ password+"'";


        sqLiteDatabase = dbTour.getReadableDatabase();


        Cursor cursor = sqLiteDatabase.rawQuery(selectQuery, null);
        if (cursor.moveToFirst()) {

            objTenantInfo.VisitorID = cursor.getInt(cursor.getColumnIndex(dbTour.C_VISITOR_ID));
            objTenantInfo.VisitorName = cursor.getString(cursor.getColumnIndex(dbTour.C_VISITOR_NAME));
            objTenantInfo.ContactNo = cursor.getString(cursor.getColumnIndex(dbTour.C_VISITOR_CONTACTNO));

            // TenantInfo objTenantInfo=new TenantInfo(tenantId,flatNo,fatherName,motherName,tenantAddress,tenantName,tenantNID,allotedDate,houseRent,gasBill,maintenanceBill);

            cursor.moveToNext();
        }

        return objTenantInfo;
    }
    public VisitorInfo checkloginMaster(String loginName) {
        VisitorInfo objTenantInfo=new VisitorInfo();

        String selectQuery = "select * from " + dbTour.TABLE_VISITORINFO +
                " where " + dbTour.C_VISITOR_LOGINNAME + " = '" + loginName +"'";


        sqLiteDatabase = dbTour.getReadableDatabase();


        Cursor cursor = sqLiteDatabase.rawQuery(selectQuery, null);
        if (cursor.moveToFirst()) {

            objTenantInfo.VisitorID = cursor.getInt(cursor.getColumnIndex(dbTour.C_VISITOR_ID));

            // TenantInfo objTenantInfo=new TenantInfo(tenantId,flatNo,fatherName,motherName,tenantAddress,tenantName,tenantNID,allotedDate,houseRent,gasBill,maintenanceBill);

            cursor.moveToNext();
        }

        return objTenantInfo;
    }
    public long addVisitor(VisitorInfo objVisitorInfo)
    {
        long data = 0;
        VisitorInfo visitorInfo=checkloginMaster(objVisitorInfo.getLoginName().toString().trim());
        Log.d("Check",String.valueOf(visitorInfo.getVisitorID()));
        if(visitorInfo.getVisitorID()<=0) {

            try {
                sqLiteDatabase = dbTour.getWritableDatabase();
                ContentValues contentValues = new ContentValues();
                contentValues.put(dbTour.C_VISITOR_NAME, objVisitorInfo.VisitorName);
                contentValues.put(dbTour.C_VISITOR_LOGINNAME, objVisitorInfo.LoginName);
                contentValues.put(dbTour.C_VISITOR_PASSWORD, objVisitorInfo.Password);
                contentValues.put(dbTour.C_VISITOR_ADDRESS, objVisitorInfo.Address);
                contentValues.put(dbTour.C_VISITOR_CONTACTNO, objVisitorInfo.ContactNo);


                data = sqLiteDatabase.insert(DbTour.TABLE_VISITORINFO, null, contentValues);
            } catch (Exception ex) {
                Toast.makeText(context, ex.getMessage(), Toast.LENGTH_SHORT).show();
            }
        }
        else
        {
            Toast.makeText(context, "User name already exists", Toast.LENGTH_SHORT).show();
        }

        return data;
    }
    public long updateVisitorInfo(VisitorInfo objVisitorInfo, String id) {
        sqLiteDatabase = dbTour.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(dbTour.C_VISITOR_NAME, objVisitorInfo.getVisitorName());
        contentValues.put(dbTour.C_VISITOR_LOGINNAME, objVisitorInfo.getLoginName());
        contentValues.put(dbTour.C_VISITOR_PASSWORD, objVisitorInfo.getPassword());
        contentValues.put(dbTour.C_VISITOR_ADDRESS, objVisitorInfo.getAddress());
        contentValues.put(dbTour.C_VISITOR_CONTACTNO, objVisitorInfo.getContactNo());
        long data = sqLiteDatabase.update(dbTour.TABLE_VISITORINFO, contentValues, dbTour.C_VISITOR_ID
                + "=?", new String[]{String.valueOf(id)});

        
        return data;
    }
    public long deleteVisitor(String id) {
        int i=0;
        try {
            sqLiteDatabase = dbTour.getWritableDatabase();
            sqLiteDatabase.delete(dbTour.TABLE_VISITORINFO, dbTour.C_VISITOR_ID + "=?", new String[]{String.valueOf(id)});
            i=1;
        }
        catch (Exception e) {
            i=0;
        }
        return i;
    }
}
