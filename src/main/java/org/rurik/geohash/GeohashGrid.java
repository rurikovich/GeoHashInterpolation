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
    private Double latitudeStep;
    private Double longitudeStep;

    public GeohashGrid(int precision) {
        this.precision = precision;
        latitudeStep = calculateLatitudeStep(precision);
        longitudeStep = calculateLongitudeStep(precision);
        calculateGrid();
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

    public Double getLatitudeStep() {
        return latitudeStep;
    }

    public Double getLongitudeStep() {
        return longitudeStep;
    }

    private void calculateGrid() {
        latitudes = calculateLatitudes();
        longitudes = calculateLongitudes();
    }

    //list is sorted by latitude: from 0 to LATITUDE_PERIOD
    List<Double> calculateLatitudes() {
        return generateCoordinates(latitudeStep, LATITUDE_PERIOD, LATITUDE_CORRECTION);
    }

    //list is sorted by longitudes: from 0 to LONGITUDE_PERIOD
    List<Double> calculateLongitudes() {
        return generateCoordinates(longitudeStep, LONGITUDE_PERIOD, LONGITUDE_CORRECTION);
    }

    List<Double> generateCoordinates(double step, int period, int correction) {
        List<Double> coordinates = new ArrayList<>();
        for (double l = 0; l <= period; l += step) {
            coordinates.add(l + correction);
        }
        return coordinates;
    }

    Double calculateLongitudeStep(int precision) {
        return LONGITUDE_PERIOD / pow(2, precision + 1);
    }

    Double calculateLatitudeStep(int precision) {
        return LATITUDE_PERIOD / pow(2, precision);
    }
}
