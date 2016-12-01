package org.rurik.geohash;

import org.junit.Before;
import org.junit.Test;

import static java.util.Arrays.*;
import static org.hamcrest.collection.IsIterableContainingInOrder.*;
import static org.junit.Assert.*;

/**
 * Created by rurik on 01.12.2016.
 */
public class GeohashGridTest {
    private GeohashGrid grid;

    @Before
    public void setUp() throws Exception {
        grid = new GeohashGrid();
    }

    @Test
    public void generateCoordinates() throws Exception {
        assertThat(grid.generateCoordinates(1d, 5d, 0d), contains(asList(0d, 1d, 2d, 3d, 4d, 5d).toArray()));
        assertThat(grid.generateCoordinates(1d, 1d, 0d), contains(asList(0d, 1d).toArray()));
        assertThat(grid.generateCoordinates(1d, 1d, -10d), contains(asList(-10d, -9d).toArray()));
        assertThat(grid.generateCoordinates(1d, 0d, 0d), contains(asList(0d).toArray()));
    }

    @Test
    public void calculateLongitudeStep() throws Exception {
        assertEquals(grid.calculateLongitudeStep(1), new Double(90));
        assertEquals(grid.calculateLongitudeStep(2), new Double(45));
        assertEquals(grid.calculateLongitudeStep(3), new Double(22.5));
        assertEquals(grid.calculateLongitudeStep(4), new Double(11.25));
        assertEquals(grid.calculateLongitudeStep(5), new Double(5.625));
    }

    @Test
    public void calculateLatitudeStep() throws Exception {
        assertEquals(grid.calculateLatitudeStep(1), new Double(90));
        assertEquals(grid.calculateLatitudeStep(2), new Double(45));
        assertEquals(grid.calculateLatitudeStep(3), new Double(22.5));
        assertEquals(grid.calculateLatitudeStep(4), new Double(11.25));
        assertEquals(grid.calculateLatitudeStep(5), new Double(5.625));
    }

    @Test
    public void calculateLatitudes() throws Exception {
        assertThat(grid.calculateLatitudes(grid.calculateLatitudeStep(1)), contains(asList(-90d, 0d, 90d).toArray()));
        assertThat(grid.calculateLatitudes(grid.calculateLatitudeStep(2)), contains(asList(-90d, -45d, 0d, 45d, 90d).toArray()));
        assertThat(grid.calculateLatitudes(grid.calculateLatitudeStep(3)), contains(asList(-90d, -67.5d, -45d, -22.5d, 0d, 22.5d, 45d, 67.5d, 90d).toArray()));
    }

    @Test
    public void calculateLongitudes() throws Exception {
        assertThat(grid.calculateLongitudes(grid.calculateLongitudeStep(1)), contains(asList(-180d, -90d, 0d, 90d, 180d).toArray()));
        assertThat(grid.calculateLongitudes(grid.calculateLongitudeStep(2)), contains(asList(-180d, -135d, -90d, -45d, 0d, 45d, 90d, 135d, 180d).toArray()));
        assertThat(grid.calculateLongitudes(grid.calculateLongitudeStep(3)), contains(asList(-180d, -157.5d, -135d, -112.5d, -90d, -67.5d, -45d, -22.5d, 0d, 22.5d, 45d, 67.5d, 90d, 112.5d, 135d, 157.5d, 180d).toArray()));
    }
}