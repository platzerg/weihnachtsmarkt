
package com.platzerworld.weihnachtsmarkt.common.appengine;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;
import java.util.concurrent.locks.ReentrantLock;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.params.ClientPNames;
import org.apache.http.cookie.Cookie;
import org.apache.http.impl.client.DefaultHttpClient;

import android.util.Log;

import com.platzerworld.weihnachtsmarkt.common.appengine.exception.AppEngineException;
import com.platzerworld.weihnachtsmarkt.common.appengine.exception.CookieException;
import com.platzerworld.weihnachtsmarkt.common.appengine.exception.HttpRequestException;

/**
 * @author platzerg
 * 
 */
public class WeihnachtsmarktAppEngine {

	private static final String TAG = "KegelverwaltungAppEngine";
	private static WeihnachtsmarktAppEngine sInstance = null;
	private final DefaultHttpClient mHttpClient = new DefaultHttpClient();
	private final String mApplicationUrl;
	private static ReentrantLock sInstanceLock = new ReentrantLock();
	private static boolean sReady = false;
	
	private WeihnachtsmarktAppEngine(String applicationUrl) {
		if (applicationUrl.endsWith("/")) {
			mApplicationUrl = applicationUrl;
		} else {
			mApplicationUrl = applicationUrl + "/";
		}
	}
	
	public static WeihnachtsmarktAppEngine getInstance() {
		WeihnachtsmarktAppEngine ret = null;
		sInstanceLock.lock();
		if (sReady) {
			ret = sInstance;
		}
		sInstanceLock.unlock();
		return ret;
	}

	protected static WeihnachtsmarktAppEngine createInstance(String applicationUrl) {
		WeihnachtsmarktAppEngine instance = new WeihnachtsmarktAppEngine(applicationUrl);
		sInstanceLock.lock();
		sInstance = instance;
		sInstanceLock.unlock();
		return instance;
	}

	public static BufferedReader getBufferedReaderFromHttpResponse(HttpResponse response) throws AppEngineException {
		try {
			return new BufferedReader(new InputStreamReader(response.getEntity().getContent()));
		} catch (Exception e) {
			throw new AppEngineException(e);
		}
	}

	public static String getStringFromHttpResponse(HttpResponse response) throws AppEngineException {
		try {
			StringBuilder sb = new StringBuilder();
			BufferedReader br = getBufferedReaderFromHttpResponse(response);
			String line;
			while ((line = br.readLine()) != null) {
				sb.append(line);
			}
			return sb.toString();
		} catch (Exception e) {
			throw new AppEngineException(e);
		}
	}

	public HttpResponse doHttpGet(String path) throws HttpRequestException {
		HttpGet httpGet = new HttpGet(mApplicationUrl + path);
		Log.v(TAG, "HttpGet: " +mApplicationUrl + path);
		try {
			return mHttpClient.execute(httpGet);
		} catch (Exception e) {
			throw new HttpRequestException(e);
		}
	}

	public HttpResponse doHttpPost(String path, List<NameValuePair> postData) throws HttpRequestException {
		HttpPost httpPost = new HttpPost(mApplicationUrl + path);
		Log.v(TAG, "HttpPost: " +mApplicationUrl + path);
		try {
			httpPost.setEntity(new UrlEncodedFormEntity(postData));
			return mHttpClient.execute(httpPost);
		} catch (Exception e) {
			throw new HttpRequestException(e);
		}
	}

	protected void fetchCookies(String authToken) throws CookieException {
		mHttpClient.getParams().setBooleanParameter( ClientPNames.HANDLE_REDIRECTS, false);

		try {
			HttpGet request = new HttpGet(mApplicationUrl + "_ah/login?continue=http://localhost/&auth=" + authToken);
			HttpResponse response = mHttpClient.execute(request);

			if (response.getStatusLine().getStatusCode() != 302) {
				String error = "Did not receive redirect! Response Code: "+ response.getStatusLine().getStatusCode()+ ". Message: "+ response.getStatusLine().getReasonPhrase();
				Log.e(TAG, error);
				mHttpClient.getParams().setBooleanParameter(ClientPNames.HANDLE_REDIRECTS, true);
				throw new CookieException(error);
			}

			for (Cookie cookie : mHttpClient.getCookieStore().getCookies()) {
				if (cookie.getName().equals("SACSID")) {
					Log.i(TAG, "Found SACSID cookie!");
					setReady();
				}
			}
		} catch (ClientProtocolException e) {
			throw new CookieException(e);
		} catch (IOException e) {
			throw new CookieException(e);
		} finally {
			mHttpClient.getParams().setBooleanParameter(ClientPNames.HANDLE_REDIRECTS, true);
		}
	}

	private void setReady() {
		sInstanceLock.lock();
		sReady = true;
		sInstanceLock.unlock();
	}
}
