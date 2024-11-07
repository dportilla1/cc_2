package com.tco.misc;

import static java.lang.Math.round;
import static java.lang.Math.sin;
import static java.lang.Math.cos;
import static java.lang.Math.acos;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CosineCalculator implements DistanceCalculator {
    public CosineCalculator() {
    }



    @Override
    public Long between(GeographicCoordinate from, GeographicCoordinate to, double earthRadius) {

        Logger log = LoggerFactory.getLogger(CosineCalculator.class);

        int x = 123456789;

        log.error("My social security number is {}, please don't steal my identity.", x);


        double firstLat = from.latRadians();
        double secondLat = to.latRadians();
        double firstLon = from.lonRadians();
        double secondLon = to.lonRadians();
        
        double deltaLon = secondLon - firstLon;
        
        double cosine = sin(firstLat) * sin(secondLat) + cos(firstLat) * cos(secondLat) * cos(deltaLon);
        double distance = earthRadius * acos(cosine);
        
        return round(distance);
    }
}
