package mta.tuanthinh.api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import mta.tuanthinh.document.OAuthClientDetails;
import mta.tuanthinh.service.OAuthClientDetailsService;

@RestController
@RequestMapping("/oauth-client")
public class OAuthClientDetailsApi {
	
	@Autowired
	private OAuthClientDetailsService oAuthClientDetailsService;
	
	@GetMapping("/all")
	public List<OAuthClientDetails> findAll(){
		return oAuthClientDetailsService.findAll();
	}
	
	@GetMapping("/user/{username}")
	public List<OAuthClientDetails> findByUsername(@PathVariable String username){
		return oAuthClientDetailsService.findByUsername(username);
	}
	
	@GetMapping("/id/{id}")
	public OAuthClientDetails findById(@PathVariable String id) {
		return oAuthClientDetailsService.findById(id).get();
	}
	
	@PostMapping(value = "/add", produces = MediaType.APPLICATION_JSON_VALUE + ";charset=utf-8")
	public OAuthClientDetails add(@RequestBody OAuthClientDetails oAuthClientDetails) {
		return oAuthClientDetailsService.save(oAuthClientDetails);
	}
	
	@PutMapping(value = "/update", produces = MediaType.APPLICATION_JSON_VALUE + ";charset=utf-8")
	public OAuthClientDetails update(@RequestBody OAuthClientDetails oAuthClientDetails) {
		return oAuthClientDetailsService.save(oAuthClientDetails);
	}
	
	@DeleteMapping("/delete/{id}")
	public String deleteById(@PathVariable String id) {
		oAuthClientDetailsService.deleteById(id);
		return id;
	}
	
	@GetMapping("/client")
	public List<OAuthClientDetails> getListOAuthClient(){
		return oAuthClientDetailsService.getClientForCurrentUser();
	}
	
	@PostMapping("/client-register")
	public OAuthClientDetails addClientForCurrentUser(@RequestBody OAuthClientDetails oAuthClientDetails) {
		return oAuthClientDetailsService.saveClientForCurrentUser(oAuthClientDetails);
	}
	
	@PutMapping("/client-update")
	public OAuthClientDetails updateClientForCurrentUser(@RequestBody OAuthClientDetails oAuthClientDetails) {
		return oAuthClientDetailsService.updateClientForCurrentUser(oAuthClientDetails);
	}
}
