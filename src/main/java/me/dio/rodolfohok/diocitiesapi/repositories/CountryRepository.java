package me.dio.rodolfohok.diocitiesapi.repositories;

import me.dio.rodolfohok.diocitiesapi.models.Country;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CountryRepository extends JpaRepository<Country, Long> {
}
