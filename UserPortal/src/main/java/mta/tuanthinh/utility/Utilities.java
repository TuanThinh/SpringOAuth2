package mta.tuanthinh.utility;

import org.springframework.http.HttpHeaders;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.provider.authentication.OAuth2AuthenticationDetails;

public class Utilities {
	
//	@Value("${resource-service}")
//	public static String resource;
	
//	public static String getCurrentUser() {
//		return SecurityContextHolder.getContext().getAuthentication().getName();
//	}
	
	public static String getAccessToken() {
		OAuth2AuthenticationDetails auth2AuthenticationDetails = (OAuth2AuthenticationDetails) SecurityContextHolder.getContext().getAuthentication().getDetails();
		return auth2AuthenticationDetails.getTokenType().concat(" ").concat(auth2AuthenticationDetails.getTokenValue());
	}
	
	public static HttpHeaders getHeader() {
		HttpHeaders httpHeaders = new HttpHeaders();
		httpHeaders.add("Authorization", getAccessToken());
		return httpHeaders;
	}
}
