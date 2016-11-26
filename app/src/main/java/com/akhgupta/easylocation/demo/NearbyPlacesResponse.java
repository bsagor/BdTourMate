package com.akhgupta.easylocation.demo;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by BITM TRAINER-403 on 19/11/2016.
 */

public class NearbyPlacesResponse {
    @SerializedName("results")
    @Expose
    private List<Result> results = new ArrayList<Result>();

    public List<Result> getResults() {
        return results;
    }

    /**
     *
     * @param results
     * The results
     */
    public void setResults(List<Result> results) {
        this.results = results;
    }

    public static class Result {

        @SerializedName("formatted_address")
        @Expose
        private String formattedAddress;

        @SerializedName("name")
        @Expose
        private String name;

        @SerializedName("place_id")
        @Expose
        private String place_id;

        public String getPlace_id() {
            return place_id;
        }

        public void setPlace_id(String place_id) {
            this.place_id = place_id;
        }

        public String getFormattedAddress() {
            return formattedAddress;
        }


        public void setFormattedAddress(String formattedAddress) {
            this.formattedAddress = formattedAddress;
        }
        @SerializedName("icon")
        @Expose
        private String icon;

        public String getIcon() {
            return icon;
        }

        public void setIcon(String icon) {
            this.icon = icon;
        }

        public String getRating() {
            return rating;
        }

        public void setRating(String rating) {
            this.rating = rating;
        }

        @SerializedName("rating")
        @Expose
        private String rating;


        public String getName() {
            return name;
        }


        public void setName(String name) {
            this.name = name;
        }

        @SerializedName("geometry")
        public Geometry getGeometry() {
            return geometry;
        }


        public void setGeometry(Geometry geometry) {
            this.geometry = geometry;
        }

        private Geometry geometry;
    }

}

