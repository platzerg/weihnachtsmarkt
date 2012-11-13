package com.platzerworld.weihnachtsmarkt.ui;

import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Typeface;
import android.location.LocationManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.os.Environment;
import android.text.util.Linkify;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;
import au.com.bytecode.opencsv.CSVWriter;

import com.platzerworld.weihnachtsmarkt.R;
import com.platzerworld.weihnachtsmarkt.common.ConstantsIF;
import com.platzerworld.weihnachtsmarkt.common.logging.LOG;
import com.platzerworld.weihnachtsmarkt.common.network.WeihnachtsmarktNetworkManager;
import com.platzerworld.weihnachtsmarkt.common.preference.WeihnachtsmarktPreference;
import com.platzerworld.weihnachtsmarkt.common.style.StyleManager;
import com.platzerworld.weihnachtsmarkt.common.task.DownloadWebPageTask;
import com.platzerworld.weihnachtsmarkt.common.task.WeihnachtsmarktFavoritenTask;
import com.platzerworld.weihnachtsmarkt.db.WeihnachtsmarktGAEDatenbank;
import com.platzerworld.weihnachtsmarkt.db.weihnachtsmarkt.WeihnachtsmarktSpeicher;
import com.platzerworld.weihnachtsmarkt.ui.preference.EinstellungenBearbeiten;
import com.platzerworld.weihnachtsmarkt.vo.WeihnachtsmarktVO;

public class WeihnachtsmarktActivity extends Activity implements ConstantsIF{
	private static final long serialVersionUID = -6754055149216909895L;
	private static final String TAG = "WeihnachtsmarktActivity";
	
	private boolean mLoadDbPreference;
	private boolean mLoadDbFromCloudPreference;
	
	private ImageButton mImageButtonWeihnachtsmarktUebersicht;
	private ImageButton mImageButtonWeihnachtsmarktFinden;
	private ImageButton mImageButtonWeihnachtsmarktEintragen;
	private ImageButton mImageButtonWeihnachtsmarktFavoriten;
	
	private List<WeihnachtsmarktVO> mWeihnachtsmarktList = null;
	private List<WeihnachtsmarktVO> mWeihnachtsmarktFavoritenList = null;
	
	private WeihnachtsmarktSpeicher mWeihnachtsmarktSpeicher;
	
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
    	LOG.v(TAG, "onCreate");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.biergarten);
        
        init();
        ladeBiergaerten(); 
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
		getMenuInflater().inflate(R.menu.biergarten_option_menu, menu);
		return true;
	}
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		return applyMenuChoice(item);
	}

	private boolean applyMenuChoice(MenuItem item) {
		switch (item.getItemId()) {
		case R.id.opt_biergarten_einstellung:
			startEinstellungen();
			return true;
		case R.id.opt_biergarten_beenden:
			LOG.v(TAG, "opt_kegelverwaltung_beenden");
			finish();
			return true;
		case R.id.opt_biergarten_hilfe:
			startHilfe();
			return true;
		case R.id.opt_biergarten_export_csv:
			startExport();
			return true;
		default:
			return super.onOptionsItemSelected(item);
		}
	}
    
    private void init(){
    	initPrefs();
		initWidgets();
		initStyle();
		initListener();
		initContextMenu();
		initDatabase();
	}
	
	private void initStyle() {
		Typeface font = StyleManager.getInstance().init(this).getTypeface();
		// mButtonSchnittlisten.setTypeface(font);
	}
	
	private boolean isGPS(){
		LocationManager service = (LocationManager) getSystemService(LOCATION_SERVICE);
		boolean enabled = service.isProviderEnabled(LocationManager.GPS_PROVIDER);

		if (!enabled) {
		  return false;
		} 
		return true;
	}
	
	private void initWidgets(){	
		mImageButtonWeihnachtsmarktUebersicht = (ImageButton) this.findViewById(R.id.btn_biergarten_uebersicht);
		mImageButtonWeihnachtsmarktFinden = (ImageButton) this.findViewById(R.id.btn_biergarten_finden);
		mImageButtonWeihnachtsmarktEintragen = (ImageButton) this.findViewById(R.id.btn_biergarten_eintragen);
		mImageButtonWeihnachtsmarktFavoriten = (ImageButton) this.findViewById(R.id.btn_biergarten_favoriten);
	}
	
	private void initListener(){		
		mImageButtonWeihnachtsmarktUebersicht.setOnClickListener(mBiergartenUebersichtListener);
		mImageButtonWeihnachtsmarktFinden.setOnClickListener(mBiergartenFindenListener);
		mImageButtonWeihnachtsmarktEintragen.setOnClickListener(mBiergartenEintragenListener);
		mImageButtonWeihnachtsmarktFavoriten.setOnClickListener(mBiergartenFavoritenListener);
	}
	
	private void initContextMenu(){
	}
	
	private void initDatabase(){
		
	}
	
	private void cleanDatabase(){
		if(null != mWeihnachtsmarktSpeicher){
			mWeihnachtsmarktSpeicher.schliessen();
		}
	}
	
	private void startHilfe(){
		LOG.v(TAG, "opt_kegelverwaltung_hilfe");
		AlertDialog.Builder alert = new AlertDialog.Builder(this);
		alert.setTitle("Hilfeseite Kegelverwaltung App");
		
		final EditText linkText = new EditText(this);
		linkText.setText("http://biergarten-bayern.com/");
		Linkify.addLinks(linkText, Linkify.ALL);
		alert.setView(linkText);
		
		alert.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
		public void onClick(DialogInterface dialog, int whichButton) {}});
		alert.show();
	}
	
	private void startExport(){
		List<String[]> biergartenList = mWeihnachtsmarktSpeicher.ladeBiergartenAsStringArray();
		
		File f1 = Environment.getExternalStorageDirectory();
		boolean f2 = Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED);
		
		try {
		    File root = Environment.getExternalStorageDirectory();
		    if (root.canWrite()){
		    	File dir = new File(root, "weihnachtsmarkt");
		    	dir.mkdir();
		    	
		    	File biergartenFile = new File(dir, "weihnachtsmarkt.csv");
		    	
		    	CSVWriter writer = new CSVWriter(new FileWriter(biergartenFile), '#');
			     // feed in your array (or convert your data to an array)
		    	
		    	for (String[] zeile : biergartenList) {
		    		writer.writeNext(zeile);
				}
				 writer.close();
		    }
		} catch (IOException e) {
		    Log.e(TAG, "Could not write file " + e.getMessage());
		}
	}
	
	private void startEinstellungen(){
		LOG.v(TAG, "opt_kegelverwaltung_einstellung:");
		final Intent intentBiergartenEinstellungen = new Intent(this, EinstellungenBearbeiten.class);
		 startActivityForResult(intentBiergartenEinstellungen, REQUEST_CODE_BIERGARTEN_EINSTELLUNGEN);
	}
	
	private final OnClickListener mBiergartenUebersichtListener = new OnClickListener() {
		public void onClick(View v) {
			if(isGPS()){
				onClickBiergartenUebersicht(v);
			}else{
				Toast.makeText(WeihnachtsmarktActivity.this, "No GPS enabled!", Toast.LENGTH_SHORT).show();
			}
			
		}
	};
	
	public void onClickBiergartenUebersicht(final View sfNormal) {
		final Intent intentBiergartenUebersicht = new Intent(this, WeihnachtsmarktUebersichtActivity.class);
		intentBiergartenUebersicht.putExtra(INTENT_EXTRA_BIERGARTEN_LISTE, (ArrayList<WeihnachtsmarktVO>)mWeihnachtsmarktList);
		startActivity(intentBiergartenUebersicht);
		
	}
	
	private final OnClickListener mBiergartenFindenListener = new OnClickListener() {
		public void onClick(View v) {
			onClickBiergartenFinden(v);
		}
	};
	public void onClickBiergartenFinden(final View sfNormal) {
		final Intent intentBiergartenFinden = new Intent(this, WeihnachtsmarktFindenActivity.class);
		startActivityForResult(intentBiergartenFinden, REQUEST_CODE_BIERGARTEN_FINDEN);
	}
	
	private final OnClickListener mBiergartenEintragenListener = new OnClickListener() {
		public void onClick(View v) {
			onClickBiergartenEintragen(v);
		}
	};
	public void onClickBiergartenEintragen(final View sfNormal) {		
		final Intent intentBiergartenEintragen = new Intent(this, WeihnachtsmarktEintragenActivity.class);		
        startActivityForResult(intentBiergartenEintragen, REQUEST_CODE_BIERGARTEN_EINTRAGEN);
	}
	
	private final OnClickListener mBiergartenFavoritenListener = new OnClickListener() {
		public void onClick(View v) {
			if(isGPS()){
				onClickBiergartenFavoriten(v);
			}else{
				Toast.makeText(WeihnachtsmarktActivity.this, "No GPS enabled!", Toast.LENGTH_SHORT).show();
			}
			
		}
	};
	public void onClickBiergartenFavoriten(final View sfNormal) {
		final Intent intentBiergartenFavoriten = new Intent(this, WeihnachtsmarktFavoritenActivity.class);
		intentBiergartenFavoriten.putExtra(INTENT_EXTRA_BIERGARTEN_FAVORITEN_LISTE, (ArrayList<WeihnachtsmarktVO>)mWeihnachtsmarktFavoritenList);
		startActivity(intentBiergartenFavoriten);
	}

	
	private void initPrefs() {
		mLoadDbPreference = WeihnachtsmarktPreference.getInstance(this).getPreferencesBoolean(PREFERENCE_KEY_LOAD_DB, true);
		mLoadDbFromCloudPreference = WeihnachtsmarktPreference.getInstance(this).getPreferencesBoolean(PREFERENCE_KEY_LOAD_DB_FROM_CLOUD, false);
	}
	
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		if (resultCode == RESULT_OK && requestCode == REQUEST_CODE_BIERGARTEN_EINTRAGEN) {
			if (data.hasExtra(INTENT_EXTRA_BIERGARTEN_EINTRAGEN_RESULT)) {
				WeihnachtsmarktVO biergartenVO = (WeihnachtsmarktVO) data.getExtras().getSerializable(INTENT_EXTRA_BIERGARTEN_EINTRAGEN_RESULT);
				if(!biergartenVO.istNeu()){
					this.mWeihnachtsmarktFavoritenList.add(biergartenVO);
					this.mWeihnachtsmarktList.add(biergartenVO);
					LOG.v(TAG,  "Back from " +biergartenVO.name);
				}
				
			}
		}
		if (resultCode == RESULT_OK && requestCode == REQUEST_CODE_BIERGARTEN_FINDEN) {
			if (data.hasExtra(INTENT_EXTRA_BIERGARTEN_FINDEN_RESULT)) {
				WeihnachtsmarktVO biergartenVO = (WeihnachtsmarktVO) data.getExtras().getSerializable(INTENT_EXTRA_BIERGARTEN_FINDEN_RESULT);
				if(null != biergartenVO && !biergartenVO.istNeu()){
					this.mWeihnachtsmarktFavoritenList.add(biergartenVO);
					this.mWeihnachtsmarktList.add(biergartenVO);
					LOG.v(TAG,  "Back from " +biergartenVO.name);
				}
				
			}
		}
		else if (resultCode == RESULT_OK && requestCode == REQUEST_CODE_BIERGARTEN_EINSTELLUNGEN) {
			initPrefs();
			this.ladeBiergaerten();
		}
	}
    
    public void setBiergartenList(List<WeihnachtsmarktVO> biergartenList) {
		this.mWeihnachtsmarktList = biergartenList;		
		this.initBiergarten();
	}

	public void setBiergartenFavoritenList(List<WeihnachtsmarktVO> biergartenFavoritenList) {
		this.mWeihnachtsmarktFavoritenList = biergartenFavoritenList;		
	}
	
	private void initBiergarten(){
		WeihnachtsmarktGAEDatenbank biergartenGAEDatenbank = new WeihnachtsmarktGAEDatenbank(this);
    	biergartenGAEDatenbank.erzeugeBiergartenDaten(this.mWeihnachtsmarktList, false);
	}
	
private void ladeBiergaerten(){
    	
    	File dbFile = new File(Environment.getExternalStorageDirectory().getAbsolutePath() +WEIHNACHTSMARKT_DB_FILE_PATH);   
    	if(mLoadDbPreference || !dbFile.exists() ){
    		
    		File biergartenDirectory = new File(Environment.getExternalStorageDirectory().getAbsolutePath(), "weihnachtsmarkt"); 
            if(!biergartenDirectory.exists()){ 
                    biergartenDirectory.mkdirs(); 
            } 
            
            File biergartenDBFile = new File(biergartenDirectory, "weihnachtsmaerkte.sqlite");         
            if(!biergartenDBFile.exists()){ 
	            try { 
                    	biergartenDBFile.createNewFile(); 
                    } catch (IOException e) { 
                            e.printStackTrace(); 
                    } 
            }
            
    		File directory = new File(Environment.getExternalStorageDirectory().getAbsolutePath() +WEIHNACHTSMARKT_FOLDER);    		
    		File[] files = directory.listFiles();
    		if(null != files){
    			for (int i = 0; i < files.length; i++) {
        			if(files[i].getName().startsWith("weihnachtsmaerkte.sqlite")){
        				LOG.v(TAG, files[i].getAbsolutePath());
        				files[i].delete();
        			}				
    			}
    		}
    		
    		if(!mLoadDbFromCloudPreference){
    			copyDataBase();
    			mWeihnachtsmarktSpeicher = new WeihnachtsmarktSpeicher(this);
    			mWeihnachtsmarktList = mWeihnachtsmarktSpeicher.ladeAlleBiergartenListeVO();
            	mWeihnachtsmarktFavoritenList = mWeihnachtsmarktSpeicher.ladeAlleBiergartenFavoritenListeVO();
            	mWeihnachtsmarktSpeicher.schliessen();
    		}else{
    			loadBiergartenFromCloud();
    			WeihnachtsmarktPreference.getInstance(this).saveBooleanState(PREFERENCE_KEY_LOAD_DB_FROM_CLOUD, false);
    		}
    		
        	WeihnachtsmarktPreference.getInstance(this).saveBooleanState(PREFERENCE_KEY_LOAD_DB, false);
        }else{
        	mWeihnachtsmarktSpeicher = new WeihnachtsmarktSpeicher(this);
        	mWeihnachtsmarktList = mWeihnachtsmarktSpeicher.ladeAlleBiergartenListeVO();
        	mWeihnachtsmarktFavoritenList = mWeihnachtsmarktSpeicher.ladeAlleBiergartenFavoritenListeVO();
        	mWeihnachtsmarktSpeicher.schliessen();
        }
    	
    }
    
    private void loadBiergartenFromCloud(){
    	if(WeihnachtsmarktNetworkManager.getInstance(this).isNetworkAvailable()){
    		// http://weihnachtsmarktservice.appspot.com/platzerworld/weihnachtsmarkt/insertweihnachtsmarkt
    		// http://weihnachtsmarktservice.appspot.com/platzerworld/weihnachtsmarkt/holefavoriten
    		// http://weihnachtsmarktservice.appspot.com/platzerworld/weihnachtsmarkt/holeweihnachtsmaerkte
    		ConnectivityManager cm = WeihnachtsmarktNetworkManager.getInstance(this).getConnectivityManager();
    		NetworkInfo activeNetworkInfo = cm.getActiveNetworkInfo();
    		if(NetworkInfo.State.CONNECTED == activeNetworkInfo.getState()){
    			DownloadWebPageTask task = new DownloadWebPageTask(this);
                WeihnachtsmarktFavoritenTask biergartenFavoritenTask = new WeihnachtsmarktFavoritenTask(this);
            	task.execute(new String[] { "http://weihnachtsmarktservice.appspot.com/platzerworld/weihnachtsmarkt/holeweihnachtsmaerkte" });
            	biergartenFavoritenTask.execute(new String[] { "http://weihnachtsmarktservice.appspot.com/platzerworld/weihnachtsmarkt/holefavoriten" });
    		}
    	}
    }
    
    private void copyDataBase() {
    	try{
	    	//Open your local db as the input stream
	    	InputStream myInput = this.getAssets().open(ASSETS_DB_WEIHNACHTSMARKT_FILE);
	 
	    	// Path to the just created empty db
	    	String outFileName = Environment.getExternalStorageDirectory().getAbsolutePath() +WEIHNACHTSMARKT_DB_FILE_PATH;
	 
	    	//Open the empty db as the output stream
	    	OutputStream myOutput = new FileOutputStream(outFileName);
	 
	    	//transfer bytes from the inputfile to the outputfile
	    	byte[] buffer = new byte[1024];
	    	int length;
	    	while ((length = myInput.read(buffer))>0){
	    		myOutput.write(buffer, 0, length);
	    	}
	 
	    	//Close the streams
	    	myOutput.flush();
	    	myOutput.close();
	    	myInput.close();
    	}catch(IOException ex){
    		
    	}
    }
}