package me.dio.rodolfohok.diocitiesapi.services;

import lombok.AllArgsConstructor;
import me.dio.rodolfohok.diocitiesapi.models.City;
import me.dio.rodolfohok.diocitiesapi.repositories.CityRepository;
import me.dio.rodolfohok.diocitiesapi.utils.StringLocationUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.geo.Point;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Service
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class DistanceService {

  private CityRepository cityRepository;

  /**
   * 1st option - use point PostgreSQL
   */
  public Double distanceByPointsInMiles(final Long city1, final Long city2) {
    return cityRepository.distanceByPoints(city1, city2);
  }

  /**
   * 2nd option - use cube PostgreSQL
   */
  public Double distanceByCubeInMeters(Long city1, Long city2) {
    final List<City> cities = cityRepository.findAllById((Arrays.asList(city1, city2)));

    Point p1 = cities.get(0).getLocation();
    Point p2 = cities.get(1).getLocation();

    return cityRepository.distanceByCube(p1.getX(), p1.getY(), p2.getX(), p2.getY());
  }

  /**
   * 3rd option - use Math Java geoLocation from City class
   */
  public Double distanceUsingMath(final Long city1, final Long city2, final EarthRadius unit) {
    final List<City> cities = cityRepository.findAllById((Arrays.asList(city1, city2)));

    final Double[] location1 = StringLocationUtils.transform(cities.get(0).getGeoLocation());
    final Double[] location2 = StringLocationUtils.transform(cities.get(1).getGeoLocation());

    return doCalculation(location1[0], location1[1], location2[0], location2[1], unit);
  }

  /**
   * 4ft option - use Math Java location from City class
   */
  public Double distanceUsingMathPoints(final Long city1, final Long city2, final EarthRadius unit) {
    final List<City> cities = cityRepository.findAllById((Arrays.asList(city1, city2)));

    Point p1 = cities.get(0).getLocation();
    Point p2 = cities.get(1).getLocation();

    return doCalculation(p1.getX(), p1.getY(), p2.getX(), p2.getY(), unit);
  }

  private double doCalculation(final double lat1, final double lon1, final double lat2,
                               final double lng2, final EarthRadius earthRadius) {
    double lat = Math.toRadians(lat2 - lat1);
    double lon = Math.toRadians(lng2 - lon1);
    double a = Math.sin(lat / 2) * Math.sin(lat / 2) +
        Math.cos(Math.toRadians(lat1)) * Math.cos(Math.toRadians(lat2)) * Math.sin(lon / 2) * Math.sin(lon / 2);
    double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));

    return earthRadius.getValue() * c;
  }
}
