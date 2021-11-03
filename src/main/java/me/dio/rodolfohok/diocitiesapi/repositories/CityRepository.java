package me.dio.rodolfohok.diocitiesapi.repositories;

import me.dio.rodolfohok.diocitiesapi.models.City;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface CityRepository extends JpaRepository<City, Long> {

  // select ((select lat_lon from cidade where id = 4929) <@> (select lat_lon from cidade where id=5254)) as distance;
  @Query(value = "SELECT ((SELECT lat_lon FROM cidade WHERE id=?1) <@> (SELECT lat_lon FROM cidade WHERE id=?2)) as distance", nativeQuery = true)
  Double distanceByPoints(Long city1, Long city2);

  //select earth_distance(
  //    ll_to_earth(-21.95840072631836,-47.98820114135742),
  //    ll_to_earth(-22.01740074157715,-47.88600158691406)
  //) as distance;
  @Query(value = "SELECT earth_distance(ll_to_earth(?1,?2), ll_to_earth(?3,?4)) as distance", nativeQuery = true)
  Double distanceByCube(double x, double y, double x1, double y1);
}
