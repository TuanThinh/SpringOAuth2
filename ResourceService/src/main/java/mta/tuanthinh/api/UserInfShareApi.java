package mta.tuanthinh.api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import mta.tuanthinh.document.UserInfShare;
import mta.tuanthinh.service.UserInfShareService;

@RestController
@RequestMapping("/share")
public class UserInfShareApi {
	
	@Autowired
	private UserInfShareService userInfShareService;
	
	@GetMapping("/profile")
	public List<UserInfShare> getUserInfShare() {
		return userInfShareService.findAll();
	}
}
