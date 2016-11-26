package com.akhgupta.easylocation.demo;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by FARUQ on 11/25/2016.
 */

public class Geometry {
    @SerializedName("location")
    @Expose
    private Location location;

    /**
     *
     * @return
     * The channel
     */
    public Location getLocation() {
        return location;
    }

    /**
     *
     * @param location
     * The channel
     */
    public void setChannel(Location location) {
        this.location = location;
    }
}
