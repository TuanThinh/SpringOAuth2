package mta.tuanthinh.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainController {
	@GetMapping(value="/")
	public String index() {
		return "view/index";
	}
	
	@GetMapping(value="/personal-inf")
	public String personal() {
		return "view/personal-inf";
	}
	
	@GetMapping(value="/profile")
	public String profile() {
		return "view/profile";
	}
	
	@GetMapping(value="/applications")
	public String applicatios() {
		return "view/applications";
	}
	
	@GetMapping(value="/login-oauth")
	public String addApplicatio() {
		return "view/login-oauth";
	}
}
