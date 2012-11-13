package com.platzerworld.weihnachtsmarkt.common.appengine;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.platzerworld.weihnachtsmarkt.common.appengine.exception.AppEngineException;
import com.platzerworld.weihnachtsmarkt.common.style.StyleManager;
import com.platzerworld.weihnachtsmarkt.db.WeihnachtsmarktGAEDatenbank;
import com.platzerworld.weihnachtsmarkt.vo.WeihnachtsmarktVO;

/**
 * @author platzerg
 * 
 */
public class RemoteActivity extends Activity {
	private static final String TAG = "RemoteActivity";

	private TextView mStatus;

	private Button mSfConnectGAC;
	private Button mSfDisconnectGAC;

	private Button mSfRemoveKlasse;
	private Button mSfRemoveMannschaft;
	private Button mSfRemoveSpieler;
	private Button mSfRemoveVerein;

	private Button mSfAddKlasse;
	private Button mSfAddMannschaft;
	private Button mSfAddSpieler;
	private Button mSfAddVerein;

	private Button mSfGetKlasse;
	private Button mSfGetMannschaft;
	private Button mSfGetSpieler;
	private Button mSfGetVerein;

	private boolean mIsAuthenticated = false;
	
	private boolean checkboxPreferenceGAEDataInsert = false;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		//setContentView(R.layout.remote);

		init();
	}

	private void init() {
		initPreferences();
		initWidgets();
		initStyle();
		initListener();
		initContextMenu();
		initDatabase();
	}

	private void initStyle() {
		Typeface font = StyleManager.getInstance().init(this).getTypeface();
		mStatus.setTypeface(font);

	}

	private void initWidgets() {
		/*
		mSfConnectGAC = (Button) findViewById(R.id.connect_gac);
		mSfDisconnectGAC = (Button) findViewById(R.id.disconnect_gac);

		mSfRemoveKlasse = (Button) findViewById(R.id.remove_klasse);
		mSfRemoveMannschaft = (Button) findViewById(R.id.remove_mannschaft);
		mSfRemoveSpieler = (Button) findViewById(R.id.remove_spieler);
		mSfRemoveVerein = (Button) findViewById(R.id.remove_verein);

		mSfAddKlasse = (Button) findViewById(R.id.add_klasse);
		mSfAddMannschaft = (Button) findViewById(R.id.add_mannschaft);
		mSfAddSpieler = (Button) findViewById(R.id.add_spieler);
		mSfAddVerein = (Button) findViewById(R.id.add_verein);

		mSfGetKlasse = (Button) findViewById(R.id.get_klasse);
		mSfGetMannschaft = (Button) findViewById(R.id.get_mannschaft);
		mSfGetSpieler = (Button) findViewById(R.id.get_spieler);
		mSfGetVerein = (Button) findViewById(R.id.get_verein);

		mStatus = (TextView) findViewById(R.id.status);
		*/
	}

	private void initListener() {
		mSfConnectGAC.setOnClickListener(mConnectGACListener);
		mSfDisconnectGAC.setOnClickListener(mDisconnectGACListener);

		mSfRemoveKlasse.setOnClickListener(mRemoveKlasseListener);
		mSfRemoveMannschaft.setOnClickListener(mRemoveMannschaftListener);
		mSfRemoveSpieler.setOnClickListener(mRemoveSpielerListener);
		mSfRemoveVerein.setOnClickListener(mRemoveVereinListener);

		mSfAddKlasse.setOnClickListener(mAddKlasseListener);
		mSfAddMannschaft.setOnClickListener(mAddMannschaftListener);
		mSfAddSpieler.setOnClickListener(mAddSpielerListener);
		mSfAddVerein.setOnClickListener(mAddVereinListener);

		mSfGetSpieler.setOnClickListener(mGetSpielerListener);
	}

	private void initContextMenu() {
	}

	private void initDatabase() {

	}

	private void cleanDatabase() {

	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);
		Log.v(TAG, "RemoteActivity.onActivityResult: Request code: " + requestCode + ". Result Code: " + resultCode + ".");
		
		mIsAuthenticated = true;

		if (requestCode == AppEngineActivity.SETUP_AUTHENTICATE && resultCode == GoogleGAEConstantsIF.RESULT_OK && null != WeihnachtsmarktAppEngine.getInstance()) {
			doAuthenticationSucceeded();
		} else {
			doAuthenticationFailed();
		}
	}

	/**
	 * Erfolgreiche Authentifizierung
	 */
	private void doAuthenticationSucceeded() {
		Log.v(TAG, "Authentication successful!.");
		mStatus.setText("Authenticated!");
	}
	/**
	 * keine erfolgreiche Authentifizierung!
	 */
	private void doAuthenticationFailed() {
		mStatus.setText("Authentication failed!");
		Log.e(TAG, "Authentication failed!");
	}

	private final OnClickListener mConnectGACListener = new OnClickListener() {
		public void onClick(View v) {
			onClickConnectGAC(v);
		}
	};

	private void onClickConnectGAC(final View sfNormal) {
		authenticate();
	}

	private void authenticate() {
		if (!mIsAuthenticated) {
			// wenn keine Authentifizierung vorliegt Authentifizierung starten
			Intent intent = new Intent(this, AppEngineActivity.class);
			intent.putExtra(AppEngineActivity.APPLICATION_NAME, "kegelverwaltung.appspot.com");
			startActivityForResult(intent, AppEngineActivity.SETUP_AUTHENTICATE);
		}
	}

	private final OnClickListener mDisconnectGACListener = new OnClickListener() {
		public void onClick(View v) {
			onClickDisconnectGAC(v);
		}
	};

	private final OnClickListener mGetSpielerListener = new OnClickListener() {
		public void onClick(View v) {
			onClickGetSpieler(v);
		}
	};

	private void onClickGetSpieler(final View sfNormal) {
		if (mIsAuthenticated) {
			getSpieler();
		}
		
	}

	private final OnClickListener mAddKlasseListener = new OnClickListener() {
		public void onClick(View v) {
			onClickAddKlasse(v);
		}
	};

	private void onClickAddKlasse(final View sfNormal) {
		if (mIsAuthenticated) {
			showDialog("Klasse", "add");
		}
		
	}

	private final OnClickListener mAddVereinListener = new OnClickListener() {
		public void onClick(View v) {
			onClickAddVerein(v);
		}
	};

	private void onClickAddVerein(final View sfNormal) {
		if (mIsAuthenticated) {
			showDialog("Verein", "add");
		}
		
	}

	private final OnClickListener mAddMannschaftListener = new OnClickListener() {
		public void onClick(View v) {
			onClickAddMannschaft(v);
		}
	};

	private void onClickAddMannschaft(final View sfNormal) {
		if (mIsAuthenticated) {
			showDialog("Mannschaft", "add");
		}
		
	}

	private final OnClickListener mAddSpielerListener = new OnClickListener() {
		public void onClick(View v) {
			onClickAddSpieler(v);
		}
	};

	private void onClickAddSpieler(final View sfNormal) {
		if (mIsAuthenticated) {
			showDialog("Spieler", "add");
		}
		
	}

	private void onClickDisconnectGAC(final View sfNormal) {
		authenticate();
		Toast.makeText(this, "onClickDisconnectGAC", Toast.LENGTH_SHORT).show();
	}

	private final OnClickListener mRemoveKlasseListener = new OnClickListener() {
		public void onClick(View v) {
			onClickRemoveKlase(v);
		}
	};

	private void onClickRemoveKlase(final View sfNormal) {
		if (mIsAuthenticated) {
			Toast.makeText(this, "onClickRemoveKlase", Toast.LENGTH_SHORT).show();
		}
		
	}

	private final OnClickListener mRemoveMannschaftListener = new OnClickListener() {
		public void onClick(View v) {
			onClickRemoveMannschaft(v);
		}
	};

	private void onClickRemoveMannschaft(final View sfNormal) {
		if (mIsAuthenticated) {
			Toast.makeText(this, "onClickRemoveMannschaft", Toast.LENGTH_SHORT).show();
		}
		
	}

	private final OnClickListener mRemoveSpielerListener = new OnClickListener() {
		public void onClick(View v) {
			onClickRemoveSpieler(v);
		}
	};

	private void onClickRemoveSpieler(final View sfNormal) {
		if (mIsAuthenticated) {
			Toast.makeText(this, "onClickRemoveSpieler", Toast.LENGTH_SHORT).show();
		}
		
	}

	private final OnClickListener mRemoveVereinListener = new OnClickListener() {
		public void onClick(View v) {
			onClickRemoveVerein(v);
		}
	};

	private void onClickRemoveVerein(final View sfNormal) {
		if (mIsAuthenticated) {
			Toast.makeText(this, "onClickRemoveVerein", Toast.LENGTH_SHORT).show();
		}
		
	}

	

	private void showDialog(String aModel, String aAction) {
		AlertDialog.Builder builder = new AlertDialog.Builder(this);
		final EditText inputText = new EditText(this);
		builder.setView(inputText);

		final String model = aModel;
		final String action = aAction;
		builder.setMessage(model + " " + action).setCancelable(false).setPositiveButton("Yes",
			new DialogInterface.OnClickListener() {
				public void onClick(DialogInterface dialog, int id) {
					if ("Klasse".equals(model)) {
						if ("add".equals(action)) {
							//addKlasse(inputText.getText().toString());							
						} else if ("remove".equals(model)) {

						}
					}  else if ("Spieler".equals(model)) {
						if ("add".equals(action)) {
							addSpieler(inputText.getText().toString());
						} else if ("remove".equals(model)) {

						}
					}
				}
			})
			.setNegativeButton("No", new DialogInterface.OnClickListener() {
				public void onClick(DialogInterface dialog, int id) {
					dialog.cancel();
				}
			});

		AlertDialog alertDialog = builder.create();
		alertDialog.show();
	}

	
	private void addSpieler(String name) {
		ArrayList<NameValuePair> postData = new ArrayList<NameValuePair>();

		postData.add(new BasicNameValuePair("typ", "0"));
		postData.add(new BasicNameValuePair("spieler", name));

		Toast.makeText(RemoteActivity.this, "Vor post Spieler", Toast.LENGTH_SHORT).show();
		post("/Spieler", postData);
		Toast.makeText(RemoteActivity.this, "Nach post Spieler", Toast.LENGTH_SHORT).show();
	}
	
	private void getSpieler() {
		List<WeihnachtsmarktVO> spielerTOs = new ArrayList<WeihnachtsmarktVO>();
		ArrayList<NameValuePair> postData = new ArrayList<NameValuePair>();
		postData.add(new BasicNameValuePair("typ", "1"));
		String result = postWithResult("/Spieler", postData);

		Gson gson = new Gson();

		Type type = new TypeToken<List<WeihnachtsmarktVO>>() {}.getType();
		List<WeihnachtsmarktVO> studentList = gson.fromJson(result, type);

		StringBuffer bf = new StringBuffer("Ergebnisse aller Spieler:").append("\n");
		for (WeihnachtsmarktVO spielerTO : studentList) {
			spielerTOs.add(spielerTO);
			bf.append(spielerTO.name).append("\n");
		}

		WeihnachtsmarktGAEDatenbank gaeDB = new WeihnachtsmarktGAEDatenbank(this);
		//gaeDB.erzeugeBiergartenDaten(spielerTOs, true);
		mStatus.setText(bf.toString());
	}
	
	private void post(String path, ArrayList<NameValuePair> postData) {
		WeihnachtsmarktAppEngine ae = WeihnachtsmarktAppEngine.getInstance();

		try {
			HttpResponse httpResponse = ae.doHttpPost(path, postData);
			WeihnachtsmarktAppEngine.getStringFromHttpResponse(httpResponse);
		} catch (AppEngineException e) {
			e.printStackTrace();
			mStatus.setText(e.getMessage());
		}
	}
	
	private String postWithResult(String path, ArrayList<NameValuePair> postData) {
		WeihnachtsmarktAppEngine ae = WeihnachtsmarktAppEngine.getInstance();

		try {
			HttpResponse httpResponse = ae.doHttpPost(path, postData);
			return WeihnachtsmarktAppEngine.getStringFromHttpResponse(httpResponse);
		} catch (AppEngineException e) {
			e.printStackTrace();
			mStatus.setText(e.getMessage());
		}
		return null;
	}
	
	private void initPreferences(){
		SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(getBaseContext());
		checkboxPreferenceGAEDataInsert = prefs.getBoolean("CHECK_GAE_INSERT_DELETE_OLD_DATA_PREF", true);
	}

}