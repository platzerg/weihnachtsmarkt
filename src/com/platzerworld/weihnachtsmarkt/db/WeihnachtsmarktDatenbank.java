/**
 * 
 */
package com.platzerworld.weihnachtsmarkt.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteStatement;
import android.os.Environment;
import android.util.Log;

import com.platzerworld.weihnachtsmarkt.common.ConstantsIF;
import com.platzerworld.weihnachtsmarkt.db.weihnachtsmarkt.WeihnachtsmarktTbl;

/**
 * Die Klasse dient als logische Verbindung zur Datenbank der Anwendung. Sie ist f�r die Erstellung und Wartung des Datenbankschemas
 * sowie die Initialbef�llung der Tabellen verantwortlich.
 * Die Datenbank 'KegelverwaltungDatenbank' wird unter /sdcard/kegeln/kegelverwaltung.db gespeichert.
 * 
 * Die Klassen KegelverwaltungGAEDatenbank und KegelverwaltungCSVDatenbank erweitern die Klasse f�r GAE und CSV Import.
 * 
 * @author platzerg
 */
public class WeihnachtsmarktDatenbank extends SQLiteOpenHelper implements ConstantsIF {

	/** Markierung f�r Logging. */
	private static final String TAG = "BiergartenDatenbank";

	/** Name der Datenbankdatei. */
	//protected static final String DATENBANK_NAME = "/sdcard/kegeln/kegelverwaltung.db";

	protected static SQLiteDatabase db;
	protected final Context mContext;
	
	protected boolean createDB = true;
	
	/**
	 * Version des Schemas.
	 */
	protected static final int DATENBANK_VERSION = 1;

	/**
	 * Die Datenbank kann nur nach Kenntnis "ihrer" Anwendung verwaltet werden.
	 * Daher muss der Context der Anwendung �bergeben werden.
	 * 
	 * @param context Context der aufrufenden Anwendung.
	 */
	public WeihnachtsmarktDatenbank(Context context) {
		super(context, Environment.getExternalStorageDirectory().getAbsolutePath() +WEIHNACHTSMARKT_DB_FILE_PATH, null, DATENBANK_VERSION);
		
		mContext = context;
	}
	
	/**
	 * Die Datenbank kann nur nach Kenntnis "ihrer" Anwendung verwaltet werden.
	 * Daher muss der Context der Anwendung �bergeben werden.
	 * 
	 * @param context Context der aufrufenden Anwendung.
	 */
	public WeihnachtsmarktDatenbank(Context context, boolean createDB) {
		super(context, Environment.getExternalStorageDirectory().getAbsolutePath() +WEIHNACHTSMARKT_DB_FILE_PATH, null, DATENBANK_VERSION);
		mContext = context;
		this.createDB = createDB;
	}
	
	/**
	 * Wird aufgerufen, wenn das Datenbankschema neu angelegt werden soll. Die Tabellen werden angelegt.
	 * Anschlie�end wird die Initialbef�llung der Datenbank durchgeführt.
	 * 
	 * @param db aktuelle Datenbank-Verbindung
	 */
	@Override
	public void onCreate(SQLiteDatabase db) {
		WeihnachtsmarktDatenbank.db = db;
		if(createDB){
			initDatabase(mContext);
		}
		
	}
	
	/**
	 * L�schen und Erzeugung der Tabellen, sowie Initialbef�llung.
	 */
	public void resetDB(){
		SQLiteDatabase dbCon = null;
		try{
		 dbCon = getWritableDatabase();
			if(null != dbCon){
				db.execSQL(WeihnachtsmarktTbl.SQL_CREATE);
				
				dbCon.close();
				
				WeihnachtsmarktDatenbank.db = getWritableDatabase();
				initDatabase(mContext);
			}
		} finally {
			dbCon.close();
			if(WeihnachtsmarktDatenbank.db.isOpen()){
				WeihnachtsmarktDatenbank.db.close();
			}
		}
	}
	
	/**
	 * L�schen der Tabellen.
	 */
	public void deleteTable(){
		SQLiteDatabase dbCon = null;
		try{
		 dbCon = getWritableDatabase();
			if(null != dbCon){
				dbCon.execSQL(WeihnachtsmarktTbl.SQL_DROP);
				
				dbCon.close();
			}
		} finally {
			dbCon.close();
		}
	}
	
	/**
	 * Erzeugung der Tabellen.
	 */
	public void createTable(){
		SQLiteDatabase dbCon = null;
		try{
		 dbCon = getWritableDatabase();
			if(null != dbCon){
				dbCon.execSQL(WeihnachtsmarktTbl.SQL_CREATE);
				
				dbCon.close();
			}
		} finally {
			dbCon.close();
		}
	}
	
	/**
	 * L�schen der Datens�tze aus den Tabellen.
	 */
	public void deleteData(){
		SQLiteDatabase dbCon = null;
		try{
		 dbCon = getWritableDatabase();
			if(null != dbCon){
				dbCon.execSQL(WeihnachtsmarktTbl.STMT_DELETE);
				
				dbCon.close();
			}
		} finally {
			dbCon.close();
		}
	}
	
	/**
	 * Auf die Tabell sqlite_sequence ein reset durchf�hren.
	 */
	public void resetAutoincrements(){
		try{
			 db = getWritableDatabase();
				if(null != db){
					try {
						db.beginTransaction();
						
						final SQLiteStatement stmtResetKLasse = db.compileStatement("delete from sqlite_sequence;");						
						stmtResetKLasse.execute();
						db.setTransactionSuccessful();
					} catch (Throwable ex) {
						Log.e(TAG, "Fehler beim Reset des Autoincrements. " + ex);
					} finally {
						db.endTransaction();
					}

				}
			} finally {
				db.close();
			}
	}
	
	/**
	 * Einf�gen der Datens�tze.
	 */
	public void insertData(){
		try{
		 db = getWritableDatabase();
			if(null != db){
				erzeugeBiergartenDaten(db, false);
			}
		} finally {
			db.close();
		}
	}

	/**
	 * Erzeugen der Tabellen und Initialbef�llung, wobei bestehende Datens�tze gel�scht werden.
	 * 
	 * @param mContext Context der aufrufenden Anwendung.
	 */
	public void initDatabase(Context mContext) {
		db.execSQL(WeihnachtsmarktTbl.SQL_CREATE);
		//erzeugeBiergartenDaten(db, true);
		
	}

	/**
	 * Wird aufgerufen, wenn sich die Version des Schemas ge�ndert hat. In diesem Fall wird die 
	 * Datenbank gel�scht und mit neuem Schema wieder aufgebaut.
	 * 
	 * 
	 * @param db aktuelle Datenbank-Verbindung
	 * @param oldVersion bisherige Schemaversion
	 * @param newVersion neue Schemaversion
	 */
	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		Log.w(TAG, "Upgrading database from version " + oldVersion + " to " + newVersion
				+ ", which will destroy all old data");
		db.execSQL(WeihnachtsmarktTbl.SQL_DROP);
		onCreate(db);
	}


	/**
	 * Hier werden die Stammdaten f�r die Spieler erzeugt.
	 * 
	 * @param db aktuelle Datenbank-Verbindung.
	 * @param deleteOldData true dann werden bestehende Daten gel�scht
	 * 
	 */
	private static void erzeugeBiergartenDaten(SQLiteDatabase db, boolean deleteOldData) {
		// loesche vorhandene daten
		if(deleteOldData){
			db.delete(WeihnachtsmarktTbl.TABLE_NAME, null, null);
		}

		final SQLiteStatement stmtInsertSpieler = db.compileStatement(WeihnachtsmarktTbl.STMT_ANLAGE_INSERT);

		db.beginTransaction();
		
		// Name, Strasse, Plz, Ort, Telefon, Email, Url, Latitude, Longitude, Desc, Favorit

		try {
			stmtInsertSpieler.bindString(1, "Aumeister");
			stmtInsertSpieler.bindString(2, "Aumeister");
			stmtInsertSpieler.bindString(3, "Aumeister");
			stmtInsertSpieler.bindString(4, "Platzer");
			stmtInsertSpieler.bindString(5, "Aumeister");
			stmtInsertSpieler.bindString(6, "13.04.1972");
			stmtInsertSpieler.bindString(7, String.valueOf((int) (48.17968 * 1E6)));
			stmtInsertSpieler.bindString(8, String.valueOf((int) (11.5922 * 1E6)));
			stmtInsertSpieler.bindString(9, "Aumeister");
			stmtInsertSpieler.bindString(10, "Aumeister");
			stmtInsertSpieler.bindString(11, "Aumeister");
			//stmtInsertSpieler.executeInsert();
			
			
			stmtInsertSpieler.bindString(1, "Aumeister");
			stmtInsertSpieler.bindString(2, "Aumeister");
			stmtInsertSpieler.bindString(3, "Aumeister");
			stmtInsertSpieler.bindString(4, "Platzer");
			stmtInsertSpieler.bindString(5, "Aumeister");
			stmtInsertSpieler.bindString(6, "13.04.1972");
			stmtInsertSpieler.bindString(7, String.valueOf((int) (48.17968 * 1E6)));
			stmtInsertSpieler.bindString(8, String.valueOf((int) (11.5922 * 1E6)));
			stmtInsertSpieler.bindString(9, "Aumeister");
			stmtInsertSpieler.bindString(10, "Aumeister");
			stmtInsertSpieler.bindString(11, "Aumeister");
			//stmtInsertSpieler.executeInsert();
			
			db.setTransactionSuccessful();
		} catch (Throwable ex) {
			Log.e(TAG, "Fehler beim Einf�gen eines Spieler-Testdatensatzes. " + ex);
		} finally {
			db.endTransaction();
		}
	}

}
