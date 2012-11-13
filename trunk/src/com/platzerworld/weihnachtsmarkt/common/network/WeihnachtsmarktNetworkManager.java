package com.platzerworld.weihnachtsmarkt.common.network;

import android.content.Context;
import android.net.ConnectivityManager;
import android.widget.Toast;

import com.platzerworld.weihnachtsmarkt.common.logging.LOG;

public class WeihnachtsmarktNetworkManager {
	static private WeihnachtsmarktNetworkManager _instance;
	private static Context ctx;
	private static ConnectivityManager connectivityManager;
	
	private WeihnachtsmarktNetworkManager(){
		
	}
	
	static synchronized public WeihnachtsmarktNetworkManager getInstance(Context context) {
		if (_instance == null)
			_instance = new WeihnachtsmarktNetworkManager();
		ctx = context;
		connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
		return _instance;
	}

	public boolean isNetworkAvailable() {
		
	     final android.net.NetworkInfo wifi = connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI);
	     final android.net.NetworkInfo mobile = connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_MOBILE);
	     
	     if( wifi.isAvailable() ){
		     LOG.v("", "WIFI Network enabled");
		     return true;
		 } else if( mobile.isAvailable() ){
			 LOG.v("", "MOBILE Network enabled");
			 return true;
		 } else{
			 LOG.v("", "NO Network enabled");
			 return false;
		 }

	}

	public ConnectivityManager getConnectivityManager() {
		return connectivityManager;
	} 
	
	public void  checkNetworkStatus(){
	     final android.net.NetworkInfo wifi = connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI);

	     final android.net.NetworkInfo mobile = connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_MOBILE);

	     if( wifi.isAvailable() ){

	     Toast.makeText(ctx, "Wifi" , Toast.LENGTH_LONG).show();
	     }
	     else if( mobile.isAvailable() ){

	     Toast.makeText(ctx, "Mobile 3G " , Toast.LENGTH_LONG).show();
	     }
	     else
	     {

	         Toast.makeText(ctx, "No Network " , Toast.LENGTH_LONG).show();
	     }

	}
	
	
}
