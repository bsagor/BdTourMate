package com.akhgupta.easylocation.demo.db;
import android.app.Activity;
import android.content.Intent;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.akhgupta.easylocation.demo.R;
import com.akhgupta.easylocation.demo.activity.ExpenseMaster;
import com.akhgupta.easylocation.demo.model.ExpenseInfo;
import com.akhgupta.easylocation.demo.util.BdTourmateAlertDialog;
import com.akhgupta.easylocation.demo.util.BdTourmatePreferenceManager;

import java.util.ArrayList;

import static com.akhgupta.easylocation.demo.activity.Home.costID;
import static com.akhgupta.easylocation.demo.activity.Home.tourID;

/**
 * Created by FARUQ on 11/19/2016.
 */


public class ExpanseAdapter extends ArrayAdapter<ExpenseInfo> {
    private Activity activity;
    private ArrayList<ExpenseInfo> expenseArrayList;
    private Intent callIntent;


    BdTourmatePreferenceManager memory;
    public ExpanseAdapter(Activity context, ArrayList<ExpenseInfo> expenseArrayList) {
        super(context, R.layout.expenselist_layout, expenseArrayList);
        activity = context;
        this.expenseArrayList = expenseArrayList;
        memory = new BdTourmatePreferenceManager(context);
    }
    private class ViewHolder {
        TextView expensedateTV;
        TextView particularsTV;
        TextView tourcostID;
        TextView editExpense;
        TextView expenseTV;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {

        View view=convertView;
        final ViewHolder holder;
        if (view == null) {

            holder=new ViewHolder();
            LayoutInflater layoutInflater = activity.getLayoutInflater();
            view = layoutInflater.inflate(R.layout.expenselist_layout, parent, false);
            holder.expensedateTV = (TextView) view.findViewById(R.id.expensedateTV);
            holder.particularsTV = (TextView) view.findViewById(R.id.particularsTV);
            holder.expenseTV = (TextView) view.findViewById(R.id.expenseTV);
            holder.tourcostID = (TextView) view.findViewById(R.id.tourcostID);
            holder.editExpense = (TextView) view.findViewById(R.id.editExpense);

            view.setTag(holder);
        }else {
            holder = (ViewHolder)view.getTag();
        }

        holder.expensedateTV.setText(expenseArrayList.get(position).getCostDate());
        holder.particularsTV.setText(String.valueOf(expenseArrayList.get(position).getExpenseParticulars()));

        holder.expenseTV.setText(String.valueOf(expenseArrayList.get(position).getExpense()));
        holder.tourcostID.setText(String.valueOf(expenseArrayList.get(position).getExpenseID()));
        holder.editExpense.setOnClickListener(new AdapterView.OnClickListener() {
            @Override
            public void onClick(View v) {
                tourID=String.valueOf(expenseArrayList.get(position).getTourID());
                costID=String.valueOf(holder.tourcostID.getText().toString());
                memory.putPref(memory.KEY_TOUR_ID, holder.tourcostID.getText().toString());
               /* Toast.makeText(activity, "Cost ID: "+costID, Toast.LENGTH_SHORT).show();*/
                BdTourmateAlertDialog.showYesNoAlertDialog(activity, "Expense List", "Do you want to update expense details?", false, new ExpenseMaster());

            }
        });


        return view;

    }
}
