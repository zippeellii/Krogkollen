package se.chalmers.krogkollen.utils;

import com.google.android.gms.maps.model.LatLng;

/**
 * Class providing distance calculating methods.
 */
public class Distance {

    /**
     * Calculate the distance between two points on earth using the Haversine formula. Since we need the great-circle
     * distance from points latitude and longitude this formula is the most efficient and correct.
     *
     * The full description of the algorithm can be found here: http://en.wikipedia.org/wiki/Haversine_formula.
     *
     * @param first the first point.
     * @param second the second point.
     * @return the distance between the two points.
     */
    public static double calcDistBetweenTwoLatLng(LatLng first, LatLng second){
        double earthRadius = 3958.75;
        double dLat = Math.toRadians(second.latitude-first.latitude);
        double dLng = Math.toRadians(second.longitude-first.longitude);
        double sindLat = Math.sin(dLat / 2);
        double sindLng = Math.sin(dLng / 2);
        double a = Math.pow(sindLat, 2) + Math.pow(sindLng, 2)
                * Math.cos(Math.toRadians(first.latitude)) * Math.cos(Math.toRadians(second.latitude));
        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1-a));

        return earthRadius * c;
    }
}
