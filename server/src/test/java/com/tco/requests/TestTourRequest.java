package com.tco.requests;

import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertThrows;
import com.tco.misc.BadRequestException;
import com.tco.misc.CosineCalculator;
import com.tco.requests.TourRequest;

public class TestTourRequest {

    private TourRequest tour;
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

    @Test
    @DisplayName("maggieri: tour returns the same places")
    public void oneDistanceReturns0() throws BadRequestException {
      earthRadius = 69670.0f;
      formula = "vincenty";
      Places places = new Places();
      places.add(lafayette);
      places.add(perth);
      places.add(salzburg);
      tour = new TourRequest(places, earthRadius, formula, 0.0);
      tour.buildResponse();

      Places actual = tour.places;
      Places expected = new Places();
      expected.add(lafayette);
      expected.add(perth);
      expected.add(salzburg);
      assertEquals(expected, actual);
    }

    @Test
    @DisplayName("petercap: handle empty list of places")
    public void testEmptyPlacesList() throws BadRequestException {
        Places places = new Places();
        tour = new TourRequest(places, fieldsEarthRadius, "vincenty", 0.0);
        tour.buildResponse();

        assertTrue(tour.places.isEmpty(), "TourRequest should return an empty list when no places are provided");
    }

    @Test
    @DisplayName("petercap: test to handle null places")
    public void testNullPlaces() {
        assertThrows(NullPointerException.class, () -> {
            TourRequest tour = new TourRequest(null, furlongEarthRadius, "cosines", 0.5);
            tour.buildResponse();
        }, "TourRequest should throw NullPointerException when places are null");
    }

    @Test
    @DisplayName("jihyun: tests if TourRequest returns the expected places")
    public void placesTourRequestCosines() {
        Places places = new Places();
        places.add(texas);
        places.add(chile);
        places.add(perth);
        TourRequest tour = new TourRequest(places, 69670.0, "cosines", .5);
        assertEquals(places, tour.places);
    }

    @Test
    @DisplayName("maggieri: Request type is \"tour\"")
    public void testTypeTour() {
    Places places = new Places();
        places.add(texas);
        places.add(chile);
        places.add(perth);
        TourRequest tour = new TourRequest(places, 69670.0, "vincenty", .5);
        String type = tour.getRequestType();
        assertEquals("tour", type);
    }
  
    @Test
    @DisplayName("maggieri: response is correct")
    public void testResponseTour() {
    Places places = new Places();
        places.add(texas);
        places.add(chile);
        places.add(perth);
        TourRequest tour = new TourRequest(places, 69670.0, "vincenty", .5);
        Double response = tour.response();
        assertEquals(.5, response);
    }
  
    @Test
    @DisplayName("maggieri: response is correct")
    public void testMorePlaces() throws BadRequestException {
    Places places = new Places();
        places.add(texas);
        places.add(chile);
        places.add(perth);
        places.add(madagascar);
        places.add(greece);
        places.add(salzburg);
        places.add(buenosAires);
        places.add(center);
        TourRequest tour = new TourRequest(places, 69670.0, "vincenty", .3);
        tour.buildResponse();
        assertFalse(tour.places.equals(null));
    }

    @Test
    @DisplayName("jihyun: places() is correct")
    public void testTourPlaces() {
        Places places = new Places();
        places.add(texas);
        places.add(chile);
        places.add(perth);
        TourRequest tour = new TourRequest(places, 69670.0, "cosines", .5);
        assertEquals(places, tour.places());
    }

    @Test
    @DisplayName("leegina: 4 places and 0.0 response so NoOpt")
    public void testNoOpt() throws BadRequestException {
        earthRadius = 69670.0f;
        formula = "vincenty";
        Places places = new Places();
        places.add(lafayette);
        places.add(perth);
        places.add(salzburg);
        places.add(center);
        tour = new TourRequest(places, earthRadius, formula, 0.0);
        tour.buildResponse();
  
        Places expected = new Places();
        expected.add(lafayette);
        expected.add(perth);
        expected.add(salzburg);
        expected.add(center);
  
        assertEquals(expected, places);
    }

    @Test
    @DisplayName("leegina: returns correct formula")
    public void testFormula() {
        Places places = new Places();
        formula = "vincenty";
        TourRequest tour = new TourRequest(places, 69670.0, "vincenty", .5);
        assertEquals(formula, tour.formula());
    }
}
