package org.rurik.geohash;

/**
 * Created by Yuri Rastegaev on 27.11.2016.
 */
public class GeohashGrid {
    /**
     * Geohash precision is a length of the encoded string: precision of the "bc3d" is 4.
     **/
    private int precision;
    private double[] latitudes;
    private double[] longitudes;

    public GeohashGrid(int precision) {
        this.precision = precision;
        calculateGrid(precision);
    }

    private void calculateGrid(int precision) {
//        TODO
    }

    public int getPrecision() {
        return precision;
    }

    public double[] getLatitudes() {
        return latitudes;
    }

    public double[] getLongitudes() {
        return longitudes;
    }
}
