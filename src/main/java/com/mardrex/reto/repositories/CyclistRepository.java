package com.mardrex.reto.repositories;

import com.mardrex.reto.collections.Cyclist;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;

public interface CyclistRepository extends ReactiveCrudRepository<Cyclist, String> {

}
