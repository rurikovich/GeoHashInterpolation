package org.rurik.geohash.geojson;

import com.vividsolutions.jts.geom.Coordinate;
import com.vividsolutions.jts.geom.CoordinateSequence;
import com.vividsolutions.jts.geom.GeometryFactory;
import com.vividsolutions.jts.geom.LineString;
import com.vividsolutions.jts.geom.impl.CoordinateArraySequence;
import org.geotools.data.DataUtilities;
import org.geotools.feature.SchemaException;
import org.geotools.feature.simple.SimpleFeatureBuilder;
import org.geotools.geojson.feature.FeatureJSON;
import org.geotools.geometry.jts.GeometryBuilder;
import org.opengis.feature.simple.SimpleFeature;
import org.opengis.feature.simple.SimpleFeatureType;
import org.rurik.geohash.GeoPoint;
import org.rurik.geohash.GreatCircleArc;

import java.io.IOException;
import java.io.StringWriter;
import java.util.List;

/**
 * Created by User on 09.12.2016.
 */
public class GeoJsonExporter {
    private SimpleFeatureType TYPE;

    public GeoJsonExporter() throws SchemaException {
        TYPE = DataUtilities.createType("GreatCircleArc", "Location:LineString");
    }

    public String geoData(List<GeoPoint> geoPoints) throws IOException {
        int size = geoPoints.size();
        Coordinate[] coordinates = new Coordinate[size];
        GeometryBuilder builder = new GeometryBuilder();
        for (int i = 0; i < size; i++) {
            GeoPoint p = geoPoints.get(i);
            coordinates[i] = new Coordinate(p.getLatitude(), p.getLongitude());
        }
        CoordinateSequence coordinateSequence = new CoordinateArraySequence(coordinates);
        GeometryFactory geometryFactory = new GeometryFactory();
        LineString lineString = new LineString(coordinateSequence, geometryFactory);
        SimpleFeatureBuilder fBuild = new SimpleFeatureBuilder(TYPE);
        SimpleFeature feature = fBuild.buildFeature(null);
        feature.setDefaultGeometry(lineString);
        StringWriter writer = new StringWriter();
        FeatureJSON fjson = new FeatureJSON();
        fjson.writeFeature(feature, writer);
        return writer.toString();
    }

    public static void main(String[] args) throws SchemaException, IOException {
        GeoJsonExporter test = new GeoJsonExporter();
        GreatCircleArc arc = new GreatCircleArc(-80, 0, 40, 70);
        System.out.println(test.geoData(arc.generateArcPoints(1000)));
    }

}
