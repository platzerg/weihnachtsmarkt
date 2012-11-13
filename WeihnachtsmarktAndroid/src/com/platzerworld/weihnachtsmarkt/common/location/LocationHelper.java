package com.platzerworld.weihnachtsmarkt.common.location;

public final class LocationHelper {	
	public static String getUri(double fromLat, double fromLon, double toLat, double toLon) {// connect to map web service
	    StringBuffer urlString = new StringBuffer();
	    urlString.append("http://maps.google.com/maps");
	    urlString.append("?&saddr=");// from
	    urlString.append(fromLat);
	    urlString.append(",");
	    urlString.append(fromLon);
	    urlString.append("&daddr=");// to
	    urlString.append(toLat);
	    urlString.append(",");
	    urlString.append(toLon);
	    return urlString.toString();
	}
}
