package com.platzerworld.weihnachtsmarkt.common.map.overlay;

import java.util.ArrayList;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.view.View;

import com.google.android.maps.ItemizedOverlay;
import com.google.android.maps.OverlayItem;
import com.platzerworld.weihnachtsmarkt.R;
import com.platzerworld.weihnachtsmarkt.common.ConstantsIF;
import com.platzerworld.weihnachtsmarkt.ui.WeihnachtsmarktDetailActivity;
import com.platzerworld.weihnachtsmarkt.vo.WeihnachtsmarktVO;

public class WeihnachtsmarktItemizedOverlay extends ItemizedOverlay<OverlayItem> implements ConstantsIF{
	private static final long serialVersionUID = -6346528233185602164L;

	private ArrayList<OverlayItem> mapOverlays = new ArrayList<OverlayItem>();
   
   private Activity context;
   private View customTitle;
   private View customView;
   
   public WeihnachtsmarktItemizedOverlay(Drawable defaultMarker) {
        super(boundCenterBottom(defaultMarker));
   }
   
   public WeihnachtsmarktItemizedOverlay(Drawable defaultMarker, Context context) {
	   super(boundCenterBottom(defaultMarker));
        this.context = (Activity) context;
   }

   @Override
   protected OverlayItem createItem(int i) {
      return mapOverlays.get(i);
   }

   @Override
   public int size() {
      return mapOverlays.size();
   }
   
   @Override
   protected boolean onTap(int index) {
      OverlayItem item = mapOverlays.get(index);
      final int idx = index;
      if(item.getPoint() instanceof WeihnachtsmarktGeoPoint){
    	  final WeihnachtsmarktVO biergartenVO = ((WeihnachtsmarktGeoPoint) item.getPoint()).getBiergartenVO();
    	  
    	  AlertDialog.Builder dialog = new AlertDialog.Builder(context);
          dialog.setPositiveButton("anzeigen", new DialogInterface.OnClickListener() {
              public void onClick(DialogInterface dialog, int id) {
            	   
                   Intent intentBiergartenDetails = new Intent(context, WeihnachtsmarktDetailActivity.class);
                   intentBiergartenDetails.putExtra(INTENT_EXTRA_BIERGARTEN_DETAILS_MODUS, false);  
                   intentBiergartenDetails.putExtra(INTENT_EXTRA_BIERGARTEN_DETAILS_BIERGARTENVO, biergartenVO);   
                   intentBiergartenDetails.putExtra(INTENT_EXTRA_BIERGARTEN_DETAILS_OVERLAY_INDEX, idx);    
                   context.startActivityForResult(intentBiergartenDetails, REQUEST_CODE_BIERGARTEN_DETAIL);
                   dialog.cancel();
              }
          }).setNegativeButton("OK",new DialogInterface.OnClickListener() {
    			public void onClick(DialogInterface dialog,int id) {
    				dialog.cancel();
    			}
    		});

          
          if(null != customView){
        	  dialog.setView(customView);
          }else{
        	  dialog.setMessage(item.getSnippet());
              Drawable drawable = context.getResources().getDrawable(R.drawable.bierundbreze);
              dialog.setIcon(drawable);   
              
              if(null != customTitle){
            	  dialog.setCustomTitle(this.customTitle);  
              }else{
            	  dialog.setTitle(item.getTitle());
              }
          }
          
          // ((BiergartenUebersichtActivity)context).di();
          dialog.show();
          return true;
      }
      
      return false;
      
     
   }
   
   public void addOverlay(OverlayItem overlay) {
      mapOverlays.add(overlay);
       this.populate();
   }
}