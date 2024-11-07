package com.tco.misc;

import com.tco.requests.Places;

public class NoOptimizer extends TourConstruction {

    @Override
    public Places construct(Places places, Double radius, String formula, Double reponse) {
        return places;
    }

    @Override
    public void improve() {}
}