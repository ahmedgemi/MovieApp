package com.ago.movieapp.data.cache.sharedpreference;


public class UserPreference {

    private final static String KEY_ID = "id";
    private final static String KEY_NAME = "name";
    private final static String KEY_EMAIL = "email";
    private final static String KEY_IMAGE_URL = "imgURL";

    private static UserPreference preference;


    private UserPreference (){}

    public static UserPreference getInstance(){
        if (preference==null)
            preference = new UserPreference();

        return preference;
    }

    public void setID(String id){
        SharedPreferenceManager.getInstance().setString(KEY_ID,id);
    }

    public String getId(){
        return SharedPreferenceManager.getInstance().getString(KEY_ID);
    }


    public void setName(String name){
        SharedPreferenceManager.getInstance().setString(KEY_NAME,name);
    }

    public void setEmail(String email){
        SharedPreferenceManager.getInstance().setString(KEY_EMAIL,email);
    }

    public String getEmail(){
        return SharedPreferenceManager.getInstance().getString(KEY_EMAIL);
    }


    public String getName(){
        return SharedPreferenceManager.getInstance().getString(KEY_NAME);
    }

    public String getImageURL(){
        return SharedPreferenceManager.getInstance().getString(KEY_IMAGE_URL);
    }

    public void setImageURL(String url){
        SharedPreferenceManager.getInstance().setString(KEY_IMAGE_URL,url);
    }


    public boolean isFirstTime(){
        String id = SharedPreferenceManager.getInstance().getString(KEY_ID);
        if (id == null)
            return true;

        return false;
    }

    public void logout(){
        SharedPreferenceManager.getInstance().clearAll();
    }


}
