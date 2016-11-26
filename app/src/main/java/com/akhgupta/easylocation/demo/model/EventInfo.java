package com.akhgupta.easylocation.demo.model;

/**
 * Created by FARUQ on 11/14/2016.
 */

public class EventInfo {
    public String tourID;
    public String tourVisitorID;
    public String destination;
    public double budget=Double.parseDouble("0");
    public String fromDate;
    public String toDate;

    public EventInfo(String tourID, String tourVisitorID, String destination, double budget, String fromDate, String toDate) {
        this.tourID = tourID;
        this.tourVisitorID = tourVisitorID;
        this.destination = destination;
        this.budget = budget;
        this.fromDate = fromDate;
        this.toDate = toDate;
    }

    public EventInfo() {
    }

    public String getTourID() {
        return tourID;
    }

    public String getTourVisitorID() {
        return tourVisitorID;
    }

    public String getDestination() {
        return destination;
    }

    public double getBudget() {
        return budget;
    }

    public String getFromDate() {
        return fromDate;
    }

    public String getToDate() {
        return toDate;
    }
}
