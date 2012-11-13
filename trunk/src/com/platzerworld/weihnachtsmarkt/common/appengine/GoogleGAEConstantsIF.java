package com.platzerworld.weihnachtsmarkt.common.appengine;

import java.io.Serializable;

public interface GoogleGAEConstantsIF extends Serializable {
	public static final String APPLICATION_NAME = "platzerworld.appspot.com";
	public static final String APPENGINE_INSTANCE = "AppEngineInstance";
	public static final String PREFS_FILE_NAME = "AppEngineAndroid.prefs";
	public static final int SETUP_AUTHENTICATE = 872635; 	
	public static final int RESULT_OK = 345246;
	public static final int RESULT_FAILED = RESULT_OK + 1;
	public static final String PREFS_ACCOUNT_NAME = "AccountName";
	
	public static final String APPLICATION_URL = "ApplicationUrl";
	public static final String ACCOUNT = "Account";
}
