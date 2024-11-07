package com.tco.misc;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class OptimizerFactory {

  public TourConstruction get(int N, double response) {

    Logger log = LoggerFactory.getLogger(CosineCalculator.class);

    // bencgmarking code
    if (response == 0){
      return new NoOptimizer();
    }
    else if (N > 3) {
      log.info("OneOptimizer \n");
      return new OneOptimizer(); // postman benchmarking
    }
    else {
      return new NoOptimizer();
    }

    // old code
    // if (N > 3 && N < 100 && response < 0.181 && response < 1) {
    // log.info("OneOptimizer \n");
    // return new OneOptimizer(); // postman benchmarking
    // } else if (N > 3 && N < 200 && response < 0.582 && response < 1) {
    // log.info("OneOptimizer \n");
    // return new OneOptimizer();
    // } else if (N > 3 && N <= 325 && response <= 1) {
    // log.info("OneOptimizer \n");
    // return new OneOptimizer();
    // } else {
    // log.info("NoOptimizer \n");
    // return new NoOptimizer();
    // }
  }
}
