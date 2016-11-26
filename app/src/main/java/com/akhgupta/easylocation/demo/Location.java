package com.akhgupta.easylocation.demo;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by FARUQ on 11/25/2016.
 */

public class Location {
    @SerializedName("lat")
    @Expose
    private String lat;

    public String getLat() {
        return lat;
    }

    public void setLat(String lat) {
        this.lat = lat;
    }

    public String getLng() {
        return lng;
    }

    public void setLng(String lng) {
        this.lng = lng;
    }

    @SerializedName("lng")
    @Expose
    private String lng;
}
