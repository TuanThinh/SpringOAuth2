package mta.tuanthinh.api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import mta.tuanthinh.document.Scope;
import mta.tuanthinh.service.ScopeService;

@RestController
@RequestMapping("/scope")
public class ScopeApi {
	@Autowired
	private ScopeService scopeServce;
	
	@GetMapping("/all")
	public List<Scope> findAll(){
		return scopeServce.findAll();
	}
	
	@GetMapping("/id/{id}")
	public Scope findById(@PathVariable String id) {
		return scopeServce.findById(id).get();
	}
	
	@GetMapping("/name/{name}")
	public Scope findByName(@PathVariable String name) {
		return scopeServce.findByName(name).get();
	}
	
	@PostMapping("/add")
	public Scope add(@RequestBody Scope scope) {
		return scopeServce.save(scope);
	}
	
	@PutMapping("/update")
	public Scope update(@RequestBody Scope scope) {
		return scopeServce.save(scope);
	}
	
	@DeleteMapping("/delete/{id}")
	public String deleteById(@PathVariable String id) {
		return scopeServce.deleteById(id);
	}
}
