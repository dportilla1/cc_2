package com.tco.misc;

import com.tco.misc.DistanceCalculator;
import com.tco.misc.VincentyCalculator;
import com.tco.misc.HaversineCalculator;
import com.tco.misc.CosineCalculator;
import com.tco.misc.GeographicCoordinate;
import com.tco.requests.Distances;
import com.tco.requests.Place;
import com.tco.requests.Places;

import com.tco.misc.BadRequestException; // to return 400

import java.util.List;
import java.util.ArrayList;

public class CalculatorFactory {
  public DistanceCalculator get(String formula) throws BadRequestException {
    DistanceCalculator calculator = new VincentyCalculator();
    if (formula != null) {
      switch (formula) {
        case "vincenty":
          calculator = new VincentyCalculator();
          break;
        case "haversine":
          calculator = new HaversineCalculator();
          break;
        case "cosines":
          calculator = new CosineCalculator();
          break;
        default:
          throw new BadRequestException();
        }
    }
    return calculator;
  }
}
