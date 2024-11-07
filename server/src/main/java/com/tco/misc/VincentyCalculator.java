package com.tco.misc;

import static java.lang.Math.pow;
import static java.lang.Math.sin;
import static java.lang.Math.cos;
import static java.lang.Math.atan2;
import static java.lang.Math.sqrt;
import static java.lang.Math.round;

public class VincentyCalculator implements DistanceCalculator {

    @Override
    public Long between(GeographicCoordinate from, GeographicCoordinate to, double earthRadius) {
        
        double lat1 = from.latRadians(); // ϕ1 the first latitude
        double lon1 = from.lonRadians(); // λ1 the first longitude
        double lat2 = to.latRadians();   // ϕ2 the second latitude
        double lon2 = to.lonRadians();   // λ2 the second longitude
        double deltaLon = lon2 - lon1;   // △λ (Difference in longitude)
        
    
        double sinDeltaLon = sin(deltaLon);
        double cosLat1 = cos(lat1);
        double cosLat2 = cos(lat2);
        double sinLat1 = sin(lat1);
        double sinLat2 = sin(lat2);
        double cosDeltaLon = cos(deltaLon);
        
        double term1 = pow((cosLat2 * sinDeltaLon), 2); // First part of numerator
        double term2 = pow((cosLat1 * sinLat2 - sinLat1 * cosLat2 * cosDeltaLon), 2); // Second part of numerator

        double numerator = sqrt(term1 + term2);
        double denominator = (sinLat1 * sinLat2) + (cosLat1 * cosLat2 * cosDeltaLon);

        double centralAngle = atan2(numerator, denominator); // θ (theta) = atan2(numerator, denominator)
        double distance = earthRadius * centralAngle;
        
        return round(distance); 
    }
}




// package com.tco.misc;

// import static java.lang.Math.round;
// import static java.lang.Math.pow;
// import static java.lang.Math.sin;
// import static java.lang.Math.cos;
// import static java.lang.Math.asin;
// import static java.lang.Math.sqrt;

// public class VincentyCalculator implements DistanceCalculator {
//     public VincentyCalculator() {
//     }

//     @Override
//     public Long between(GeographicCoordinate from, GeographicCoordinate to, double earthRadius) {
        
//         double lat1 = from.latRadians(); // ϕ1
//         double lon1 = from.lonRadians(); // λ1
//         double lat2 = to.latRadians();   // ϕ2
//         double lon2 = to.lonRadians();   // λ2
//         double lonDiff = lon2 - lon1; // △λ
        
//         double longDiffSin = Math.sin(lonDiff);
//         double lat1Cos = Math.cos(lat1);
//         double lat2Cos = Math.cos(lat2);
//         double numerator1 = Math.pow((lat2Cos * longDiffSin), 2);
    
//         double lat1Sin = Math.sin(lat1);
//         double lat2Sin = Math.sin(lat2);
//         double lonDiffCos = Math.cos(lonDiff);
//         double numerator2 = (lat1Cos * lat2Sin) - (lat1Sin * lat2Cos * lonDiffCos);
//         numerator2 = Math.pow(numerator2, 2);
    
//         double numeratorTotal = sqrt((numerator1 + numerator2));
//         double denominator = (lat1Sin * lat2Sin) + (lat1Cos * lat2Cos * lonDiffCos);
    
//         double arctan = Math.atan2(numeratorTotal, denominator);
//         double resultDouble = earthRadius * arctan;
        
//         Long result = 420L; // debug
//         // Long result = Math.round(resultDouble);
//         return result;
//     }
// }
