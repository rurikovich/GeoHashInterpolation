package org.rurik.geohash;

import java.util.ArrayList;
import java.util.List;

import static java.lang.Math.*;

/**
 * Created by Yuri Rastegaev on 27.11.2016.
 */
public class GreatCircleArc {
    private double latitudeInRad1;
    private double longitudeInRad1;

    private double latitudeInRad2;
    private double longitudeInRad2;

    private double sin_diff_lon2_lon1;
    private double tan_lat1;
    private double tan_lat2;

    private double A;
    private double B;

    private double sqrt_sum_A2_B2;
    private double A_div_sqrt_sum_A2_B2;

    public GreatCircleArc(GeoPoint geoPoint1, GeoPoint geoPoint2) {
        this(geoPoint1.getLatitude(), geoPoint1.getLongitude(), geoPoint2.getLatitude(), geoPoint2.getLongitude());
    }

    public GreatCircleArc(double latitude1, double longitude1, double latitude2, double longitude2) {
        latitudeInRad1 = getRadian(latitude1);
        longitudeInRad1 = getRadian(longitude1);
        latitudeInRad2 = getRadian(latitude2);
        longitudeInRad2 = getRadian(longitude2);
        preComputeConstants(latitudeInRad1, longitudeInRad1, latitudeInRad2, longitudeInRad2);
    }

    public Double computeLatitude(Double longitude) {
        return computeLatitudeForRadians(getRadian(longitude));
    }

    public Double computeLongitude(Double latitude) {
        return computeLongitudeForRadians(getRadian(latitude));
    }

    public List<GeoPoint> generateArcPoints(int count) {
        ArrayList<GeoPoint> geoPoints = new ArrayList<>();
        double step = (longitudeInRad2 - longitudeInRad1) / count;
        for (double lonInRad = longitudeInRad1; lonInRad < longitudeInRad2; lonInRad += step) {
            Double latInAngles = computeLatitudeForRadians(lonInRad);
            geoPoints.add(new GeoPoint(latInAngles, getAngle(lonInRad)));
        }
        return geoPoints;
    }

    private Double computeLatitudeForRadians(double longitudeRad) {
        double exp1 = tan_lat1 * sin(longitudeInRad2 - longitudeRad) / sin_diff_lon2_lon1;
        double exp2 = tan_lat2 * sin(longitudeRad - longitudeInRad1) / sin_diff_lon2_lon1;
        return getAngle(atan(exp1 + exp2));
    }

    private Double computeLongitudeForRadians(double latitudeRad) {
        double C = tan(latitudeRad) * sin_diff_lon2_lon1;
        double radian = signum(A) * asin(C / sqrt_sum_A2_B2) - signum(A) * signum(B) * acos(A_div_sqrt_sum_A2_B2);
        return getAngle(radian);
    }

    private void preComputeConstants(double latitudeInRad1, double longitudeInRad1, double latitudeInRad2, double longitudeInRad2) {
        sin_diff_lon2_lon1 = sin(longitudeInRad2 - longitudeInRad1);
        tan_lat1 = tan(latitudeInRad1);
        tan_lat2 = tan(latitudeInRad2);

        A = tan_lat2 * cos(longitudeInRad1) - tan_lat1 * cos(longitudeInRad2);
        B = tan_lat1 * sin(longitudeInRad2) - tan_lat2 * sin(longitudeInRad1);

        sqrt_sum_A2_B2 = sqrt(A * A + B * B);
        A_div_sqrt_sum_A2_B2 = abs(A) / sqrt_sum_A2_B2;

    }

    private Double getRadian(Double angle) {
        return angle * (PI / 180);
    }

    private Double getAngle(Double radian) {
        return radian * (180 / PI);
    }

}
