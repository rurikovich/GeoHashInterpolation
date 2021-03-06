package org.rurik.geohash;

import java.util.ArrayList;
import java.util.List;

import static java.lang.Math.*;

/**
 * Created by Yuri Rastegaev on 27.11.2016.
 */
public class GeohashGrid {
    private final double LONGITUDE_PERIOD = 360;
    private final double LONGITUDE_CORRECTION = -180;
    private final double LATITUDE_PERIOD = 180;
    private final double LATITUDE_CORRECTION = -90;

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
        calculateGrid();
    }

    public GeohashGrid() {
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

    public void calculateGrid() {
        latitudeStep = calculateLatitudeStep(precision);
        longitudeStep = calculateLongitudeStep(precision);
        latitudes = calculateLatitudes(latitudeStep);
        longitudes = calculateLongitudes(longitudeStep);
    }

    //list is sorted by latitude: from 0 to LATITUDE_PERIOD
    List<Double> calculateLatitudes(Double latitudeStep) {
        return generateCoordinates(latitudeStep, LATITUDE_PERIOD, LATITUDE_CORRECTION);
    }

    //list is sorted by longitudes: from 0 to LONGITUDE_PERIOD
    List<Double> calculateLongitudes(Double longitudeStep) {
        return generateCoordinates(longitudeStep, LONGITUDE_PERIOD, LONGITUDE_CORRECTION);
    }

    List<Double> generateCoordinates(Double step, Double period, Double correction) {
        List<Double> coordinates = new ArrayList<>();
        for (Double l = 0d; l <= period; l += step) {
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
