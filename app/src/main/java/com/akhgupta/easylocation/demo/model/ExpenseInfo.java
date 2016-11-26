package com.akhgupta.easylocation.demo.model;

/**
 * Created by faruq on 11/17/2016.
 */

public class ExpenseInfo {
    public String ExpenseID;
    public String tourID;
    public String CostDate;
    public String ExpenseParticulars;
    public  double Expense;


    public String getExpenseID() {
        return ExpenseID;
    }

    public String getTourID() {
        return tourID;
    }
    public String getExpenseParticulars() {
        return ExpenseParticulars;
    }

    public double getExpense() {
        return Expense;
    }
    public String getCostDate() {
        return CostDate;
    }




    public ExpenseInfo(String expenseID, String tourID, String expenseParticulars, double expense, String costDate) {
        this.ExpenseID = expenseID;
        this.tourID = tourID;
        this.ExpenseParticulars = expenseParticulars;
        this.Expense = expense;
        this.CostDate=costDate;
    }
    public ExpenseInfo(String tourID, String expenseParticulars, double expense) {

        this.tourID = tourID;
        ExpenseParticulars = expenseParticulars;
        Expense = expense;
    }

    public ExpenseInfo() {
    }


}
