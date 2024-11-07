package com.tco.requests;

import com.tco.misc.BadRequestException; // to return 400
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class NearRequest extends Request {
    private static final transient Logger log = LoggerFactory.getLogger(NearRequest.class);

    public Place place;
    public int distance;
    public Double earthRadius;
    public String formula;
    public int limit;
    public Places places;
    public Distances distances;

    @Override
    public void buildResponse() throws BadRequestException {
        places = buildNearList();
        distances = returnDistances();
        log.trace("buildResponse -> {}", this);
    }

    public Places buildNearList() throws BadRequestException {
        // ...
        return places;
    }

    public Distances returnDistances() throws BadRequestException {
        distances = new Distances();
        return distances;
    }

    public NearRequest(Place place, int distance, Double earthRadius, String formula, int limit) {
        super();
        this.requestType = "near";
        this.place = place;
        this.distance = distance;
        this.earthRadius = earthRadius;
        this.formula = formula;
        this.limit = limit;
    }

    public Place place() {
        return place;
    }

    public int distance() {
        return distance;
    }

    public Double earthRadius() {
        return earthRadius;
    }

    public String formula() {
        return formula;
    }

    public int limit() {
        return limit;
    }

    public Places places() {
        return places;
    }

    public Distances distances() {
        return distances;
    }
}
