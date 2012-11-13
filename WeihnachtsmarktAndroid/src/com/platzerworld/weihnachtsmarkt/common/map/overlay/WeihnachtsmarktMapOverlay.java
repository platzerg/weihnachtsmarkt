package com.platzerworld.weihnachtsmarkt.common.map.overlay;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Point;

import com.google.android.maps.GeoPoint;
import com.google.android.maps.MapView;
import com.google.android.maps.Overlay;
import com.platzerworld.weihnachtsmarkt.R;

class WeihnachtsmarktMapOverlay extends Overlay {
	private Context context;
	private GeoPoint geoCurrentPoint;
	
	public WeihnachtsmarktMapOverlay(Context context, GeoPoint geoCurrentPoint){
		this.context = context;
		this.geoCurrentPoint = geoCurrentPoint;
	}
	
    public boolean draw(Canvas canvas, MapView mapView, boolean shadow, long when) 
    {
        super.draw(canvas, mapView, shadow);                   

        //---translate the GeoPoint to screen pixels---
        Point screenPts = new Point();
        mapView.getProjection().toPixels(geoCurrentPoint, screenPts);

        //---add the marker---
        Bitmap bmp = BitmapFactory.decodeResource(context.getResources(), R.drawable.bierundbreze);            
        canvas.drawBitmap(bmp, screenPts.x, screenPts.y-32, null);         
        return true;
    }
} 