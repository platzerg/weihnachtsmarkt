package com.platzerworld.weihnachtsmarkt.common.map.activity;

import java.util.List;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Point;
import android.os.Bundle;

import com.google.android.maps.GeoPoint;
import com.google.android.maps.MapActivity;
import com.google.android.maps.MapController;
import com.google.android.maps.MapView;
import com.google.android.maps.Overlay;
import com.google.android.maps.Projection;
import com.platzerworld.weihnachtsmarkt.R;

public class GoogleMapsDrawPath extends MapActivity {
	/** Called when the activity is first created. */
	private List mapOverlays;
	private Projection projection;
	private MapController mc;
	private MapView mapView;
	private GeoPoint gP;
	// private GeoPoint gP2;
	private MyOverlay myoverlay;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.biergarten_uebersicht);

		mapView = (MapView) findViewById(R.id.map_view);// Creating an instance
														// of MapView
		mapView.setBuiltInZoomControls(true);// Enabling the built-in Zoom
												// Controls

		gP = new GeoPoint(33695043, 73000000);// Creating a GeoPoint

		mc = mapView.getController();
		mc.setCenter(gP);
		mc.setZoom(9);// Initializing the MapController and setting the map to
						// center at the
		// defined GeoPoint

		mapOverlays = mapView.getOverlays();
		projection = mapView.getProjection();

		myoverlay = new MyOverlay();
		mapOverlays.add(myoverlay);

	}

	@Override
	protected boolean isRouteDisplayed() {
		// TODO Auto-generated method stub
		return false;
	}

	class MyOverlay extends Overlay {

		public MyOverlay() {

		}

		public void draw(Canvas canvas, MapView mapv, boolean shadow) {
			super.draw(canvas, mapv, shadow);
			// Configuring the paint brush
			Paint mPaint = new Paint();
			mPaint.setDither(true);
			mPaint.setColor(Color.RED);
			mPaint.setStyle(Paint.Style.FILL_AND_STROKE);
			mPaint.setStrokeJoin(Paint.Join.ROUND);
			mPaint.setStrokeCap(Paint.Cap.ROUND);
			mPaint.setStrokeWidth(4);

			GeoPoint gP1 = new GeoPoint(34159000, 73220000);// starting point
															// Abbottabad
			GeoPoint gP2 = new GeoPoint(33695043, 73050000);// End point
															// Islamabad

			GeoPoint gP4 = new GeoPoint(33695043, 73050000);// Start point
															// Islamabad
			GeoPoint gP3 = new GeoPoint(33615043, 73050000);// End Point
															// Rawalpindi

			Point p1 = new Point();
			Point p2 = new Point();
			Path path1 = new Path();

			Point p3 = new Point();
			Point p4 = new Point();
			Path path2 = new Path();
			projection.toPixels(gP2, p3);
			projection.toPixels(gP1, p4);

			path1.moveTo(p4.x, p4.y);// Moving to Abbottabad location
			path1.lineTo(p3.x, p3.y);// Path till Islamabad

			projection.toPixels(gP3, p1);
			projection.toPixels(gP4, p2);

			path2.moveTo(p2.x, p2.y);// Moving to Islamabad location
			path2.lineTo(p1.x, p1.y);// Path to Rawalpindi

			canvas.drawPath(path1, mPaint);// Actually drawing the path from
											// Abbottabad to Islamabad
			canvas.drawPath(path2, mPaint);// Actually drawing the path from
											// Islamabad to Rawalpindi

		}

	}
}