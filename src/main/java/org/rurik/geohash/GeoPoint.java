package org.rurik.geohash;

/**
 * Created by Yuri Rastegaev on 27.11.2016.
 */
public class GeoPoint {
    private double latitude; // fi in math proof
    private double longitude; // lambda in math proof

    public GeoPoint(double latitude, double longitude) {
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public double getLatitude() {
        return latitude;
    }

    public double getLongitude() {
        return longitude;
    }
}
