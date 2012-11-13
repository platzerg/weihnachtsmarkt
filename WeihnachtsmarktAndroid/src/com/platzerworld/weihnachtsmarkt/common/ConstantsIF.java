package com.platzerworld.weihnachtsmarkt.common;

import java.io.Serializable;

public interface ConstantsIF extends Serializable {
	public static final int REQUEST_CODE_BIERGARTEN_DETAIL = 1;
	public static final int REQUEST_CODE_BIERGARTEN_DETAIL_GETRAENKE = 2;
	public static final int REQUEST_CODE_BIERGARTEN_DETAIL_SPEISEN = 3;
	
	public static final int REQUEST_CODE_BIERGARTEN_EINTRAGEN = 4;
	public static final int REQUEST_CODE_BIERGARTEN_EINSTELLUNGEN = 5;
	public static final int REQUEST_CODE_BIERGARTEN_FINDEN = 6;
	
	public static final String INTENT_EXTRA_BIERGARTEN_LISTE = "com.platzerworld.biergarten.BiergartenListe";
	public static final String INTENT_EXTRA_BIERGARTEN_FAVORITEN_LISTE = "com.platzerworld.biergarten.BiergartenFavoritenListe";
	
	public static final String INTENT_EXTRA_BIERGARTEN_DETAILS_MODUS = "com.platzerworld.biergarten.BiergartenDetailsModus";
	public static final String INTENT_EXTRA_BIERGARTEN_DETAILS_RESULT = "com.platzerworld.biergarten.BiergartenDetailsResult";
	public static final String INTENT_EXTRA_BIERGARTEN_EINTRAGEN_RESULT = "com.platzerworld.biergarten.BiergartenEintragenResult";
	public static final String INTENT_EXTRA_BIERGARTEN_FINDEN_RESULT = "com.platzerworld.biergarten.BiergartenFindenResult";
	public static final String INTENT_EXTRA_BIERGARTEN_DETAILS_BIERGARTENVO = "com.platzerworld.biergarten.BiergartenVO";
	
	public static final String PREFERENCE_KEY_RADIUS_ENABLED = "PREFERENCE_KEY_RADIUS_ENABLED";
	public static final String PREFERENCE_KEY_RADIUS_VALUE = "PREFERENCE_KEY_RADIUS_VALUE";
	
	public static final String PREFERENCE_KEY_RADIUS_COLOR = "PREFERENCE_KEY_RADIUS_COLOR";
	public static final String PREFERENCE_KEY_RADIUS_STROKE_WIDTH = "PREFERENCE_KEY_RADIUS_STROKE_WIDTH";
	public static final String PREFERENCE_KEY_RADIUS_ALPHA = "PREFERENCE_KEY_RADIUS_ALPHA";	
	public static final String PREFERENCE_KEY_PLZ_VALUE = "PREFERENCE_KEY_PLZ_VALUE";
	public static final String PREFERENCE_KEY_LOAD_DB = "PREFERENCE_KEY_LOAD_DB";
	public static final String PREFERENCE_KEY_LOAD_DB_FROM_CLOUD = "PREFERENCE_KEY_LOAD_DB_FROM_CLOUD";
	
	
	public static final String PREFERENCE_KEY_INDEX_KLASSE = "INDEX_KLASSE";
	public static final String INTENT_EXTRA_BIERGARTEN_DETAILS_OVERLAY_INDEX = "INTENT_EXTRA_BIERGARTEN_DETAILS_OVERLAY_INDEX";
	
	public static final String ASSETS_DB_WEIHNACHTSMARKT_FILE = "db/weihnachtsmaerkte.sqlite";
	public static final String WEIHNACHTSMARKT_DB_FILE_PATH = "/weihnachtsmarkt/weihnachtsmaerkte.sqlite";
	public static final String WEIHNACHTSMARKT_FOLDER = "/weihnachtsmarkt/";
	public static final String DATENBANK_NAME = "weihnachtsmaerkte.sqlite";
	
	
}
