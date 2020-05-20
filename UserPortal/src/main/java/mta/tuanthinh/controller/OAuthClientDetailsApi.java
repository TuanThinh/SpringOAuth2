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

import mta.tuanthinh.modal.OAuthClientDetails;
import mta.tuanthinh.utility.Utilities;

@RestController
@RequestMapping("/api/oauth-client")
public class OAuthClientDetailsApi {
	
	@Autowired
	RestTemplate restTemplate;
	
	@GetMapping("/all")
	public List<OAuthClientDetails> findAll(){
		String url = "http://localhost:8181/oauth-client/all";
		HttpEntity<OAuthClientDetails> entity = new HttpEntity<OAuthClientDetails>(Utilities.getHeader());
		ResponseEntity<OAuthClientDetails[]> responseEntity = restTemplate.exchange(url, HttpMethod.GET, entity, OAuthClientDetails[].class);
		List<OAuthClientDetails> getList = Arrays.asList(responseEntity.getBody());
		return getList;
	}
	
	@GetMapping("/id/{id}")
	public OAuthClientDetails findById(@PathVariable String id) {
		String url = "http://localhost:8181/oauth-client/id/{id}";
		HttpEntity<OAuthClientDetails> entity = new HttpEntity<OAuthClientDetails>(Utilities.getHeader());
		ResponseEntity<OAuthClientDetails> responseEntity = restTemplate.exchange(url, HttpMethod.GET, entity, OAuthClientDetails.class, id);
		return responseEntity.getBody();
	}
//	
//	@GetMapping("/name/{name}")
//	public Scope findByName(@PathVariable String name) {
//		String url = "http://localhost:8181/scope/name/{name}";
//		HttpEntity<Scope> entity = new HttpEntity<Scope>(Utilities.getHeader());
//		ResponseEntity<Scope> responseEntity = restTemplate.exchange(url, HttpMethod.GET, entity, Scope.class, name);
//		return responseEntity.getBody();
//	}
	
	@PostMapping("/add")
	public OAuthClientDetails add(@RequestBody OAuthClientDetails oAuthClientDetails) {
		String url = "http://localhost:8181/oauth-client/add";
		HttpEntity<OAuthClientDetails> entity = new HttpEntity<OAuthClientDetails>(oAuthClientDetails, Utilities.getHeader());
		ResponseEntity<OAuthClientDetails> responseEntity = restTemplate.exchange(url, HttpMethod.POST, entity, OAuthClientDetails.class);
		return responseEntity.getBody();
	}
	
	@PutMapping("/update")
	public OAuthClientDetails update(@RequestBody OAuthClientDetails oAuthClientDetails) {
		String url = "http://localhost:8181/oauth-client/update";
		HttpEntity<OAuthClientDetails> entity = new HttpEntity<OAuthClientDetails>(oAuthClientDetails, Utilities.getHeader());
		ResponseEntity<OAuthClientDetails> responseEntity = restTemplate.exchange(url, HttpMethod.PUT, entity, OAuthClientDetails.class);
		return responseEntity.getBody();
	}
	
	@DeleteMapping("/delete/{id}")
	public String deleteById(@PathVariable String id) {
		String url = "http://localhost:8181/scope/oauth-client/{id}";
		HttpEntity<String> entity = new HttpEntity<String>(Utilities.getHeader());
		ResponseEntity<String> responseEntity = restTemplate.exchange(url, HttpMethod.GET, entity, String.class, id);
		return responseEntity.getBody();
	}
}
