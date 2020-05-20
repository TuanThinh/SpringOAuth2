package mta.tuanthinh.repository;

import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;

import mta.tuanthinh.document.Scope;

public interface ScopeRepository extends MongoRepository<Scope, String>{
	Optional<Scope> findByName(String name);
}
