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
        checkOrdinaryArc(0, 7, 89, 89);
    }

    @Test
    public void constantLongitudeArcTest() throws Exception {
        int steps = 1000;
        checkConstantLongitudeArc(40.0083001, -89, 89, steps);
        checkConstantLongitudeArc(0, 19, 71, steps);
        checkConstantLongitudeArc(-77, -89, 10, steps);
        checkConstantLongitudeArc(-147, -89, 89, 2);
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

    private void checkOrdinaryArc(double lon1, double lat1, double lon2, double lat2) {
        GreatCircleArc arc = new GreatCircleArc(lon1, lat1, lon2, lat2);
        for (double lon = lon1; lon < lon2; lon += 1) {
            Double lat = arc.computeLatitude(lon);
            Double computedLongitude = arc.computeLongitude(lat);
            assertEquals(lon, computedLongitude, DOUBLE_DELTA);
        }
    }

    private void checkConstantLongitudeArc(double longitude, double latStart, double latEnd, int steps) {
        GreatCircleArc arc = new GreatCircleArc(latStart, longitude, latEnd, longitude);
        Double step = (latEnd-latStart) / steps;
        for (Double lat = latStart; lat <= latEnd; lat += step) {
            Double lon = arc.computeLongitude(lat);
            assertEquals(longitude, lon, DOUBLE_DELTA);
        }
    }
}