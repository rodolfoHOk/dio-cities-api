package me.dio.rodolfohok.diocitiesapi.controllers;

import lombok.AllArgsConstructor;
import me.dio.rodolfohok.diocitiesapi.services.DistanceService;
import me.dio.rodolfohok.diocitiesapi.services.EarthRadius;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/distances")
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class DistancesResource {

  private DistanceService service;

  @GetMapping("/by-points")
  public Double byPoints(@RequestParam(name = "from") Long city1, @RequestParam(name = "to") Long city2){
    return service.distanceByPointsInMiles(city1, city2);
  }

  @GetMapping("/by-cube")
  public Double byCube(@RequestParam(name = "from") Long city1, @RequestParam(name = "to") Long city2){
    return service.distanceByCubeInMeters(city1, city2);
  }

  @GetMapping("/by-math")
  public Double byMath(@RequestParam(name = "from") Long city1,
                       @RequestParam(name = "to") Long city2,
                       @RequestParam EarthRadius unit){
    return service.distanceUsingMath(city1, city2, unit);
  }
}
