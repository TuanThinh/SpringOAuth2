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

import mta.tuanthinh.modal.Role;
import mta.tuanthinh.utility.Utilities;

@RestController
@RequestMapping("/api/role")
public class RoleApi {

	@Autowired
	RestTemplate restTemplate;
	
	@GetMapping("/all")
	public List<Role> findAll(){
		String url = "http://localhost:8181/role/all";
		HttpEntity<Role> entity = new HttpEntity<Role>(Utilities.getHeader());
		ResponseEntity<Role[]> responseEntity = restTemplate.exchange(url, HttpMethod.GET, entity, Role[].class);
		List<Role> getList = Arrays.asList(responseEntity.getBody());
		return getList;
	}
	
	@GetMapping("/id/{id}")
	public Role findById(@PathVariable String id) {
		String url = "http://localhost:8181/role/id/{id}";
		HttpEntity<Role> entity = new HttpEntity<Role>(Utilities.getHeader());
		ResponseEntity<Role> responseEntity = restTemplate.exchange(url, HttpMethod.GET, entity, Role.class, id);
		return responseEntity.getBody();
	}
	
	@GetMapping("/name/{name}")
	public Role findByName(@PathVariable String name) {
		String url = "http://localhost:8181/role/name/{name}";
		HttpEntity<Role> entity = new HttpEntity<Role>(Utilities.getHeader());
		ResponseEntity<Role> responseEntity = restTemplate.exchange(url, HttpMethod.GET, entity, Role.class, name);
		return responseEntity.getBody();
	}
	
	@PostMapping("/add")
	public Role add(@RequestBody Role role) {
		String url = "http://localhost:8181/role/add";
		HttpEntity<Role> entity = new HttpEntity<Role>(role, Utilities.getHeader());
		ResponseEntity<Role> responseEntity = restTemplate.exchange(url, HttpMethod.POST, entity, Role.class);
		return responseEntity.getBody();
	}
	
	@PutMapping("/update")
	public Role update(@RequestBody Role role) {
		String url = "http://localhost:8181/role/update";
		HttpEntity<Role> entity = new HttpEntity<Role>(role, Utilities.getHeader());
		ResponseEntity<Role> responseEntity = restTemplate.exchange(url, HttpMethod.PUT, entity, Role.class);
		return responseEntity.getBody();
	}
	
	@DeleteMapping("/delete/{id}")
	public String deleteById(@PathVariable String id) {
		String url = "http://localhost:8181/role/delete/{id}";
		HttpEntity<String> entity = new HttpEntity<String>(Utilities.getHeader());
		ResponseEntity<String> responseEntity = restTemplate.exchange(url, HttpMethod.DELETE, entity, String.class, id);
		return responseEntity.getBody();
	}
}
