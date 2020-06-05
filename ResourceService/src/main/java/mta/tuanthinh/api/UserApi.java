package mta.tuanthinh.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
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
	
	@GetMapping("/update/fullname/{firstName}/{lastName}")
	public Boolean updateFullname(@PathVariable String firstName, @PathVariable String lastName) {
		return userService.updateFullname(firstName, lastName);
	}
	
	@GetMapping("/update/birthday")
	public Boolean updateBirthday(@RequestParam String birthday) {
		return userService.updateBirthday(birthday);
	}
	
	@GetMapping("/update/gender/{gender}")
	public Boolean updateGender(@PathVariable String gender) {
		return userService.updateGender(gender);
	}
	
	@GetMapping("/check-password/{exPassword}")
	public Boolean checkPassword(@PathVariable String exPassword) {
		return userService.checkPassword(exPassword);
	}
	
	@GetMapping("/update/password/{exPassword}/{newPassword}")
	public Boolean updatePassword(@PathVariable String exPassword, @PathVariable String newPassword) {
		return userService.updatePassword(exPassword, newPassword);
	}
	
	@GetMapping("/update/email/{email}")
	public Boolean updateEmail(@PathVariable String email) {
		return userService.updateEmail(email);
	}
	
	@GetMapping("/update/phonenumber/{phoneNumber}")
	public Boolean updatePhoneNumber(@PathVariable String phoneNumber) {
		return userService.updatePhoneNumber(phoneNumber);
	}
}
