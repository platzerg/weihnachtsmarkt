package com.platzerworld.weihnachtsmarkt.common.location;

import android.content.Context;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.widget.Toast;

import com.google.android.maps.MapController;
import com.google.android.maps.MapView;
import com.platzerworld.weihnachtsmarkt.common.map.overlay.WeihnachtsmarktGeoPoint;


public class WeihnachtsmarktLocationManager{
	private static LocationManager locationManager;
	private MapController mapController;
	private MapView mapView;
	
	static private WeihnachtsmarktLocationManager _instance;
	
	private static Context ctx;
	
	private WeihnachtsmarktLocationManager(){
		
	}
	
	static synchronized public WeihnachtsmarktLocationManager getInstance(Context context) {
		if (_instance == null)
			_instance = new WeihnachtsmarktLocationManager();
		init(context);
		return _instance;
	}
	
	private static void init(Context aCtx){
		ctx = aCtx;
		locationManager = (LocationManager) ctx.getSystemService(Context.LOCATION_SERVICE);
	}
	
	public void bind(MapView aMapView){
		this.mapView = aMapView;
		mapController = mapView.getController();
		
		setProperties();
		
		locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0,
				0, new GeoUpdateHandler());
	}
	
	private void setProperties(){
		mapView.setBuiltInZoomControls(true);
		mapView.setStreetView(true);
		mapController.setZoom(16); // Zoon 1 is world view
		int lat = (int) (48.17968 * 1E6); // 48
		int lng = (int) (11.5922 * 1E6); // 11
		WeihnachtsmarktGeoPoint point = new WeihnachtsmarktGeoPoint(lat, lng);
		mapController.setCenter(point);
	}
	
	public boolean isRouteDisplayed(){
		return true;
	}
	
	public class GeoUpdateHandler implements LocationListener {

		@Override
		public void onLocationChanged(Location location) {
			int lat = (int) (location.getLatitude() * 1E6);
			int lng = (int) (location.getLongitude() * 1E6);
			WeihnachtsmarktGeoPoint point = new WeihnachtsmarktGeoPoint(lat, lng);
			mapController.animateTo(point); //	mapController.setCenter(point);
		}

		@Override
		public void onProviderDisabled(String provider) {
		}

		@Override
		public void onProviderEnabled(String provider) {
		}

		@Override
		public void onStatusChanged(String provider, int status, Bundle extras) {
		}
	}
	
	public Location getLastKnownLocation(){		
		if(isGPS()){
			locationManager = (LocationManager) ctx.getSystemService(Context.LOCATION_SERVICE);      	
	    	Criteria criteria = new Criteria();
	    	String provider = locationManager.getBestProvider(criteria, false);
	    	return locationManager.getLastKnownLocation(provider);
		}
		Toast.makeText(ctx, "No GPS enabled!", Toast.LENGTH_SHORT).show();
		return null;
	}
	
	private boolean isGPS(){
		boolean enabled = locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER);
		if (!enabled) {
		  return false;
		} 
		return true;
	}
}
