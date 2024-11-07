package com.tco.misc;

import com.tco.requests.Places;

public class TwoOptimizer extends TourConstruction {

    @Override
    public Places construct(Places places, Double radius, String formula, Double response) {
        return places;
    }

    @Override
    public void improve() {

    }
}
