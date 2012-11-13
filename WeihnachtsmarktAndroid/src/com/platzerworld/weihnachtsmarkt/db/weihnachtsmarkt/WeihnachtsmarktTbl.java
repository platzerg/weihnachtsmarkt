/**
 * 
 */
package com.platzerworld.weihnachtsmarkt.db.weihnachtsmarkt;

/**
 * Schnittstelle zur Tabelle Biergarten.
 * Die Klasse liefert SQL-Code zur Erzeugung der Tabelle SQL-Code für alle für Biergarten erforderlichen Statements
 * 
 * @author platzerg
 */
public final class WeihnachtsmarktTbl implements WeihnachtsmarktColumns {
	/**
	 * Name der Datenbanktabelle.
	 */
	public static final String TABLE_NAME = "weihnachtsmarkt";

	/**
	 * SQL Anweisung zur Schemadefinition.
	 */
	public static final String SQL_CREATE = "CREATE TABLE WEIHNACHTSMARKT (" +
			"ID INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, Name TEXT, " +
			"Oeffnungszeit TEXT, Strasse TEXT, Plz TEXT, Ort TEXT, Telefon TEXT, Email TEXT, Url TEXT, Latitude TEXT, Longitude TEXT, " +
			"Desc TEXT, Desclong TEXT, Gluehwein TEXT, Lieblingsgericht TEXT, Speisenkommentar TEXT, Favorit TEXT);";

	/**
	 * Standard-Sortierreihenfolge für die Tabelle.
	 * Sortiert wird nach Namen absteigend.
	 */
	public static final String DEFAULT_SORT_ORDER = NAME + " DESC";

	/**
	 * SQL Anweisung zur Löschung der Tabelle.
	 */
	public static final String SQL_DROP = "DROP TABLE IF EXISTS " + TABLE_NAME;

	/**
	 * SQL Anweisung für Erzeugung eines Minimal-Biergarten aus Name.
	 */
	public static final String STMT_MIN_INSERT = "INSERT INTO WEIHNACHTSMARKT " + "(name) " + "VALUES (?)";
	
	/**
	 * SQL Anweisung für Erzeugung eines Spielers aus den Stammdaten.
	 */
	public static final String STMT_ANLAGE_INSERT = "INSERT INTO WEIHNACHTSMARKT "
			+ "(Name, Oeffnungszeit, Strasse, Plz, Ort, Telefon, Email, Url, Latitude, Longitude, Desc, Favorit) " + "VALUES (?,?,?,?,?,?,?,?,?,?,?,?)";

	/**
	 * SQL Anweisung für Erzeugung eines Spielers aus den Stammdaten.
	 */
	public static final String STMT_ANLAGE_INSERT_WITH_ID = "INSERT INTO WEIHNACHTSMARKT "
			+ "(ID, Name, Oeffnungszeit, Strasse, Plz, Ort, Telefon, Email, Url, Latitude, Longitude, Desc, Favorit) " + "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

	/**
	 * SQL-Anweisung zur Lüschung aller Spieler.
	 */
	public static final String STMT_DELETE = "DELETE from WEIHNACHTSMARKT ";

	/**
	 * SQL-Anweisung zur Löchung eines Spielers anhand seines Schlüsselwerts.
	 */
	public static final String STMT_DELETE_BY_ID = "DELETE WEIHNACHTSMARKT " + "WHERE ID = ?";

	/**
	 * SQL-Anweisung zur Löschung eines Spielers anhand seiner mannschaft_id
	 */
	public static final String STMT_DELETE_BY_NAME = "DELETE WEIHNACHTSMARKT WHERE NAME = ?";

	/** Liste aller bekannten Attribute. */
	public static final String[] ALL_COLUMNS = new String[] { 
		ID, NAME, OEFFNUNGSZEIT, STRASSE, PLZ, ORT, TELEFON, EMAIL, URL, LATITUDE, LONGITUDE, DESC, DESCLONG, GLUEHWEIN, LIEBLINGSGERICHT, FAVORIT};

	/**
	 * WHERE-Bedingung für ID-Anfrage.
	 */
	public static final String WHERE_ID_EQUALS = ID + "=?";
	
	/**
	 * WHERE-Bedingung für NAME-Anfrage.
	 */
	public static final String WHERE_NAME_EQUALS = NAME + "=?";
	
	/**
	 * WHERE-Bedingung für NAME-Anfrage LIKE.
	 */
	public static final String WHERE_NAME_LIKE = NAME + " LIKE ?";
	
	/**
	 * WHERE-Bedingung für NAME-Anfrage LIKE.
	 */
	public static final String AND_WHERE_NAME_LIKE = " AND " +NAME + " LIKE ?";
	
		
	/**
	 * Klasse enthält nur Konstanten. Daher keine Objekterzeugung vorgesehen.
	 */
	private WeihnachtsmarktTbl() {
	}
}
