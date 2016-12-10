package org.rurik.geohash.geojson;

import com.vividsolutions.jts.geom.Point;
import org.geotools.data.DataUtilities;
import org.geotools.feature.SchemaException;
import org.geotools.feature.simple.SimpleFeatureBuilder;
import org.geotools.geojson.feature.FeatureJSON;
import org.geotools.geometry.jts.GeometryBuilder;
import org.opengis.feature.simple.SimpleFeature;
import org.opengis.feature.simple.SimpleFeatureType;

import java.io.IOException;
import java.io.StringWriter;

/**
 * Created by User on 09.12.2016.
 */
public class GeoJsonExporter {
    private SimpleFeatureType TYPE;

    public GeoJsonExporter() throws SchemaException {
        TYPE = DataUtilities.createType("Test", "Location:Point");
    }

    public String geoData() {
        final GeometryBuilder builder = new GeometryBuilder();
        final Point point = builder.point(132.159633, 43.350116);
        SimpleFeatureBuilder fBuild = new SimpleFeatureBuilder(TYPE);
        fBuild.add(point);
        SimpleFeature feature = fBuild.buildFeature(null);
        FeatureJSON fjson = new FeatureJSON();
        StringWriter writer = new StringWriter();
        try {
            fjson.writeFeature(feature, writer);
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        String json = writer.toString();
        return json;
    }

    public static void main(String[] args) throws SchemaException {
        GeoJsonExporter test = new GeoJsonExporter();
        System.out.println(test.geoData());
    }

}
