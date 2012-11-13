package com.platzerworld.weihnachtsmarkt.ui;


import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.location.Location;
import android.net.Uri;
import android.os.Bundle;
import android.text.util.Linkify;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import com.platzerworld.weihnachtsmarkt.R;
import com.platzerworld.weihnachtsmarkt.common.ConstantsIF;
import com.platzerworld.weihnachtsmarkt.common.location.WeihnachtsmarktLocationManager;
import com.platzerworld.weihnachtsmarkt.common.logging.LOG;
import com.platzerworld.weihnachtsmarkt.common.style.StyleManager;
import com.platzerworld.weihnachtsmarkt.db.weihnachtsmarkt.WeihnachtsmarktSpeicher;
import com.platzerworld.weihnachtsmarkt.vo.WeihnachtsmarktVO;

public class WeihnachtsmarktDetailActivity extends Activity implements ConstantsIF{
	private static final String TAG = "BiergartenActivity";
	private static final long serialVersionUID = -6754055149216909895L;
	
	private boolean isEditMode = false;
	
	private WeihnachtsmarktVO biergartenVO;
	
	private Menu menu;
	
	private TextView textViewTitle;
	private EditText mEditTextStrasse;
	private EditText mEditTextPlz;
	private EditText mEditTextOrt;
	private EditText mEditTextTelefon;
	private EditText mEditTextEmail;
	private EditText mEditTextUrl;
	private EditText mEditTextDesc;
	
	private ImageButton mImageButtonSpeisen;
	private ImageButton mImageButtonGetraenke;
	private WeihnachtsmarktSpeicher mBiergartenSpeicher;
	
	private int overlayIdx = 0;
	private boolean isSaved = false;
	
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.biergarten_detail_1);
        textViewTitle = (TextView) findViewById(R.id.txt_biergarten_details_title);
        
        Bundle extras = getIntent().getExtras();
		if (extras == null) {
			return;
		}
		
		overlayIdx = extras.getInt(INTENT_EXTRA_BIERGARTEN_DETAILS_OVERLAY_INDEX);
		
		
		
		
		isEditMode = extras.getBoolean(INTENT_EXTRA_BIERGARTEN_DETAILS_MODUS, false);
		
		biergartenVO =  (WeihnachtsmarktVO) extras.getSerializable(INTENT_EXTRA_BIERGARTEN_DETAILS_BIERGARTENVO);
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
    
    @Override
	public void finish() {
    	Intent data = new Intent();
		data.putExtra(INTENT_EXTRA_BIERGARTEN_DETAILS_RESULT, biergartenVO);
		data.putExtra(INTENT_EXTRA_BIERGARTEN_DETAILS_OVERLAY_INDEX, overlayIdx);
		setResult(RESULT_OK, data);
		
				
		if(!isEditMode){
			super.finish();
		} else if(!isSaved){
			AlertDialog.Builder builder = new AlertDialog.Builder(this);
			builder.setMessage("Biergarten speichern?").setCancelable(false)
			       .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
			           public void onClick(DialogInterface dialog, int id) {
			        	   dialog.cancel();
			        	   speichernBiergarten();
			        	   isEditMode = false;
			        	   WeihnachtsmarktDetailActivity.this.finish();
			           }
			       })
			       .setNegativeButton("No", new DialogInterface.OnClickListener() {
			           public void onClick(DialogInterface dialog, int id) {
			        	   isEditMode = false;
			        	   dialog.cancel();
			        	   
			        	   WeihnachtsmarktDetailActivity.this.finish();
			           }
			       });
			AlertDialog alertDialog = builder.create();
			alertDialog.show();
		}
	}
    
    @Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.biergarten_details_option_menu, menu);
		this.menu = menu;
		return true;
	}
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		return applyMenuChoice(item);
	}
	
	@Override
	public boolean onPrepareOptionsMenu (Menu menu) {
		menu.getItem(1).setVisible(isEditMode);
	    return true;
	}

	private boolean applyMenuChoice(MenuItem item) {
		switch (item.getItemId()) {
		case R.id.opt_biergarten_details_editmodus:
			startEditModus();
			return true;	
		case R.id.opt_biergarten_details_speichern:
			speichernBiergarten();
			return true;	
		case R.id.opt_biergarten_details_route:
			showRoute();
			return true;	
		default:
			return super.onOptionsItemSelected(item);
		}
	}
	
	private void startEditModus(){
		isEditMode = true;
		menu.getItem(1).setVisible(isEditMode);
		init();
	}
	
	private void showRoute(){
		Location lastKnownLocation = WeihnachtsmarktLocationManager.getInstance(this).getLastKnownLocation();
	    
	    Uri uri =Uri.parse(getUri(lastKnownLocation.getLatitude(), lastKnownLocation.getLongitude(),
	    		Double.valueOf(biergartenVO.getLatitude()), Double.valueOf(biergartenVO.getLongitude())));
	     
	    //Uri uri =Uri.parse("http://maps.google.com/maps?&saddr=13.042206,80.17000&daddr=9.580000,78.100000");
	    Intent intent = new Intent(Intent.ACTION_VIEW, uri);
	    startActivity(intent);
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

	private void speichernBiergarten(){
		if(validate()){
			mBiergartenSpeicher = new WeihnachtsmarktSpeicher(this);			
			biergartenVO.id = mBiergartenSpeicher.speichereBiergarten(biergartenVO);
			isSaved = true;
			isEditMode = false;
			init();
		}
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
		mEditTextStrasse = (EditText) findViewById(R.id.edt_biergarten_details_strasse);	
		mEditTextPlz = (EditText) findViewById(R.id.edt_biergarten_details_plz);		
		mEditTextOrt = (EditText) findViewById(R.id.edt_biergarten_details_ort);			
		mEditTextTelefon = (EditText) findViewById(R.id.edt_biergarten_details_telefon);			
		mEditTextEmail = (EditText) findViewById(R.id.edt_biergarten_details_email);
		mEditTextUrl = (EditText) findViewById(R.id.edt_biergarten_details_url);	
		mEditTextDesc = (EditText) findViewById(R.id.edt_biergarten_details_desc);			
				
		setAppearance();
				
		mImageButtonSpeisen = (ImageButton) findViewById(R.id.img_button_biergarten_details_speisen);
		mImageButtonGetraenke = (ImageButton) findViewById(R.id.img_button_biergarten_details_getraenke);
	}
	
	private void setAppearance(){
		int color = 0;
		mEditTextStrasse.setEnabled(isEditMode);
		mEditTextPlz.setEnabled(isEditMode);
		mEditTextOrt.setEnabled(isEditMode);
		mEditTextTelefon.setEnabled(isEditMode);
		mEditTextEmail.setEnabled(isEditMode);
		mEditTextUrl.setEnabled(isEditMode);
		mEditTextDesc.setEnabled(isEditMode);
		
		if(isEditMode){
			color = getResources().getColor(R.color.color_eingabe_text);
		}else{
			color = getResources().getColor(R.color.color_transparent);
		}
		
		
		mEditTextStrasse.setBackgroundColor(color);
		mEditTextPlz.setBackgroundColor(color);
		mEditTextOrt.setBackgroundColor(color);
		mEditTextTelefon.setBackgroundColor(color);
		mEditTextEmail.setBackgroundColor(color);
		mEditTextUrl.setBackgroundColor(color);
		mEditTextDesc.setBackgroundColor(color);
	}
	
	private void initStyle() {
		textViewTitle.setTypeface(StyleManager.getInstance().getTypefaceDroidserifBold());	
		mEditTextStrasse.setTypeface(StyleManager.getInstance().getTypefaceDroidserifBold());
		mEditTextPlz.setTypeface(StyleManager.getInstance().getTypefaceDroidserifBold());
		mEditTextOrt.setTypeface(StyleManager.getInstance().getTypefaceDroidserifBold());
		mEditTextTelefon.setTypeface(StyleManager.getInstance().getTypefaceDroidserifBold());
		mEditTextEmail.setTypeface(StyleManager.getInstance().getTypefaceDroidserifBold());
		mEditTextUrl.setTypeface(StyleManager.getInstance().getTypefaceDroidserifBold());
		mEditTextDesc.setTypeface(StyleManager.getInstance().getTypefaceDroidserifBold());
	}
	
	private void initListener(){		
		mImageButtonSpeisen.setOnClickListener(mSpeisenClickListener);
		mImageButtonGetraenke.setOnClickListener(mGetraenkeClickListener);
	}
	
	private void bindWidgets(){
		textViewTitle.setText(biergartenVO.name);
		
		mEditTextStrasse.setText(biergartenVO.strasse);
		mEditTextPlz.setText(biergartenVO.plz);
		mEditTextOrt.setText(biergartenVO.ort);
		mEditTextTelefon.setText(biergartenVO.telefon);
		mEditTextEmail.setText(biergartenVO.email);
		mEditTextUrl.setText(biergartenVO.url);
		mEditTextDesc.setText(biergartenVO.desc);
		
		
		if(!isEditMode){
			Linkify.addLinks(mEditTextTelefon, Linkify.PHONE_NUMBERS);
			Linkify.addLinks(mEditTextEmail, Linkify.EMAIL_ADDRESSES);
			Linkify.addLinks(mEditTextUrl, Linkify.ALL);
		}else{
			Linkify.addLinks(mEditTextTelefon, Linkify.PHONE_NUMBERS);
			Linkify.addLinks(mEditTextEmail, Linkify.EMAIL_ADDRESSES);
			Linkify.addLinks(mEditTextUrl, Linkify.ALL);
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
			intentBiergartenGetraenke.putExtra(INTENT_EXTRA_BIERGARTEN_DETAILS_MODUS, isEditMode);  
			startActivityForResult(intentBiergartenGetraenke, REQUEST_CODE_BIERGARTEN_DETAIL_GETRAENKE);
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
			intentBiergartenSpeisen.putExtra(INTENT_EXTRA_BIERGARTEN_DETAILS_MODUS, isEditMode);  
			startActivityForResult(intentBiergartenSpeisen, REQUEST_CODE_BIERGARTEN_DETAIL_SPEISEN);

		}
	}
	
	private boolean validate(){		
		if(mEditTextStrasse.getText().length() > 0){
			biergartenVO.strasse = mEditTextStrasse.getText().toString();
		}
		if(mEditTextPlz.getText().length() > 0){
			biergartenVO.plz = mEditTextPlz.getText().toString();
		}
		if(mEditTextOrt.getText().length() > 0){
			biergartenVO.ort = mEditTextOrt.getText().toString();
		}
		if(mEditTextTelefon.getText().length() > 0){
			biergartenVO.telefon = mEditTextTelefon.getText().toString();
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
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		if (resultCode == RESULT_OK && (requestCode == REQUEST_CODE_BIERGARTEN_DETAIL_GETRAENKE || requestCode == REQUEST_CODE_BIERGARTEN_DETAIL_SPEISEN )  ) {
			if (data.hasExtra(INTENT_EXTRA_BIERGARTEN_DETAILS_BIERGARTENVO)) {
				WeihnachtsmarktVO biergartenVO = (WeihnachtsmarktVO) data.getExtras().getSerializable(INTENT_EXTRA_BIERGARTEN_DETAILS_BIERGARTENVO);
				this.biergartenVO = biergartenVO;
				LOG.v(TAG,  "Back from " +biergartenVO.name);
			}
		}
	}
	
}