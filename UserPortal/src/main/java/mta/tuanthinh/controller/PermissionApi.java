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

import mta.tuanthinh.modal.Permission;
import mta.tuanthinh.utility.Utilities;

@RestController
@RequestMapping("/api/permission")
public class PermissionApi {
	@Autowired
	RestTemplate restTemplate;
	
	@GetMapping("/all")
	public List<Permission> findAll(){
		String url = "http://localhost:8181/permission/all";
		HttpEntity<Permission> entity = new HttpEntity<Permission>(Utilities.getHeader());
		ResponseEntity<Permission[]> responseEntity = restTemplate.exchange(url, HttpMethod.GET, entity, Permission[].class);
		List<Permission> getList = Arrays.asList(responseEntity.getBody());
		return getList;
	}
	
	@GetMapping("/id/{id}")
	public Permission findById(@PathVariable String id) {
		String url = "http://localhost:8181/permission/id/{id}";
		HttpEntity<Permission> entity = new HttpEntity<Permission>(Utilities.getHeader());
		ResponseEntity<Permission> responseEntity = restTemplate.exchange(url, HttpMethod.GET, entity, Permission.class, id);
		return responseEntity.getBody();
	}
	
	@GetMapping("/name/{name}")
	public Permission findByName(@PathVariable String name) {
		String url = "http://localhost:8181/permission/name/{name}";
		HttpEntity<Permission> entity = new HttpEntity<Permission>(Utilities.getHeader());
		ResponseEntity<Permission> responseEntity = restTemplate.exchange(url, HttpMethod.GET, entity, Permission.class, name);
		return responseEntity.getBody();
	}
	
	@PostMapping("/add")
	public Permission add(@RequestBody Permission permission) {
		String url = "http://localhost:8181/permission/add";
		HttpEntity<Permission> entity = new HttpEntity<Permission>(permission, Utilities.getHeader());
		ResponseEntity<Permission> responseEntity = restTemplate.exchange(url, HttpMethod.POST, entity, Permission.class);
		return responseEntity.getBody();
	}
	
	@PutMapping("/update")
	public Permission update(@RequestBody Permission permission) {
		String url = "http://localhost:8181/permission/update";
		HttpEntity<Permission> entity = new HttpEntity<Permission>(permission, Utilities.getHeader());
		ResponseEntity<Permission> responseEntity = restTemplate.exchange(url, HttpMethod.PUT, entity, Permission.class);
		return responseEntity.getBody();
	}
	
	@DeleteMapping("/delete/{id}")
	public String deleteById(@PathVariable String id) {
		String url = "http://localhost:8181/permission/delete/{id}";
		HttpEntity<String> entity = new HttpEntity<String>(Utilities.getHeader());
		ResponseEntity<String> responseEntity = restTemplate.exchange(url, HttpMethod.DELETE, entity, String.class, id);
		return responseEntity.getBody();
	}
}
