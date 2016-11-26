package com.akhgupta.easylocation.demo.db;
import android.database.sqlite.SQLiteOpenHelper;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

/**
 * Created by DTCL_R on 10/31/2016.
 */
public class DbTour extends SQLiteOpenHelper {



    public static String DATABASE_NAME="bdTourMate";

    private static final int DATABASE_VERSION=1;

    //create table for visitor info
    public static final String TABLE_VISITORINFO="VisitorInfo";

    public static final String C_VISITOR_ID="VisitorID";
    public static final String C_VISITOR_NAME="VisitorName";
    public static final String C_VISITOR_LOGINNAME="LoginName";
    public static final String C_VISITOR_PASSWORD="Password";
    public static final String C_VISITOR_ADDRESS="Address";
    public static final String C_VISITOR_CONTACTNO="ContactNo";
    private static final String CREATE_TABLE_VISITORINFO="create table "+TABLE_VISITORINFO+
            "("+C_VISITOR_ID+" integer primary key autoincrement,"
            +C_VISITOR_NAME+" text,"
            +C_VISITOR_LOGINNAME+" text,"
            +C_VISITOR_PASSWORD+" text,"
            +C_VISITOR_ADDRESS+" text,"
            +C_VISITOR_CONTACTNO+" text);";

    //create table for tour info
    public static final String TABLE_TOURINFO="TourInfo";

    public static final String C_TOUR_ID="tourID";
    public static final String C_TOUR_VISITORID="tourVisitorID";
    public static final String C_TOUR_DESTINATION="destination";
    public static final String C_TOUR_BUDGET="budget";
    public static final String C_TOUR_FROMDATE="fromDate";
    public static final String C_TOUR_TODATE="toDate";


    private static final String CREATE_TABLE_TOURINFO="create table "+TABLE_TOURINFO+
            "("+C_TOUR_ID+" integer primary key autoincrement,"
            +C_TOUR_VISITORID+" text,"
            +C_TOUR_DESTINATION+" text,"
            +C_TOUR_BUDGET+" REAL,"
            +C_TOUR_FROMDATE+" text,"
            +C_TOUR_TODATE+" text);";

    //create table for tour cost info
    public static final String TABLE_COST_INFO="TourCostInfo";

    public static final String C_COST_ID="tourcostID";
    public static final String C_COST_TOURID="tourIDs";
    public static final String C_PARTICULARS="Particulars";
    public static final String C_COST="Cost";
    public static final String C_COSTDATE="CostDate";
    private static final String CREATE_TABLE_TOURCOSTINFO="create table "+TABLE_COST_INFO+
            "("+C_COST_ID+" integer primary key autoincrement,"
            +C_COST_TOURID+" text,"
            +C_PARTICULARS+" text ,"
            +C_COST+" REAL ,"
            +C_COSTDATE+" REAL DEFAULT (datetime('now', 'localtime'))) ;";

    public DbTour(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE_VISITORINFO);
        db.execSQL(CREATE_TABLE_TOURINFO);
        db.execSQL(CREATE_TABLE_TOURCOSTINFO);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("drop table if exist "+TABLE_TOURINFO);
        db.execSQL("drop table if exist "+TABLE_COST_INFO);
        db.execSQL("drop table if exist "+TABLE_VISITORINFO);
        onCreate(db);
    }
}


