package com.platzerworld.weihnachtsmarkt.vo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class BiergartenJSON  implements Serializable{
	private static final long serialVersionUID = 6869160557458781385L;
	
	private String biergartenid = "GPL";
	private List<BiergartenVO> biergartenListe = new ArrayList<BiergartenVO>();
	public String getBiergartenid() {
		return biergartenid;
	}
	public void setBiergartenid(String biergartenid) {
		this.biergartenid = biergartenid;
	}
	public List<BiergartenVO> getBiergartenListe() {
		return biergartenListe;
	}
	public void setBiergartenListe(List<BiergartenVO> biergartenListe) {
		this.biergartenListe = biergartenListe;
	}

}
