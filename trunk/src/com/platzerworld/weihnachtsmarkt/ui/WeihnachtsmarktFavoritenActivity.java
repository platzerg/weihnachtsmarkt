package com.platzerworld.weihnachtsmarkt.ui;


import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import com.google.android.maps.GeoPoint;
import com.google.android.maps.MapActivity;
import com.google.android.maps.MapController;
import com.google.android.maps.MapView;
import com.google.android.maps.OverlayItem;
import com.platzerworld.weihnachtsmarkt.R;
import com.platzerworld.weihnachtsmarkt.common.ConstantsIF;
import com.platzerworld.weihnachtsmarkt.common.location.WeihnachtsmarktLocationManager;
import com.platzerworld.weihnachtsmarkt.common.map.navigation.NavigationDataSet;
import com.platzerworld.weihnachtsmarkt.common.map.overlay.RouteOverlay;
import com.platzerworld.weihnachtsmarkt.common.map.overlay.WeihnachtsmarktGeoPoint;
import com.platzerworld.weihnachtsmarkt.common.map.overlay.WeihnachtsmarktItemizedOverlay;
import com.platzerworld.weihnachtsmarkt.common.map.overlay.WeihnachtsmarktOwnLocationOverlay;
import com.platzerworld.weihnachtsmarkt.common.preference.WeihnachtsmarktPreference;
import com.platzerworld.weihnachtsmarkt.vo.WeihnachtsmarktVO;

public class WeihnachtsmarktFavoritenActivity extends MapActivity implements ConstantsIF{
	private static final String TAG = "BiergartenFavoritenActivity";
	private static final long serialVersionUID = -6754055149216909895L;
	
	private List<WeihnachtsmarktVO> biergartenList = null;
	
	private boolean enableRadiusPreference;
	private int radiusValuePreference;
	
	private MapView mapView;
	int latE6 = (int) (48.185771 * 1e6);
    int lonE6 = (int) (11.620338 * 1e6);
    // 48.175914,11.627169
    
    private MapController mapController;
	private LocationManager locationManager;
	private WeihnachtsmarktItemizedOverlay itemizedoverlay;
	private WeihnachtsmarktOwnLocationOverlay myLocationOverlay;
	private WeihnachtsmarktItemizedOverlay itemizedOverlay;
	
	private String url = null;
	private Location gpsLocation;
    
    @Override
    public void onCreate(Bundle savedInstanceState) {
        
        super.onCreate(savedInstanceState);
        setContentView(R.layout.biergarten_favoriten);
        
        Bundle extras = getIntent().getExtras();
		if (extras == null) {
			return;
		}
		
		biergartenList =  (List<WeihnachtsmarktVO>) extras.getSerializable(INTENT_EXTRA_BIERGARTEN_FAVORITEN_LISTE);
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
		myLocationOverlay.setMeters(radiusValuePreference);	
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
		mapView = (MapView) findViewById(R.id.map_view_favoriten);        
        mapController = mapView.getController();
        mapView.setBuiltInZoomControls(true);
		mapView.setSatellite(true);		
		mapController = mapView.getController();
		mapController.setZoom(13);
    }
    
    private void initGPSLocation(){
    	gpsLocation = WeihnachtsmarktLocationManager.getInstance(this).getLastKnownLocation();
    }
    
    private void displayResults() {
    	Drawable drawable = this.getResources().getDrawable(R.drawable.bierundbreze);	
    	itemizedOverlay =  new WeihnachtsmarktItemizedOverlay(drawable, this);
            		
		// itemizedoverlay = new BiergartenItemizedOverlay(drawable, this);
		// createMarker();
		
        for (WeihnachtsmarktVO biergartenVO : biergartenList) {
        	String lat = biergartenVO.getLatitude();
        	String lon = biergartenVO.getLongitude();
        	double d1 = Double.parseDouble(lat);
        	double d2 = Double.parseDouble(lon);
        	
        	WeihnachtsmarktGeoPoint point = new WeihnachtsmarktGeoPoint( (int)( d1 * 1e6), (int) (d2* 1e6), biergartenVO);
        	 
             OverlayItem overlayitem = new OverlayItem(point, biergartenVO.name, biergartenVO.strasse + ", " + biergartenVO.plz +" " +biergartenVO.ort);
             itemizedOverlay.addOverlay(overlayitem);
             mapView.getOverlays().add(itemizedOverlay);
		}
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
    
    
    /**
     * Does the actual drawing of the route, based on the geo points provided in the nav set
     *
     * @param navSet     Navigation set bean that holds the route information, incl. geo pos
     * @param color      Color in which to draw the lines
     * @param mMapView01 Map view to draw onto
     */
    public void drawPath(NavigationDataSet navSet, int color, MapView mMapView01) {
        Log.d("", "map color before: " + color);        

        // color correction for dining, make it darker
        if (color == Color.parseColor("#add331")) color = Color.parseColor("#6C8715");
        Log.d("", "map color after: " + color);

        Collection overlaysToAddAgain = new ArrayList();
        for (Iterator iter = mMapView01.getOverlays().iterator(); iter.hasNext();) {
            Object o = iter.next();
            Log.d("", "overlay type: " + o.getClass().getName());
            if (!RouteOverlay.class.getName().equals(o.getClass().getName())) {
                // mMapView01.getOverlays().remove(o);
                overlaysToAddAgain.add(o);
            }
        }
        mMapView01.getOverlays().clear();
        mMapView01.getOverlays().addAll(overlaysToAddAgain);

        String path = navSet.getRoutePlacemark().getCoordinates();
        Log.d("", "path=" + path);
        if (path != null && path.trim().length() > 0) {
            String[] pairs = path.trim().split(" ");

            Log.d("", "pairs.length=" + pairs.length);

            String[] lngLat = pairs[0].split(","); // lngLat[0]=longitude lngLat[1]=latitude lngLat[2]=height

            Log.d("", "lnglat =" + lngLat + ", length: " + lngLat.length);

            if (lngLat.length<3) lngLat = pairs[1].split(","); // if first pair is not transferred completely, take seconds pair //TODO 

            try {
            	WeihnachtsmarktGeoPoint startGP = new WeihnachtsmarktGeoPoint((int) (Double.parseDouble(lngLat[1]) * 1E6), (int) (Double.parseDouble(lngLat[0]) * 1E6));
                mMapView01.getOverlays().add(new RouteOverlay(startGP, startGP, 1));
                WeihnachtsmarktGeoPoint gp1;
                WeihnachtsmarktGeoPoint gp2 = startGP;

                for (int i = 1; i < pairs.length; i++) // the last one would be crash
                {
                    lngLat = pairs[i].split(",");

                    gp1 = gp2;

                    if (lngLat.length >= 2 && gp1.getLatitudeE6() > 0 && gp1.getLongitudeE6() > 0
                            && gp2.getLatitudeE6() > 0 && gp2.getLongitudeE6() > 0) {

                        // for GeoPoint, first:latitude, second:longitude
                        gp2 = new WeihnachtsmarktGeoPoint((int) (Double.parseDouble(lngLat[1]) * 1E6), (int) (Double.parseDouble(lngLat[0]) * 1E6));

                        if (gp2.getLatitudeE6() != 22200000) { 
                            mMapView01.getOverlays().add(new RouteOverlay(gp1, gp2, 2, color));
                            Log.d("", "draw:" + gp1.getLatitudeE6() + "/" + gp1.getLongitudeE6() + " TO " + gp2.getLatitudeE6() + "/" + gp2.getLongitudeE6());
                        }
                    }
                    // Log.d(myapp.APP,"pair:" + pairs[i]);
                }
                //routeOverlays.add(new RouteOverlay(gp2,gp2, 3));
                mMapView01.getOverlays().add(new RouteOverlay(gp2, gp2, 3));
            } catch (NumberFormatException e) {
                Log.e("", "Cannot draw route.", e);
            }
        }
        // mMapView01.getOverlays().addAll(routeOverlays); // use the default color
        mMapView01.setEnabled(true);
    }
        
    private void initPrefs() {    	
		radiusValuePreference = WeihnachtsmarktPreference.getInstance(this).getPreferencesInt(PREFERENCE_KEY_RADIUS_VALUE, 3000);
		enableRadiusPreference = WeihnachtsmarktPreference.getInstance(this).getPreferencesBoolean(PREFERENCE_KEY_RADIUS_ENABLED, true);
	} 
    
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