package com.gigigo.orchextra.domain.entities;

/**
 * Created by Sergio Martinez Rodriguez
 * Date 16/12/15.
 */
public enum ProximityPointType{
  BEACON("beacon"),
  GEOFENCE("geofence");

  private final String text;

  ProximityPointType(final String text) {
    this.text = text;
  }

  public String getStringValue() {
    return text;
  }

  public static ProximityPointType getProximityPointTypeValue(String type) {
    if (type.equals(BEACON.getStringValue())){
      return BEACON;
    }else{
      return GEOFENCE;
    }
  }
}
