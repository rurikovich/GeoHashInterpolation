package org.rurik.geohash;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Created by rurik on 01.12.2016.
 */
public class GreatCircleArcTest {
    private static final Integer STEPS_NUMBER = 1;
    private static final Double DELTA = 0.00000000475173;
    private static final Double STEP = DELTA / STEPS_NUMBER;

    private static final Double POINT_LONGITUDE = 46.00790977478027;
    private static final Double POINT_LATITUDE = 51.52337735475173;
    public static final double DOUBLE_DELTA = 0.00000000001;

    @Test
    public void constantLongitudeArc() throws Exception {
        Double longitude = POINT_LONGITUDE;
        Double latStart = POINT_LATITUDE;
        Double latEnd = POINT_LATITUDE + DELTA;

        GreatCircleArc arc = new GreatCircleArc(latStart, longitude, latEnd, longitude);
        for (double lat = latStart; lat <= latEnd; lat += STEP) {
            assertEquals(longitude, arc.computeLongitude(lat));
        }
    }


    @Test
    public void equatorArcTest() throws Exception {
        Double latitude = 0d;
        Double lonStart = -45d;
        Double lonEnd = 45d;
        double step = 0.5;

        GreatCircleArc arc = new GreatCircleArc(latitude, lonStart, latitude, lonEnd);
        for (double lon = lonStart; lon <= lonEnd; lon += step) {
            assertEquals(latitude, arc.computeLatitude(lon), DOUBLE_DELTA);
        }

    }
}