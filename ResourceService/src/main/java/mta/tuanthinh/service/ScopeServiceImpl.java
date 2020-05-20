package mta.tuanthinh.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import mta.tuanthinh.document.Scope;
import mta.tuanthinh.repository.ScopeRepository;

@Service
public class ScopeServiceImpl implements ScopeService{

	@Autowired
	private ScopeRepository scopeRepository;
	
	@Override
	public Optional<Scope> findById(String id) {
		return scopeRepository.findById(id);
	}

	@Override
	public Optional<Scope> findByName(String name) {
		return scopeRepository.findByName(name);
	}

	@Override
	public List<Scope> findAll() {
		return scopeRepository.findAll();
	}

	@Override
	public Scope save(Scope scope) {
		return scopeRepository.insert(scope);
	}

	@Override
	public String deleteById(String id) {
		scopeRepository.deleteById(id);
		return id;
	}

}
