package me.dio.rodolfohok.diocitiesapi.controllers;

import lombok.AllArgsConstructor;
import me.dio.rodolfohok.diocitiesapi.services.DistanceService;
import me.dio.rodolfohok.diocitiesapi.services.EarthRadius;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
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
  public ResponseEntity<Double> byPoints(@RequestParam(name = "from") Long city1, @RequestParam(name = "to") Long city2){
    try {
      Double distance = service.distanceByPointsInMiles(city1, city2);
      if (distance == null) {
        return ResponseEntity.notFound().build();
      }
      return ResponseEntity.ok().body(distance);
    } catch (IndexOutOfBoundsException e) {
      return ResponseEntity.notFound().build();
    }
  }

  @GetMapping("/by-cube")
  public ResponseEntity<Double> byCube(@RequestParam(name = "from") Long city1, @RequestParam(name = "to") Long city2){
    try {
      return ResponseEntity.ok().body(service.distanceByCubeInMeters(city1, city2));
    } catch (IndexOutOfBoundsException e) {
      return ResponseEntity.notFound().build();
    }
  }

  @GetMapping("/by-math")
  public ResponseEntity<Double> byMath(@RequestParam(name = "from") Long city1,
                       @RequestParam(name = "to") Long city2,
                       @RequestParam EarthRadius unit){
    try {
      return ResponseEntity.ok().body(service.distanceUsingMath(city1, city2, unit));
    } catch (IndexOutOfBoundsException e) {
      return ResponseEntity.notFound().build();
    }
  }
}
