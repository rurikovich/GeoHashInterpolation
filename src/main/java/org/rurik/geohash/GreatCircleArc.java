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
        preComputeConstants();
    }

    private void preComputeConstants() {
        sin_diff_lon2_lon1 = sin(longitude2 - longitude1);
        tan_lat1 = tan(latitude1);
        tan_lat2 = tan(latitude2);

        double A = tan_lat2 * cos(longitude1) - tan_lat1 * cos(longitude2);
        double B = tan_lat1 * sin(longitude2) - tan_lat2 * sin(longitude1);

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
