package mta.tuanthinh.controller;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import mta.tuanthinh.modal.InfoPersonal;
import mta.tuanthinh.modal.OAuthApproval;
import mta.tuanthinh.modal.OAuthClientDetails;
import mta.tuanthinh.modal.UserInfShare;
import mta.tuanthinh.utility.Utilities;

@RestController
@RequestMapping("/api")
public class PersonalInfApi {
	@Autowired
	RestTemplate restTemplate;
	
	@GetMapping("/personal-inf")
	public InfoPersonal getPersonalInf() {
		String url = "http://localhost:8181/user/personal-inf";
		HttpEntity<InfoPersonal> infoPersonalEntity = new HttpEntity<InfoPersonal>(Utilities.getHeader());
		ResponseEntity<InfoPersonal> responseEntity = restTemplate.exchange(url, HttpMethod.GET, infoPersonalEntity, InfoPersonal.class);
		return responseEntity.getBody();
	}
	
	@GetMapping("/update/fullname")
	public Boolean updateFullname(@RequestParam String firstName, @RequestParam String lastName) {
		String url = "http://localhost:8181/user/update/fullname/{firstName}/{lastName}";
		Map<String, String> param = new HashMap<String, String>();
		param.put("firstName", firstName);
		param.put("lastName", lastName);
		HttpEntity<Boolean> entity = new HttpEntity<Boolean>(Utilities.getHeader());
		ResponseEntity<Boolean> responseEntity = restTemplate.exchange(url, HttpMethod.GET, entity, Boolean.class, param);
		return responseEntity.getBody();
	}
	
	@GetMapping("/update/birthday")
	public Boolean updateBirthday(@RequestParam String birthday) {
		String url = "http://localhost:8181/user/update/birthday?birthday=" + birthday;
		HttpEntity<Boolean> entity = new HttpEntity<Boolean>(Utilities.getHeader());
		ResponseEntity<Boolean> responseEntity = restTemplate.exchange(url, HttpMethod.GET, entity, Boolean.class);
		return responseEntity.getBody();
	}
	
	@GetMapping("/update/gender")
	public Boolean updateGender(@RequestParam String gender) {
		String url = "http://localhost:8181/user/update/gender/{gender}";
		HttpEntity<Boolean> entity = new HttpEntity<Boolean>(Utilities.getHeader());
		ResponseEntity<Boolean> responseEntity = restTemplate.exchange(url, HttpMethod.GET, entity, Boolean.class, gender);
		return responseEntity.getBody();
	}
	
	@GetMapping("/check-password")
	public Boolean updatebirthday(@RequestParam String exPassword) {
		String url = "http://localhost:8181/user/check-password/{exPassword}";
		HttpEntity<Boolean> entity = new HttpEntity<Boolean>(Utilities.getHeader());
		ResponseEntity<Boolean> responseEntity = restTemplate.exchange(url, HttpMethod.GET, entity, Boolean.class, exPassword);
		return responseEntity.getBody();
	}
	
	@GetMapping("/update/password")
	public Boolean updatePassword(@RequestParam String exPassword, @RequestParam String newPassword) {
		String url = "http://localhost:8181/user/update/password/{exPassword}/{newPassword}";
		Map<String, String> param = new HashMap<String, String>();
		param.put("exPassword", exPassword);
		param.put("newPassword", newPassword);
		HttpEntity<Boolean> entity = new HttpEntity<Boolean>(Utilities.getHeader());
		ResponseEntity<Boolean> responseEntity = restTemplate.exchange(url, HttpMethod.GET, entity, Boolean.class, param);
		return responseEntity.getBody();
	}
	
	@GetMapping("/update/email")
	public Boolean updateEmail(@RequestParam String email) {
		String url = "http://localhost:8181/user/update/email/{email}";
		HttpEntity<Boolean> entity = new HttpEntity<Boolean>(Utilities.getHeader());
		ResponseEntity<Boolean> responseEntity = restTemplate.exchange(url, HttpMethod.GET, entity, Boolean.class, email);
		return responseEntity.getBody();
	}
	
	@GetMapping("/update/phonenumber")
	public Boolean updatePhoneNumber(@RequestParam String phoneNumber) {
		String url = "http://localhost:8181/user/update/phonenumber/{phoneNumber}";
		HttpEntity<Boolean> entity = new HttpEntity<Boolean>(Utilities.getHeader());
		ResponseEntity<Boolean> responseEntity = restTemplate.exchange(url, HttpMethod.GET, entity, Boolean.class, phoneNumber);
		return responseEntity.getBody();
	}
	
	@GetMapping("/profile")
	public UserInfShare getProfile() {
		String url = "http://localhost:8181/share/current-profile";
		HttpEntity<UserInfShare> infoPersonalEntity = new HttpEntity<UserInfShare>(Utilities.getHeader());
		ResponseEntity<UserInfShare> responseEntity = restTemplate.exchange(url, HttpMethod.GET, infoPersonalEntity, UserInfShare.class);
		return responseEntity.getBody();
	}
	
	@GetMapping("/inf-share/update/fullname")
	public Boolean updateSFullname(@RequestParam String fullName, @RequestParam String flag) {
		String url = "http://localhost:8181/share/update/fullname/{fullName}/{flag}";
		Map<String, String> param = new HashMap<String, String>();
		param.put("fullName", fullName);
		param.put("flag", flag);
		HttpEntity<Boolean> entity = new HttpEntity<Boolean>(Utilities.getHeader());
		ResponseEntity<Boolean> responseEntity = restTemplate.exchange(url, HttpMethod.GET, entity, Boolean.class, param);
		return responseEntity.getBody();
	}
	
	@GetMapping("/inf-share/update/birthday")
	public Boolean updateSBirthday(@RequestParam String birthday, @RequestParam String flag) {
		String url = "http://localhost:8181/share/update/birthday?birthday=" + birthday + "&flag=" + flag;
		HttpEntity<Boolean> entity = new HttpEntity<Boolean>(Utilities.getHeader());
		ResponseEntity<Boolean> responseEntity = restTemplate.exchange(url, HttpMethod.GET, entity, Boolean.class);
		return responseEntity.getBody();
	}
	
	@GetMapping("/inf-share/update/gender")
	public Boolean updateSGender(@RequestParam String gender, @RequestParam String flag) {
		String url = "http://localhost:8181/share/update/gender/{gender}/{flag}";
		Map<String, String> param = new HashMap<String, String>();
		param.put("gender", gender);
		param.put("flag", flag);
		HttpEntity<Boolean> entity = new HttpEntity<Boolean>(Utilities.getHeader());
		ResponseEntity<Boolean> responseEntity = restTemplate.exchange(url, HttpMethod.GET, entity, Boolean.class, param);
		return responseEntity.getBody();
	}
	
	@GetMapping("/inf-share/update/email")
	public Boolean updateSEmail(@RequestParam String email, @RequestParam String flag) {
		String url = "http://localhost:8181/share/update/email/{email}/{flag}";
		Map<String, String> param = new HashMap<String, String>();
		param.put("email", email);
		param.put("flag", flag);
		HttpEntity<Boolean> entity = new HttpEntity<Boolean>(Utilities.getHeader());
		ResponseEntity<Boolean> responseEntity = restTemplate.exchange(url, HttpMethod.GET, entity, Boolean.class, param);
		return responseEntity.getBody();
	}
	
	@GetMapping("/inf-share/update/phonenumber")
	public Boolean updateSPhoneNumber(@RequestParam String phoneNumber, @RequestParam String flag) {
		String url = "http://localhost:8181/share/update/phonenumber/{phoneNumber}/{flag}";
		Map<String, String> param = new HashMap<String, String>();
		param.put("phoneNumber", phoneNumber);
		param.put("flag", flag);
		HttpEntity<Boolean> entity = new HttpEntity<Boolean>(Utilities.getHeader());
		ResponseEntity<Boolean> responseEntity = restTemplate.exchange(url, HttpMethod.GET, entity, Boolean.class, param);
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
