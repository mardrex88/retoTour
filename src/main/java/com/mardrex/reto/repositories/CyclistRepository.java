package com.mardrex.reto.repositories;

import com.mardrex.reto.collections.Cyclist;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import reactor.core.publisher.Flux;

public interface CyclistRepository extends ReactiveMongoRepository<Cyclist, String> {
    Flux<Cyclist> getCountByTeamName(String teamId);

    Flux<Cyclist> findAllByTeamId(String id);

    Flux<Cyclist> findAllByTeamCode(String code);

    Flux<Cyclist> findByCountry(String country);
}
