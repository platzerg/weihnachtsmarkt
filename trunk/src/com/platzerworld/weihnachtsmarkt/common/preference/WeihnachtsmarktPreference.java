package com.platzerworld.weihnachtsmarkt.common.preference;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;

public class WeihnachtsmarktPreference {
	public static final String BIERGARTEN_PREF = "_preferences";
	static private WeihnachtsmarktPreference _instance;
    
    private static Context ctx;
    
	private WeihnachtsmarktPreference() {
	}
	
	static synchronized public WeihnachtsmarktPreference getInstance(Context context) {
		if (_instance == null)
			_instance = new WeihnachtsmarktPreference();
			ctx = context;
			init(context);
		return _instance;
	}
	
	private static void init(Context ctx){
	}
	
	private static SharedPreferences getPrivateSharedPreferences(){
		return ctx.getSharedPreferences(ctx.getPackageName() + "_preferences", Activity.MODE_PRIVATE);
	}
	
	public WeihnachtsmarktPreference saveIntState(String key, int value){
		final Editor myEditor = getPrivateSharedPreferences().edit();
		myEditor.putInt(key, value);
		myEditor.commit();
		return _instance;
	}
	
	public WeihnachtsmarktPreference saveStringState(String key, String value){
		final Editor myEditor = getPrivateSharedPreferences().edit();
		myEditor.putString(key, value);
		myEditor.commit();
		return _instance;
	}
	
	public WeihnachtsmarktPreference saveBooleanState(String key, boolean value){
		final Editor myEditor = getPrivateSharedPreferences().edit();
		myEditor.putBoolean(key, value);
		myEditor.commit();
		return _instance;
	}
	
	public WeihnachtsmarktPreference saveFloatState(String key, float value){
		final Editor myEditor = getPrivateSharedPreferences().edit();
		myEditor.putFloat(key, value);
		myEditor.commit();
		return _instance;
	}
	
	public WeihnachtsmarktPreference saveLongState(String key, long value){
		final Editor myEditor = getPrivateSharedPreferences().edit();
		myEditor.putLong(key, value);
		myEditor.commit();
		return _instance;
	}
	
	public int getPreferencesInt(String key){
		return getPrivateSharedPreferences().getInt(key, 0);
	}
	
	public int getPreferencesInt(String key, int defaultValue){
		return getPrivateSharedPreferences().getInt(key, defaultValue);
	}
	
	public boolean getPreferencesBoolean(String key){
		return getPrivateSharedPreferences().getBoolean(key, false);
	}
	
	public boolean getPreferencesBoolean(String key, boolean defaultValue){
		return getPrivateSharedPreferences().getBoolean(key, defaultValue);
	}
	
	public String getPreferencesString(String key){
		return getPrivateSharedPreferences().getString(key, "5000");
	}
	
	public String getPreferencesString(String key, String defaultValue){
		return getPrivateSharedPreferences().getString(key, defaultValue);
	}
}
