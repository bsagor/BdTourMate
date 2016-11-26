package com.akhgupta.easylocation.demo.util;

import android.content.Context;
import android.content.SharedPreferences;

public class BdTourmatePreferenceManager {
	
    private static final String TAG = "PreferenceManager"; 
	 // Shared pref mode
    int PRIVATE_MODE = 0;
     
    // Sharedpref file name
    private static final String PREF_NAME = "loginPrefs";
    
    public static final String KEY_VISITOR_ID = "visitorid";
    
    public static final String KEY_VISITOR_NAME = "visitorname";

	public static final String KEY_VISITOR_LOGIN = "rememberPass";
	public static final String KEY_TOUR_ID = "tourID";
	
	 private SharedPreferences prefs;
	 private Context mContext;
	 
	public BdTourmatePreferenceManager(Context aContext){
		this. mContext = aContext;
	    prefs = aContext.getSharedPreferences(PREF_NAME,  PRIVATE_MODE);
	  
	}
	
	public  void putPref(String key, String value) {
		
		SharedPreferences.Editor editor = prefs.edit();
		editor.putString(key, value);
		editor.commit();
	}

	public  String getPref(String key) {
		
		return prefs.getString(key, null);
	}
	
	/**
	 *  Method used to delete Preferences */
	public boolean deletePreferences(String key)
	{
		SharedPreferences.Editor editor = prefs.edit();
	    editor.remove(key).commit();
	    return false;
	}

}
