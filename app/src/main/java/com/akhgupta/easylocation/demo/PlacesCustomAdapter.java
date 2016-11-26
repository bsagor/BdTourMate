package com.akhgupta.easylocation.demo;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.akhgupta.easylocation.demo.activity.ExpenseList;
import com.akhgupta.easylocation.demo.activity.ExpenseMaster;
import com.akhgupta.easylocation.demo.activity.Home;
import com.akhgupta.easylocation.demo.activity.MainActivity2;
import com.akhgupta.easylocation.demo.activity.MapsActivity;
import com.akhgupta.easylocation.demo.activity.TravelMaster;
import com.akhgupta.easylocation.demo.db.TourAdapter;
import com.akhgupta.easylocation.demo.util.BdTourmateAlertDialog;
import com.google.android.gms.maps.model.LatLng;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

import static com.akhgupta.easylocation.demo.activity.Home.isplaceUpdate;
import static com.akhgupta.easylocation.demo.activity.Home.la;
import static com.akhgupta.easylocation.demo.activity.Home.lo;
import static com.akhgupta.easylocation.demo.activity.Home.mapTitle;
import static com.akhgupta.easylocation.demo.activity.Home.parentparameter;
import static com.akhgupta.easylocation.demo.activity.Home.placeID;
import static com.akhgupta.easylocation.demo.activity.Home.sla;
import static com.akhgupta.easylocation.demo.activity.Home.slo;
import static com.akhgupta.easylocation.demo.activity.Home.tourID;

/**
 * Created by BITM TRAINER-403 on 19/11/2016.
 */

public class PlacesCustomAdapter extends ArrayAdapter<NearbyPlacesResponse.Result> {
    private Activity activity;
    String distinacevlua;
    private List<NearbyPlacesResponse.Result> results;
    public PlacesCustomAdapter(Activity activity, List<NearbyPlacesResponse.Result>results) {
        super(activity, R.layout.places_single_row, results);
        this.activity = activity;
        this.results = results;
    }
    private class ViewHolder {
        TextView rating;
        TextView name;
        TextView address;
        TextView placeid;
        ImageView mapim;

    }



    public View getView(final int position, View convertView, ViewGroup parent) {

        View view=convertView;
        final PlacesCustomAdapter.ViewHolder holder;
        if (view == null) {

            holder=new PlacesCustomAdapter.ViewHolder();
            LayoutInflater layoutInflater = activity.getLayoutInflater();
            view = layoutInflater.inflate(R.layout.places_single_row, parent, false);

            holder.name = (TextView) view.findViewById(R.id.placeName);
            holder.rating = (TextView) view.findViewById(R.id.rating);
            holder.address = (TextView) view.findViewById(R.id.placeAddress);
            holder.placeid = (TextView) view.findViewById(R.id.placeid);
            holder.mapim = (ImageView) view.findViewById(R.id.mapim);
            view.setTag(holder);
        }else {
            holder = (PlacesCustomAdapter.ViewHolder)view.getTag();
        }
        holder.name.setText(results.get(position).getName());
        holder.address.setText(results.get(position).getFormattedAddress());
        holder.placeid.setText(results.get(position).getPlace_id());
        holder.rating.setText("Rating: "+results.get(position).getRating());
        holder.mapim.setOnClickListener(new AdapterView.OnClickListener() {
            @Override
            public void onClick(View v) {
                placeID=String.valueOf(holder.placeid.getText().toString());
                /*Toast.makeText(activity, "ID: "+placeID, Toast.LENGTH_SHORT).show();*/
                Intent intent=new Intent(getContext(),MapsActivity.class);
                mapTitle=results.get(position).getName().toString();
                
                la=results.get(position).getGeometry().getLocation().getLat().toString();
                lo=results.get(position).getGeometry().getLocation().getLng().toString();

                double dis=calculateDistance(Double.valueOf(lo),Double.valueOf(la),Double.valueOf(slo),Double.valueOf(sla));
                if(isplaceUpdate.equals("No")) {
                    BdTourmateAlertDialog.showYesNoAlertDialog(activity, "Near By Place List", "Your distance from Zero point is: "+String.valueOf(dis)+". Do you want to view MAP?", false, new MapsActivity());
                }
                else
                {
                    BdTourmateAlertDialog.showYesNoAlertDialog(activity, "Near By Place List", "Your distance from "+parentparameter+" is: "+String.valueOf(dis)+". Do you want to view MAP?", false, new MapsActivity());
                }
                /*Toast.makeText(activity, String.valueOf(dis), Toast.LENGTH_SHORT).show();*/

             /*   getContext().startActivity(intent);*/

            }
        });
        return view;
    }
    private double calculateDistance(double fromLong, double fromLat,
                                     double toLong, double toLat) {
        double d2r = Math.PI / 180;
        double dLong = (toLong - fromLong) * d2r;
        double dLat = (toLat - fromLat) * d2r;
        double a = Math.pow(Math.sin(dLat / 2.0), 2) + Math.cos(fromLat * d2r)
                * Math.cos(toLat * d2r) * Math.pow(Math.sin(dLong / 2.0), 2);
        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
        double d = 6367 * c;
        return d;
    }
}
