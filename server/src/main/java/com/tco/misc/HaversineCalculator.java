package com.tco.misc;

import static java.lang.Math.round;
import static java.lang.Math.pow;
import static java.lang.Math.sin;
import static java.lang.Math.cos;
import static java.lang.Math.asin;
import static java.lang.Math.sqrt;

public class HaversineCalculator implements DistanceCalculator {

  public HaversineCalculator() {
  }

  @Override
  public Long between (GeographicCoordinate from, GeographicCoordinate to, double earthRadius) {
    double firstLat = from.latRadians();
    double secondLat = to.latRadians();
    double firstLon = from.lonRadians();
    double secondLon = to.lonRadians();
    
    double sinLat = Math.pow(Math.sin((secondLat - firstLat)/2),2);
    double sinLon = Math.pow(Math.sin((secondLon - firstLon)/2),2);
    double cosLat = Math.pow(Math.cos((firstLat + secondLat)/2),2);

    double haversine = 2 * Math.asin(Math.sqrt(sinLat + (cosLat - sinLat) * sinLon));
    haversine = haversine * earthRadius;
    Long result = Math.round(haversine);
    
    return result;
  }
}
