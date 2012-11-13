/**
 * 
 */
package com.platzerworld.weihnachtsmarkt.db.weihnachtsmarkt;

import java.util.ArrayList;
import java.util.List;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteQueryBuilder;
import android.util.Log;

import com.platzerworld.weihnachtsmarkt.common.Sortierung;
import com.platzerworld.weihnachtsmarkt.common.logging.LOG;
import com.platzerworld.weihnachtsmarkt.db.WeihnachtsmarktDatenbank;
import com.platzerworld.weihnachtsmarkt.vo.WeihnachtsmarktVO;

/**
 * Der SpielerSpeicher ist die Schnittstelle zu persistenten Spielerdaten.
 * Die Spielerdaten sind in der Anwendungsdatenbank abgelegt. Die Anwendung sollte nur über den SpielerSpeicher auf gespeicherte Daten zugreifen.
 * Um den SpielerSpeicher erzeugen zu können, muss die aufrufende Android-Komponente ihren Context übergeben.
 * 
 * @author platzerg
 */
public class WeihnachtsmarktSpeicher {

	/** Markierung für Logging. */
	private static final String TAG = "SpielerSpeicher";
	private static final String WHERE_NAME_LIKE = WeihnachtsmarktTbl.NAME + " LIKE ?";
	private static final String WHERE_FAVORITEN = WeihnachtsmarktTbl.FAVORIT + " = ?";

	/** Verweis auf die Kegelverwaltung-Datenbank. */
	private WeihnachtsmarktDatenbank mDb;

	/**
	 * Erzeugt einen neuen SpielerSpeicher. 
	 * Dabei wird sichergestellt, dass die zugrundeliegende Datenbank unmittelbar nutzbar ist.
	 * 
	 * @param context Kontext der Anwendung, für die der Speicher gültig sein soll.
	 */
	public WeihnachtsmarktSpeicher(Context context) {
		mDb = new WeihnachtsmarktDatenbank(context, false);
		LOG.v(TAG, "Spielerspeicher angelegt.");
	}

	/**
	 * Erzeugung ohne Context nicht möglich.
	 */
	private WeihnachtsmarktSpeicher() {
	}
	
	/**
	 * Speicherung eines Spielers.
	 * 
	 * @param spielerVO enthält die Daten des Spielers
	 * @param isUpdate true dann update sonst insert
	 * 
	 * @return key Spieler
	 */
	public long speichereBiergarten(WeihnachtsmarktVO biergartenVO) {
		if (biergartenVO.istNeu()) {
			return speichereBiergartenAllColumns(biergartenVO);
		} else {
			aendereBiergartenAllColumns(biergartenVO);
			return biergartenVO.id;
		}
	}
	
	/**
	 * Legt einen neuen Spieler in der Datenbank an.
	 * 
	 * @param biergartenVO enthält die Daten des Spielers
	 * 
	 * @return key SpielerVO
	 */
	private long speichereBiergartenAllColumns(WeihnachtsmarktVO biergartenVO) {
		final ContentValues daten = new ContentValues();
		daten.put(WeihnachtsmarktTbl.NAME, biergartenVO.name);
		daten.put(WeihnachtsmarktTbl.OEFFNUNGSZEIT, biergartenVO.oeffnungszeit);
		daten.put(WeihnachtsmarktTbl.STRASSE, biergartenVO.strasse);
		daten.put(WeihnachtsmarktTbl.PLZ, biergartenVO.plz);
		daten.put(WeihnachtsmarktTbl.ORT, biergartenVO.ort);
		daten.put(WeihnachtsmarktTbl.TELEFON, biergartenVO.telefon);
		daten.put(WeihnachtsmarktTbl.EMAIL, biergartenVO.email);
		daten.put(WeihnachtsmarktTbl.URL, biergartenVO.url);
		daten.put(WeihnachtsmarktTbl.LATITUDE, biergartenVO.latitude);
		daten.put(WeihnachtsmarktTbl.LONGITUDE, biergartenVO.longitude);
		daten.put(WeihnachtsmarktTbl.DESC, biergartenVO.desc);
		
		daten.put(WeihnachtsmarktTbl.DESCLONG, biergartenVO.desclong);
		daten.put(WeihnachtsmarktTbl.GLUEHWEIN, biergartenVO.gluehwein);
		daten.put(WeihnachtsmarktTbl.LIEBLINGSGERICHT, biergartenVO.lieblingsgericht);
		daten.put(WeihnachtsmarktTbl.SPEISEKOMMENTAR, biergartenVO.speisenkommentar);
		daten.put(WeihnachtsmarktTbl.FAVORIT, String.valueOf(biergartenVO.favorit));

		final SQLiteDatabase dbCon = mDb.getWritableDatabase();

		try {
			final long id = dbCon.insertOrThrow(WeihnachtsmarktTbl.TABLE_NAME, null, daten);
			Log.i(TAG, "Biergarten mit id=" + id + " erzeugt.");
			return id;
		} finally {
			dbCon.close();
		}
	}
	
	/**
	 * Ändert einen vorhandenen Spieler in der Datenbank. 
	 * Wenn die id nicht mitgegeben wird, wird keine Änderung durchgeführt. 
	 * Es werden bei der Änderung alle Parameter berücksichtigt.
	 * 
	 * @param biergartenVO enthält die Daten des Spielers
	 */
	private void aendereBiergartenAllColumns(WeihnachtsmarktVO biergartenVO) {
		if (biergartenVO.istNeu()) {
			Log.w(TAG, "id == 0 => kein update möglich.");
			return;
		}

		final ContentValues daten = new ContentValues();
		daten.put(WeihnachtsmarktTbl.NAME, biergartenVO.name);
		daten.put(WeihnachtsmarktTbl.OEFFNUNGSZEIT, biergartenVO.oeffnungszeit);
		daten.put(WeihnachtsmarktTbl.STRASSE, biergartenVO.strasse);
		daten.put(WeihnachtsmarktTbl.PLZ, biergartenVO.plz);
		daten.put(WeihnachtsmarktTbl.ORT, biergartenVO.ort);
		daten.put(WeihnachtsmarktTbl.TELEFON, biergartenVO.telefon);
		daten.put(WeihnachtsmarktTbl.EMAIL, biergartenVO.email);
		daten.put(WeihnachtsmarktTbl.URL, biergartenVO.url);
		daten.put(WeihnachtsmarktTbl.LATITUDE, biergartenVO.latitude);
		daten.put(WeihnachtsmarktTbl.LONGITUDE, biergartenVO.longitude);
		daten.put(WeihnachtsmarktTbl.DESC, biergartenVO.desc);
		
		daten.put(WeihnachtsmarktTbl.DESCLONG, biergartenVO.desclong);
		daten.put(WeihnachtsmarktTbl.GLUEHWEIN, biergartenVO.gluehwein);
		daten.put(WeihnachtsmarktTbl.LIEBLINGSGERICHT, biergartenVO.lieblingsgericht);
		daten.put(WeihnachtsmarktTbl.SPEISEKOMMENTAR, biergartenVO.speisenkommentar);
		daten.put(WeihnachtsmarktTbl.FAVORIT, String.valueOf(biergartenVO.favorit));

		final SQLiteDatabase dbCon = mDb.getWritableDatabase();

		try {
			dbCon.update(WeihnachtsmarktTbl.TABLE_NAME, daten, WeihnachtsmarktTbl.WHERE_ID_EQUALS, new String[] { String.valueOf(biergartenVO.id) });
			Log.i(TAG, "Biergarten id=" + biergartenVO.id + " aktualisiert.");
		} finally {
			dbCon.close();
		}
	}

	/**
	 * Entfernt einen Spieler aus der Datenbank.
	 * 
	 * @param id Schlüssel des gesuchten Spielers
	 * @return true, wenn Datensatz geloescht wurde.
	 */
	public boolean loescheBiergartenById(long id) {
		final SQLiteDatabase dbCon = mDb.getWritableDatabase();

		int anzahlLoeschungen = 0;
		try {
			anzahlLoeschungen = dbCon.delete(WeihnachtsmarktTbl.TABLE_NAME, WeihnachtsmarktTbl.WHERE_ID_EQUALS,
					new String[] { String.valueOf(id) });
			Log.i(TAG, "Biergarten id=" + id + " gelöscht.");
		} finally {
			dbCon.close();
		}
		return anzahlLoeschungen == 1;
	}
	
	/**
	 * Entfernt alle Spieler zur mannschafts_id aus der Datenbank.
	 * 
	 * @param mannschaftId Mannschafts-ID
	 * @return true, wenn Datensatz geloescht wurde.
	 */
	public boolean loescheAlleSpielerMitNamen(String name) {
		final SQLiteDatabase dbCon = mDb.getWritableDatabase();

		int anzahlLoeschungen = 0;
		try {
			anzahlLoeschungen = dbCon.delete(WeihnachtsmarktTbl.TABLE_NAME, WeihnachtsmarktTbl.WHERE_NAME_EQUALS,
					new String[] { String.valueOf(name) });
			Log.i(TAG, "Biergarten Name=" + name + " gelöscht.");
		} finally {
			dbCon.close();
		}
		return anzahlLoeschungen == 1;
	}

	/**
	 * Liefert einen Cursor zur ID auf alle Felder der Spieler-Tabelle zurück.
	 * 
	 * @param id Schlüssel des gesuchten Spielers
	 * 
	 * @return Cursor, oder null
	 */
	public Cursor ladeSpielerDetails(long id) {
		return mDb.getReadableDatabase().query(WeihnachtsmarktTbl.TABLE_NAME, WeihnachtsmarktTbl.ALL_COLUMNS,
				WeihnachtsmarktTbl.WHERE_ID_EQUALS, new String[] { String.valueOf(id) }, null, null, null);
	}

	/**
	 * Liefert ein Value-Object SpielerVO zur id mit allen Feldern der Spieler-Tabelle zurück.
	 * 
	 * @param id Schlüssel des gesuchten Spielers
	 * 
	 * @return WettkampfVO, oder null
	 */
	public WeihnachtsmarktVO ladeBiergartenVO(long id) {
		WeihnachtsmarktVO kontakt = null;
		Cursor c = null;
		try {
			c = mDb.getReadableDatabase().query(WeihnachtsmarktTbl.TABLE_NAME, WeihnachtsmarktTbl.ALL_COLUMNS,
					WeihnachtsmarktTbl.WHERE_ID_EQUALS, new String[] { String.valueOf(id) }, null, null, null);
			if (c.moveToFirst() == false) {
				return null;
			}
			kontakt = ladeBiergarten(c);
		} finally {
			if (c != null) {
				c.close();
			}
		}
		return kontakt;
	}

	/**
	 * Liefert einen Cursor zum Namen auf alle Felder der Spieler-Tabelle zurück.
	 * 
	 * @param name Name des gesuchten Spielers
	 * 
	 * @return Cursor, oder null
	 */
	public Cursor ladeBiergartenDetails(String name) {
		if (name == null) {
			return null;
		}
		return mDb.getReadableDatabase().query(WeihnachtsmarktTbl.TABLE_NAME, WeihnachtsmarktTbl.ALL_COLUMNS, WeihnachtsmarktTbl.WHERE_NAME_EQUALS,
				new String[] { name }, null, null, WeihnachtsmarktTbl.DEFAULT_SORT_ORDER);
	}

	/**
	 * Liefert alle SpielerVO's sortiert nach Name zurück.
	 * 
	 * Es kann (optional) ein Filterkriterium angegeben werden. 
	 * Wenn dernamensFilterdefiniert ist, werden nur Spieler geliefert, deren NAME mit diesem Buchstaben beginnt.
	 * 
	 * @param namensFilter Anfangsbuchstaben (case sensitive) des zu suchenden Vereins.
	 * 
	 * @return Cursor auf die Ergebnisliste.
	 */
	public Cursor ladeBiergartenListe(CharSequence namensFilter) {
		return ladeBiergartenListe(Sortierung.STANDARD, namensFilter);
	}

	/**
	 * Liefert alle Spieler mit einstellbarer Sortierung zurück.
	 * Es kann (optional) ein Filterkriterium angegeben werden. Wenn der namensFilter definiert ist, 
	 * werden nur Spieler geliefert, deren NAME mit diesem Buchstaben beginnt.
	 * 
	 * @param sortierung Art der Sortierung
	 * @param namensFilter  Anfangsbuchstaben (case sensitive) der zu suchenden Spieler.
	 * @return Cursor auf die Ergebnisliste.
	 */
	public Cursor ladeBiergartenListe(Sortierung sortierung, CharSequence namensFilter) {
		final SQLiteQueryBuilder biergartenSuche = new SQLiteQueryBuilder();
		biergartenSuche.setTables(WeihnachtsmarktTbl.TABLE_NAME);
		String[] whereAttribs = null;
		if (namensFilter != null && namensFilter.length() > 0) {
			biergartenSuche.appendWhere(WeihnachtsmarktTbl.WHERE_NAME_LIKE);
			whereAttribs = new String[] { namensFilter + "%" };
		}

		return biergartenSuche.query(mDb.getReadableDatabase(), WeihnachtsmarktTbl.ALL_COLUMNS, null, whereAttribs, null, null,
				getBiergartenSortierung(sortierung));
	}
	
	/**
	 * Liefert ein Value-Object SpielerVO zur id mit allen Feldern der Spieler-Tabelle zurück.
	 * 
	 * @param sortierung Art der Sortierung
	 * @param namensFilter  Anfangsbuchstaben (case sensitive) der zu suchenden Spieler.
	 * 
	 * @return SpielerVO, oder null
	 */
	public ArrayList<WeihnachtsmarktVO> ladeBiergartenBySuchkriterium(Sortierung sortierung, WeihnachtsmarktVO biergartenVOSearch) {
		ArrayList<WeihnachtsmarktVO> biergartenVOs = new ArrayList<WeihnachtsmarktVO>();
		WeihnachtsmarktVO biergartenVO = null;
		Cursor c = null;
		
		final SQLiteQueryBuilder biergartenSuche = new SQLiteQueryBuilder();
		biergartenSuche.setTables(WeihnachtsmarktTbl.TABLE_NAME);
		
		List<String> searchQuery = new ArrayList<String>();
		
		
		
		if(null != biergartenVOSearch){
			biergartenSuche.appendWhere("ID > 0");
			
			if (biergartenVOSearch.name  != null && biergartenVOSearch.name .length() > 0) {
				biergartenSuche.appendWhere(WeihnachtsmarktTbl.AND_WHERE_NAME_LIKE);
				searchQuery.add( biergartenVOSearch.name  + "%" );
			}
			
			String[] queryPar = searchQuery.toArray(new String[searchQuery.size()]);  
			
			
			try {
				c = biergartenSuche.query(mDb.getReadableDatabase(), WeihnachtsmarktTbl.ALL_COLUMNS, null, queryPar, null, null,
						getBiergartenSortierung(Sortierung.NAME));
				
				if (c != null ) {
		    		if  (c.moveToFirst()) {
		    			do {    				
		    				biergartenVO= new WeihnachtsmarktVO();    				
		    				biergartenVO.key = c.getLong(c.getColumnIndex(WeihnachtsmarktTbl.ID));
		    				biergartenVO.id = c.getLong(c.getColumnIndex(WeihnachtsmarktTbl.ID));
		    				biergartenVO.name = c.getString(c.getColumnIndex(WeihnachtsmarktTbl.NAME));    
		    				biergartenVO.value = c.getString(c.getColumnIndex(WeihnachtsmarktTbl.NAME));
		    				
		    				biergartenVOs.add(biergartenVO);
		    			}while (c.moveToNext());
		    		} 
		    	}
			} finally {
				if (c != null) {
					c.close();
				}
			}
		}
		
		
		return biergartenVOs;
	}
	
	/**
	 * Liefert ein Value-Object SpielerVO zur id mit allen Feldern der Spieler-Tabelle zurück.
	 * 
	 * @param sortierung Art der Sortierung
	 * @param namensFilter  Anfangsbuchstaben (case sensitive) der zu suchenden Spieler.
	 * 
	 * @return SpielerVO, oder null
	 */
	public WeihnachtsmarktVO ladeBiergartenByName(Sortierung sortierung, CharSequence namensFilter) {
		WeihnachtsmarktVO biergartenVO = null;
		Cursor c = null;
		
		final SQLiteQueryBuilder spielerSuche = new SQLiteQueryBuilder();
		spielerSuche.setTables(WeihnachtsmarktTbl.TABLE_NAME);
		String[] whereAttribs = null;
		if (namensFilter != null && namensFilter.length() > 0) {
			spielerSuche.appendWhere(WeihnachtsmarktTbl.WHERE_NAME_LIKE);
			whereAttribs = new String[] { namensFilter + "%" };
		}
		
		try {
			c = spielerSuche.query(mDb.getReadableDatabase(), WeihnachtsmarktTbl.ALL_COLUMNS, null, whereAttribs, null, null,
					getBiergartenSortierung(Sortierung.NAME));
			
			if (c.moveToFirst() == false) {
				return null;
			}
			biergartenVO = ladeBiergarten(c);
		} finally {
			if (c != null) {
				c.close();
			}
		}
		
		return biergartenVO;
	}
		
	/**
	 * Lädt die Spieler aus dem SpielerTbl-Datensatz, auf dem der Cursor gerade steht.
	 * Der Cursor wird anschließend deaktiviert, da er im SpielerSpeicher nur intern als "letzter Aufruf" aufgerufen wird.
	 * 
	 * @param c aktuelle Cursorposition
	 * 
	 * @return ArrayList<SpielerVO> spieler
	 */
	public ArrayList<WeihnachtsmarktVO> ladeBiergartenKeyValueVO(Cursor c) {	
		ArrayList<WeihnachtsmarktVO> biergartenVOs = new ArrayList<WeihnachtsmarktVO>();
		WeihnachtsmarktVO biergartenVO = null;
		
		try{
			if (c != null ) {
	    		if  (c.moveToFirst()) {
	    			do {    				
	    				biergartenVO= new WeihnachtsmarktVO();    				
	    				biergartenVO.key = c.getLong(c.getColumnIndex(WeihnachtsmarktTbl.ID));
	    				biergartenVO.id = c.getLong(c.getColumnIndex(WeihnachtsmarktTbl.ID));
	    				biergartenVO.name = c.getString(c.getColumnIndex(WeihnachtsmarktTbl.NAME));    
	    				biergartenVO.oeffnungszeit = c.getString(c.getColumnIndex(WeihnachtsmarktTbl.OEFFNUNGSZEIT));  
	    				biergartenVO.value = c.getString(c.getColumnIndex(WeihnachtsmarktTbl.NAME));
	    				
	    				
	    				biergartenVO.strasse = c.getString(c.getColumnIndex(WeihnachtsmarktTbl.STRASSE));    
						biergartenVO.plz = c.getString(c.getColumnIndex(WeihnachtsmarktTbl.PLZ));    
						biergartenVO.ort = c.getString(c.getColumnIndex(WeihnachtsmarktTbl.ORT));    
						biergartenVO.telefon = c.getString(c.getColumnIndex(WeihnachtsmarktTbl.TELEFON));    
						biergartenVO.email = c.getString(c.getColumnIndex(WeihnachtsmarktTbl.EMAIL));    
						biergartenVO.url = c.getString(c.getColumnIndex(WeihnachtsmarktTbl.URL));    
						biergartenVO.latitude = c.getString(c.getColumnIndex(WeihnachtsmarktTbl.LATITUDE));    
						biergartenVO.longitude= c.getString(c.getColumnIndex(WeihnachtsmarktTbl.LONGITUDE));    
						biergartenVO.desc = c.getString(c.getColumnIndex(WeihnachtsmarktTbl.DESC));    
						biergartenVO.favorit = Boolean.parseBoolean(c.getString(c.getColumnIndex(WeihnachtsmarktTbl.FAVORIT)));    
						
						biergartenVO.gluehwein= c.getString(c.getColumnIndex(WeihnachtsmarktTbl.GLUEHWEIN));   
						biergartenVO.lieblingsgericht= c.getString(c.getColumnIndex(WeihnachtsmarktTbl.LIEBLINGSGERICHT));  
						biergartenVO.speisenkommentar= c.getString(c.getColumnIndex(WeihnachtsmarktTbl.SPEISEKOMMENTAR));   
						
	    				
	    				biergartenVOs.add(biergartenVO);
	    			}while (c.moveToNext());
	    		} 
	    	}
		} finally {
			if (c != null) {
				c.close();
			}
		}
		

		return biergartenVOs;
	}
	
	/**
	 * Lädt die Spieler aus dem SpielerTbl-Datensatz, auf dem der Cursor gerade steht.
	 * Der Cursor wird anschließend deaktiviert, da er im Spielerpeicher nur intern als "letzter Aufruf" aufgerufen wird.
	 * 
	 * @return List<String[]> spieler als String-Array
	 */
	public List<String[]> ladeBiergartenAsStringArray() {
		Cursor c = ladeBiergartenListe(null);
		List<String[]> spielerAsArray = new ArrayList<String[]>();
		try{
			if (c != null ) {
	    		if  (c.moveToFirst()) {
	    			do {    	
	    				String[] spielerZeile = new String[2];
	    				// ID, PASS_NR, MANNSCHAFT_ID, AUSHELFER_MANNSCHAFT_ID, NAME, VORNAME, GEB_DATUM, LOC_LATITUDE, LOC_LONGITUDE
	    				spielerZeile[0] = String.valueOf(c.getLong(c.getColumnIndex(WeihnachtsmarktTbl.ID)));
	    				spielerZeile[1] = c.getString(c.getColumnIndex(WeihnachtsmarktTbl.NAME));
	    				
	    				spielerAsArray.add(spielerZeile);
	    			}while (c.moveToNext());
	    		} 
	    	}
		} finally {
			if (c != null) {
				c.close();
			}
		}

		return spielerAsArray;
	}

	/**
	 * Liefert die Sortierung unter Berücksichtigung der Standard-Sortierung der Spielertabelle.
	 * 
	 * @param sortierung Sortierung als enum.
	 * @return Sortierung als ORDER_BY kompatible Anweisung.
	 */
	private static String getBiergartenSortierung(Sortierung sortierung) {
		String sortiertNach = WeihnachtsmarktTbl.DEFAULT_SORT_ORDER;
		switch (sortierung) {
		case NAME:
			sortiertNach = WeihnachtsmarktTbl.NAME;
			break;
		default:
			break;
		}
		return sortiertNach;
	}

	/**
	 * Lädt den Spieler aus dem SpielerTbl-Datensatz, auf dem der Cursor gerade steht.
	 * Der Cursor wird anschließend deaktiviert, da er im SpielerSpeicher nur intern als "letzter Aufruf" aufgerufen wird.
	 * 
	 * @param c aktuelle Cursorposition
	 * 
	 * @return SpielerVO
	 */
	public WeihnachtsmarktVO ladeBiergarten(Cursor c) {
		final WeihnachtsmarktVO biergartenVO = new WeihnachtsmarktVO();

		biergartenVO.id = c.getLong(c.getColumnIndex(WeihnachtsmarktTbl.ID));
		biergartenVO.key = biergartenVO.id;
		
		biergartenVO.name = c.getString(c.getColumnIndex(WeihnachtsmarktTbl.NAME));
		biergartenVO.value = c.getString(c.getColumnIndex(WeihnachtsmarktTbl.NAME));
		
		biergartenVO.favorit = Boolean.valueOf(c.getString(c.getColumnIndex(WeihnachtsmarktTbl.FAVORIT)));
		
		return biergartenVO;
	}
	
	/**
	 * Liefert alle Klassen sortiert nach NAME zurück. 
	 * Es kann (optional) ein Filterkriterium angegeben werden. 
	 * Wenn der namensFilter definiert ist, werden nur Klassen geliefert, deren NAME mit diesem Buchstaben beginnt.
	 * 
	 * @return Cursor auf die Klassenliste.
	 */
	public List<WeihnachtsmarktVO> ladeAlleBiergartenListeVO() {
		return ladeBiergartenListeVO(Sortierung.STANDARD, null);
	}
			
	/**
	 * Liefert alle Klassen mit einstellbarer Sortierung zurück. 
	 * Es kann (optional) ein Filterkriterium angegeben werden. 
	 * Wenn der namensFilter definiert ist, werden nur Klassen geliefert, deren NAME mit diesem Buchstaben beginnt.
	 * 
	 * @param sortierung Art der Sortierung
	 * @param namensFilter Anfangsbuchstaben (case sensitive) der zu suchenden Kontakte.
	 * @return Cursor auf die Klassenliste.
	 */
	public List<WeihnachtsmarktVO> ladeBiergartenListeVO(Sortierung sortierung, CharSequence namensFilter) {
		try{
			final SQLiteQueryBuilder klassenSuche = new SQLiteQueryBuilder();
			klassenSuche.setTables(WeihnachtsmarktTbl.TABLE_NAME);
			String[] whereAttribs = null;
			if (namensFilter != null && namensFilter.length() > 0) {
				klassenSuche.appendWhere(WHERE_NAME_LIKE);
				whereAttribs = new String[] { namensFilter + "%" };
			}
			Cursor klassenCursor = klassenSuche.query(mDb.getReadableDatabase(), WeihnachtsmarktTbl.ALL_COLUMNS, null, whereAttribs, null, null,
					getBiergartenSortierung(sortierung));
			
			
			return ladeBiergartenKeyValueVO(klassenCursor);
		}finally{
			schliessen();
		}
	}
	
	/**
	 * Liefert alle Klassen sortiert nach NAME zurück. 
	 * Es kann (optional) ein Filterkriterium angegeben werden. 
	 * Wenn der namensFilter definiert ist, werden nur Klassen geliefert, deren NAME mit diesem Buchstaben beginnt.
	 * 
	 * @return Cursor auf die Klassenliste.
	 */
	public List<WeihnachtsmarktVO> ladeAlleBiergartenFavoritenListeVO() {
		return ladeBiergartenFavoritenListeVO(Sortierung.STANDARD, null);
	}
	/**
	 * Liefert alle Klassen mit einstellbarer Sortierung zurück. 
	 * Es kann (optional) ein Filterkriterium angegeben werden. 
	 * Wenn der namensFilter definiert ist, werden nur Klassen geliefert, deren NAME mit diesem Buchstaben beginnt.
	 * 
	 * @param sortierung Art der Sortierung
	 * @param namensFilter Anfangsbuchstaben (case sensitive) der zu suchenden Kontakte.
	 * @return Cursor auf die Klassenliste.
	 */
	public List<WeihnachtsmarktVO> ladeBiergartenFavoritenListeVO(Sortierung sortierung, CharSequence namensFilter) {
		try{
			final SQLiteQueryBuilder klassenSuche = new SQLiteQueryBuilder();
			klassenSuche.setTables(WeihnachtsmarktTbl.TABLE_NAME);
			String[] whereAttribs = null;
			klassenSuche.appendWhere(WHERE_FAVORITEN);
			whereAttribs = new String[] { "true" };
			Cursor klassenCursor = klassenSuche.query(mDb.getReadableDatabase(), WeihnachtsmarktTbl.ALL_COLUMNS, null, whereAttribs, null, null,
					getBiergartenSortierung(sortierung));
			
			
			return ladeBiergartenKeyValueVO(klassenCursor);
		}finally{
			schliessen();
		}
	}

	/**
	 * Schliesst die zugrundeliegende Datenbank. Vor dem naechsten Zugriff muss oeffnen() aufgerufen werden.
	 */
	public void schliessen() {
		mDb.close();
		LOG.v(TAG, "Datenbank Biergarten geschlossen.");
	}

	/**
	 * Oeffnet die Datenbank, falls sie vorher mit schliessen() geschlossen wurde.
	 * Bei Bedarf wird das Schema angelegt bzw. aktualisiert.
	 */
	public void oeffnen() {
		mDb.getReadableDatabase();
		LOG.v(TAG, "Datenbank Biergarten geoeffnet.");
	}

	/**
	 * Gibt die Anzahl der Geokontakte in der Datenbank zurueck.
	 * Performanter als Cursor::getCount.
	 * 
	 * @return Anzahl der Spieler.
	 */
	public int anzahlBiergarten() {
		final Cursor c = mDb.getReadableDatabase().rawQuery("select count(*) from " + WeihnachtsmarktTbl.TABLE_NAME, null);
		if (c.moveToFirst() == false) {
			return 0;
		}
		return c.getInt(0);
	}

}
