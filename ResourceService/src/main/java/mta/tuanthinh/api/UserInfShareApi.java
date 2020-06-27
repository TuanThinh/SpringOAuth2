package mta.tuanthinh.api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import mta.tuanthinh.document.UserInfShare;
import mta.tuanthinh.dto.InfShare;
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
	
	@GetMapping("/current-profile")
	public UserInfShare getProfileForCurretUser() {
		return userInfShareService.getProfileForCurrentUser();
	}
	
	@PreAuthorize("#oauth2.hasScope('READ_PROFILE')")
	@GetMapping("/inf-share")
	public InfShare getInfShare() {
		return userInfShareService.getInfShare();
	}
	
	@PreAuthorize("#oauth2.hasScope('READ_EMAIL')")
	@GetMapping("/email-share")
	public String getEmailShare() {
		return userInfShareService.getEmailShare();
	}
	
	@PostMapping("/add")
	public UserInfShare add(@RequestBody UserInfShare entity) {
		return userInfShareService.save(entity);
	}
	
	@PutMapping("/update")
	public UserInfShare update(@RequestBody UserInfShare entity) {
		return userInfShareService.save(entity);
	}
	
	@DeleteMapping("/delete/{id}")
	public String deleteById(@PathVariable String id) {
		return userInfShareService.deleteById(id);
	}
	
	@GetMapping("/update/fullname/{fullName}/{flag}")
	public Boolean updateFullname(@PathVariable String fullName, @PathVariable String flag) {
		return userInfShareService.updateFullname(fullName, flag);
	}
	
	@GetMapping("/update/birthday")
	public Boolean updateBirthday(@RequestParam String birthday, @RequestParam String flag) {
		return userInfShareService.updateBirthday(birthday, flag);
	}
	
	@GetMapping("/update/gender/{gender}/{flag}")
	public Boolean updateGender(@PathVariable String gender, @PathVariable String flag) {
		return userInfShareService.updateGender(gender, flag);
	}

	@GetMapping("/update/email/{email}/{flag}")
	public Boolean updateEmail(@PathVariable String email, @PathVariable String flag) {
		return userInfShareService.updateEmail(email, flag);
	}
	
	@GetMapping("/update/phonenumber/{phoneNumber}/{flag}")
	public Boolean updatePhoneNumber(@PathVariable String phoneNumber, @PathVariable String flag) {
		return userInfShareService.updatePhoneNumber(phoneNumber, flag);
	}
}
