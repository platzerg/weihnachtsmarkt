package com.platzerworld.weihnachtsmarkt.common.map.overlay;

import java.io.Serializable;

import com.google.android.maps.GeoPoint;
import com.platzerworld.weihnachtsmarkt.vo.WeihnachtsmarktVO;

public class WeihnachtsmarktGeoPoint extends GeoPoint implements Serializable{
	private static final long serialVersionUID = -799570234672196147L;
	private WeihnachtsmarktVO biergartenVO;

	public WeihnachtsmarktGeoPoint(int latitudeE6, int longitudeE6) {
		super(latitudeE6, longitudeE6);
		// TODO Auto-generated constructor stub
	}
	
	public WeihnachtsmarktGeoPoint(int latitudeE6, int longitudeE6, WeihnachtsmarktVO biergartenVO) {
		super(latitudeE6, longitudeE6);
		this.biergartenVO = biergartenVO;
	}

	public WeihnachtsmarktVO getBiergartenVO() {
		return biergartenVO;
	}

	public void setBiergartenVO(WeihnachtsmarktVO biergartenVO) {
		this.biergartenVO = biergartenVO;
	}
	
}
