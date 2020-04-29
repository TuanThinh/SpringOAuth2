package mta.tuanthinh.controller;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.provider.authentication.OAuth2AuthenticationDetails;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import mta.tuanthinh.conf.AccessToken;
import mta.tuanthinh.document.KhachHang;

//@Controller
@RestController
public class MainController {
	
	
	@Autowired
	RestTemplate restTemplate;
	
	@RequestMapping("/")
	public String Default() {
		return "home";
	}
	
	@RequestMapping("/home")
	public String home() {
		return "home";
	}
	
	@RequestMapping("/secret")
	public List<KhachHang> secret() {
		HttpHeaders httpHeaders = new HttpHeaders();
		httpHeaders.add("Authorization", AccessToken.getAccessToken());
		
		HttpEntity<KhachHang> khachHangEntity = new HttpEntity<KhachHang>(httpHeaders);
		ResponseEntity<KhachHang[]> responseEntity = restTemplate.exchange("http://localhost:8181/api/khach-hang/all", HttpMethod.GET, khachHangEntity, KhachHang[].class);
		
		System.out.println("response: " + responseEntity);
		
		List<KhachHang> kh = Arrays.asList(responseEntity.getBody());
		
		System.out.println("response entity: " + kh);
		
		return kh;
	}
	
	@RequestMapping("/checktoken")
	public Object checkToken() {
		HttpHeaders httpHeaders = new HttpHeaders();
//		httpHeaders.add("Authorization", AccessToken.getAccessToken());
		
		HttpEntity<Object> khachHangEntity = new HttpEntity<Object>(httpHeaders);
		OAuth2AuthenticationDetails auth2AuthenticationDetails = (OAuth2AuthenticationDetails) SecurityContextHolder.getContext().getAuthentication().getDetails();
		
		String token = auth2AuthenticationDetails.getTokenValue();
		String url = "http://localhost:8080/oauth/check_token?token=" + token;
		ResponseEntity<Object[]> responseEntity = restTemplate.exchange(url, HttpMethod.GET, khachHangEntity, Object[].class);
		
		System.out.println("response: " + responseEntity);
		
		List<Object> kh = Arrays.asList(responseEntity.getBody());
		
		System.out.println("response entity: " + kh);
		
		return kh;
	}
}
