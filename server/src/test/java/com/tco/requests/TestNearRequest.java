package com.tco.requests;

import org.junit.jupiter.api.Test;

import com.tco.misc.BadRequestException;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;

public class TestNearRequest {
    private NearRequest nearRequest;
    private Place testPlace;

    @BeforeEach
    public void setUp() {
        testPlace = new Place("39.7392", "-104.9903");
        nearRequest = new NearRequest(testPlace, 100, 6371.0, "vincenty", 5);
    }

    @Test
    @DisplayName("jihyun: tests if NearRequest returns the expected places")
    public void testPlaceVariable() {
        assertEquals(testPlace, nearRequest.place());
    }
    
    @Test
    @DisplayName("maggieri: distance is the same")
    public void testDistanceVariable() {
        assertEquals(100, nearRequest.distance());
    }

    @Test
    @DisplayName("petercap: verifies the datatype of field formula is a String")
    void testFormulaIsString() {
        Place place = new Place(); 
        int distance = 1000;
        Double earthRadius = 6371.0; 
        String formula = "haversine"; // can be anything, this test just verifies the datatype
        int limit = 10;

        NearRequest nearRequest = new NearRequest(place, distance, earthRadius, formula, limit);

        assertTrue(nearRequest.formula() instanceof String, "The formula field should be of type String.");
    }

    @Test
    @DisplayName("leegina: Returns earth radius")
    public void testEarthRadius() {
        double earthRadius = 6371.0;
        assertEquals(earthRadius, nearRequest.earthRadius());
    }

    @Test
    @DisplayName("petercap: verifies limit value is correct")
    void testLimitValue() {
        assertEquals(5, nearRequest.limit(), "The limit field should match the initial value.");
    }

    @Test
    @DisplayName("petercap: verifies request type is 'near'")
    void testRequestType() {
        assertEquals("near", nearRequest.requestType, "The request type should be 'near'.");
    }

}
