package com.akhgupta.easylocation.demo.db;
import android.app.Activity;
import android.content.Intent;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.akhgupta.easylocation.demo.R;
import com.akhgupta.easylocation.demo.activity.ExpenseList;
import com.akhgupta.easylocation.demo.activity.ExpenseMaster;
import com.akhgupta.easylocation.demo.activity.TravelMaster;
import com.akhgupta.easylocation.demo.model.TourInfo;
import com.akhgupta.easylocation.demo.util.BdTourmateAlertDialog;
import com.akhgupta.easylocation.demo.util.BdTourmatePreferenceManager;

import java.util.ArrayList;

import static com.akhgupta.easylocation.demo.activity.Home.tourID;

public class TourAdapter extends ArrayAdapter<TourInfo> {
    private Activity activity;
    private ArrayList<TourInfo> tourArrayList;
    private Intent callIntent;


    BdTourmatePreferenceManager memory;
    public TourAdapter(Activity context, ArrayList<TourInfo> tourArrayList) {
        super(context, R.layout.travelist_layout, tourArrayList);
        activity = context;
        this.tourArrayList = tourArrayList;
        memory = new BdTourmatePreferenceManager(context);
    }
    private class ViewHolder {
        Button btnviewExpense;
        Button btnSave;
        Button btnbudgetRemain;
        Button btnExpense;
        Button btnEstimatedbudget;
        Button btnbudgetExpended;
        TextView fromdateTV;
        TextView todateTV;
        ImageView editTour;
        TextView tourIDtv;
        double budget;
        double expended;

    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {

        View view=convertView;
        final ViewHolder holder;
        if (view == null) {

            holder=new ViewHolder();
            LayoutInflater layoutInflater = activity.getLayoutInflater();
            view = layoutInflater.inflate(R.layout.travelist_layout, parent, false);
            holder.editTour= (ImageView) view.findViewById(R.id.editTour);
            holder.fromdateTV = (TextView) view.findViewById(R.id.fromdateTV);
            holder.tourIDtv = (TextView) view.findViewById(R.id.tourID);
            holder.todateTV = (TextView) view.findViewById(R.id.todateTV);
            holder.btnSave = (Button) view.findViewById(R.id.btnSaveplace);
            holder.btnExpense = (Button) view.findViewById(R.id.btnExpense);
            holder.btnbudgetRemain = (Button) view.findViewById(R.id.btnbudgetRemain);
            holder.btnEstimatedbudget = (Button) view.findViewById(R.id.btnEstimatedbudget);
            holder.btnviewExpense = (Button) view.findViewById(R.id.btnviewExpense);
            holder.btnbudgetExpended = (Button) view.findViewById(R.id.btnbudgetExpended);
            holder.budget=0;
            holder.expended=0;
            view.setTag(holder);
        }else {
            holder = (ViewHolder)view.getTag();
        }
        holder.tourIDtv.setText(tourArrayList.get(position).getTourID());
        holder.fromdateTV.setText(tourArrayList.get(position).getFROMDATE());
        holder.todateTV.setText(tourArrayList.get(position).getTODATE());
        holder.btnSave.setText(String.valueOf(tourArrayList.get(position).getDESTINATION()));
        holder.btnEstimatedbudget.setText("B:"+String.valueOf(tourArrayList.get(position).getBUDGET()));
        holder.btnbudgetExpended.setText("E:"+String.valueOf(tourArrayList.get(position).getExpendedCost()));
        holder.budget=tourArrayList.get(position).getBUDGET();
        holder.expended=tourArrayList.get(position).getExpendedCost();
        holder.btnbudgetRemain.setText("R:"+String.valueOf(holder.budget-holder.expended));

        holder.btnExpense.setOnClickListener(new AdapterView.OnClickListener() {
            @Override
            public void onClick(View v) {
                tourID=String.valueOf(holder.tourIDtv.getText().toString());
                memory.putPref(memory.KEY_TOUR_ID, holder.tourIDtv.getText().toString());
                /*Toast.makeText(activity, tourID, Toast.LENGTH_LONG).show();*/

                BdTourmateAlertDialog.showYesNoAlertDialog(activity, "Travel List", "Do you want to add event Expense?", false, new ExpenseMaster());

            }
        });

        holder.editTour.setOnClickListener(new AdapterView.OnClickListener() {
            @Override
            public void onClick(View v) {
                tourID=String.valueOf(holder.tourIDtv.getText().toString());
                memory.putPref(memory.KEY_TOUR_ID, holder.tourIDtv.getText().toString());

                BdTourmateAlertDialog.showYesNoAlertDialog(activity, "Travel List", "Do you want to view event details?", false, new TravelMaster());

            }
        });
        holder.btnviewExpense.setOnClickListener(new AdapterView.OnClickListener() {
            @Override
            public void onClick(View v) {
                tourID=String.valueOf(holder.tourIDtv.getText().toString());
                memory.putPref(memory.KEY_TOUR_ID, holder.tourIDtv.getText().toString());

                BdTourmateAlertDialog.showYesNoAlertDialog(activity, "Travel List", "Do you want to view all expense details?", false, new ExpenseList());

            }
        });

        return view;

    }
}
