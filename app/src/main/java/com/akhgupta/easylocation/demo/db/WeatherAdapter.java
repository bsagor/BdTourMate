package com.akhgupta.easylocation.demo.db;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.akhgupta.easylocation.demo.R;
import com.akhgupta.easylocation.demo.model.weatherResult;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

/**
 * Created by Mobile App Develop on 11/9/2016.
 */
public class WeatherAdapter extends ArrayAdapter<weatherResult> {
    private Activity activity;
    private ArrayList<weatherResult> weatherResultsarray;
    String imageurl="http://l.yimg.com/a/i/us/we/52/";
    String studentId;

    public WeatherAdapter(Activity context, ArrayList<weatherResult> weatherResultsarray) {
        super(context, R.layout.activity_weather_master, weatherResultsarray);
        activity = context;
        this.weatherResultsarray = weatherResultsarray;
    }
    private class ViewHolder {
        TextView dayTv;
        TextView dateTV;
        TextView maxTV;
        TextView statusTV;
        TextView highTV;
        TextView lowTV;
        ImageView weatherIV;
        /*TextView highTVC;
        TextView lowTVC;*/
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View view=convertView;
        final ViewHolder holder;
        if (view == null) {

            holder=new ViewHolder();
            LayoutInflater layoutInflater = activity.getLayoutInflater();
            view = layoutInflater.inflate(R.layout.row_weather, parent, false);

            holder.dayTv = (TextView) view.findViewById(R.id.dayTV);
            holder.dateTV = (TextView) view.findViewById(R.id.dateTV);
            holder.maxTV = (TextView) view.findViewById(R.id.maxtempTV);
            holder.statusTV = (TextView) view.findViewById(R.id.statusTV);
            holder.highTV = (TextView) view.findViewById(R.id.highTV);
            holder.lowTV = (TextView) view.findViewById(R.id.lowTV);
           /* holder.highTVC = (TextView) view.findViewById(R.id.highTVC);
            holder.lowTVC = (TextView) view.findViewById(R.id.lowTVC);*/
            holder.weatherIV = (ImageView) view.findViewById(R.id.weatherIV);
            view.setTag(holder);
        }else {

            holder = (ViewHolder)view.getTag();
        }
        holder.dayTv = (TextView) view.findViewById(R.id.dayTV);
        holder.dateTV = (TextView) view.findViewById(R.id.dateTV);
        holder.maxTV = (TextView) view.findViewById(R.id.maxtempTV);
        holder.statusTV = (TextView) view.findViewById(R.id.statusTV);
        holder.highTV = (TextView) view.findViewById(R.id.highTV);
        holder.lowTV = (TextView) view.findViewById(R.id.lowTV);
        holder.weatherIV = (ImageView) view.findViewById(R.id.weatherIV);

       /* holder.lowTVC = (TextView) view.findViewById(R.id.lowTVC);
        holder.highTVC = (TextView) view.findViewById(R.id.highTVC);*/


        holder.dayTv.setText(weatherResultsarray.get(position).getDay());
        holder.dateTV.setText(weatherResultsarray.get(position).getDate());
        holder.statusTV.setText(weatherResultsarray.get(position).getText());
        holder.highTV.setText(weatherResultsarray.get(position).getHigh());
        holder.lowTV.setText(weatherResultsarray.get(position).getLow());
       /* holder.highTVC.setText(weatherResultsarray.get(position).getHighc());
        holder.lowTVC.setText(weatherResultsarray.get(position).getLowC());*/
        Picasso.with(getContext()).load(imageurl+weatherResultsarray.get(position).getCode()+".gif").error(R.drawable.clear).into( holder.weatherIV);
        return view;

    }
}
