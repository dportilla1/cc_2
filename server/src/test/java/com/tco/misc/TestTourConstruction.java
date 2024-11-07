package com.tco.misc;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.tco.requests.Distances;
import com.tco.requests.DistancesRequest;
import com.tco.requests.Place;
import com.tco.requests.Places;
import com.tco.requests.TestDistancesRequest;
import com.tco.requests.TourRequest;
import java.util.Arrays;

public class TestTourConstruction {

    private static final transient Logger log = LoggerFactory.getLogger(TourConstruction.class);

    private DistancesRequest dist;
    private Distances distances;
    private TourRequest tour;
    private Places places;
    private Place place;
    Double earthRadius;
    String formula;

    final Place center = new Place("0.0", "0.0");

    final Place a = new Place("38.41", "-115.47");
    final Place b = new Place("38.41", "-98.56");
    final Place c = new Place("43.78", "-115.47");
    final Place d = new Place("43.78", "-98.56");

    final Place A = new Place("45.08", "-102.89");
    final Place B = new Place("40.85", "-95.62");
    final Place C = new Place("34.52", "-98.77");
    final Place D = new Place("34.52", "-107.00");
    final Place E = new Place("40.85", "-110.15");

    final Places ABCDE = new Places();
    final Places AEDCB = new Places();
    final Places acdb = new Places();

    @BeforeEach
    public void createConfigurationForTestCases() throws BadRequestException {
        earthRadius = 6371.0;
        formula = "vincenty";
        places = new Places();
        distances = new Distances();
        dist = new DistancesRequest(earthRadius, formula, places);
        dist.buildResponse();
    }

    @BeforeEach
    public void createTours() {
        ABCDE.add(A);
        ABCDE.add(B);
        ABCDE.add(C);
        ABCDE.add(D);
        ABCDE.add(E);

        AEDCB.add(A);
        AEDCB.add(E);
        AEDCB.add(D);
        AEDCB.add(C);
        AEDCB.add(B);

        acdb.add(a);
        acdb.add(c);
        acdb.add(d);
        acdb.add(b);
    }

    @Test
    @DisplayName("c836907548: bowtie tour to rectangle tour")
    public void acbd() throws BadRequestException {

        earthRadius = 69670.0d;
        formula = "vincenty";
        Places actualPlaces = new Places();
        actualPlaces.add(a);
        actualPlaces.add(c);
        actualPlaces.add(b);
        actualPlaces.add(d);

        OneOptimizer oneOp = new OneOptimizer();
        Places actualNN = oneOp.construct(actualPlaces, earthRadius, formula, earthRadius);

        Logger log = LoggerFactory.getLogger(TestDistancesRequest.class);
        log.error("debug actualNN", actualNN);

        assertEquals(acdb, actualNN);
    }

    @Test
    @DisplayName("c836907548: star shaped tour to pentagon")
    public void ACEBD() throws BadRequestException {
        earthRadius = 69670.0d;
        formula = "vincenty";

        Places actualPlaces = new Places();
        actualPlaces.add(A);
        actualPlaces.add(C);
        actualPlaces.add(E);
        actualPlaces.add(B);
        actualPlaces.add(D);

        OneOptimizer oneOp = new OneOptimizer();
        Places actualNN = oneOp.construct(actualPlaces, earthRadius, formula, earthRadius);
        log.info("Expected: {}", AEDCB);
        log.info("Actual: {}", actualPlaces);        
        assertEquals(AEDCB, actualNN);
    }

    @Test
    @DisplayName("c836907548: paperhat shaped tour to pentagon tour")
    public void ACDBE() throws BadRequestException {
        earthRadius = 69670.0d;
        formula = "vincenty";

        Places actualPlaces = new Places();
        actualPlaces.add(A);
        actualPlaces.add(C);
        actualPlaces.add(D);
        actualPlaces.add(B);
        actualPlaces.add(E);

        OneOptimizer oneOp = new OneOptimizer();
        Places actualNN = oneOp.construct(actualPlaces, earthRadius, formula, earthRadius);
        assertEquals(AEDCB, actualNN);
    }

    @Test
    @DisplayName("c836907548: fish shaped tour to pentagon tour")
    public void ACDEB() throws BadRequestException {
        earthRadius = 69670.0d;
        formula = "vincenty";

        Places actualPlaces = new Places();
        actualPlaces.add(A);
        actualPlaces.add(C);
        actualPlaces.add(D);
        actualPlaces.add(E);
        actualPlaces.add(B);

        OneOptimizer oneOp = new OneOptimizer();
        Places actualNN = oneOp.construct(actualPlaces, earthRadius, formula, earthRadius);
        assertEquals(AEDCB, actualNN);
    }
}