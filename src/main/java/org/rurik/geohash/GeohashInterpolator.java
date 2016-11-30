package org.rurik.geohash;

import java.util.ArrayList;
import java.util.List;

import static java.util.Collections.sort;
import static java.util.stream.Collectors.*;

/**
 * Created by Yuri Rastegaev on 25.11.2016.
 */
public class GeohashInterpolator {

    public List<GeoPoint> computeGeohashInterpolation(int geohashGridPrecision, GeoPoint startPoint, GeoPoint endPoint) {
        GreatCircleArc greatCircleArc = new GreatCircleArc(startPoint, endPoint);
        GeohashGrid grid = new GeohashGrid(geohashGridPrecision);

        List<GeoPoint> latIntersections = grid.getLatitudes().stream().map(lat -> new GeoPoint(lat, greatCircleArc.computeLongitude(lat))).collect(toList());
        List<GeoPoint> lonIntersections = grid.getLongitudes().stream().map(lon -> new GeoPoint(greatCircleArc.computeLatitude(lon), lon)).collect(toList());

        double latStepHalf = grid.getLatitudeStep() / 2;
        double lonStepHalf = grid.getLongitudeStep() / 2;

        List<GeoPoint> interpoletedPoints = new ArrayList<>();
        if (lonIntersections.isEmpty()) {
            listWithoutLastElem(latIntersections).forEach(p -> interpoletedPoints.add(new GeoPoint(p.getLatitude() + latStepHalf, p.getLongitude())));
        } else if (latIntersections.isEmpty()) {
            listWithoutLastElem(lonIntersections).forEach(p -> interpoletedPoints.add(new GeoPoint(p.getLatitude(), p.getLongitude() + lonStepHalf)));
        } else {
            lonIntersections.addAll(latIntersections);
            sort(lonIntersections, (p1, p2) -> p1.getLongitude().compareTo(p2.getLongitude()));
            for (int i = 0; i < lonIntersections.size() - 1; i++) {
                GeoPoint curr = lonIntersections.get(i);
                GeoPoint next = lonIntersections.get(i + 1);
                Double iPointLat = (curr.getLatitude() + next.getLatitude()) / 2;
                Double iPointLon = greatCircleArc.computeLongitude(iPointLat);
                interpoletedPoints.add(new GeoPoint(iPointLat, iPointLon));
            }
        }
        return interpoletedPoints;
    }

    private List<GeoPoint> listWithoutLastElem(List<GeoPoint> list) {
        return list.subList(0, list.size() - 1);
    }

}
