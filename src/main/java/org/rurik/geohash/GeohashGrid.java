package org.rurik.geohash;

import java.util.ArrayList;
import java.util.List;

import static java.lang.Math.*;

/**
 * Created by Yuri Rastegaev on 27.11.2016.
 */
public class GeohashGrid {

    private final int LONGITUDE_PERIOD = 360;
    private final int LONGITUDE_CORRECTION = -180;

    private final int LATITUDE_PERIOD = 180;
    private final int LATITUDE_CORRECTION = -90;

    /**
     * Geohash precision is a length of the encoded string: precision of the "bc3d" is 4.
     **/
    private int precision;
    private List<Double> latitudes;
    private List<Double> longitudes;

    public GeohashGrid(int precision) {
        this.precision = precision;
        calculateGrid();
    }

    private void calculateGrid() {
        latitudes = calculateLatitudes(precision);
        longitudes = calculateLongitudes(precision);
    }

    private List<Double> calculateLatitudes(int precision) {
        return generateCoordinates(LATITUDE_PERIOD / pow(2, precision), LATITUDE_PERIOD, LATITUDE_CORRECTION);
    }

    private List<Double> calculateLongitudes(int precision) {
        return generateCoordinates(LONGITUDE_PERIOD / pow(2, precision + 1), LONGITUDE_PERIOD, LONGITUDE_CORRECTION);
    }

    private List<Double> generateCoordinates(double step, int period, int correction) {
        List<Double> coordinates = new ArrayList<>();
        for (double l = 0; l <= period; l += step) {
            coordinates.add(l + correction);
        }
        return coordinates;
    }

    public int getPrecision() {
        return precision;
    }

    public List<Double> getLatitudes() {
        return latitudes;
    }

    public List<Double> getLongitudes() {
        return longitudes;
    }
}
