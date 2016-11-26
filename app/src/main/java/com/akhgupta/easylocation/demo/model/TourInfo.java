package com.akhgupta.easylocation.demo.model;

/**
 * Created by faruq on 11/15/2016.
 */

public class TourInfo {
    public  String TourID;

    public String getTourID() {
        return TourID;
    }

    public String DESTINATION;
    public String FROMDATE;
    public String TODATE;
    public String tourVisitorID;
    public double expendedCost;

    public TourInfo() {
    }

    public String getDESTINATION() {
        return DESTINATION;
    }

    public String getFROMDATE() {
        return FROMDATE;
    }

    public String getTODATE() {
        return TODATE;
    }

    public String getTourVisitorID() {
        return tourVisitorID;
    }

    public double getBUDGET() {
        return BUDGET;
    }

    public TourInfo(String tourID) {
        TourID = tourID;
    }

    public double getExpendedCost() {
        return expendedCost;
    }



    public TourInfo(String TourID, String DESTINATION, String FROMDATE, String TODATE, String tourVisitorID, double BUDGET, double expendedCost) {
        this.TourID = TourID;
        this.DESTINATION = DESTINATION;
        this.FROMDATE = FROMDATE;
        this.TODATE = TODATE;
        this.tourVisitorID = tourVisitorID;
        this.BUDGET = BUDGET;
        this.expendedCost=expendedCost;
    }

    public TourInfo(String DESTINATION, String FROMDATE, String TODATE, String tourVisitorID, double BUDGET) {
        this.DESTINATION = DESTINATION;
        this.FROMDATE = FROMDATE;
        this.TODATE = TODATE;
        this.tourVisitorID = tourVisitorID;
        this.BUDGET = BUDGET;
    }

    public double BUDGET=Double.parseDouble("0");


}
