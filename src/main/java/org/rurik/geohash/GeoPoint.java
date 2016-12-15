package org.rurik.geohash;

/**
 * Created by Yuri Rastegaev on 27.11.2016.
 */
public class GeoPoint {
    /**
     * angles
     */
    private Double latitude; // fi in math proof
    private Double longitude; // lambda in math proof

    public GeoPoint(Double latitude, Double longitude) {
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public Double getLatitude() {
        return latitude;
    }

    public Double getLongitude() {
        return longitude;
    }
}
