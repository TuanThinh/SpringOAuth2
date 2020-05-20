package mta.tuanthinh.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import mta.tuanthinh.dto.InfoPersonal;
import mta.tuanthinh.service.UserService;

@RestController
@RequestMapping("/user")
public class UserApi {
	@Autowired
	private UserService userService;
	
	@GetMapping("/personal-inf")
	public InfoPersonal getInfoPersonal() {
		return userService.getInfoPersonal();
	}
}
