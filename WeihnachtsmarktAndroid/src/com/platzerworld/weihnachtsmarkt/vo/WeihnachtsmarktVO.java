package com.platzerworld.weihnachtsmarkt.vo;

import java.util.HashMap;

import com.platzerworld.weihnachtsmarkt.common.KeyValueVO;
import com.platzerworld.weihnachtsmarkt.db.weihnachtsmarkt.WeihnachtsmarktColumns;



/**
 * Value-Object SpielerVO
 * 
 * @author platzerg
 */
public class WeihnachtsmarktVO extends KeyValueVO{
	private static final long serialVersionUID = -8854310183206811021L;

	public long id;

	public String name;
	
	public String oeffnungszeit;
	
	public String strasse;
	
	public String plz;
	
	public String ort;
	
	public String telefon;
	
	public String email;
	
	public String url;
	
	public String latitude;
	
	public String longitude;
	
	public String desc;
	
	public String desclong;
	
	public String gluehwein;
	
	public String lieblingsgericht;
	
	public String speisenkommentar;
	
	public boolean favorit;
	
		
	public WeihnachtsmarktVO(){
		id=0;
		name="";
		oeffnungszeit="";
		strasse="";
		plz="";
		ort="";
		telefon="";
		email="";
		url="";
		latitude="";
		longitude="";
		desclong="";
		gluehwein="";
		lieblingsgericht="";
		speisenkommentar="";
		favorit = true;
	}
	
	@Override
	public String toString() {		
		StringBuffer bf = new StringBuffer("em = EMFService.get().createEntityManager();").append("\n");
		bf.append("bg = new Biergarten();").append("\n");
		bf.append("bg.name=\"").append(this.name).append("\";").append("\n");
		bf.append("bg.oeffnungszeit=\"").append(this.oeffnungszeit).append("\";").append("\n");
		bf.append("bg.strasse=\"").append(this.strasse).append("\";").append("\n");
		bf.append("bg.plz=\"").append(this.plz).append("\";").append("\n");
		bf.append("bg.ort=\"").append(this.ort).append("\";").append("\n");
		bf.append("bg.telefon=\"").append(this.telefon).append("\";").append("\n");
		bf.append("bg.email=\"").append(this.email).append("\";").append("\n");
		bf.append("bg.url=\"").append(this.url).append("\";").append("\n");
		bf.append("bg.latitude=\"").append(this.latitude).append("\";").append("\n");
		bf.append("bg.longitude=\"").append(this.longitude).append("\";").append("\n");
		bf.append("bg.desc=\"").append(this.desc).append("\";").append("\n");
		bf.append("bg.favorit=").append(String.valueOf(this.favorit)).append(";").append("\n");
		bf.append("em.persist(bg);").append("\n");
		bf.append("em.close();").append("\n").append("\n");
		
		return bf.toString();
	}
	
	public WeihnachtsmarktVO(long key, String value){
		super(key, value);
	}

	public WeihnachtsmarktVO(String name, String latitude, String longitude){		
		this.name = name;
		this.latitude = latitude;
		this.longitude = longitude;
	}
	
	
	public WeihnachtsmarktVO(HashMap<String, String> selHashMap){
		this.id = Integer.parseInt(selHashMap.get(WeihnachtsmarktColumns.ID));
		this.key = id;
		this.name = selHashMap.get(WeihnachtsmarktColumns.NAME);
		this.value = selHashMap.get(WeihnachtsmarktColumns.NAME);
	}
	
	/**
	 * Zeigt an, ob der Spieler bereits gespeichert wurde.
	 * 
	 * @return true, wenn der Spieler in der Datenbank vorhanden ist.
	 */
	public boolean istNeu() {
		return id == 0;
	}

	public String getLatitude() {
		return latitude.replaceAll(",", ".");
	}

	public void setLatitude(String latitude) {
		this.latitude = latitude;
	}

	public String getLongitude() {
		return longitude.replaceAll(",", ".");
	}

	public void setLongitude(String longitude) {
		this.longitude = longitude;
	}
}
