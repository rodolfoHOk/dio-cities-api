package me.dio.rodolfohok.diocitiesapi.controllers;

import lombok.AllArgsConstructor;
import me.dio.rodolfohok.diocitiesapi.models.City;
import me.dio.rodolfohok.diocitiesapi.repositories.CityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequestMapping("/cities")
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class CityResource {

  private CityRepository repository;

  @GetMapping
  public Page<City> cities(Pageable page){
    return repository.findAll(page);
  }

  @GetMapping("/{id}")
  public ResponseEntity<City> getOne(@PathVariable Long id) {
    Optional<City> optional = repository.findById(id);
    return optional.map(city -> ResponseEntity.ok().body(optional.get()))
        .orElseGet(() -> ResponseEntity.notFound().build());
  }
}
