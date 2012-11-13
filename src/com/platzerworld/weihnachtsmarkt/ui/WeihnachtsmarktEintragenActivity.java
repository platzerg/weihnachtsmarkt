package com.platzerworld.weihnachtsmarkt.ui;


import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Typeface;
import android.location.Location;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.platzerworld.weihnachtsmarkt.R;
import com.platzerworld.weihnachtsmarkt.common.ConstantsIF;
import com.platzerworld.weihnachtsmarkt.common.location.WeihnachtsmarktLocationManager;
import com.platzerworld.weihnachtsmarkt.common.logging.LOG;
import com.platzerworld.weihnachtsmarkt.common.style.StyleManager;
import com.platzerworld.weihnachtsmarkt.db.weihnachtsmarkt.WeihnachtsmarktSpeicher;
import com.platzerworld.weihnachtsmarkt.vo.WeihnachtsmarktVO;

public class WeihnachtsmarktEintragenActivity extends Activity implements ConstantsIF{
	private static final String TAG = "BiergartenActivity";
	private static final long serialVersionUID = -6754055149216909895L;
	
	private boolean isEditMode = true;
	
	private WeihnachtsmarktSpeicher mBiergartenSpeicher;
	
	private WeihnachtsmarktVO biergartenVO;
	
	private TextView textViewTitle;
	private EditText mEditTextName;
	private EditText mEditTextStrasse;
	private EditText mEditTextPlz;
	private EditText mEditTextOrt;
	private EditText mEditTextTelefon;
	private EditText mEditTextEmail;
	private EditText mEditTextUrl;
	private EditText mEditTextDesc;
	
	private Button mButtonBiergartenSpeichern;
	
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.biergarten_eintragen_1);
        
        textViewTitle = (TextView) findViewById(R.id.txt_biergarten_eintragen_title);
        
		if(null == biergartenVO){
			biergartenVO = new WeihnachtsmarktVO();
		}
		
        init();
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
    
    private void init(){
    	initWidgets();
		initStyle();
		initListener();
		initContextMenu();
		initDatabase();
		bindWidgets();
	}
	
    private void initWidgets(){		
		mEditTextName = (EditText) findViewById(R.id.edt_biergarten_eintragen_name);	
		mEditTextName.setEnabled(isEditMode);
		
		mEditTextStrasse = (EditText) findViewById(R.id.edt_biergarten_eintragen_strasse);	
		mEditTextStrasse.setEnabled(isEditMode);
		
		mEditTextPlz = (EditText) findViewById(R.id.edt_biergarten_eintragen_plz);	
		mEditTextPlz.setEnabled(isEditMode);
		
		mEditTextOrt = (EditText) findViewById(R.id.edt_biergarten_eintragen_ort);	
		mEditTextOrt.setEnabled(isEditMode);
		
		mEditTextTelefon = (EditText) findViewById(R.id.edt_biergarten_eintragen_telefon);	
		mEditTextTelefon.setEnabled(isEditMode);
		
		mEditTextEmail = (EditText) findViewById(R.id.edt_biergarten_eintragen_email);	
		mEditTextEmail.setEnabled(isEditMode);
		
		mEditTextUrl = (EditText) findViewById(R.id.edt_biergarten_eintragen_url);	
		mEditTextUrl.setEnabled(isEditMode);
		
		mEditTextDesc = (EditText) findViewById(R.id.edt_biergarten_eintragen_desc);	
		mEditTextDesc.setEnabled(isEditMode);
		
		if(!isEditMode){
			mEditTextName.setBackgroundColor(getResources().getColor(R.color.color_transparent));
			mEditTextStrasse.setBackgroundColor(getResources().getColor(R.color.color_transparent));
			mEditTextPlz.setBackgroundColor(getResources().getColor(R.color.color_transparent));
			mEditTextOrt.setBackgroundColor(getResources().getColor(R.color.color_transparent));
			mEditTextTelefon.setBackgroundColor(getResources().getColor(R.color.color_transparent));
			mEditTextEmail.setBackgroundColor(getResources().getColor(R.color.color_transparent));
			mEditTextUrl.setBackgroundColor(getResources().getColor(R.color.color_transparent));
			mEditTextDesc.setBackgroundColor(getResources().getColor(R.color.color_transparent));
			
			mEditTextName.setFocusable(false);
			mEditTextStrasse.setFocusable(false);
			mEditTextPlz.setFocusable(false);
			mEditTextOrt.setFocusable(false);
			mEditTextTelefon.setFocusable(false);
			mEditTextEmail.setFocusable(false);
			mEditTextUrl.setFocusable(false);
			mEditTextDesc.setFocusable(false);
		}
		
		mButtonBiergartenSpeichern = (Button) findViewById(R.id.btn_biergarten_speichern);
		
	}
	
	private void initStyle() {
		Typeface font = StyleManager.getInstance().init(this).getTypeface();
		textViewTitle.setTypeface(font);		
	}
	
	private void initListener(){	
		mButtonBiergartenSpeichern.setOnClickListener(mBiergartenSpeichernClickListener);
	}
	
	private void bindWidgets(){
		mEditTextName.setText(biergartenVO.name);
		mEditTextStrasse.setText(biergartenVO.strasse);
		mEditTextPlz.setText(biergartenVO.plz);
		mEditTextOrt.setText(biergartenVO.ort);
		mEditTextTelefon.setText(biergartenVO.telefon);
		mEditTextEmail.setText(biergartenVO.email);
		mEditTextUrl.setText(biergartenVO.url);
		mEditTextDesc.setText(biergartenVO.desc);
		
		if(!isEditMode){
			// textViewTitle.setText(getResources().getString(R.string.text_biergarten_uebersicht_title));
		}else{
			// textViewTitle.setText(getResources().getString(R.string.txt_spieler_bearbeiten_titel));
		}
		
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
	
	private OnClickListener mGetraenkeClickListener = new OnClickListener() {
	    public void onClick(View v) {	    	
	      showGetraenke();
	    }
	};
	
	private void showGetraenke(){
		if(validate()){
			final Intent intentBiergartenGetraenke = new Intent(this, WeihnachtsmarktGetraenkeActivity.class);
			intentBiergartenGetraenke.putExtra(INTENT_EXTRA_BIERGARTEN_DETAILS_BIERGARTENVO, biergartenVO);
			startActivity(intentBiergartenGetraenke);
		}
	}
	
	private OnClickListener mSpeisenClickListener = new OnClickListener() {
	    public void onClick(View v) {	    	
	      showSpeisen();
	    }
	};
	
	private void showSpeisen(){
		if(validate()){
			final Intent intentBiergartenSpeisen = new Intent(this, WeihnachtsmarktSpeisenActivity.class);
			intentBiergartenSpeisen.putExtra(INTENT_EXTRA_BIERGARTEN_DETAILS_BIERGARTENVO, biergartenVO);
			startActivity(intentBiergartenSpeisen);

		}
	}
	
	private OnClickListener mBiergartenSpeichernClickListener = new OnClickListener() {
	    public void onClick(View v) {	    	
	    	speichernBiergarten();
	    }
	};
	
	private boolean speichernBiergarten(){
		if(validate()){
			mBiergartenSpeicher = new WeihnachtsmarktSpeicher(this);
						
			Location myLastLocation = WeihnachtsmarktLocationManager.getInstance(this).getLastKnownLocation();
			if(null != myLastLocation){
				double lat = myLastLocation.getLatitude();
				double lon = myLastLocation.getLongitude();
				biergartenVO.latitude = String.valueOf(lat);
				biergartenVO.longitude = String.valueOf(lon);
				biergartenVO.favorit = true;
				biergartenVO.id = mBiergartenSpeicher.speichereBiergarten(biergartenVO);
				finish();
				return true;
			}
		}
		return false;
	}
	
	private boolean validate(){		
		if(mEditTextName.getText().length() <= 0){
			Toast.makeText(this, "Der Name nuss eingetragen werden!", Toast.LENGTH_SHORT).show();
			return false;
		}else{
			biergartenVO.name = mEditTextName.getText().toString();
		}
		if(mEditTextStrasse.getText().length() > 0){
			biergartenVO.strasse = mEditTextStrasse.getText().toString();
		}
		if(mEditTextPlz.getText().length() > 0){
			biergartenVO.plz = mEditTextPlz.getText().toString();
		}
		if(mEditTextOrt.getText().length() > 0){
			biergartenVO.ort = mEditTextOrt.getText().toString();
		}
		if(mEditTextEmail.getText().length() > 0){
			biergartenVO.email = mEditTextEmail.getText().toString();
		}
		if(mEditTextUrl.getText().length() > 0){
			biergartenVO.url = mEditTextUrl.getText().toString();
		}
		if(mEditTextDesc.getText().length() > 0){
			biergartenVO.desc = mEditTextDesc.getText().toString();
		}
		return true;
	}
	
	@Override
	public void finish() {
		if(!isEditMode || !biergartenVO.istNeu()){			
			// final Intent intentBiergartenSpeisen = new Intent(this, BiergartenSpeisenActivity.class);
			final Intent intentBiergartenSpeisen = new Intent();
			intentBiergartenSpeisen.putExtra(INTENT_EXTRA_BIERGARTEN_EINTRAGEN_RESULT, biergartenVO);
			setResult(RESULT_OK, intentBiergartenSpeisen);
			
			super.finish();
		}
		else{
			AlertDialog.Builder builder = new AlertDialog.Builder(this);
			builder.setMessage("Biergarten speichern?").setCancelable(false)
			       .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
			           public void onClick(DialogInterface dialog, int id) {
			        	   isEditMode = false;
			        	   dialog.cancel();
			        	   if(speichernBiergarten()){
			        		   WeihnachtsmarktEintragenActivity.this.finish();
			        	   }else{
			        		   isEditMode = true;
			        	   }
			           }
			       })
			       .setNegativeButton("No", new DialogInterface.OnClickListener() {
			           public void onClick(DialogInterface dialog, int id) {
			        	   isEditMode = false;
			        	   dialog.cancel();
			        	   
			        	   WeihnachtsmarktEintragenActivity.this.finish();
			           }
			       });
			AlertDialog alertDialog = builder.create();
			alertDialog.show();
		}
    	
	}
}