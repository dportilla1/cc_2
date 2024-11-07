package com.tco.requests;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.tco.misc.OptimizerFactory;
import com.tco.misc.TourConstruction;
import com.tco.misc.BadRequestException;

public class TourRequest extends Request {
    public Places places;
    public Double earthRadius;
    public String formula;
    public Double response;

    private static final transient Logger log = LoggerFactory.getLogger(TourRequest.class);

    public TourRequest(Places places, double earthRadius, String formula, double response) {
        super();
        this.requestType = "tour";
        this.earthRadius = earthRadius;
        this.formula = formula;
        this.places = places;
        this.response = response;
    }

    @Override
    public void buildResponse() throws BadRequestException {
        int amountOfPlaces = places.size();
        OptimizerFactory optimizerFactory = new OptimizerFactory();
        TourConstruction tourConstruction = optimizerFactory.get(amountOfPlaces, response);

        Places optimized = tourConstruction.construct(places, earthRadius, formula, response);

        this.places = optimized;

        log.trace("buildResponse -> {}", this);
    }


    public String getRequestType() {
        return requestType;
    }

    public double earthRadius() {
        return this.earthRadius;
    }

    public String formula() {
        return this.formula;
    }

    public Places places() {
        return this.places;
    }

    public double response() {
        return this.response;
    }
}
