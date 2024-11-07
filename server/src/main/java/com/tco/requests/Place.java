package com.tco.requests;

import java.util.HashMap;
import java.lang.Math;
import java.util.LinkedHashMap;
import com.tco.misc.GeographicCoordinate;

import static java.lang.Double.parseDouble;
import static java.lang.Math.toRadians;

public class Place extends LinkedHashMap<String, String> implements GeographicCoordinate  {

  public Place (String lat, String lon) {
    this.put("latitude", lat);
    this.put("longitude", lon);
  }

  //necessary for GSON 
  public Place() {}
  
  @Override
  public Double latRadians(){
    return toRadians(Double.parseDouble(this.get("latitude")));
  }

  @Override
  public Double lonRadians(){
    return toRadians(Double.parseDouble(this.get("longitude")));
  }
}
