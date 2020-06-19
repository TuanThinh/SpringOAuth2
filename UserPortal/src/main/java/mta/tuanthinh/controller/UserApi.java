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

import mta.tuanthinh.modal.User;
import mta.tuanthinh.utility.Utilities;

@RestController
@RequestMapping("/api/user-account")
public class UserApi {

	@Autowired
	RestTemplate restTemplate;
	
	@GetMapping("/all")
	public List<User> findAll(){
		String url = "http://localhost:8181/user/all";
		HttpEntity<User> entity = new HttpEntity<User>(Utilities.getHeader());
		ResponseEntity<User[]> responseEntity = restTemplate.exchange(url, HttpMethod.GET, entity, User[].class);
		List<User> getList = Arrays.asList(responseEntity.getBody());
		return getList;
	}
	
	@GetMapping("/id/{id}")
	public User findById(@PathVariable String id) {
		String url = "http://localhost:8181/user/id/{id}";
		HttpEntity<User> entity = new HttpEntity<User>(Utilities.getHeader());
		ResponseEntity<User> responseEntity = restTemplate.exchange(url, HttpMethod.GET, entity, User.class, id);
		return responseEntity.getBody();
	}
	
	@GetMapping("/name/{name}")
	public User findByName(@PathVariable String name) {
		String url = "http://localhost:8181/user/name/{name}";
		HttpEntity<User> entity = new HttpEntity<User>(Utilities.getHeader());
		ResponseEntity<User> responseEntity = restTemplate.exchange(url, HttpMethod.GET, entity, User.class, name);
		return responseEntity.getBody();
	}
	
	@PostMapping("/add")
	public User add(@RequestBody User user) {
		String url = "http://localhost:8181/user/add";
		HttpEntity<User> entity = new HttpEntity<User>(user, Utilities.getHeader());
		ResponseEntity<User> responseEntity = restTemplate.exchange(url, HttpMethod.POST, entity, User.class);
		return responseEntity.getBody();
	}
	
	@PutMapping("/update")
	public User update(@RequestBody User user) {
		String url = "http://localhost:8181/user/update";
		HttpEntity<User> entity = new HttpEntity<User>(user, Utilities.getHeader());
		ResponseEntity<User> responseEntity = restTemplate.exchange(url, HttpMethod.PUT, entity, User.class);
		return responseEntity.getBody();
	}
	
	@DeleteMapping("/delete/{id}")
	public String deleteById(@PathVariable String id) {
		String url = "http://localhost:8181/user/delete/{id}";
		HttpEntity<String> entity = new HttpEntity<String>(Utilities.getHeader());
		ResponseEntity<String> responseEntity = restTemplate.exchange(url, HttpMethod.DELETE, entity, String.class, id);
		return responseEntity.getBody();
	}
}
