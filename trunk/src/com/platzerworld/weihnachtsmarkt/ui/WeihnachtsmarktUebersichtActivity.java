package com.platzerworld.weihnachtsmarkt.ui;


import java.util.ArrayList;
import java.util.List;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.location.Location;
import android.location.LocationListener;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import com.google.android.maps.GeoPoint;
import com.google.android.maps.MapActivity;
import com.google.android.maps.MapController;
import com.google.android.maps.MapView;
import com.google.android.maps.Overlay;
import com.google.android.maps.OverlayItem;
import com.platzerworld.weihnachtsmarkt.R;
import com.platzerworld.weihnachtsmarkt.common.ConstantsIF;
import com.platzerworld.weihnachtsmarkt.common.location.WeihnachtsmarktLocationManager;
import com.platzerworld.weihnachtsmarkt.common.map.overlay.WeihnachtsmarktGeoPoint;
import com.platzerworld.weihnachtsmarkt.common.map.overlay.WeihnachtsmarktItemizedOverlay;
import com.platzerworld.weihnachtsmarkt.common.map.overlay.WeihnachtsmarktOwnLocationOverlay;
import com.platzerworld.weihnachtsmarkt.common.preference.WeihnachtsmarktPreference;
import com.platzerworld.weihnachtsmarkt.vo.WeihnachtsmarktVO;

public class WeihnachtsmarktUebersichtActivity extends MapActivity implements ConstantsIF{
	private static final String TAG = "BiergartenUebersichtActivity";
	private static final long serialVersionUID = -6754055149216909895L;
	
	private List<WeihnachtsmarktVO> biergartenList = null;
	
	private boolean enableRadiusPreference;
	private int radiusValuePreference;
	
	private MapView mapView;
	int latE6 = (int) (48.185771 * 1e6);
    int lonE6 = (int) (11.620338 * 1e6);
    
    private MapController mapController;
	private WeihnachtsmarktItemizedOverlay itemizedoverlay;
	private WeihnachtsmarktOwnLocationOverlay myLocationOverlay;
	private WeihnachtsmarktItemizedOverlay itemizedOverlay;
	
	private String url = null;
	private Location gpsLocation;
        
	
    @Override
    public void onCreate(Bundle savedInstanceState) {        
        super.onCreate(savedInstanceState);
        setContentView(R.layout.biergarten_uebersicht);
        
        Bundle extras = getIntent().getExtras();
		if (extras == null) {
			return;
		}
		
		biergartenList =  (List<WeihnachtsmarktVO>) extras.getSerializable(INTENT_EXTRA_BIERGARTEN_LISTE);
		if(null == biergartenList){
			biergartenList = new ArrayList<WeihnachtsmarktVO>();
		}
        
		initPrefs();
        initBiergartenMap();
		initGPSLocation();		
		initBiergartenOwnLocationOverlay();		
		displayResults();
		mapView.invalidate();
    }
    
    private void initBiergartenOwnLocationOverlay(){
    	myLocationOverlay = new WeihnachtsmarktOwnLocationOverlay(this, mapView, new WeihnachtsmarktGeoPoint( (int)( gpsLocation.getLatitude() * 1e6), (int) (gpsLocation.getLongitude() * 1e6)));
		myLocationOverlay.setMeters(this.radiusValuePreference);	
		myLocationOverlay.enableCompass();
        myLocationOverlay.enableMyLocation();
		myLocationOverlay.runOnFirstFix(new Runnable() {
			public void run() {
				mapView.getController().animateTo(myLocationOverlay.getMyLocation());
			}
		});
		mapView.getOverlays().add(myLocationOverlay);		
		
    }
    
    private void initBiergartenMap(){
    	mapView = (MapView) findViewById(R.id.map_view);        
        mapController = mapView.getController();
        mapView.setBuiltInZoomControls(true);		
		mapController = mapView.getController();
		mapController.setZoom(13);
    }
    
    private void initGPSLocation(){
    	gpsLocation = WeihnachtsmarktLocationManager.getInstance(this).getLastKnownLocation();
    }
    
    public class GeoUpdateHandler implements LocationListener {

		@Override
		public void onLocationChanged(Location location) {
			int lat = (int) (location.getLatitude() * 1E6);
			int lng = (int) (location.getLongitude() * 1E6);
			GeoPoint point = new GeoPoint(lat, lng);
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
		case R.id.opt_biergarten_maps_show_route:
			 //"http://maps.google.com/maps?&saddr=13.042206,80.17000&daddr=9.580000,78.100000"
			 Location lastKnownLocation = WeihnachtsmarktLocationManager.getInstance(this).getLastKnownLocation();
		     Uri uri =Uri .parse(getUri(lastKnownLocation.getLatitude(), lastKnownLocation.getLongitude(), 48.185705, 11.620322));
		     
		     //Uri uri =Uri.parse("http://maps.google.com/maps?&saddr=13.042206,80.17000&daddr=9.580000,78.100000");
		     Intent intent = new Intent(Intent.ACTION_VIEW, uri);
		     startActivity(intent);
		     finish();
		     return true;
		default:
			return super.onOptionsItemSelected(item);
		}
	}
	
	public static String getUri(double fromLat, double fromLon, double toLat, double toLon) {// connect to map web service
	    StringBuffer urlString = new StringBuffer();
	    urlString.append("http://maps.google.com/maps");
	    urlString.append("?&saddr=");// from
	    urlString.append(fromLat);
	    urlString.append(",");
	    urlString.append(fromLon);
	    urlString.append("&daddr=");// to
	    urlString.append(toLat);
	    urlString.append(",");
	    urlString.append(toLon);
	    return urlString.toString();
	}
    
    @Override
    protected boolean isRouteDisplayed() {
        return false;
    }
    
    public static String getUrl(double fromLat, double fromLon, double toLat,
            double toLon) {// connect to map web service
    StringBuffer urlString = new StringBuffer();
    
    urlString.append("http://maps.googleapis.com/maps/api/directions/json");
    urlString.append("?origin=");// from
    urlString.append(Double.toString(fromLat));
    urlString.append(",");
    urlString.append(Double.toString(fromLon));
    urlString.append("&destination=");// to
    urlString.append(Double.toString(toLat));
    urlString.append(",");
    urlString.append(Double.toString(toLon));
    urlString.append("&sensor=false");
    return urlString.toString();
}
    
    
    private void displayResults() {
    	boolean isFound = false;
		// Create dummy list of GeoPoint
    	List<WeihnachtsmarktGeoPoint> points = new ArrayList<WeihnachtsmarktGeoPoint>();
    	for (WeihnachtsmarktVO biergartenVO : biergartenList) {
        	String lat = biergartenVO.getLatitude();
        	String lon = biergartenVO.getLongitude();
        	double d1 = Double.parseDouble(lat);
        	double d2 = Double.parseDouble(lon);
        	
        	WeihnachtsmarktGeoPoint point = new WeihnachtsmarktGeoPoint( (int)( d1 * 1e6), (int) (d2* 1e6), biergartenVO);
        	points.add(point);
    	}
    	
		List<Overlay> mapOverlays = mapView.getOverlays();
		Drawable drawable = getResources().getDrawable(R.drawable.bierundbreze);
		itemizedOverlay = new WeihnachtsmarktItemizedOverlay(drawable, this);

        for(WeihnachtsmarktGeoPoint point : points) {
	        Location pointLocation = new Location("point");
	        pointLocation.setLatitude(point.getLatitudeE6() / 1000000.0);
	        pointLocation.setLongitude(point.getLongitudeE6() / 1000000.0);

	        // Calculate the distance between current location and point location
	        if(!enableRadiusPreference || (gpsLocation.distanceTo(pointLocation) < new Float(radiusValuePreference))) {
		        isFound = true;
	        	//OverlayItem overlayitem = new OverlayItem(point, "", "");
		        WeihnachtsmarktVO biergartenVO = point.getBiergartenVO();
	        	OverlayItem overlayitem = new OverlayItem(point, biergartenVO.name, biergartenVO.strasse + ", " + biergartenVO.plz +" " +biergartenVO.ort);
		        itemizedOverlay.addOverlay(overlayitem);
	        }
        }
        // If any location found, draw the placemark
        if(isFound)
        	mapOverlays.add(itemizedOverlay);
	}
    
    private void initPrefs() {    	
		radiusValuePreference = WeihnachtsmarktPreference.getInstance(this).getPreferencesInt(PREFERENCE_KEY_RADIUS_VALUE, 3000);
		enableRadiusPreference = WeihnachtsmarktPreference.getInstance(this).getPreferencesBoolean(PREFERENCE_KEY_RADIUS_ENABLED, true);
	}    
    
    @Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
    	WeihnachtsmarktVO biergartenVO = null;
		if (resultCode == RESULT_OK && requestCode == REQUEST_CODE_BIERGARTEN_DETAIL) {
			if (data.hasExtra(INTENT_EXTRA_BIERGARTEN_DETAILS_RESULT)) {
				biergartenVO = (WeihnachtsmarktVO) data.getExtras().getSerializable(INTENT_EXTRA_BIERGARTEN_DETAILS_RESULT);
			}
			if(data.hasExtra(INTENT_EXTRA_BIERGARTEN_DETAILS_OVERLAY_INDEX)){
				int idx = data.getExtras().getInt(INTENT_EXTRA_BIERGARTEN_DETAILS_OVERLAY_INDEX);
				
				WeihnachtsmarktGeoPoint biergartenGeoPoint = (WeihnachtsmarktGeoPoint) itemizedOverlay.getItem(idx).getPoint();
				if(null != biergartenVO){
					biergartenGeoPoint.setBiergartenVO(biergartenVO);
				}
			}
		}
	}
}
    