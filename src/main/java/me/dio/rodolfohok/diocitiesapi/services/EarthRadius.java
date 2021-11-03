package me.dio.rodolfohok.diocitiesapi.services;

import lombok.Getter;

@Getter
public enum EarthRadius {
  METERS("m", 6378168),
  KILOMETERS("km", 6378.168f),
  MILES("mi", 3958.747716f);

  private final String unit;
  private final float value;

  EarthRadius(final String unit, final float value) {
    this.unit = unit;
    this.value = value;
  }
}
