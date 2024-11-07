package com.tco.requests;

import com.tco.misc.DistanceCalculator;
import com.tco.misc.VincentyCalculator;
import com.tco.misc.HaversineCalculator;
import com.tco.misc.CosineCalculator;
import com.tco.misc.GeographicCoordinate;
import com.tco.requests.Distances;
import com.tco.requests.Place;
import com.tco.requests.Places;
import com.tco.misc.CalculatorFactory;

import com.tco.misc.BadRequestException; // to return 400

import java.util.List;
import java.util.ArrayList;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DistancesRequest extends Request {

  private static final transient Logger log = LoggerFactory.getLogger(DistancesRequest.class);

  public Places places;
  public Double earthRadius;
  public String formula;
  public Distances distances;

  @Override
  public void buildResponse() throws BadRequestException {
    distances = buildDistancesList();
    log.trace("buildResponse -> {}", this);
  }
  
  public Distances buildDistancesList() throws BadRequestException {
    distances = new Distances();
    GeographicCoordinate from = new Place(), to = new Place();
    CalculatorFactory calculator = new CalculatorFactory();
    DistanceCalculator calculatorformula = calculator.get(formula);

    for (int i = 0; i < places.size(); i++) {
      from = places.get(i);
      to = places.get(0);
      if (i != places.size() - 1) {to = places.get(i+1);}
      distances.add(calculatorformula.between(from, to, this.earthRadius));
    }
    return distances;
  }

  public DistancesRequest(double earthRadius, String formula, Places places) {
    super();
    this.requestType = "distances";
    this.earthRadius = earthRadius;
    this.formula = formula;
    this.places = places;
  }

  public DistancesRequest(double earthRadius, Places places) {
    super();
    this.requestType = "distances";
    this.earthRadius = earthRadius;
    this.places = places;
  }

  public double earthRadius() {return this.earthRadius; }
  
  public String formula() {return this.formula; }

  public Places places() {return this.places; }

  public Distances distances() {return this.distances; }

}

