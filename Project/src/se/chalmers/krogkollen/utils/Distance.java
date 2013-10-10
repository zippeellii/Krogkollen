package se.chalmers.krogkollen.utils;

import com.google.android.gms.maps.model.LatLng;

public class Distance {

	//TODO COMMENT THIS USING THIS http://en.wikipedia.org/wiki/Haversine_formula
	public static double calcDistBetweenTwoLatLng(LatLng first, LatLng second){
	    double earthRadius = 3958.75;
	    double dLat = Math.toRadians(second.latitude-first.latitude);
	    double dLng = Math.toRadians(second.longitude-first.longitude);
	    double sindLat = Math.sin(dLat / 2);
	    double sindLng = Math.sin(dLng / 2);
	    double a = Math.pow(sindLat, 2) + Math.pow(sindLng, 2)
	            * Math.cos(Math.toRadians(first.latitude)) * Math.cos(Math.toRadians(second.latitude));
	    double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1-a));
	    double dist = earthRadius * c;

	    return dist;
	    }
}
