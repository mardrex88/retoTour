package com.mardrex.reto.repositories;

import com.mardrex.reto.collections.Cyclist;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Optional;

public interface CyclistRepository extends ReactiveMongoRepository<Cyclist, String> {
        Flux<Cyclist> getCountByTeamName(String teamId);
        Flux<Cyclist> findAllByTeamId(String id);
}
