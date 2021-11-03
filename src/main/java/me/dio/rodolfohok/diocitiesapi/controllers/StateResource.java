package me.dio.rodolfohok.diocitiesapi.controllers;

import lombok.AllArgsConstructor;
import me.dio.rodolfohok.diocitiesapi.models.State;
import me.dio.rodolfohok.diocitiesapi.repositories.StateRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/states")
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class StateResource {

  private StateRepository repository;

  @GetMapping
  public List<State> states() {
    return repository.findAll();
  }
}
