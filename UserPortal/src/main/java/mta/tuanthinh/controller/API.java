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

import mta.tuanthinh.modal.InfoPersonal;
import mta.tuanthinh.modal.OAuthApproval;
import mta.tuanthinh.modal.OAuthClientDetails;
import mta.tuanthinh.utility.Utilities;

@RestController
@RequestMapping("/api")
public class API {
	@Autowired
	RestTemplate restTemplate;
	
	@GetMapping("/personal-inf")
	public InfoPersonal getPersonalInf() {
		String url = "http://localhost:8181/user/personal-inf";
		HttpEntity<InfoPersonal> infoPersonalEntity = new HttpEntity<InfoPersonal>(Utilities.getHeader());
		ResponseEntity<InfoPersonal> responseEntity = restTemplate.exchange(url, HttpMethod.GET, infoPersonalEntity, InfoPersonal.class);
		return responseEntity.getBody();
	}
	
	@GetMapping("/oauth-approval")
	public List<OAuthApproval> getApprovalClient(){
		String url = "http://localhost:8181/oauth-access-token/approval/current-user";
		HttpEntity<OAuthApproval> entity = new HttpEntity<OAuthApproval>(Utilities.getHeader());
		ResponseEntity<OAuthApproval[]> responseEntity = restTemplate.exchange(url, HttpMethod.GET, entity, OAuthApproval[].class);
		List<OAuthApproval> getListClient = Arrays.asList(responseEntity.getBody());
		return getListClient;
	}
	
	@DeleteMapping("/disapproval/{id}")
	public String disapprovalClient(@PathVariable String id){
		String url = "http://localhost:8181/oauth-access-token/disapproval/id/{id}";
		HttpEntity<String> entity = new HttpEntity<String>(Utilities.getHeader());
		ResponseEntity<String> responseEntity = restTemplate.exchange(url, HttpMethod.DELETE, entity, String.class, id);
		return responseEntity.getBody();
	}
	
	@GetMapping("/client")
	public List<OAuthClientDetails> getOauthClient(){
		String url = "http://localhost:8181/oauth-client/client";
		HttpEntity<OAuthClientDetails> entity = new HttpEntity<OAuthClientDetails>(Utilities.getHeader());
		ResponseEntity<OAuthClientDetails[]> responseEntity = restTemplate.exchange(url, HttpMethod.GET, entity, OAuthClientDetails[].class);
		List<OAuthClientDetails> getListClient = Arrays.asList(responseEntity.getBody());
		return getListClient;
	}
	
	@PostMapping("/client-register")
	public OAuthClientDetails registerClient(@RequestBody OAuthClientDetails client) {
		String url = "http://localhost:8181/oauth-client/client-register";
		HttpEntity<OAuthClientDetails> entity = new HttpEntity<OAuthClientDetails>(client, Utilities.getHeader());
		ResponseEntity<OAuthClientDetails> responseEntity = restTemplate.exchange(url, HttpMethod.POST, entity, OAuthClientDetails.class);
		return responseEntity.getBody();
	}
	
	@PutMapping("/client-update")
	public OAuthClientDetails updateClient(@RequestBody OAuthClientDetails oAuthClientDetails) {
		String url = "http://localhost:8181/oauth-client/client-update";
		HttpEntity<OAuthClientDetails> entity = new HttpEntity<OAuthClientDetails>(oAuthClientDetails, Utilities.getHeader());
		ResponseEntity<OAuthClientDetails> responseEntity = restTemplate.exchange(url, HttpMethod.PUT, entity, OAuthClientDetails.class);
		return responseEntity.getBody();
	}
	
	@DeleteMapping("/client-delete/{id}")
	public String deleteClient(@PathVariable String id) {
		String url = "http://localhost:8181/oauth-client/delete/{id}";
		HttpEntity<String> entity = new HttpEntity<String>(Utilities.getHeader());
		ResponseEntity<String> responseEntity = restTemplate.exchange(url, HttpMethod.DELETE, entity, String.class, id);
		return responseEntity.getBody();
	}
}
