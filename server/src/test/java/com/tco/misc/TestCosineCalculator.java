package com.tco.misc;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import com.tco.requests.Place;

import static org.junit.jupiter.api.Assertions.*;

public class TestCosineCalculator {

    final Place newYork = new Place("40.7128", "-74.0060");
    final Place losAngeles = new Place("34.0522", "-118.2437");
    final Place samePlace = new Place("40.7128", "-74.0060"); 
    final Place nearPoint1 = new Place("0.0001", "0.0001"); 
    final Place nearPoint2 = new Place("0.0001", "0.0002");
    final Place equatorPoint1 = new Place("0.0", "-74.0060"); 
    final Place equatorPoint2 = new Place("0.0", "0.0");     
    final Place northPole = new Place("90.0", "0.0");         
    final Place southPole = new Place("-90.0", "0.0");        

    final static double earthRadius = 6371.0; 
    final static double moonRadius = 1737.1;  

   
    CosineCalculator calculator = new CosineCalculator();

    @Test
    @DisplayName("Basic Cosine distance between New York and Los Angeles")
    public void testDistanceBetweenNYAndLA() {
        long distance = calculator.between(newYork, losAngeles, earthRadius);
        assertEquals(3936, distance); 
    }

    @Test
    @DisplayName("Distance between the same point should be 0")
    public void testDistanceSamePoint() {
        long distance = calculator.between(newYork, samePlace, earthRadius);
        assertEquals(0L, distance); 
    }

    @Test
    @DisplayName("Distance between two very close points")
    public void testDistanceVeryClosePoints() {
        long distance = calculator.between(nearPoint1, nearPoint2, earthRadius);
        assertTrue(distance < 1); 
    }

    @Test
    @DisplayName("Distance between North and South Pole")
    public void testDistancePoles() {
        long distance = calculator.between(northPole, southPole, earthRadius);
        assertEquals(20015, distance);
    }

}
