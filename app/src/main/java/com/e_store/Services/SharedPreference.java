package com.e_store.Services;

import android.content.Context;

import java.util.List;
import java.util.Set;

public class SharedPreference {

    private Context context;
    private android.content.SharedPreferences pref;
    private android.content.SharedPreferences.Editor editor;

    public SharedPreference(Context context){
        this.context = context;
        pref = context.getSharedPreferences("Prefs", 0); // 0 - for private mode
        editor = pref.edit();
    }

    public void setPreference(String key, String value){
        editor.putString(key, value);
        editor.commit();
    }

    public String getPreference(String key){
        String value = pref.getString(key, null);
        return value;
    }

    public void removePreference(String key){
        editor.remove(key);
        editor.commit();
    }

    public void clearPreference(){
        editor.clear();
        editor.commit();
    }
}