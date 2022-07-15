package com.mardrex.reto.repositories;

import com.mardrex.reto.collections.Team;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;

@Repository
public interface TeamRepository extends ReactiveCrudRepository<Team, String> {
    Flux<Team> findByCountry(String country);
}
