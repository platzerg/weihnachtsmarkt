package com.platzerworld.weihnachtsmarkt.ui;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import org.json.JSONArray;
import org.json.JSONObject;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Point;
import android.graphics.Typeface;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.android.maps.GeoPoint;
import com.google.android.maps.MapActivity;
import com.google.android.maps.MapController;
import com.google.android.maps.MapView;
import com.google.android.maps.Overlay;
import com.platzerworld.weihnachtsmarkt.R;
import com.platzerworld.weihnachtsmarkt.common.ConstantsIF;
import com.platzerworld.weihnachtsmarkt.common.location.LocationHelper;
import com.platzerworld.weihnachtsmarkt.common.location.WeihnachtsmarktLocationManager;
import com.platzerworld.weihnachtsmarkt.common.logging.LOG;
import com.platzerworld.weihnachtsmarkt.common.map.json.RoutePathOverlay;
import com.platzerworld.weihnachtsmarkt.common.map.overlay.WeihnachtsmarktGeoPoint;
import com.platzerworld.weihnachtsmarkt.common.map.overlay.WeihnachtsmarktItemizedOverlay;
import com.platzerworld.weihnachtsmarkt.common.map.overlay.WeihnachtsmarktOwnLocationOverlay;
import com.platzerworld.weihnachtsmarkt.common.preference.WeihnachtsmarktPreference;
import com.platzerworld.weihnachtsmarkt.common.style.StyleManager;
import com.platzerworld.weihnachtsmarkt.db.weihnachtsmarkt.WeihnachtsmarktSpeicher;
import com.platzerworld.weihnachtsmarkt.vo.WeihnachtsmarktVO;

public class WeihnachtsmarktFindenActivity extends MapActivity implements ConstantsIF{
	private static final String TAG = "BiergartenActivity";
	private static final long serialVersionUID = -6754055149216909895L;
	
	private int radiusValuePreference;
	
	private WeihnachtsmarktSpeicher mBiergartenSpeicher;
	
	private WeihnachtsmarktVO biergartenVO;
	private Address biergartenAdress;
	
	private MapView mapView;
	int latE6 = (int) (48.185771 * 1e6);
    int lonE6 = (int) (11.620338 * 1e6);
    
    private MapController mapController;
	private WeihnachtsmarktItemizedOverlay itemizedoverlay;
	private WeihnachtsmarktOwnLocationOverlay myLocationOverlay;
	private WeihnachtsmarktItemizedOverlay itemizedOverlay;
	
	private String url = null;
	private Location gpsLocation;
	
	private TextView textViewTitle;
	
	private EditText mEditTextName;
	private Button mButtonBiergartenSuche;
	
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.biergarten_finden);
        
        textViewTitle = (TextView) findViewById(R.id.txt_biergarten_finden_title);
        
        init();
        
        initPrefs();
        initBiergartenMap();
		initGPSLocation();		
		initBiergartenOwnLocationOverlay();		
		// sucheMaps();
		
		mapView.invalidate();
    }
    
    @Override
	protected void onStart() {
		super.onStart();
	}

	@Override
	protected void onResume() {
		Log.v(TAG, "onResume");
		super.onResume();
	}
	
	@Override
	protected void onPause() {
		LOG.v(TAG, "onPause");
		super.onPause();
	}

	@Override
	protected void onStop() {
		Log.v(TAG, "onStop");
		super.onStop();
	}
	
	@Override
	protected void onRestart() {
		Log.v(TAG, "onRestart");
		super.onRestart();
	}

	@Override
	protected void onDestroy() {
		Log.v(TAG, "onDestroy");
		cleanDatabase();
		super.onDestroy();
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.biergarten_finden_option_menu, menu);
		return true;
	}
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		return applyMenuChoice(item);
	}

	private boolean applyMenuChoice(MenuItem item) {
		switch (item.getItemId()) {
		case R.id.opt_biergarten_finden_route:
			startRoute();
			return true;	
		case R.id.opt_biergarten_finden_uebernehmen:
			uebernehmen();
			return true;
		default:
			return super.onOptionsItemSelected(item);
		}
	}
	
	private void startRoute(){
		Location lastKnownLocation = WeihnachtsmarktLocationManager.getInstance(this).getLastKnownLocation();
	    
	    Uri uri =Uri .parse(LocationHelper.getUri(lastKnownLocation.getLatitude(), lastKnownLocation.getLongitude(),
	    		Double.valueOf(biergartenVO.getLatitude()), Double.valueOf(biergartenVO.getLongitude())));
	     
	    //Uri uri =Uri.parse("http://maps.google.com/maps?&saddr=13.042206,80.17000&daddr=9.580000,78.100000");
	    Intent intent = new Intent(Intent.ACTION_VIEW, uri);
	    startActivity(intent);
	}
	
	
	private void uebernehmen(){
		mBiergartenSpeicher = new WeihnachtsmarktSpeicher(this);			
		biergartenVO.id = mBiergartenSpeicher.speichereBiergarten(biergartenVO);
		this.finish();
	}
	
	@Override
	public void finish() {
		Intent data = new Intent();
		data.putExtra(INTENT_EXTRA_BIERGARTEN_FINDEN_RESULT, biergartenVO);
		setResult(RESULT_OK, data);
		
		super.finish();
	}
	
	private void initBiergartenOwnLocationOverlay(){
    	myLocationOverlay = new WeihnachtsmarktOwnLocationOverlay(this, mapView, new WeihnachtsmarktGeoPoint( (int)( gpsLocation.getLatitude() * 1e6), (int) (gpsLocation.getLongitude() * 1e6)), false);
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
    	mapView = (MapView) findViewById(R.id.map_view_finden);        
        mapController = mapView.getController();
        mapView.setBuiltInZoomControls(true);		
		mapController = mapView.getController();
		mapController.setZoom(13);
    }
    
    private void initGPSLocation(){
    	gpsLocation = WeihnachtsmarktLocationManager.getInstance(this).getLastKnownLocation();
    }
        
    public void sucheMaps(){
    	Geocoder geoCoder = new Geocoder(this, Locale.GERMAN);
    	List<Address> addresses = null;
    	WeihnachtsmarktGeoPoint biergartenGeoPoint;
		try {
			addresses = geoCoder.getFromLocationName(mEditTextName.getText().toString(),5);
			 
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		if(null != addresses && addresses.size() > 0){
			biergartenVO = new WeihnachtsmarktVO();
			biergartenAdress = addresses.get(0);
			biergartenVO.name = biergartenAdress.getFeatureName();
			biergartenVO.strasse = biergartenAdress.getThoroughfare() + " " +biergartenAdress.getSubThoroughfare();
			biergartenVO.plz = biergartenAdress.getPostalCode();
			biergartenVO.ort = biergartenAdress.getLocality();
			biergartenVO.latitude = String.valueOf(biergartenAdress.getLatitude());
			biergartenVO.longitude = String.valueOf(biergartenAdress.getLongitude());
			biergartenVO.favorit = true;
			
			biergartenGeoPoint = new WeihnachtsmarktGeoPoint( (int) ( biergartenAdress.getLatitude() * 1E6),   (int) (biergartenAdress.getLongitude() * 1E6), biergartenVO);
		       url = getUrl(gpsLocation.getLatitude(), gpsLocation.getLongitude(), biergartenAdress.getLatitude(), biergartenAdress.getLongitude());

		       URL aUrl;
				try {
					aUrl = new URL(url);
				
		       final URLConnection conn = aUrl.openConnection();
		       conn.setReadTimeout(15 * 1000);  // timeout for reading the google maps data: 15 secs
		       conn.connect();
		       
		       
		       InputStream is = null;
		       is = aUrl.openStream();
		       
		       BufferedReader reader = new BufferedReader(new InputStreamReader(is, "iso-8859-1"), 8);
		       StringBuilder sb = new StringBuilder();
		       sb.append(reader.readLine() + "\n");
		       String line = "0";
		       while ((line = reader.readLine()) != null) {
		           sb.append(line + "\n");
		       }
		       is.close();
		       reader.close();
		       String result = sb.toString();
		       JSONObject jsonObject = new JSONObject(result);
		       JSONArray routeArray = jsonObject.getJSONArray("routes");
		       JSONObject routes = routeArray.getJSONObject(0);
		       JSONObject overviewPolylines = routes.getJSONObject("overview_polyline");
		       String encodedString = overviewPolylines.getString("points");
		       List<GeoPoint> pointToDraw = decodePoly(encodedString);

		       //Added line:
		       mapView.getOverlays().add(new RoutePathOverlay(pointToDraw));
		       
				} catch (Exception e) {
					LOG.v(TAG, e.getMessage());
				}
		}
   }
   
    private List<GeoPoint> decodePoly(String encoded) {

        List<GeoPoint> poly = new ArrayList<GeoPoint>();
        int index = 0, len = encoded.length();
        int lat = 0, lng = 0;

        while (index < len) {
            int b, shift = 0, result = 0;
            do {
                b = encoded.charAt(index++) - 63;
                result |= (b & 0x1f) << shift;
                shift += 5;
            } while (b >= 0x20);
            int dlat = ((result & 1) != 0 ? ~(result >> 1) : (result >> 1));
            lat += dlat;

            shift = 0;
            result = 0;
            do {
                b = encoded.charAt(index++) - 63;
                result |= (b & 0x1f) << shift;
                shift += 5;
            } while (b >= 0x20);
            int dlng = ((result & 1) != 0 ? ~(result >> 1) : (result >> 1));
            lng += dlng;

            GeoPoint p = new GeoPoint((int) (((double) lat / 1E5) * 1E6), (int) (((double) lng / 1E5) * 1E6));
            poly.add(p);
        }

        return poly;
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
    
   
    private void init(){
		initWidgets();
		initStyle();
		initListener();
		initContextMenu();
		initDatabase();
	}
    
    private void initPrefs() {    	
		radiusValuePreference = WeihnachtsmarktPreference.getInstance(this).getPreferencesInt(PREFERENCE_KEY_RADIUS_VALUE, 3000);
	}
	
	private void initStyle() {
		Typeface font = StyleManager.getInstance().init(this).getTypeface();
		textViewTitle.setTypeface(font);			
		mEditTextName.setBackgroundColor(getResources().getColor(R.color.color_eingabe_text));
	}
	
	private void initWidgets(){		
		mButtonBiergartenSuche = (Button) findViewById(R.id.btn_biergarten_suchen);		
		mEditTextName = (EditText) findViewById(R.id.edt_biergarten_finden_name);
	}
	
	private void initListener(){		
		mButtonBiergartenSuche.setOnClickListener(mBiergartenSucheClickListener);
	}
	
	private void initContextMenu(){
	}
	
	private void initDatabase(){
		
	}
	
	private void cleanDatabase(){
		if(null != mBiergartenSpeicher){
			mBiergartenSpeicher.schliessen();
		}
	}

	@Override
	protected boolean isRouteDisplayed() {
		return false;
	}
	
	private OnClickListener mBiergartenSucheClickListener = new OnClickListener() {
	    public void onClick(View v) {	    	
	      sucheBiergarten();
	    }
	};
	
	private void sucheBiergarten(){
		sucheMaps();
	}
	
	private class BiergartenMapOverlay extends Overlay {		
		public BiergartenMapOverlay(){
			
		}
		
	    public boolean draw(Canvas canvas, MapView mapView, boolean shadow, long when) 
	    {
	        super.draw(canvas, mapView, shadow);                   

	        //---translate the GeoPoint to screen pixels---
	        Point screenPts = new Point();
	        GeoPoint current = new GeoPoint( (int) (gpsLocation.getLatitude() * 1E6),  (int) (gpsLocation.getLatitude() * 1E6) );
	        mapView.getProjection().toPixels(current, screenPts);

	        //---add the marker---
	        Bitmap bmp = BitmapFactory.decodeResource(getResources(), R.drawable.bierundbreze);            
	        canvas.drawBitmap(bmp, screenPts.x, screenPts.y-32, null);         
	        return true;
	    }
	} 
	
}