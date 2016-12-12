package org.rurik.geohash;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Created by rurik on 01.12.2016.
 */
public class GreatCircleArcTest {
    public static final double DOUBLE_DELTA = 0.00000000001;

    @Test
    public void ordinaryArcTest() throws Exception {
        checkOrdinaryArc(0d, 7d, 89d, 89d);
    }

    private void checkOrdinaryArc(Double lon1, Double lat1, Double lon2, Double lat2) {
        GreatCircleArc arc = new GreatCircleArc(lon1, lat1, lon2, lat2);
        for (double lon = lon1; lon < lon2; lon += 1) {
            Double lat = arc.computeLatitude(lon);
            Double computedLongitude = arc.computeLongitude(lat);
            assertEquals(lon, computedLongitude, DOUBLE_DELTA);
        }
    }

    @Test
    public void constantLongitudeArcTest() throws Exception {
        int steps = 1000;
        checkConstantLongitudeArc(40.0083001d, -89d, 170d, steps);
        checkConstantLongitudeArc(0d, 19d, 71, steps);
        checkConstantLongitudeArc(-77d, -89d, 10d, steps);
        checkConstantLongitudeArc(-147d, -90d, 180d, 2);
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

    private void checkConstantLongitudeArc(double longitude, double latStart, double latDelta, int steps) {
        Double latEnd = latStart + latDelta;
        GreatCircleArc arc = new GreatCircleArc(latStart, longitude, latEnd, longitude);
        Double step = latDelta / steps;
        for (Double lat = latStart; lat <= latEnd; lat += step) {
            Double lon = arc.computeLongitude(lat);
            assertEquals(longitude, lon, DOUBLE_DELTA);
        }
    }
}