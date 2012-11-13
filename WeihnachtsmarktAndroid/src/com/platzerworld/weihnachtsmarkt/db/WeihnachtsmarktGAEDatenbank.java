/**
 * 
 */
package com.platzerworld.weihnachtsmarkt.db;

import java.util.List;

import android.content.Context;
import android.database.sqlite.SQLiteStatement;
import android.util.Log;

import com.platzerworld.weihnachtsmarkt.db.weihnachtsmarkt.WeihnachtsmarktTbl;
import com.platzerworld.weihnachtsmarkt.vo.WeihnachtsmarktVO;


/**
 * Die Klasse dient als logische Verbindung zur Datenbank der Anwendung. Sie ist f�r die Erstellung und Wartung des Datenbankschemas 
 * sowie die Initialbef�llung der Tabellen verantwortlich.
 * 
 * Die Stammdaten werden �ber die Google App Engine Anwendung https://kegelverwaltung.appspot.com/ geliefert.
 * 
 * @author platzerg
 */
public class WeihnachtsmarktGAEDatenbank extends WeihnachtsmarktDatenbank {

	/** Markierung f�r Logging. */
	private static final String TAG = "BiergartenGAEDatenbank";

	/**
	 * Die Datenbank kann nur nach Kenntnis "ihrer" Anwendung verwaltet werden.
	 * Daher muss der Context der Anwendung �bergeben werden.
	 * 
	 * @param context Context der aufrufenden Anwendung.
	 */
	public WeihnachtsmarktGAEDatenbank(Context context) {
		super(context);
	}
		


	/**
	 * Hier werden die Stammdaten f�r die Spieler erzeugt.
	 * 
	 * @param spieler die ermittelten Spieler aus GAE
	 * @param deleteOldData true dann werden bestehende Daten gel�scht
	 * 
	 */
	public void erzeugeBiergartenDaten(List<WeihnachtsmarktVO> biergaerten, boolean deleteOldData) {
		try{
			db = getWritableDatabase();
			if(null != db){
				if(deleteOldData){
						db.delete(WeihnachtsmarktTbl.TABLE_NAME, null, null);
				}

				//ID, Name, Oeffnungszeit, Strasse, Plz, Ort, Telefon, Email, Url, Latitude, Longitude, Desc, Favorit
				final SQLiteStatement stmtInsertSpieler = db.compileStatement(WeihnachtsmarktTbl.STMT_ANLAGE_INSERT_WITH_ID);

				db.beginTransaction();
				try {
					for (WeihnachtsmarktVO biergartenVO : biergaerten) {						
						stmtInsertSpieler.bindLong(1, 3);						
						stmtInsertSpieler.bindString(2, biergartenVO.name == null ? "" : biergartenVO.name);
						stmtInsertSpieler.bindString(3, biergartenVO.oeffnungszeit == null ? "" : biergartenVO.oeffnungszeit);
						stmtInsertSpieler.bindString(4, biergartenVO.strasse == null ? "" : biergartenVO.strasse);
						stmtInsertSpieler.bindString(5, biergartenVO.plz == null ? "" : biergartenVO.plz);
						stmtInsertSpieler.bindString(6, biergartenVO.ort == null ? "" : biergartenVO.ort);
						stmtInsertSpieler.bindString(7, biergartenVO.telefon == null ? "" : biergartenVO.telefon);						
						stmtInsertSpieler.bindString(8, biergartenVO.email == null ? "" : biergartenVO.email);
						stmtInsertSpieler.bindString(9, biergartenVO.url == null ? "" : biergartenVO.url);
						stmtInsertSpieler.bindString(10, biergartenVO.latitude == null ? "0" : biergartenVO.latitude);
						stmtInsertSpieler.bindString(11, biergartenVO.longitude == null ? "0" : biergartenVO.longitude);
						stmtInsertSpieler.bindString(12, biergartenVO.desc == null ? "" : biergartenVO.desc);
						stmtInsertSpieler.bindString(13, String.valueOf(biergartenVO.favorit));
						stmtInsertSpieler.executeInsert();
					}

					db.setTransactionSuccessful();
				} catch (Throwable ex) {
					Log.e(TAG, "Fehler beim Einfügen eines Biergarten-Datensatzes. " + ex);
				} finally {
					db.endTransaction();
				}
			 }
		} finally {
			db.close();
		}
		
	}

}
