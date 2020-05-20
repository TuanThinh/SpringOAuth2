package mta.tuanthinh.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class MainController {
	@GetMapping
	public String index() {
		return "view/index";
	}
	
	@GetMapping(value="/logout")
	public String logout(HttpServletRequest request) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
	    if (auth != null){    
	    	SecurityContextHolder.getContext().setAuthentication(null);;
	    }
//	    	    
//	    try {
//	        String authorization = request.getHeader("Authorization");
//	        if (authorization != null && authorization.contains("Bearer")) {
//	            String tokenValue = authorization.replace("Bearer", "").trim();
//
//	            OAuth2AccessToken accessToken = tokenStore.readAccessToken(tokenValue);
//	            tokenStore.removeAccessToken(accessToken);
//
//	            //OAuth2RefreshToken refreshToken = tokenStore.readRefreshToken(tokenValue);
//	            OAuth2RefreshToken refreshToken = accessToken.getRefreshToken();
//	            tokenStore.removeRefreshToken(refreshToken);
//	        }
//	    } catch (Exception e) {
//	        return ResponseEntity.badRequest().body("Invalid access token");
//	    }
	    
//	    if (request.getSession() != null) {
//	        request.getSession().invalidate();
//	    }
		return "redirect:/login";
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
	
	@GetMapping(value="/oauth")
	public String addApplicatio() {
		return "view/oauth-client";
	}
	
}
