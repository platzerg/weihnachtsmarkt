package com.platzerworld.weihnachtsmarkt.common.appengine;

import android.accounts.Account;
import android.accounts.AccountManager;
import android.accounts.AccountManagerCallback;
import android.accounts.AccountManagerFuture;
import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;

import com.platzerworld.weihnachtsmarkt.common.appengine.exception.CookieException;
import com.platzerworld.weihnachtsmarkt.common.logging.LOG;

/**
 * @author platzerg
 */
public class AuthenticationActivity extends Activity implements GoogleGAEConstantsIF{
	private static final long serialVersionUID = -8665511922168961164L;

	private static final String TAG = "AuthenticationActivity";

	private String mApplicationUrl;
	
	private WeihnachtsmarktAppEngine mAEInstance;
	private AccountManager mAccountManager;
	private Account mAccount;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		//setContentView(R.layout.appengine);

		Bundle extras = getIntent().getExtras();
		mApplicationUrl = (String) extras.get(APPLICATION_URL);
		mAccount = (Account) extras.get(ACCOUNT);
		mAEInstance = WeihnachtsmarktAppEngine.createInstance(mApplicationUrl);
		mAccountManager = AccountManager.get(this);

		//TextView status = (TextView) findViewById(R.id.appengine_status);
		//status.setText("Connecting to: " + mApplicationUrl + "...");
	}

	@Override
	protected void onResume() {
		super.onResume();
		LOG.v(TAG, "Fetching auth token for the first time.");
		// This needs to be in onResume, read comments below to understand why.
		mAccountManager.getAuthToken(mAccount, "ah", false, new GetAuthTokenCallback(), null);
	}

	private class GetAuthTokenCallback implements AccountManagerCallback<Bundle> {
		public void run(AccountManagerFuture<Bundle> result) {
			Bundle bundle;
			try {
				bundle = result.getResult();
				Intent intent = (Intent) bundle.get(AccountManager.KEY_INTENT);
				if (intent != null) {
					LOG.v(TAG, "Need user input, firing intent.");
					startActivity(intent);
				} else {
					onGetAuthToken(bundle);
				}
			} catch (Exception e) {
				e.printStackTrace();
				setResult(AppEngineActivity.RESULT_FAILED);
				finish();
				return;
			}
		}
	}

	/**
	 * Start GetAuthTokenTask.
	 * 
	 * @param bundle
	 *            Bundle returned by AccountManager - should contain extra for
	 *            auth token.
	 */
	private void onGetAuthToken(Bundle bundle) {
		new GetAuthTokenTask().execute(bundle.getString(AccountManager.KEY_AUTHTOKEN));
	}

	private class GetAuthTokenTask extends AsyncTask<String, Void, String> {

		/**
		 * @see android.os.AsyncTask#doInBackground(Params[])
		 */
		@Override
		protected String doInBackground(String... params) {
			mAccountManager.invalidateAuthToken(mAccount.type, params[0]);
			return getAuthToken();
		}

		private String getAuthToken() {
			try {
				return mAccountManager.getAuthToken(mAccount, "ah", false,
						null, null).getResult().getString(
						AccountManager.KEY_AUTHTOKEN);
			} catch (Exception e) {
				e.printStackTrace();
				return null;
			}
		}

		/**
		 * @see android.os.AsyncTask#onPostExecute(java.lang.Object)
		 */
		@Override
		protected void onPostExecute(String authToken) {
			if (authToken == null) {
				Log.e(TAG, "Failed to retrieve auth token!");
				setResult(AppEngineActivity.RESULT_FAILED);
				finish();
				return;
			}
			new GetCookiesTask().execute(authToken);
		}

	}

	private class GetCookiesTask extends AsyncTask<String, Void, Boolean> {

		/**
		 * @see android.os.AsyncTask#doInBackground(Params[])
		 */
		@Override
		protected Boolean doInBackground(String... params) {
			try {
				mAEInstance.fetchCookies(params[0]);
				return true;
			} catch (CookieException e) {
				e.printStackTrace();
				return false;
			}
		}

		/**
		 * @see android.os.AsyncTask#onPostExecute(java.lang.Object)
		 */
		@Override
		protected void onPostExecute(Boolean result) {
			super.onPostExecute(result);
			if (result) {
				Log.i(TAG, "AppEngine instance is ready to go.");
				setResult(GoogleGAEConstantsIF.RESULT_OK);
			} else {
				Log.e(TAG, "Failed to fetch cookies!");
				setResult(GoogleGAEConstantsIF.RESULT_FAILED);
			}
			finish();
		}

	}

}
