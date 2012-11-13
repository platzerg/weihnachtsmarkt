package com.platzerworld.weihnachtsmarkt.ui;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.util.Log;
import android.widget.EditText;
import android.widget.TextView;

import com.platzerworld.weihnachtsmarkt.R;
import com.platzerworld.weihnachtsmarkt.common.ConstantsIF;
import com.platzerworld.weihnachtsmarkt.common.logging.LOG;
import com.platzerworld.weihnachtsmarkt.common.style.StyleManager;
import com.platzerworld.weihnachtsmarkt.vo.WeihnachtsmarktVO;

public class WeihnachtsmarktSpeisenActivity extends Activity implements ConstantsIF{

	private static final String TAG = "BiergartenActivity";
	private static final long serialVersionUID = -6754055149216909895L;
	
	private boolean isEditMode = false;
	
	private WeihnachtsmarktVO biergartenVO;
	
	private TextView textViewTitle;
	private EditText mEditTextRiesenbreze;
	private EditText mEditTextObazda;
	private EditText mEditTextLieblingsgericht;
	private EditText mEditTextSpeisekommentar;
	
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.biergarten_speisen_1);
        textViewTitle = (TextView) findViewById(R.id.txt_biergarten_speisen_title);
        
        Bundle extras = getIntent().getExtras();
		if (extras == null) {
			return;
		}
		
		biergartenVO =  (WeihnachtsmarktVO) extras.getSerializable(INTENT_EXTRA_BIERGARTEN_DETAILS_BIERGARTENVO);
		if(null == biergartenVO){
			biergartenVO = new WeihnachtsmarktVO();
		}
		
		isEditMode = extras.getBoolean(INTENT_EXTRA_BIERGARTEN_DETAILS_MODUS, false);
		
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
    	if(validate()){
    		Intent data = new Intent();
    		data.putExtra(INTENT_EXTRA_BIERGARTEN_DETAILS_BIERGARTENVO, biergartenVO);
    		setResult(RESULT_OK, data);
    		
    		super.finish();
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
		mEditTextRiesenbreze = (EditText) findViewById(R.id.edt_biergarten_details_riesenbrenze);
		mEditTextObazda = (EditText) findViewById(R.id.edt_biergarten_details_obazda);	
		mEditTextLieblingsgericht = (EditText) findViewById(R.id.edt_biergarten_details_lieblingsgericht);	
		mEditTextSpeisekommentar = (EditText) findViewById(R.id.edt_biergarten_details_speisenkommentar);	

		setAppearance();
	}
	
	private void setAppearance(){
		int color = 0;
		mEditTextRiesenbreze.setEnabled(isEditMode);
		mEditTextObazda.setEnabled(isEditMode);
		mEditTextLieblingsgericht.setEnabled(isEditMode);
		mEditTextSpeisekommentar.setEnabled(isEditMode);
		
		if(isEditMode){
			color = getResources().getColor(R.color.color_eingabe_text);
		}else{
			color = getResources().getColor(R.color.color_transparent);
		}
		
		mEditTextRiesenbreze.setBackgroundColor(color);
		mEditTextObazda.setBackgroundColor(color);
		mEditTextLieblingsgericht.setBackgroundColor(color);
		mEditTextSpeisekommentar.setBackgroundColor(color);
	}
	
	private void initStyle() {
		Typeface font = StyleManager.getInstance().init(this).getTypeface();
		textViewTitle.setTypeface(font);		
		
		mEditTextLieblingsgericht.setTypeface(StyleManager.getInstance().getTypefaceDroidserifBold());
		mEditTextObazda.setTypeface(StyleManager.getInstance().getTypefaceDroidserifBold());
		mEditTextRiesenbreze.setTypeface(StyleManager.getInstance().getTypefaceDroidserifBold());
		mEditTextSpeisekommentar.setTypeface(StyleManager.getInstance().getTypefaceDroidserifBold());
	}
	
	private void initListener(){	
	}
	
	private void bindWidgets(){
		
		// mEditTextRiesenbreze.setText(biergartenVO.riesenbreze);
		// mEditTextObazda.setText(biergartenVO.obazda);
		mEditTextLieblingsgericht.setText(biergartenVO.lieblingsgericht);
		mEditTextSpeisekommentar.setText(biergartenVO.speisenkommentar);
		
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
		
	}
		
	private boolean validate(){
		if(mEditTextLieblingsgericht.getText().length() > 0){
			biergartenVO.lieblingsgericht = mEditTextLieblingsgericht.getText().toString();
		}
		if(mEditTextObazda.getText().length() > 0){
			// biergartenVO.obazda = mEditTextObazda.getText().toString();
		}
		if(mEditTextRiesenbreze.getText().length() > 0){
			// biergartenVO.riesenbreze = mEditTextRiesenbreze.getText().toString();
		}
		if(mEditTextSpeisekommentar.getText().length() > 0){
			biergartenVO.speisenkommentar = mEditTextSpeisekommentar.getText().toString();
		}
		return true;
	}
}
