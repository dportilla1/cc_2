package com.tco.misc;

import java.util.Arrays;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.tco.requests.Distances;
import com.tco.requests.Place;
import com.tco.requests.Places;

import java.util.Date;

public abstract class TourConstruction {

    final long benchmark = 1000000000;
    final int STARTING_INDEX = -4;

    int[] tour;
    boolean[] visited;
    int placesAmount;
    long newDistances;
    long[][] distances; // initialize in construct if we are using concurency
    long nearestDistance;
    int nearestPlace;
    boolean isRunning;
    long stopTime;
    // int bestStartingPlace = -1;

    private static final transient Logger log = LoggerFactory.getLogger(TourConstruction.class);

    public Places construct(Places places, Double radius, String formula, Double response) throws BadRequestException {
        log.info("\n\n[construct]");

        initialize(places);
        long originalDistance = originalDistance(places, radius, formula);
        distances = new long[placesAmount][placesAmount];
        fillMatrix(places, radius, formula);
        long shortestTourDistance = Long.MAX_VALUE;
        int bestStartingPlace = STARTING_INDEX;

        for (int startingCity = 0; startingCity < placesAmount; startingCity++) {
            getNearestNeighbor(startingCity);

            if (newDistances < shortestTourDistance) {
                shortestTourDistance = newDistances;
                bestStartingPlace = startingCity;
            }
            if (checkTime()) {
                log.info("[construct] break");
                break;
            }
        }
        finalizeBestTour(originalDistance, bestStartingPlace, shortestTourDistance, places);
        return places;
    }

    public void initialize(Places places) {
        stopTime = System.currentTimeMillis() + benchmark;
        placesAmount = places.size();
        tour = new int[placesAmount];
    }

    public void finalizeBestTour(long originalDistance, int bestStartingPlace, long shortestTourDistance,
            Places places) {
        if (bestStartingPlace != STARTING_INDEX) {
            getNearestNeighbor(bestStartingPlace);
        }
        tour = rotateToStart(tour);
        if (originalDistance > shortestTourDistance) {
            setNearestNeighbor(places);
        }
    }

    public boolean checkTime() {
        // log.info("[checkTime] stopTime {}", stopTime);
        long currentTime = System.currentTimeMillis();

        if (currentTime > stopTime) {
            log.info("[currentTime] breaking {}", currentTime);
            return true;
        }
        return false;
    }

    public static int[] rotateToStart(int[] arr) {
        if (arr[0] == 0) {
            return arr;
        }

        int zeroIndex = -2;

        for (int i = 0; i < arr.length; i++) {
            if (arr[i] == 0) {
                zeroIndex = i;
                break;
            }
        }

        int[] result = new int[arr.length];
        System.arraycopy(arr, zeroIndex, result, 0, arr.length - zeroIndex);
        System.arraycopy(arr, 0, result, arr.length - zeroIndex, zeroIndex);

        return result;
    }

    public long originalDistance(Places places, Double radius, String formula) throws BadRequestException {
        log.info("\n\n[originalDistance]");
        // TODO refactor to use matrix instead of calculating from scratch

        long sum = 0;
        Distances totalDistances = new Distances();
        Place from;
        Place to;
        CalculatorFactory calculator = new CalculatorFactory();
        DistanceCalculator calculatorFormula = calculator.get(formula);

        for (int i = 0; i < places.size(); i++) {
            from = places.get(i);
            to = places.get((i + 1) % places.size());
            log.info("[originalDistance] distance: {} {}", from, to);
            totalDistances.add(calculatorFormula.between(from, to, radius));
        }
        sum = totalDistances.total();
        log.info("[originalDistance] sum: {}", sum);
        return sum;
    }

    private void getNearestNeighbor(int startingCity) {
        log.info("[getNearestNeighbor]\n");
        setVisited();
        int currentPlace = startingCity;
        tour[0] = currentPlace;
        visited[currentPlace] = true;
        newDistances = 0;
        for (int i = 1; i < placesAmount; i++) {
            findNearest(currentPlace);

            if (checkTime()) {
                return;
            }
            newDistances += nearestDistance;
            tour[i] = nearestPlace;
            visited[nearestPlace] = true;
            currentPlace = nearestPlace;
        }

        newDistances += distances[currentPlace][tour[0]];
        // log.info("[getNearestNeighbor] matrix: {}", Arrays.deepToString(distances));
        // debug
    }

    private int findNearest(int currentPlace) {
        nearestPlace = -1;
        nearestDistance = Long.MAX_VALUE;

        for (int j = 0; j < placesAmount; j++) {
            if (!visited[j] && distances[currentPlace][j] < nearestDistance) {
                nearestPlace = j;
                nearestDistance = distances[currentPlace][j];
            }

            if (checkTime()) {
                return nearestPlace;
            }
        }
        return nearestPlace;
    }

    private void setNearestNeighbor(Places places) {
        log.info("\n");
        log.info("[setNearestNeighbor] starts");

        Places newPlaces = new Places();
        for (int i = 0; i < placesAmount; i++) {
            newPlaces.add(places.get(tour[i]));
            log.info("[setNearestNeighbor] places.get(tour[i]): {}", places.get(tour[i]));
        }

        places.clear();
        places.addAll(newPlaces);

        log.info("[setNearestNeighbor] ends");
    }

    public void fillMatrix(Places places, Double radius, String formula)
            throws BadRequestException {
        log.info("[fillMatrix]\n");
        calculateLowerTriangle(places, radius, formula);
        setMainDiagnol();
        setUpperDiagnol();
        // log.info("[fillMatrix] matrix: {}", Arrays.deepToString(distances));
    }

    public void calculateLowerTriangle(Places places, Double radius, String formula)
            throws BadRequestException {
        log.info("\n[calculateLowerTriangle]");

        CalculatorFactory calculatorFactory = new CalculatorFactory();
        DistanceCalculator calculator = calculatorFactory.get(formula);

        for (int i = 0; i < distances.length; i++) {
            for (int j = 0; j < i; j++) {
                Place to = places.get(j);
                Place from = places.get(i);
                distances[i][j] = calculator.between(from, to, radius);
            }
        }
        // log.info("[getmatrix] matrix: {}", Arrays.deepToString(distances)); // debug
    }

    public void setMainDiagnol() {
        log.info("\n[setMainDiagnol]");

        for (int i = 0; i < distances.length; i++) {
            distances[i][i] = 0L;
        }
    }

    public void setUpperDiagnol() {
        log.info("\n[setUpperDiagnol]");

        for (int i = 1; i < distances.length; i++) {
            for (int j = 0; j < i; j++) {
                distances[j][i] = distances[i][j]; // mirror the lower triangle onto the upper triangle
            }
        }
    }

    private void setVisited() {
        log.info("[setVisited]\n");

        visited = new boolean[placesAmount];

        Arrays.fill(visited, false);
        // log.info("[setVisited] visited: {}", Arrays.toString(visited));
        // TODO are log prints part of our final benchmark?
    }

    public abstract void improve();
}

