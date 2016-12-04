package org.rurik.geohash;

import static java.lang.Math.*;

/**
 * Created by Yuri Rastegaev on 27.11.2016.
 */
public class GreatCircleArc {
    private double latitude1;
    private double longitude1;

    private double latitude2;
    private double longitude2;

    private double sin_diff_lon2_lon1;
    private double tan_lat1;
    private double tan_lat2;

    private double sqrt_sum_A2_B2;
    private double A_div_sqrt_sum_A2_B2;


    public GreatCircleArc(GeoPoint geoPoint1, GeoPoint geoPoint2) {
        latitude1 = geoPoint1.getLatitude();
        longitude1 = geoPoint1.getLongitude();
        latitude2 = geoPoint2.getLatitude();
        longitude2 = geoPoint2.getLongitude();
        preComputeConstants(latitude1, longitude1, latitude2, longitude2);
    }

    public GreatCircleArc(double latitude1, double longitude1, double latitude2, double longitude2) {
        this.latitude1 = latitude1;
        this.longitude1 = longitude1;
        this.latitude2 = latitude2;
        this.longitude2 = longitude2;
        preComputeConstants(latitude1, longitude1, latitude2, longitude2);
    }

    private void preComputeConstants(double latitude1, double longitude1, double latitude2, double longitude2) {
        sin_diff_lon2_lon1 = sin(this.longitude2 - this.longitude1);
        tan_lat1 = tan(this.latitude1);
        tan_lat2 = tan(this.latitude2);

        double A = tan_lat2 * cos(this.longitude1) - tan_lat1 * cos(this.longitude2);
        double B = tan_lat1 * sin(this.longitude2) - tan_lat2 * sin(this.longitude1);

        sqrt_sum_A2_B2 = sqrt(A * A + B * B);
        A_div_sqrt_sum_A2_B2 = A / sqrt_sum_A2_B2;

    }

    public Double computeLatitude(Double longitude) {
        double exp1 = tan_lat1 * sin(longitude2 - longitude) / sin_diff_lon2_lon1;
        double exp2 = tan_lat2 * sin(longitude - longitude1) / sin_diff_lon2_lon1;
        return atan(exp1 + exp2);
    }

    public Double computeLongitude(Double latitude) {
        double C = tan(latitude) * sin_diff_lon2_lon1;
        return -acos(A_div_sqrt_sum_A2_B2) + asin(C / sqrt_sum_A2_B2);
    }
}
