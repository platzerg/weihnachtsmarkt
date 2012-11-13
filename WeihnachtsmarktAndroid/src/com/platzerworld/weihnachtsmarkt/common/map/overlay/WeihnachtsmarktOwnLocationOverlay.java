package com.platzerworld.weihnachtsmarkt.common.map.overlay;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.graphics.Point;

import com.google.android.maps.MapView;
import com.google.android.maps.MyLocationOverlay;
import com.google.android.maps.Projection;
import com.platzerworld.weihnachtsmarkt.common.ConstantsIF;
import com.platzerworld.weihnachtsmarkt.common.preference.WeihnachtsmarktPreference;

public class WeihnachtsmarktOwnLocationOverlay extends MyLocationOverlay implements ConstantsIF{
	private static final long serialVersionUID = -8869266167228696846L;
	private Context context;
	private MapView mapView;
    private Paint circlePainter;
    private Point screenCurrentPoint;
	private WeihnachtsmarktGeoPoint geoCurrentPoint;
	private boolean enableRadiusPreference;
	
	private float radiusStrokeWidthPreference;
	private int radiusColorPreference;
	private int radiusAlphaWertPreference;
	
    private int meters;

    public WeihnachtsmarktOwnLocationOverlay(Context context, MapView mapView, WeihnachtsmarktGeoPoint currentPoint) {
    	this(context, mapView, currentPoint, true);
    }
    public WeihnachtsmarktOwnLocationOverlay(Context context, MapView mapView, WeihnachtsmarktGeoPoint currentPoint, boolean showRadius) {
		super(context, mapView);
		this.context = context;
		this.mapView = mapView;
		this.geoCurrentPoint = currentPoint;
		if(showRadius){
			this.initPrefs();
		}else{
			enableRadiusPreference = false;
		}
		
	}

	// This method is used to get user submitted radius from our application
    public void setMeters(int meters) {
    	this.meters = meters;
    }

    @Override
	public synchronized boolean draw(Canvas canvas, MapView mapView, boolean shadow, long when) {
    	// Set the painter to paint our circle. setColor = blue, setAlpha = 70 so the background
    	// can still be seen. Feel free to change these settings
    	if(this.enableRadiusPreference){
    		circlePainter = new Paint();
        	circlePainter.setAntiAlias(true);
        	circlePainter.setStrokeWidth(radiusStrokeWidthPreference);
        	circlePainter.setColor(radiusColorPreference);
        	circlePainter.setStyle(Style.FILL_AND_STROKE);
        	circlePainter.setAlpha(radiusAlphaWertPreference);
        	
        	// Get projection from the mapView. 
        	Projection projection = mapView.getProjection();
        	// Get current location

        	screenCurrentPoint = new Point();
        	// Project the gps coordinate to screen coordinate
        	projection.toPixels(geoCurrentPoint, screenCurrentPoint);
        	
        	int radius = metersToRadius(geoCurrentPoint.getLatitudeE6() /1000000);
        	// draw the blue circle
        	canvas.drawCircle(screenCurrentPoint.x, screenCurrentPoint.y, radius, circlePainter);
    	}
    	
        return super.draw(canvas, mapView, shadow, when);
    }
    
    // hack to get more accurate radius, because the accuracy is changing as the location
    // getting further away from the equator
	public int metersToRadius(double latitude) {
	    return (int) (mapView.getProjection().metersToEquatorPixels(meters) * (1/ Math.cos(Math.toRadians(latitude))));         
	}
	
	private void initPrefs() {		
		enableRadiusPreference = WeihnachtsmarktPreference.getInstance(context).getPreferencesBoolean(PREFERENCE_KEY_RADIUS_ENABLED);
		radiusStrokeWidthPreference = Float.parseFloat(WeihnachtsmarktPreference.getInstance(context).getPreferencesString(PREFERENCE_KEY_RADIUS_STROKE_WIDTH));
		radiusColorPreference = Integer.parseInt(WeihnachtsmarktPreference.getInstance(context).getPreferencesString(PREFERENCE_KEY_RADIUS_COLOR));
		radiusAlphaWertPreference = Integer.parseInt(WeihnachtsmarktPreference.getInstance(context).getPreferencesString(PREFERENCE_KEY_RADIUS_ALPHA));
	}
}