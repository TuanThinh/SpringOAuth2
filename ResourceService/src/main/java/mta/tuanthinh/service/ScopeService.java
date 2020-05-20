package mta.tuanthinh.service;

import java.util.List;
import java.util.Optional;

import mta.tuanthinh.document.Scope;

public interface ScopeService {
	Optional<Scope> findById(String id);
	Optional<Scope> findByName(String name);
	List<Scope> findAll();
	Scope save(Scope scope);
	String deleteById(String id);
}
