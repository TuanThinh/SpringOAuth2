package mta.tuanthinh.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MailController {
	@GetMapping("/index")
	public String index() {
		return "Hello";
	}
}
