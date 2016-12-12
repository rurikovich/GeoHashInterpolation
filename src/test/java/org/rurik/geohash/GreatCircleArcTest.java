package org.rurik.geohash;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Created by rurik on 01.12.2016.
 */
public class GreatCircleArcTest {
    public static final double DOUBLE_DELTA = 0.00000000001;


    @Test
    public void constantLongitudeArcTest() throws Exception {
        Double longitude = 40.0083001d;
        Double latStart = -89d;
        Double latEnd = longitude + 170d;
        int step = 10000;

        GreatCircleArc arc = new GreatCircleArc(latStart, longitude, latEnd, longitude);
        for (Double lat = latStart; lat <= latEnd; lat += step) {
            Double lon = arc.computeLongitude(lat);
            assertEquals(longitude, lon, DOUBLE_DELTA);
        }
    }


    @Test
    public void ordinaryArcTest() throws Exception {
        Double lon1 = 0d;
        Double lat1 = 7d;

        Double lon2 = 89d;
        Double lat2 = 89d;
        GreatCircleArc arc = new GreatCircleArc(lon1, lat1, lon2, lat2);
        for (double lon = lon1; lon < lon2; lon += 1) {
            Double lat = arc.computeLatitude(lon);
            Double computedLongitude = arc.computeLongitude(lat);
            assertEquals(lon, computedLongitude, DOUBLE_DELTA);
        }
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
}