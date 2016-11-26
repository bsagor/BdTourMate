package com.akhgupta.easylocation.demo.model;

/**
 * Created by faruq on 11/14/2016.
 */

public class VisitorInfo {
    public Integer getVisitorID() {
        return VisitorID;
    }

    public String getVisitorName() {
        return VisitorName;
    }

    public String getLoginName() {
        return LoginName;
    }

    public String getPassword() {
        return Password;
    }

    public VisitorInfo() {
    }

    public String getContactNo() {
        return ContactNo;
    }

    public String getAddress() {
        return Address;
    }

    public int VisitorID;
    public String VisitorName;
    public String LoginName;
    public String Password;
    public String Address;
    public String ContactNo;
    public VisitorInfo(Integer visitorID, String visitorName, String loginName, String password, String address,String contactNo) {
        VisitorID = visitorID;
        VisitorName = visitorName;
        LoginName = loginName;
        Password = password;
        Address = address;
        ContactNo=contactNo;
    }
}
