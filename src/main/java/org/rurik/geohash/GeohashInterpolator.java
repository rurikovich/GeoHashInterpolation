package org.rurik.geohash;

/**
 * Created by Yuri Rastegaev on 25.11.2016.
 */
public class GeohashInterpolator {

    public GeoPoint[] computeGeohashGridIntersections(int geohashGridPrecision, GeoPoint startPoint, GeoPoint endPoint) {
        return computeGeohashGridIntersections(geohashGridPrecision, new GreatCircleArc(startPoint, endPoint));
    }

    public GeoPoint[] computeGeohashGridIntersections(int geohashGridPrecision, GreatCircleArc greatCircleArc) {
        //  TODO
        return null;
    }

    public GeoPoint[] computeGeohashInterpolation(GeoPoint[] geohashGridIntersections) {
        //  TODO
        return null;
    }
}
