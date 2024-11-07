package com.tco.misc;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import com.tco.requests.Place;

import static org.junit.jupiter.api.Assertions.*;

public class TestVincentyCalculator {

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

    VincentyCalculator calculator = new VincentyCalculator();

    @Test
    @DisplayName("leegina: Distance of one place. Should equal 0")
    public void testDistanceOnePlace() {
        assertEquals(0L, calculator.between(center, center, fieldsEarthRadius));
    }

    @Test
    @DisplayName("petercap: lafayette to salzburg. Should equal 103885")
    public void testDistanceLafayette() {
        assertEquals(103885L, calculator.between(lafayette, salzburg, fieldsEarthRadius));
    }

    @Test
    @DisplayName("petercap: Salzburg to perth. Should equal 147437")
    public void testDistanceSalzburg() {
        assertEquals(147437L, calculator.between(salzburg, perth, fieldsEarthRadius));
    }

    @Test
    @DisplayName("jihyun: Close distance edge test case")
    public void closeDistanceTest() {
        long totalDistance = calculator.between(closePoint1, closePoint2, furlongEarthRadius)
                + calculator.between(closePoint2, closePoint3, furlongEarthRadius)
                + calculator.between(closePoint3, closePoint4, furlongEarthRadius)
                + calculator.between(closePoint4, closePoint1, furlongEarthRadius);
        assertEquals(0L, totalDistance);
    }

    @Test
    @DisplayName("petercap: To and from same destination. Should equal")
    public void testDistanceToFrom() {
        long tourTo = calculator.between(lafayette, buenosAires, fieldsEarthRadius);
        long tourFrom = calculator.between(buenosAires, lafayette, fieldsEarthRadius);

        assertEquals(tourTo, tourFrom);
    }
}




// package com.tco.misc;

// import org.junit.jupiter.api.DisplayName;
// import org.junit.jupiter.api.Test;

// import static com.tco.misc.VincentyCalculator.between;
// import static java.lang.Math.toRadians;
// import static org.junit.jupiter.api.Assertions.*;

// public class TestVincentyCalculator {

//     class Geo implements GeographicCoordinate {
//         Double degreesLatitude;
//         Double degreesLongitude;
    
//         public Geo(Double lat, Double lon){
//           this.degreesLatitude = lat;
//           this.degreesLongitude = lon;
//         }
//         public Double latRadians() {return toRadians(degreesLatitude);}
//         public Double lonRadians() {return toRadians(degreesLongitude);}
//       }
    
//       final Geo center = new Geo(0., 0.);
    
//       //leegina: test case
//       final Geo texas = new Geo(29.423143, -94.896581);
//       final Geo chile = new Geo(-35.147416, -61.258050);
//       final Geo madagascar = new Geo(-21.814350, 46.752996);
//       final Geo greece = new Geo(37.990836, 23.738339);
//       final static double furlongEarthRadius = 31670.046926;

//       //petercap: test case
//       final Geo lafayette = new Geo(37.9, -122.1);
//       final Geo salzburg = new Geo(47.8, 13.0);
//       final Geo perth = new Geo(-31.9, 115.9);
//       final Geo buenosAires = new Geo(-36.6, -58.5);
//       final static double fieldsEarthRadius = 69670.0;

//       // jihyun
//       final Geo closePoint1 = new Geo(0.0007, 0.0007);
//       final Geo closePoint2 = new Geo(0.0007, 0.0008);
//       final Geo closePoint3 = new Geo(0.0008, 0.0008);
//       final Geo closePoint4 = new Geo(0.0008, 0.0007);
    
//       @Test 
//       @DisplayName("leegina: Distance of one place. Should equal 0")
//       public void testDistanceOnePlace() {
//         assertEquals(0L, between(center, center, fieldsEarthRadius));
//       }

//      @Test 
//      @DisplayName("c836907548: lafayette to salzburg. Should equal 103885")
//      public void testDistanceLafayette() {
//        assertEquals(103885L, between(lafayette, salzburg, fieldsEarthRadius));
//      }

//      @Test 
//      @DisplayName("c836907548: Salzburg to perth. Should equal 147437")
//      public void testDistanceSalzburg() {
//        assertEquals(147437L, between(salzburg, perth, fieldsEarthRadius));
//      }

//      @Test
//      @DisplayName("jihyun: Close distance edge test case")
//      public void closeDistanceTest() {
//        long totalDistance = between(closePoint1, closePoint2, furlongEarthRadius) + between(closePoint2, closePoint3, furlongEarthRadius) + between(closePoint3, closePoint4, furlongEarthRadius) + between(closePoint4, closePoint1, furlongEarthRadius);
//        assertEquals(0L, totalDistance);
//      }

//     //  @Test 
//     //  @DisplayName("c836907548: perth to buenosAires. Should equal 135329")
//     //  public void testDistancePerth() {
//     //    assertEquals(135329L, between(perth, buenosAires, fieldsEarthRadius));
//     //  }

//     //  @Test 
//     //  @DisplayName("c836907548: buenosAires to lafayette. Should equal 115406")
//     //  public void testDistanceBuenosAires() {
//     //    assertEquals(115406L, between(buenosAires, lafayette, fieldsEarthRadius));
//     //  }

//      @Test 
//      @DisplayName("c836907548: To and from same destination. Should equal 115406")
//      public void testDistanceToFrom() {
//         long tourTo = between(lafayette, buenosAires, fieldsEarthRadius);
//         long tourFrom = between(buenosAires, lafayette, fieldsEarthRadius);

//        assertEquals(tourTo, tourFrom);
//   }

// }

// // petercap.json
// // {
// //     "requestType"    : "distances",
// //     "places"         : [{"name":"Lafayette", "latitude":  "37.9",  "longitude": "-122.1"},
// //                         {"name": "Salzburg", "latitude":  "47.8", "longitude": "13.0"},
// //                         {"name": "Perth", "latitude":  "-31.9", "longitude": "115.9"},
// //                         {"name": "Buenos Aires", "latitude":  "-36.6", "longitude": "-58.4"}],
// //     "earthRadius"    : 69670.0,
// //     "distances"      : [103885, 147437, 135329, 115406]
// //   }