package com.tco.requests;

import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertThrows;
import com.tco.misc.BadRequestException;
import com.tco.misc.CosineCalculator;

public class TestDistancesRequest {

    private DistancesRequest dist;
    private Distances distances;
    private Places places;
    private Place place;
    double earthRadius;
    String formula;

    final Place center = new Place("0.0", "0.0");

    final Place texas = new Place("29.423143", "-94.896581");
    final Place chile = new Place("-35.147416", "-61.258050");
    final Place madagascar = new Place("-21.814350", "46.752996");
    final Place greece = new Place("37.990836", "23.738339");
    final static double furlongEarthRadius = 31670.046926;

    final Place lafayette = new Place("37.9", "-122.1");
    final Place salzburg = new Place("47.8", "13.0");
    final Place perth = new Place("-31.9", "115.9");
    final Place buenosAires = new Place("-36.6", "-58.5");
    final static double fieldsEarthRadius = 69670.0;

    final Place closePoint1 = new Place("0.0007", "0.0007");
    final Place closePoint2 = new Place("0.0007", "0.0008");
    final Place closePoint3 = new Place("0.0008", "0.0008");
    final Place closePoint4 = new Place("0.0008", "0.0007");

    @BeforeEach
    public void createConfigurationForTestCases() throws BadRequestException {
        earthRadius = 6371.0f;
        formula = "vincenty";
        places = new Places();
        distances = new Distances();
        dist = new DistancesRequest(earthRadius, formula, places);
        dist.buildResponse();
    }

    @Test
    @DisplayName("c836907548: Radius is exact when floating point max")
    public void testRadiusMax() throws BadRequestException {
        earthRadius = 1234567827654321.0f;
        formula = "vincenty";
        places = new Places();
        distances = new Distances();

        dist = new DistancesRequest(earthRadius, formula, places);
        dist.buildResponse();

        double radiusResponse = dist.earthRadius();
        float testFloat = 1234567827654321.0f;

        // Logger log = LoggerFactory.getLogger(TestDistancesRequest.class);
        // log.error("debug radiusResponse", radiusResponse);
        assertEquals(testFloat, radiusResponse);
    }

    @Test
    @DisplayName("c836907548: Checking distances when radius is max float")
    public void testRadiusMaxDistances() throws BadRequestException {
        earthRadius = 1234567827654321.0f;
        formula = "vincenty";
        places = new Places();
        distances = new Distances();
        places.add(center);
        places.add(center);
        dist = new DistancesRequest(earthRadius, formula, places);
        dist.buildResponse();

        Distances actual = dist.distances();
        Distances expected = new Distances();
        expected.add(0l);
        expected.add(0l);
        assertEquals(expected, actual);
    }

    @Test
    @DisplayName("c836907548: Radius is exact when floating point max")
    public void testType() throws BadRequestException {
        earthRadius = 1234567827654321.0f;
        formula = "vincenty";
        places = new Places();
        distances = new Distances();
        dist = new DistancesRequest(earthRadius, formula, places);
        dist.buildResponse();

        double radiusResponse = dist.earthRadius();
        float testFloat = 1234567827654321.0f;

        // Logger log = LoggerFactory.getLogger(TestDistancesRequest.class);
        // log.error("debug radiusResponse", radiusResponse);
        assertEquals(testFloat, radiusResponse);
    }

    @Test
    @DisplayName("c836907548: 1 distances should return array with 0")
    public void oneDistanceReturns0() throws BadRequestException {
        earthRadius = 69670.0f;
        formula = "vincenty";
        places = new Places();
        distances = new Distances();
        places.add(lafayette);
        dist = new DistancesRequest(earthRadius, formula, places);
        dist.buildResponse();

        Distances actual = dist.distances();
        Distances expected = new Distances();
        expected.add(0l);
        assertEquals(expected, actual);
    }

    @Test
    @DisplayName("maggieri: Request type is \"distances\"")
    public void testTypeDistance() {
        String type = dist.getRequestType();
        assertEquals("distances", type);
    }

    @Test
    @DisplayName("leegina: No formula specified. Default is Vincenty Calculator")
    public void testNoFormula() throws BadRequestException{
        distances.add(103885L);
        distances.add(103885L);
        Place lafayette = new Place("37.9", "-122.1");
        Place salzburg = new Place("47.8", "13.0");
        places.add(lafayette);
        places.add(salzburg);

        dist = new DistancesRequest(69670.0, places);
        assertEquals(distances, dist.buildDistancesList());
        assertEquals(null, dist.formula());
    }

    @Test
    @DisplayName("leegina:")
    public void testWrongFormula() throws BadRequestException{
        formula = "ellipsoid";
        dist = new DistancesRequest(earthRadius, formula, places);
        assertThrows(BadRequestException.class, () -> {dist.buildDistancesList();});
    }

    @Test
    @DisplayName("jihyun: places() returns correct places")
    public void testDistancesRequestPlaces() throws BadRequestException {
        Places places = new Places();
        places.add(texas);
        places.add(chile);
        places.add(madagascar);
        earthRadius = 69670.0;
        formula = "vincenty";
        DistancesRequest distance = new DistancesRequest(earthRadius, formula, places);
        distance.buildResponse();
        assertEquals(places, distance.places());
    }

}
