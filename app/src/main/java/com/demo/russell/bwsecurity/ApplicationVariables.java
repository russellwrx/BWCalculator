package com.demo.russell.bwsecurity;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by russell on 1/03/16.
 */
public class ApplicationVariables {
    public static final String PREFS_NAME = "BWSCURITY_PREFS";
    public static final String PREF_KEY = "DATALIST";
    public static final String PREF_VAR = "DATAVAR";
    public ApplicationVariables(){
        super();
    }

    public void saveData(Context context, ArrayList<Row> dataList, AppStoredValus storeValues){
        Gson gson = new Gson();
        String jsonObject = gson.toJson(dataList);
        String jaonObjectStore = gson.toJson(storeValues);
        //Save to SharedPreferences
        SharedPreferences settings = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = settings.edit();
        editor.putString(PREF_VAR,jaonObjectStore);
        editor.putString(PREF_KEY,jsonObject);
        editor.commit();
    }


    public ArrayList<Row> getData(Context context) {
        List<Row> datalist;

        SharedPreferences settings = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
        if (settings.contains(PREF_KEY)) {
            String jsonObject = settings.getString(PREF_KEY, null);
            Gson gson = new Gson();
            Row[] rows = gson.fromJson(jsonObject, Row[].class);
            datalist = Arrays.asList(rows);
            datalist = new ArrayList<Row>(datalist);
            //    Log.d("JSON READ: ", jsonObject);
            return  (ArrayList<Row>) datalist;
            } else {
            ArrayList<Row> resetArray = new ArrayList<Row>();
            return resetArray;
        }

    }

    public AppStoredValus getDataVariables(Context context) {
        AppStoredValus detailsvars  = new AppStoredValus();

        SharedPreferences settings = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
        if (settings.contains(PREF_KEY)) {
            String jsonObject = settings.getString(PREF_VAR, null);
            Gson gson = new Gson();
            AppStoredValus storedvariables = gson.fromJson(jsonObject, AppStoredValus.class);
            return  storedvariables;
        } else {
            return detailsvars ;
        }

    }



    public Double getVariables(Context context){
        SharedPreferences settings = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
        if (settings.contains(PREF_VAR)){
            String startFloat = settings.getString(PREF_VAR,null);
            return Double.parseDouble(startFloat);
        }
        return 30550.0;
    }


    public void removekey(Context context,String remove_key){
      //Save to SharedPreferences
        SharedPreferences settings = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = settings.edit();
        editor.remove(remove_key);
        editor.commit();
    }
}
