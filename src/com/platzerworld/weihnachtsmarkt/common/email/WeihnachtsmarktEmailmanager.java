package com.platzerworld.weihnachtsmarkt.common.email;

import java.io.File;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Environment;
import android.widget.Toast;


public class WeihnachtsmarktEmailmanager {
	static private WeihnachtsmarktEmailmanager _instance;
	private static Context context;
	
	private WeihnachtsmarktEmailmanager(){
	}
	
	static synchronized public WeihnachtsmarktEmailmanager getInstance(Context aContext) {
		if (_instance == null)
			_instance = new WeihnachtsmarktEmailmanager();
		context = aContext;
		return _instance;
	}
	
	private void sendPictureMessage() {
        try {
            Intent emailIntent = new Intent(android.content.Intent.ACTION_SEND);
            
            String aEmailList[] = { "guenter.platzerworld@gmail.com" };  
            String aEmailCCList[] = { "guenter_platzer@web.de"};  
            //String aEmailBCCList[] = { "guenter.platzerworld@gmail.com" };  
              
            emailIntent.putExtra(android.content.Intent.EXTRA_EMAIL, aEmailList);  
            emailIntent.putExtra(android.content.Intent.EXTRA_CC, aEmailCCList);  
            //emailIntent.putExtra(android.content.Intent.EXTRA_BCC, aEmailBCCList);  
            
            emailIntent.setType("image/jpeg");
            File root = Environment.getExternalStorageDirectory();
		    if (root.canWrite()){
		    	File dir = new File(root, "biergarten");
		    	dir.mkdir();
		    	File fileUpload = new File(dir, "biergarten.csv");
		    	emailIntent.putExtra(Intent.EXTRA_STREAM, Uri.fromFile(fileUpload));
		    }

            context.startActivity(Intent.createChooser(emailIntent, "Sende Anhang:"));
        } catch (Exception e) {
            Toast.makeText(context, "No handler", Toast.LENGTH_LONG).show();
        }
    }
	
	private void sendMessage() {
        try {
            Intent emailIntent = new Intent(android.content.Intent.ACTION_SEND);
            
            String aEmailList[] = { "guenter.platzerworld@gmail.com" };  
            String aEmailCCList[] = { "guenter_platzer@web.de"};  
            //String aEmailBCCList[] = { "guenter.platzerworld@gmail.com" };  
              
            emailIntent.putExtra(android.content.Intent.EXTRA_EMAIL, aEmailList);  
            emailIntent.putExtra(android.content.Intent.EXTRA_CC, aEmailCCList);  
            //emailIntent.putExtra(android.content.Intent.EXTRA_BCC, aEmailBCCList);  
            
            
            
            emailIntent.setType("plain/text");  
            emailIntent.putExtra(android.content.Intent.EXTRA_SUBJECT, "Biergarten: ");  
            StringBuffer bf = new StringBuffer("Biergarten: ");
            
            emailIntent.putExtra(android.content.Intent.EXTRA_TEXT, bf.toString());
            
            //emailIntent.setType("image/jpeg");
            //File downloadedPic = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS), "q.jpeg");
            //emailIntent.putExtra(Intent.EXTRA_STREAM, Uri.fromFile(downloadedPic));
            
            

            context.startActivity(Intent.createChooser(emailIntent, "Send picture using:"));
        } catch (Exception e) {
            Toast.makeText(context, "No handler", Toast.LENGTH_LONG).show();
        }
    }
}
