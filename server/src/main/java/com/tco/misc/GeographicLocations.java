package com.tco.misc;

import java.util.List;
import com.tco.requests.*;

public class GeographicLocations {
    Places find(String match, List<String> type, List<String> where, Integer limit) {
        Places places = new Places();
        try {
            places = Database.places(match, limit);
            places = findPlaces(places, type, where);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return places;
    }
    
    private Places findPlaces(Places places, List<String> type, List<String> where) {
        Places filterPlaces = new Places();
        for (Place place : places) {
            boolean matchedType = (type.contains(place.get("type")) || type.isEmpty() || type == null);
            boolean matchedWhere = (where.contains(place.get("iso_country")) || type.isEmpty() || type == null);
            if (matchedType && matchedWhere) {
                filterPlaces.add(place);
            }
        }
        return filterPlaces;
    }

    Integer found(String match, List<String> type, List<String> where) {
        try {
            Places places = Database.places(match, Integer.MAX_VALUE);
            return foundPlaces(places, type, where);
        }
        catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }

    private Integer foundPlaces(Places places, List<String> type, List<String> where) {
        Places filterPlaces = findPlaces(places, type, where);
        return filterPlaces.size();
    }

    //skeleton code for near()
    Places near(Places place, Integer distance, Double earthRadius, String formula, Integer limit) {
        Places places = new Places();
        return places;
    }

    //skeleton code for distances()
    Distances distances(Place place, Places places) {
        return null;
    }
}
