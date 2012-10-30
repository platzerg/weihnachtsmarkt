package com.platzerworld.weihnachtsmarkt.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Weihnachtsmarkt {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public Long id;
	
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
	public String descLong;
	public String gluehwein;
	public String lieblingsgericht;
	public String speisekommentar;
	public boolean favorit;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	public String getOeffnungszeit() {
		return oeffnungszeit;
	}
	public void setOeffnungszeit(String oeffnungszeit) {
		this.oeffnungszeit = oeffnungszeit;
	}
	public String getGluehwein() {
		return gluehwein;
	}
	public void setGluehwein(String gluehwein) {
		this.gluehwein = gluehwein;
	}
	public String getStrasse() {
		return strasse;
	}
	public void setStrasse(String strasse) {
		this.strasse = strasse;
	}
	public String getPlz() {
		return plz;
	}
	public void setPlz(String plz) {
		this.plz = plz;
	}
	public String getOrt() {
		return ort;
	}
	public void setOrt(String ort) {
		this.ort = ort;
	}
	public String getTelefon() {
		return telefon;
	}
	public void setTelefon(String telefon) {
		this.telefon = telefon;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getLatitude() {
		return latitude;
	}
	public void setLatitude(String latitude) {
		this.latitude = latitude;
	}
	public String getLongitude() {
		return longitude;
	}
	public void setLongitude(String longitude) {
		this.longitude = longitude;
	}
	public String getDesc() {
		return desc;
	}
	public void setDesc(String desc) {
		this.desc = desc;
	}
	public String getDescLong() {
		return descLong;
	}
	public void setDescLong(String descLong) {
		this.descLong = descLong;
	}
	public String getLieblingsgericht() {
		return lieblingsgericht;
	}
	public void setLieblingsgericht(String lieblingsgericht) {
		this.lieblingsgericht = lieblingsgericht;
	}
	public String getSpeisekommentar() {
		return speisekommentar;
	}
	public void setSpeisekommentar(String speisekommentar) {
		this.speisekommentar = speisekommentar;
	}
	public boolean isFavorit() {
		return favorit;
	}
	public void setFavorit(boolean favorit) {
		this.favorit = favorit;
	}
	
	
}