package org.rurik.geohash;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Created by rurik on 01.12.2016.
 */
public class GreatCircleArcTest {
    // longitude [-180;180]
    // latitude [-89;89]
    public static final double DOUBLE_DELTA = 0.00000000001;

    @Test
    public void ordinaryArcTest() throws Exception {
        int lonSteps = 100;
        checkOrdinaryArc(8, 80, 74, 54, lonSteps);
//        checkOrdinaryArc(-8, -80, 74, 54, lonSteps);// dont work
        checkOrdinaryArc(-18, 80, 0, 0, lonSteps);
        checkOrdinaryArc(0, 0, 18, -80, lonSteps);

        checkOrdinaryArc(-169, -80, 0, 0, lonSteps);// need -PI
//        checkOrdinaryArc(0, 0, 169, -80, lonSteps);// need -PI
//        checkOrdinaryArc(-8, -80, 8, 80, lonSteps);// need -2*PI


    }

    @Test
    // works for longitude [-90,90]
    public void constantLongitudeArcTest() throws Exception {
        int steps = 1000;
        checkConstantLongitudeArc(40.0083001, -89, 89, steps);
        checkConstantLongitudeArc(0, 19, 71, steps);
        checkConstantLongitudeArc(-77, -89, 10, steps);
    }

    @Test
    // works for longitude [-180,-90) || (90,180]
    public void constantLongitudeArcTest90_180() throws Exception {
        int steps = 1000;
        checkConstantLongitudeArc(-91, -89, 89, steps);//need -PI
        checkConstantLongitudeArc(-102, -89, 89, steps);//need -PI
        checkConstantLongitudeArc(-147, -89, 89, steps);//need -PI
        checkConstantLongitudeArc(-180, -89, 89, steps);//need -PI

    }

    @Test
    public void equatorArcTest() throws Exception {
        Double latitude = 0d;
        Double lonStart = -45d;
        Double lonEnd = 45d;
        Double step = 0.5;

        GreatCircleArc arc = new GreatCircleArc(latitude, lonStart, latitude, lonEnd);
        for (double lon = lonStart; lon <= lonEnd; lon += step) {
            assertEquals(latitude, arc.computeLatitude(lon), DOUBLE_DELTA);
        }
    }

    private void checkOrdinaryArc(double lon1, double lat1, double lon2, double lat2, int lonSteps) {
        GreatCircleArc arc = new GreatCircleArc(lon1, lat1, lon2, lat2);
        double lonStep1 = (lon2 - lon1) / lonSteps;
        for (double lon = lon1; lon < lon2; lon += lonStep1) {
            Double lat = arc.computeLatitude(lon);
            Double computedLongitude = arc.computeLongitude(lat);
            assertEquals(lon, computedLongitude, DOUBLE_DELTA);
        }
    }

    private void checkConstantLongitudeArc(double longitude, double latStart, double latEnd, int steps) {
        GreatCircleArc arc = new GreatCircleArc(latStart, longitude, latEnd, longitude);
        Double step = (latEnd - latStart) / steps;
        for (Double lat = latStart; lat <= latEnd; lat += step) {
            Double lon = arc.computeLongitude(lat);
            assertEquals(longitude, lon, DOUBLE_DELTA);
        }
    }
}