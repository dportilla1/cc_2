package com.tco.misc;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

// import com.tco.misc.HaversineCalculator;
import static java.lang.Math.toRadians;
import static org.junit.jupiter.api.Assertions.*;

public class TestHaversineCalculator {

  class Geo implements GeographicCoordinate {
    Double degreesLatitude;
    Double degreesLongitude;

    public Geo(Double lat, Double lon){
      this.degreesLatitude = lat;
      this.degreesLongitude = lon;
    }
    public Double latRadians() {return toRadians(degreesLatitude);}
    public Double lonRadians() {return toRadians(degreesLongitude);}
  }

  final Geo center = new Geo(0., 0.);
  final Geo eastLongitude = new Geo(0., 180.);
  final Geo westLongitude = new Geo(0., -180.);
  final Geo sameLatitude1 = new Geo(90., 0.);
  final Geo sameLatitude2 = new Geo(90., 0.);
  
  final Geo texas = new Geo(29.423143, -94.896581);
  final Geo chile = new Geo(-35.147416, -61.258050);
  final Geo madagascar = new Geo(-21.814350, 46.752996);
  final Geo greece = new Geo(37.990836, 23.738339);

  final Geo closePoint1 = new Geo(0.0007, 0.0007);
  final Geo closePoint2 = new Geo(0.0007, 0.0008);
  final Geo closePoint3 = new Geo(0.0008, 0.0008);
  final Geo closePoint4 = new Geo(0.0008, 0.0007);

  final static double furlongEarthRadius = 31670.046926;

  HaversineCalculator calculator = new HaversineCalculator();

  @Test 
  @DisplayName("leegina: Distance of one place. Should equal 0")
  public void testDistanceOnePlace() {

    assertEquals(0L, calculator.between(center, center, furlongEarthRadius));
    assertEquals(0L, calculator.between(eastLongitude, eastLongitude, furlongEarthRadius));
    assertEquals(0L, calculator.between(sameLatitude1, sameLatitude1, furlongEarthRadius));
  }

  @Test 
  @DisplayName("leegina: Distance of same place but different points or variable. Should equal 0")
  public void testDistanceSamePlace() {
    assertEquals(0L, calculator.between(eastLongitude, westLongitude, furlongEarthRadius));
    assertEquals(0L, calculator.between(sameLatitude1, sameLatitude2, furlongEarthRadius));
  }
  
  @Test 
  @DisplayName("leegina: Texas to Greece tour. Four places from different quadrants.")
  public void testDistanceTest() {
    assertEquals(39760L, calculator.between(texas, chile, furlongEarthRadius));
    assertEquals(50406L, calculator.between(chile, madagascar, furlongEarthRadius));
    assertEquals(35152L, calculator.between(madagascar, greece, furlongEarthRadius));
    assertEquals(50589L, calculator.between(greece, texas, furlongEarthRadius));
  }

  @Test
  @DisplayName("jihyun: Close distance edge test case")
  public void closeDistanceTest() {

    long distance1 = calculator.between(closePoint1, closePoint2, furlongEarthRadius);
    long distance2 = calculator.between(closePoint2, closePoint3, furlongEarthRadius);
    long distance3 = calculator.between(closePoint3, closePoint4, furlongEarthRadius);
    long distance4 = calculator.between(closePoint4, closePoint1, furlongEarthRadius);

    long totalDistance = distance1 + distance2 + distance3 + distance4;
    
    assertEquals(0L, totalDistance);
  }
}
