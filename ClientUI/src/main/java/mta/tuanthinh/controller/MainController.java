package mta.tuanthinh.controller;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.oauth2.provider.authentication.OAuth2AuthenticationDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;

import mta.tuanthinh.utility.Utilities;

@Controller
//@RestController
public class MainController {
	
	@Autowired
	RestTemplate restTemplate;
	@Autowired
	Utilities utilities;
	
	public String accessToken;
	
	@RequestMapping("/")
	public String Default() {
		return "home";
	}
	
	@RequestMapping("/home")
	public String home() {
		return "home";
	}
	
	@RequestMapping("/login")
	public String login(@RequestParam(required = false) String code, Model model, HttpServletRequest request) {
		String url = "http://localhost:8080/oauth/authorize?client_id=" + utilities.clientId + "&redirect_uri=http://localhost:9000/login&response_type=code";
		model.addAttribute("urlRequest", url);
		if(code !=null) {
			try {
				accessToken = utilities.getAccessToken(code);
				
				UserDetails userDetail = utilities.buildUser();
			    UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(userDetail, null, userDetail.getAuthorities());
			    authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
			    SecurityContextHolder.getContext().setAuthentication(authentication);

			    return "redirect:/";
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
		return "login";
	}
	
	@RequestMapping("/logout")
	public String logout() {
		return "redirect:/login";
	}
	
	@GetMapping(value="/403")
	public String accessDenie() {
		return "403";
	}
	
	@RequestMapping("/get-user-inf")
	@ResponseBody
	public Object getInfUser() throws JsonMappingException, JsonProcessingException {
		
//		OAuth2AuthenticationDetails auth2AuthenticationDetails = (OAuth2AuthenticationDetails) SecurityContextHolder.getContext().getAuthentication().getDetails();
//		String token = auth2AuthenticationDetails.getTokenType().concat(" ").concat(auth2AuthenticationDetails.getTokenValue());
//		
//		HttpHeaders httpHeaders = new HttpHeaders();
//		httpHeaders.add("Authorization", token);
//		
//		HttpEntity<Object> khachHangEntity = new HttpEntity<Object>(httpHeaders);
//		ResponseEntity<Object> responseEntity = restTemplate.exchange("http://localhost:8181/share/inf-share", HttpMethod.GET, khachHangEntity, Object.class);
//		
//		System.out.println("response: " + responseEntity);
//		
//		Object object = responseEntity.getBody();
//		
//		System.out.println("response entity: " + object);
		
		return utilities.getUserInfo(accessToken);
	}
	
	@RequestMapping("/get-email")
	@ResponseBody
	public String getInfEmail() throws JsonMappingException, JsonProcessingException {
//		OAuth2AuthenticationDetails auth2AuthenticationDetails = (OAuth2AuthenticationDetails) SecurityContextHolder.getContext().getAuthentication().getDetails();
//		String token = auth2AuthenticationDetails.getTokenType().concat(" ").concat(auth2AuthenticationDetails.getTokenValue());
//		
//		HttpHeaders httpHeaders = new HttpHeaders();
//		httpHeaders.add("Authorization", token);
//		
//		HttpEntity<String> khachHangEntity = new HttpEntity<String>(httpHeaders);
//		ResponseEntity<String> responseEntity = restTemplate.exchange("http://localhost:8181/share/email-share", HttpMethod.GET, khachHangEntity, String.class);
//		
//		System.out.println("response: " + responseEntity);
//		
//		String email = responseEntity.getBody();
//		
//		System.out.println("response entity: " + email);
		
		return utilities.getEmail(accessToken);
	}
	
}
