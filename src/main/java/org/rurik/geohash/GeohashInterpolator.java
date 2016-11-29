package org.rurik.geohash;

import java.util.ArrayList;
import java.util.List;

import static java.util.Collections.sort;

/**
 * Created by Yuri Rastegaev on 25.11.2016.
 */
public class GeohashInterpolator {

    public List<GeoPoint> computeGeohashGridIntersections(int geohashGridPrecision, GeoPoint startPoint, GeoPoint endPoint) {
        return computeGeohashGridIntersections(geohashGridPrecision, new GreatCircleArc(startPoint, endPoint));
    }

    public List<GeoPoint> computeGeohashGridIntersections(int geohashGridPrecision, GreatCircleArc greatCircleArc) {
        GeohashGrid grid = new GeohashGrid(geohashGridPrecision);
        List<GeoPoint> intersections = new ArrayList<>();
        grid.getLatitudes().stream().forEach(lat -> intersections.add(new GeoPoint(lat, greatCircleArc.computeLongitude(lat))));
        grid.getLongitudes().stream().forEach(lon -> intersections.add(new GeoPoint(greatCircleArc.computeLatitude(lon), lon)));
        sort(intersections, (p1, p2) -> p1.getLongitude().compareTo(p2.getLongitude()));
        return intersections;
    }

    public List<GeoPoint> computeGeohashInterpolation(List<GeoPoint> geohashGridIntersections) {
        //  TODO
        return null;
    }
}
