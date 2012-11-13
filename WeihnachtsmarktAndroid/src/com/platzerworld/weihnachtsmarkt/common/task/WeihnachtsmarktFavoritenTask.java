package com.platzerworld.weihnachtsmarkt.common.task;

import java.lang.reflect.Type;
import java.util.List;

import android.content.Context;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.platzerworld.weihnachtsmarkt.ui.WeihnachtsmarktActivity;
import com.platzerworld.weihnachtsmarkt.vo.WeihnachtsmarktJSON;
import com.platzerworld.weihnachtsmarkt.vo.WeihnachtsmarktVO;

public class WeihnachtsmarktFavoritenTask extends DownloadWebPageTask {
	public WeihnachtsmarktFavoritenTask(){
		
	}
	public WeihnachtsmarktFavoritenTask(Context context){
		this.context = context;
	}
	
	@Override
	protected void onPostExecute(String result) {
		Gson gson = new Gson();
		Type type = new TypeToken<WeihnachtsmarktJSON>() {}.getType();
		
		WeihnachtsmarktJSON biergartenJSON = gson.fromJson(result, type);
		
		List<WeihnachtsmarktVO> biergartenFavoritenList = biergartenJSON.getBiergartenListe();
		((WeihnachtsmarktActivity)context).setBiergartenFavoritenList(biergartenFavoritenList);
	}
}
