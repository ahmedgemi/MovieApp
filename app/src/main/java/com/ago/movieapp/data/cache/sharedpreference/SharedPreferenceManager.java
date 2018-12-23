package com.ago.movieapp.data.cache.sharedpreference;

import android.content.Context;
import android.content.SharedPreferences;

import com.ago.movieapp.MovieApp;


public class SharedPreferenceManager {

    private static SharedPreferenceManager sharedManager;
    private static SharedPreferences sharedPreferences;

    private final static String SHARED_PREFERENCE_NAME = "app.sharedPref";


    public static SharedPreferenceManager getInstance(){
        if (sharedManager==null){
            sharedManager = new SharedPreferenceManager();
        }

        return sharedManager;
    }

    private SharedPreferenceManager(){
        Context context = MovieApp.getInstance().getApplicationContext();
        sharedPreferences = context.getSharedPreferences(SHARED_PREFERENCE_NAME, Context.MODE_PRIVATE);
    }

    public void setString(String key, String value){
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(key,value);
        editor.apply();
    }

    public String getString(String key){
        return sharedPreferences.getString(key,null);
    }

    public void setInteger(String key, int value){
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putInt(key,value);
        editor.apply();
    }

    public int getInteger(String key){
        return sharedPreferences.getInt(key,-1);
    }

    public long getLong(String key){
        return sharedPreferences.getLong(key,-1);
    }

    public void setLong(String key, long value){
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putLong(key,value);
        editor.apply();
    }

    public void setBoolean(String key, boolean value){
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putBoolean(key,value);
        editor.apply();
    }

    public boolean getBoolean(String key){
        return sharedPreferences.getBoolean(key,false);
    }

    public void setFloat(String key, Float value){
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putFloat(key,value);
        editor.apply();
    }

    public Float getFloat(String key){
        return sharedPreferences.getFloat(key,0.0f);
    }

    public void clearAll(){
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.clear();
        editor.apply();
    }
}
