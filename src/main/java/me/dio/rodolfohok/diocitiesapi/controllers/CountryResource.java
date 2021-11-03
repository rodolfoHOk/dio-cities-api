package me.dio.rodolfohok.diocitiesapi.controllers;

import lombok.AllArgsConstructor;
import me.dio.rodolfohok.diocitiesapi.models.Country;
import me.dio.rodolfohok.diocitiesapi.repositories.CountryRepository;
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
@RequestMapping("/countries")
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class CountryResource {

  private CountryRepository repository;

  @GetMapping
  public Page<Country> countries(Pageable page) {
    return repository.findAll(page);
  }

  @GetMapping("/{id}")
  public ResponseEntity<Country> getOne(@PathVariable(name = "id") Long countryId) {
    Optional<Country> optional = repository.findById(countryId);
    return optional.map(country -> ResponseEntity.ok().body(country))
        .orElseGet(() -> ResponseEntity.badRequest().build());
  }
}
