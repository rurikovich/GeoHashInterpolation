package org.rurik.geohash;

/**
 * Created by Yuri Rastegaev on 27.11.2016.
 */
public class GreatCircleArc {
    private GeoPoint startGeoPoint;
    private GeoPoint endGeoPoint;

    public GreatCircleArc(GeoPoint startGeoPoint, GeoPoint endGeoPoint) {
        this.startGeoPoint = startGeoPoint;
        this.endGeoPoint = endGeoPoint;
    }

    public Double computeLatitude(Double longitude) {
//        TODO
        return 0d;
    }

    public Double computeLongitude(Double latitude) {
//        TODO
        return 0d;
    }


}
