package com.mardrex.reto.repositories;

import com.mardrex.reto.collections.Team;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TeamRepository extends ReactiveCrudRepository<Team,String> {
}
