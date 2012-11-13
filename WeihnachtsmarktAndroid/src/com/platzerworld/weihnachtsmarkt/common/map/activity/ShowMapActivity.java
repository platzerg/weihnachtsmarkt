package com.platzerworld.weihnachtsmarkt.common.map.activity;


import java.util.List;

import android.graphics.drawable.Drawable;
import android.os.Bundle;

import com.google.android.maps.GeoPoint;
import com.google.android.maps.MapActivity;
import com.google.android.maps.MapController;
import com.google.android.maps.MapView;
import com.google.android.maps.Overlay;
import com.google.android.maps.OverlayItem;
import com.platzerworld.weihnachtsmarkt.R;
import com.platzerworld.weihnachtsmarkt.common.map.overlay.WeihnachtsmarktItemizedOverlay;

public class ShowMapActivity extends MapActivity {

private MapView mapView;
    
    int latE6 = (int) (48.185771 * 1e6);
    int lonE6 = (int) (11.620338 * 1e6);
    
    @Override
    public void onCreate(Bundle savedInstanceState) {
        
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.main);
        
        //mapView = (MapView) findViewById(R.id.map_view);       
        mapView.setBuiltInZoomControls(true);
       
        
        List<Overlay> mapOverlays = mapView.getOverlays();
        Drawable drawable = this.getResources().getDrawable(R.drawable.bierundbreze);
        WeihnachtsmarktItemizedOverlay itemizedOverlay =  new WeihnachtsmarktItemizedOverlay(drawable, this);
        
        GeoPoint point = new GeoPoint(latE6, lonE6);
        OverlayItem overlayitem = new OverlayItem(point, "Hello", "I'm in the beergarden!");
        
        itemizedOverlay.addOverlay(overlayitem);
        mapOverlays.add(itemizedOverlay);
        
        MapController mapController = mapView.getController();
        
        mapController.animateTo(point);
        mapController.setZoom(14);
        
    }

    @Override
    protected boolean isRouteDisplayed() {
        return false;
    }
    
}