package com.platzerworld.weihnachtsmarkt.ui.preference;

import android.content.ContextWrapper;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import com.platzerworld.weihnachtsmarkt.R;
import com.platzerworld.weihnachtsmarkt.common.ConstantsIF;
import com.platzerworld.weihnachtsmarkt.common.preference.WeihnachtsmarktPreference;

	public class EinstellungenBearbeiten extends PreferenceActivity implements ConstantsIF{
		private static final long serialVersionUID = 4874771231367176855L;
		private static final int KEGELVERWALTUNG_BEENDEN_ID = Menu.FIRST + 2;
		public static final String EINSTELLUNGEN_NAME = EinstellungenBearbeiten.class.getSimpleName();

		@Override
		protected void onCreate(Bundle savedInstanceState) {
			super.onCreate(savedInstanceState);
			addPreferencesFromResource(R.xml.einstellungen);	
		}

		@Override
		protected void onPause() {			
			super.onPause();
		}

		@Override
		protected void onStart() {
			super.onStart();
		}

		@Override
		public boolean onCreateOptionsMenu(Menu menu) {
			menu.add(0, KEGELVERWALTUNG_BEENDEN_ID, Menu.NONE, "Beenden");
			return super.onCreateOptionsMenu(menu);
		}

		@Override
		public void finish() {
			SharedPreferences sharedPreferences = getAnwendungsEinstellungen(this);
			boolean loadDbPreference = sharedPreferences.getBoolean(PREFERENCE_KEY_LOAD_DB, true);
			boolean loadDbCloudPreference = sharedPreferences.getBoolean(PREFERENCE_KEY_LOAD_DB_FROM_CLOUD, false);
			boolean enableRadiusPreference = sharedPreferences.getBoolean(PREFERENCE_KEY_RADIUS_ENABLED, true);
			String radiusValuePreference = sharedPreferences.getString(PREFERENCE_KEY_RADIUS_VALUE, "1000");			
			String radiusColorValuePreference = sharedPreferences.getString(PREFERENCE_KEY_RADIUS_COLOR, "-16777216");
			String radiusStrokeWidthValuePreference = sharedPreferences.getString(PREFERENCE_KEY_RADIUS_STROKE_WIDTH, "2");
			String radiusAlphawertaluePreference = sharedPreferences.getString(PREFERENCE_KEY_RADIUS_ALPHA, "70");			
			String plzValuePreference = sharedPreferences.getString(PREFERENCE_KEY_PLZ_VALUE, "80807");
			
			WeihnachtsmarktPreference.getInstance(this).saveBooleanState(PREFERENCE_KEY_RADIUS_ENABLED, enableRadiusPreference);
			WeihnachtsmarktPreference.getInstance(this).saveBooleanState(PREFERENCE_KEY_LOAD_DB, loadDbPreference);
			WeihnachtsmarktPreference.getInstance(this).saveBooleanState(PREFERENCE_KEY_LOAD_DB_FROM_CLOUD, loadDbCloudPreference);
			WeihnachtsmarktPreference.getInstance(this).saveStringState(PREFERENCE_KEY_RADIUS_VALUE, radiusValuePreference);			
			WeihnachtsmarktPreference.getInstance(this).saveStringState(PREFERENCE_KEY_RADIUS_COLOR, radiusColorValuePreference);
			WeihnachtsmarktPreference.getInstance(this).saveStringState(PREFERENCE_KEY_RADIUS_STROKE_WIDTH, radiusStrokeWidthValuePreference);
			WeihnachtsmarktPreference.getInstance(this).saveStringState(PREFERENCE_KEY_RADIUS_ALPHA, radiusAlphawertaluePreference);
			WeihnachtsmarktPreference.getInstance(this).saveStringState(PREFERENCE_KEY_PLZ_VALUE, plzValuePreference);
			
			Intent data = new Intent();
			setResult(RESULT_OK, data);
			
			super.finish();
		}

		@Override
		public boolean onOptionsItemSelected(MenuItem item) {
			switch (item.getItemId()) {
			case KEGELVERWALTUNG_BEENDEN_ID:
				finish();
				return true;
			default:
				Log.w(EINSTELLUNGEN_NAME, "unbekannte Option gewaehlt: " + item);
				return super.onOptionsItemSelected(item);
			}
		}


		/**
		 * Liefert die Anwendungseinstellungen.
		 * 
		 * @param ctx
		 *            Context der Anwendung
		 * @return SharedPreferences-Objekt mit den Anwendungseinstellungen
		 */
		public static final SharedPreferences getAnwendungsEinstellungen(final ContextWrapper ctx) {
			return ctx.getSharedPreferences(ctx.getPackageName() + "_preferences", MODE_PRIVATE);
		}
}
