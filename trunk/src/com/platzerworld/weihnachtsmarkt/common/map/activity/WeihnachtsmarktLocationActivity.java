package com.platzerworld.weihnachtsmarkt.common.map.activity;


import android.content.Context;
import android.graphics.drawable.Drawable;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import com.google.android.maps.GeoPoint;
import com.google.android.maps.MapActivity;
import com.google.android.maps.MapController;
import com.google.android.maps.MapView;
import com.google.android.maps.MyLocationOverlay;
import com.google.android.maps.OverlayItem;
import com.platzerworld.weihnachtsmarkt.R;
import com.platzerworld.weihnachtsmarkt.common.ConstantsIF;
import com.platzerworld.weihnachtsmarkt.common.map.overlay.WeihnachtsmarktGeoPoint;
import com.platzerworld.weihnachtsmarkt.common.map.overlay.WeihnachtsmarktItemizedOverlay;

public class WeihnachtsmarktLocationActivity extends MapActivity implements ConstantsIF{
	private static final String TAG = "BiergartenUebersichtActivity";
	private static final long serialVersionUID = -6754055149216909895L;
	
	private MapView mapView;
	int latE6 = (int) (48.185771 * 1e6);
    int lonE6 = (int) (11.620338 * 1e6);
    
    private MapController mapController;
	private LocationManager locationManager;
	private WeihnachtsmarktItemizedOverlay itemizedoverlay;
	private MyLocationOverlay myLocationOverlay;
    
    
    @Override
    public void onCreate(Bundle savedInstanceState) {
        
        super.onCreate(savedInstanceState);
        setContentView(R.layout.biergarten_uebersicht);
        
        mapView = (MapView) findViewById(R.id.map_view);
        
        MapController mapController = mapView.getController();
        mapView.setBuiltInZoomControls(true);
		mapView.setSatellite(true);
		mapController = mapView.getController();
		mapController.setZoom(14); // Zoon 1 is world view
		locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
		locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, new GeoUpdateHandler());

		myLocationOverlay = new MyLocationOverlay(this, mapView);
		mapView.getOverlays().add(myLocationOverlay);

		myLocationOverlay.runOnFirstFix(new Runnable() {
			public void run() {
				mapView.getController().animateTo(myLocationOverlay.getMyLocation());
			}
		});

		Drawable drawable = this.getResources().getDrawable(R.drawable.bierundbreze);
		itemizedoverlay = new WeihnachtsmarktItemizedOverlay(drawable, this);
		createMarker();
        
    }
    
    public class GeoUpdateHandler implements LocationListener {

		@Override
		public void onLocationChanged(Location location) {
			int lat = (int) (location.getLatitude() * 1E6);
			int lng = (int) (location.getLongitude() * 1E6);
			WeihnachtsmarktGeoPoint point = new WeihnachtsmarktGeoPoint(lat, lng);
			createMarker();
			mapController.animateTo(point); // mapController.setCenter(point);

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

	private void createMarker() {
		GeoPoint p = mapView.getMapCenter();
		OverlayItem overlayitem = new OverlayItem(p, "", "");
		itemizedoverlay.addOverlay(overlayitem);
		if (itemizedoverlay.size() > 0) {
			mapView.getOverlays().add(itemizedoverlay);
		}
	}

	@Override
	protected void onResume() {
		super.onResume();
		myLocationOverlay.enableMyLocation();
		myLocationOverlay.enableCompass();
	}

	@Override
	protected void onPause() {
		super.onPause();
		myLocationOverlay.disableMyLocation();
		myLocationOverlay.disableCompass();
	}




	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.biergarten_maps_menu_items, menu);
		return true;
	}
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		return applyMenuChoice(item);
	}

	private boolean applyMenuChoice(MenuItem item) {
		switch (item.getItemId()) {
		case R.id.opt_biergarten_maps_style_strasse:
			mapView.setTraffic(true);
			mapView.setSatellite(false);
			mapView.setStreetView(false);			
			return true;
		
		case R.id.opt_biergarten_maps_style_satelite:
			mapView.setSatellite(true);
			mapView.setStreetView(false);
			mapView.setTraffic(false);
			return true;
		case R.id.opt_biergarten_maps_style_verkehr:
			mapView.setSatellite(false);
			mapView.setStreetView(true);
			mapView.setTraffic(true);
			return true;
		default:
			return super.onOptionsItemSelected(item);
		}
	}
    
    @Override
    protected boolean isRouteDisplayed() {
        return false;
    }
}
    