package mta.tuanthinh.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import mta.tuanthinh.document.User;
import mta.tuanthinh.form.UserRegister;
import mta.tuanthinh.service.UserService;

@Controller
public class MailController {
	@Autowired
	private UserService userService;
	
	@GetMapping("/")
	public String index() {
		return "login";
	}
	
	@GetMapping("/login")
	public String login() {
		return "login";
	}
	
	@GetMapping("/register")
	public String register() {
		return "register";
	}
	
	@PostMapping("/register")
	public String registeSubmitr(@ModelAttribute("user") UserRegister user, Model model) {
		if(user.getPassword().equals(user.getConfirmPassword()) == false) {
			model.addAttribute("user",user);
			model.addAttribute("password", "Password incorrect");
			return "register";
		}
		
		Boolean check = userService.save(user);
		if(check) {
			return "register-successfull";
		}
		return "redirect:/register?error=true";
	}
}
