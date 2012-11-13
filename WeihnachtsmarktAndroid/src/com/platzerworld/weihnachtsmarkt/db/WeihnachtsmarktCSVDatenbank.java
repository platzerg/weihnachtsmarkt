/**
 * 
 */
package com.platzerworld.weihnachtsmarkt.db;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.database.sqlite.SQLiteStatement;
import android.os.Environment;
import android.util.Log;
import au.com.bytecode.opencsv.CSVReader;

import com.platzerworld.weihnachtsmarkt.db.weihnachtsmarkt.WeihnachtsmarktTbl;
import com.platzerworld.weihnachtsmarkt.vo.WeihnachtsmarktVO;

/**
 * Die Klasse dient als logische Verbindung zur Datenbank der Anwendung. Sie ist f�r die Erstellung und Wartung des Datenbankschemas 
 * sowie die Initialbef�llung der Tabellen verantwortlich.
 * 
 * Die Stammdaten werden �ber einen CSV-Import geliefert.
 * 
 * @author platzerg
 */
public class WeihnachtsmarktCSVDatenbank extends WeihnachtsmarktDatenbank {

	/** Markierung f�r Logging. */
	private static final String TAG = "KegelverwaltungGAEDatenbank";

	/**
	 * Die Datenbank kann nur nach Kenntnis "ihrer" Anwendung verwaltet werden.
	 * Daher muss der Context der Anwendung �bergeben werden.
	 * 
	 * @param context Context der aufrufenden Anwendung.
	 */
	public WeihnachtsmarktCSVDatenbank(Context context) {
		super(context);
	}
		
	/**
	 * Erzeugen der Tabellen.
	 * 
	 * @param context Context der aufrufenden Anwendung.
	 */
	public void initDatabase(Context mContext) {	
		db.execSQL(WeihnachtsmarktTbl.SQL_CREATE);
	}
	

	/**
	 * Hier werden die Stammdaten f�r die Spieler erzeugt.
	 * 
	 * @param deleteOldData true dann werden bestehende Daten gel�scht
	 * 
	 */
	public void erzeugeSpielerDaten(boolean deleteOldData) {
		List<WeihnachtsmarktVO> spieler = new  ArrayList<WeihnachtsmarktVO>();
		try {
		    File root = Environment.getExternalStorageDirectory();
		    if (root.canWrite()){
		    	File dir = new File(root, "kegeln");
		    	dir.mkdir();
				 
				 File spielerFileRead = new File(dir, "spieler.csv");
				 CSVReader spielerCSVReader = new CSVReader(new FileReader(spielerFileRead), '#');
				 List<String[]> spielerEntries = spielerCSVReader.readAll();

				 for (String[] zeile : spielerEntries) {
					 // ID, PASS_NR, MANNSCHAFT_ID, NAME, VORNAME, GEB_DATUM, LOC_LATITUDE, LOC_LONGITUDE
					 WeihnachtsmarktVO spielerTO = new WeihnachtsmarktVO();
					 spielerTO.id = Long.parseLong(zeile[0]);
					 spielerTO.key = Long.parseLong(zeile[0]);
					 spielerTO.name = zeile[4];
					 spielerTO.value = zeile[4];
					 spieler.add(spielerTO);
				}
		    	
		    }
		} catch (IOException e) {
		    Log.e(TAG, "Could not write file " + e.getMessage());
		}
		try{
			db = getWritableDatabase();
			if(null != db){
				if(deleteOldData){
						db.delete(WeihnachtsmarktTbl.TABLE_NAME, null, null);
				}

				final SQLiteStatement stmtInsertSpieler = db.compileStatement(WeihnachtsmarktTbl.STMT_ANLAGE_INSERT_WITH_ID);

				db.beginTransaction();
				try {
					for (WeihnachtsmarktVO spielerTO : spieler) {						
						stmtInsertSpieler.bindLong(1, spielerTO.id);
						stmtInsertSpieler.bindLong(3, 0);
						stmtInsertSpieler.bindString(5, spielerTO.name);
						stmtInsertSpieler.bindString(6, spielerTO.name== null ? "" : spielerTO.name);
						stmtInsertSpieler.executeInsert();
					}

					db.setTransactionSuccessful();
				} catch (Throwable ex) {
					Log.e(TAG, "Fehler beim Einf�gen eines Spieler-Datensatzes. " + ex);
				} finally {
					db.endTransaction();
				}
			 }
		} finally {
			db.close();
		}
		
	}

}
