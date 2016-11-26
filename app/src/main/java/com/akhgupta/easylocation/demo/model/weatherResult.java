package com.akhgupta.easylocation.demo.model;

/**
 * Created by Mobile App Develop on 11/9/2016.
 */
public class weatherResult {
    private String code;
    private String date;
    private String high;
    private String low;
    private String highc;

    public String getHighc() {
        return highc;
    }

    public String getLowC() {
        return lowC;
    }

    private String lowC;
    private String day;
    private String text;

    public String getText() {
        return text;
    }

    public String getCode() {
        return code;
    }

    public String getDate() {
        return date;
    }

    public String getHigh() {
        return high;
    }

    public String getLow() {
        return low;
    }

    public String getDay() {
        return day;
    }

    @Override
    public String toString() {
        return text;
    }

    public weatherResult(String code, String date, String high, String low, String day, String text) {
        this.code = code;
        this.date = date;
        this.high = high;
        this.low = low;

        this.day = day;
        this.text = text;

        this.highc = highc;
        this.lowC = lowC;

    }
}
