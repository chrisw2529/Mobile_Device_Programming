package com.example.whitec39.csci412_assignment2_white;


import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.widget.EditText;
import android.widget.RatingBar;

public class RestDataHolder {
//    private static SharedPreferences prefs;
    private static RestDataHolder rdh;
    private RestData wfpData;
    private RestData abData;
    private RestData ogData;

    public void setData(){
        wfpData = new RestData(4.8f, "This is the best Pizza in Bellingham!");
        abData = new RestData(2.6f, "I really like the iced teas, but the food is just alright");
        ogData = new RestData(4.2f, "Your telling me I can have unlimited breadsticks??? I'll be back next week.");
    }

    public RestData getRestData(int i){
        switch (i){
            case 1:
                return wfpData;
            case 2:
                return abData;

            case 3:
                return ogData;
        }
        return null;

    }
    public synchronized static RestDataHolder getInstance(){
        if(rdh == null){
            rdh = new RestDataHolder();
            rdh.setData();
        }
        return rdh;
    }
}
