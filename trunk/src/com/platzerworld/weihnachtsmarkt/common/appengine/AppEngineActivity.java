
package com.platzerworld.weihnachtsmarkt.common.appengine;

import android.R;
import android.accounts.Account;
import android.accounts.AccountManager;
import android.app.Activity;
import android.app.ListActivity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.platzerworld.weihnachtsmarkt.common.logging.LOG;

/**
 * @author platzerg
 */
public class AppEngineActivity extends ListActivity implements GoogleGAEConstantsIF{
	private static final long serialVersionUID = -1051880879204177936L;

	private static final String TAG = "AppEngineActivity";

	private AccountManager mAccountManager;
	private Account[] mAllAccounts;
	private String mApplicationUrl;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		if(null != getIntent().getExtras()){
			mApplicationUrl = "https://" + getIntent().getExtras().get(APPLICATION_NAME);
		}
		
		fetchAllAccounts();
		
		if (mAllAccounts.length < 1) {
			Log.e(TAG, "No accounts found!");
			setResult(RESULT_FAILED);
			finish();
			return;
		}
		if (mAllAccounts.length == 1) {
			LOG.v(TAG, "Only one account found, using it.");
			startAuthenticationActivity(mAllAccounts[0]);
		} else {
			Account account = readAccountFromPreferences();
			if (account == null) {
				LOG.v(TAG, "Account not found in preferences.");
				setListAdapter(new ArrayAdapter<Account>(this,R.layout.simple_list_item_1, mAllAccounts));
			} else {
				LOG.v(TAG, "Found account in preferences. Will use it.");
				startAuthenticationActivity(account);
			}
		}
	}

	@Override
	protected void onDestroy() {
		super.onDestroy();
		mAccountManager = null;
		mAllAccounts = null;
	}

	@Override
	protected void onListItemClick(ListView l, View v, int position, long id) {
		super.onListItemClick(l, v, position, id);
		Account account = (Account) getListView().getItemAtPosition(position);
		startAuthenticationActivity(account);
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);
		if (requestCode == SETUP_AUTHENTICATE) {
			setResult(resultCode);
		}
		else {
			setResult(RESULT_CANCELED);
		}
		finish();
	}

	private void fetchAllAccounts() {
		mAccountManager = AccountManager.get(this);
		mAllAccounts = mAccountManager.getAccountsByType("com.google");
	}
	
	private Account readAccountFromPreferences() {
		SharedPreferences preferences = getSharedPreferences(PREFS_FILE_NAME, Activity.MODE_PRIVATE);
		String accountName = preferences.getString(PREFS_ACCOUNT_NAME, null);
		
		if (accountName == null) {
			return null;
		}

		for (Account account : mAllAccounts) {
			if (account.name.compareTo(accountName) == 0) {
				return account;
			}
		}

		return null;
	}

	private void startAuthenticationActivity(Account account) {
		Log.i(TAG, "Using account: " + account.name);

		SharedPreferences preferences = getSharedPreferences(PREFS_FILE_NAME, MODE_PRIVATE);
		SharedPreferences.Editor editor = preferences.edit();
		editor.putString(PREFS_ACCOUNT_NAME, account.name);
		editor.commit();

		Intent intent = new Intent(this, AuthenticationActivity.class);
		intent.putExtra(AuthenticationActivity.ACCOUNT, account);
		intent.putExtra(AuthenticationActivity.APPLICATION_URL, mApplicationUrl);
		startActivityForResult(intent, SETUP_AUTHENTICATE);
	}

}
