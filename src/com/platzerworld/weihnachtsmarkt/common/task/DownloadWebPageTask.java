package com.platzerworld.weihnachtsmarkt.common.task;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Type;
import java.util.List;

import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;

import android.content.Context;
import android.os.AsyncTask;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.platzerworld.weihnachtsmarkt.ui.WeihnachtsmarktActivity;
import com.platzerworld.weihnachtsmarkt.vo.WeihnachtsmarktJSON;
import com.platzerworld.weihnachtsmarkt.vo.WeihnachtsmarktVO;

/**
 * DownloadWebPageTask task = new DownloadWebPageTask();
	task.execute(new String[] { "http://www.vogella.com" });
 * @author admin
 *
 */
public class DownloadWebPageTask extends AsyncTask<String, Void, String> {
	protected Context context = null;
	
	public DownloadWebPageTask(){
		
	}
	public DownloadWebPageTask(Context context){
		this.context = context;
	}
	
	@Override
	protected String doInBackground(String... urls) {
		String response = "";
		for (String url : urls) {
			DefaultHttpClient client = new DefaultHttpClient();
			HttpGet httpGet = new HttpGet(url);
			try {
				HttpResponse execute = client.execute(httpGet);
				InputStream content = execute.getEntity().getContent();

				BufferedReader buffer = new BufferedReader(new InputStreamReader(content));
				String s = "";
				while ((s = buffer.readLine()) != null) {
					response += s;
				}

			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return response;
	}

	@Override
	protected void onPostExecute(String result) {
		Gson gson = new Gson();
		Type type = new TypeToken<WeihnachtsmarktJSON>() {}.getType();
		
		WeihnachtsmarktJSON biergartenJSON = gson.fromJson(result, type);
		
		List<WeihnachtsmarktVO> biergartenList = biergartenJSON.getBiergartenListe();
		
		((WeihnachtsmarktActivity)context).setBiergartenList(biergartenList);
	}
}
