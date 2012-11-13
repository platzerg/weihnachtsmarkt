package com.platzerworld.weihnachtsmarkt.common;


import android.content.Context;


public class WeihnachtsmarktManager {
	
	static private WeihnachtsmarktManager _instance;
	private static Context mContext;
	
	private WeihnachtsmarktManager(){
		
	}
	
	static synchronized public WeihnachtsmarktManager getInstance(Context context) {
	    if (_instance == null) {
	    	_instance = new WeihnachtsmarktManager();
	    }
	    mContext = context; 
	    return _instance;
	 }

}
