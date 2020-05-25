package mta.tuanthinh.api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import mta.tuanthinh.document.OAuthAccessToken;
import mta.tuanthinh.dto.OAuthApproval;
import mta.tuanthinh.service.OAuthAccessTokenService;

@RestController
@RequestMapping("/oauth-access-token")
public class OAuthAccessTokenApi {
	@Autowired 
	private OAuthAccessTokenService oAuthAccessTokenService;

	@GetMapping("/id/{id}")
	public OAuthAccessToken findById(@PathVariable String id){
		return oAuthAccessTokenService.findById(id).get();
	}
	
	@GetMapping("/approval/current-user")
	public List<OAuthApproval> approvalClientIdByUsername(){
		return oAuthAccessTokenService.findClientIdByCurrentUser();
	}
	
	@DeleteMapping("/disapproval/id/{id}")
	public String disapprovalClientIdByUsername(@PathVariable String id){
		return oAuthAccessTokenService.disapproval(id);
	}
	
//	@GetMapping("/username/{username}")
//	public List<OAuthApproval> findByUsername(@PathVariable String username){
//		return oAuthAccessTokenService.findByUsername(username);
//	}
}
