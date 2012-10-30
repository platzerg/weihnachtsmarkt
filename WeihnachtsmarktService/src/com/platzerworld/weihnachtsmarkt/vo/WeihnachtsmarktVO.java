package com.platzerworld.weihnachtsmarkt.vo;

import java.io.Serializable;



/**
 * Value-Object SpielerVO
 * 
 * @author platzerg
 */
public class WeihnachtsmarktVO implements Serializable{
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
		
	}
}
