package mta.tuanthinh.controller;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import mta.tuanthinh.modal.Scope;
import mta.tuanthinh.utility.Utilities;


@RestController
@RequestMapping("/api/scope")
public class ScopeApi {
	
	@Autowired
	RestTemplate restTemplate;
	
	@GetMapping("/all")
	public List<Scope> findAll(){
		String url = "http://localhost:8181/scope/all";
		HttpEntity<Scope> entity = new HttpEntity<Scope>(Utilities.getHeader());
		ResponseEntity<Scope[]> responseEntity = restTemplate.exchange(url, HttpMethod.GET, entity, Scope[].class);
		List<Scope> getList = Arrays.asList(responseEntity.getBody());
		return getList;
	}
	
	@GetMapping("/id/{id}")
	public Scope findById(@PathVariable String id) {
		String url = "http://localhost:8181/scope/id/{id}";
		HttpEntity<Scope> entity = new HttpEntity<Scope>(Utilities.getHeader());
		ResponseEntity<Scope> responseEntity = restTemplate.exchange(url, HttpMethod.GET, entity, Scope.class, id);
		return responseEntity.getBody();
	}
	
	@GetMapping("/name/{name}")
	public Scope findByName(@PathVariable String name) {
		String url = "http://localhost:8181/scope/name/{name}";
		HttpEntity<Scope> entity = new HttpEntity<Scope>(Utilities.getHeader());
		ResponseEntity<Scope> responseEntity = restTemplate.exchange(url, HttpMethod.GET, entity, Scope.class, name);
		return responseEntity.getBody();
	}
	
	@PostMapping("/add")
	public Scope add(@RequestBody Scope scope) {
		String url = "http://localhost:8181/scope/add";
		HttpEntity<Scope> entity = new HttpEntity<Scope>(scope, Utilities.getHeader());
		ResponseEntity<Scope> responseEntity = restTemplate.exchange(url, HttpMethod.POST, entity, Scope.class);
		return responseEntity.getBody();
	}
	
	@PutMapping("/update")
	public Scope update(@RequestBody Scope scope) {
		String url = "http://localhost:8181/scope/update";
		HttpEntity<Scope> entity = new HttpEntity<Scope>(scope, Utilities.getHeader());
		ResponseEntity<Scope> responseEntity = restTemplate.exchange(url, HttpMethod.PUT, entity, Scope.class);
		return responseEntity.getBody();
	}
	
	@DeleteMapping("/delete/{id}")
	public String deleteById(@PathVariable String id) {
		String url = "http://localhost:8181/scope/delete/{id}";
		HttpEntity<String> entity = new HttpEntity<String>(Utilities.getHeader());
		ResponseEntity<String> responseEntity = restTemplate.exchange(url, HttpMethod.DELETE, entity, String.class, id);
		return responseEntity.getBody();
	}
}
