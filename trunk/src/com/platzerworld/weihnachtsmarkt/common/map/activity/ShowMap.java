package com.platzerworld.weihnachtsmarkt.common.map.activity;

import android.content.Context;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;

import com.google.android.maps.GeoPoint;
import com.google.android.maps.MapActivity;
import com.google.android.maps.MapController;
import com.google.android.maps.MapView;

public class ShowMap extends MapActivity {

	private MapController mapController;
	private MapView mapView;
	private LocationManager locationManager;
	private GeoUpdateHandler geoUpdateHandler;

	public void onCreate(Bundle bundle) {
		super.onCreate(bundle);
		//setContentView(R.layout.show_map); // bind the layout to the activity

		// create a map view
		//RelativeLayout linearLayout = (RelativeLayout) findViewById(R.id.mainlayout);
		//mapView = (MapView) findViewById(R.id.mapview);
		mapView.setBuiltInZoomControls(true);
		mapView.setStreetView(true);
		mapController = mapView.getController();
		mapController.setZoom(16); // Zoon 1 is world view
		int lat = (int) (48.17968 * 1E6); // 48
		int lng = (int) (11.5922 * 1E6); // 11
		GeoPoint point = new GeoPoint(lat, lng);
		mapController.setCenter(point);
		geoUpdateHandler = new GeoUpdateHandler();
		locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
	}
	
	@Override
	protected void onResume() {
		super.onResume();
		// register listener
		geoUpdateHandler = new GeoUpdateHandler();
		locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, geoUpdateHandler);
	}

	@Override
	protected void onPause() {
		// unregister listener
		locationManager.removeUpdates(geoUpdateHandler);
		geoUpdateHandler = null;
		super.onStop();
	}
	
	@Override
	protected void onDestroy() {
		super.onDestroy();
	}

	@Override
	protected boolean isRouteDisplayed() {
		return false;
	}

	public class GeoUpdateHandler implements LocationListener {

		@Override
		public void onLocationChanged(Location location) {
			int lat = (int) (location.getLatitude() * 1E6);
			int lng = (int) (location.getLongitude() * 1E6);
			GeoPoint point = new GeoPoint(lat, lng);
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
}