package mta.tuanthinh.utility;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.provider.authentication.OAuth2AuthenticationDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

@Component
public class Utilities {
	
	@Value("${security.oauth2.client.client-id}")
	public String clientId;
	
	@Value("${security.oauth2.client.client-secret}")
	public String clientSecret;
	
	@Autowired
    PasswordEncoder passwordEncoder;
	@Autowired
	RestTemplate restTemplate;
	
	public String getAccessToken(String code) throws JsonMappingException, JsonProcessingException {
		System.out.println("client-id: " + clientId + " client-secret: " + clientSecret);
		
//		Map<String, String> client = new HashMap<String, String>();
//		client.put("client_id", clientId);
//		client.put("client_secret", passwordEncoder.encode(clientSecret));
		
		String credentials = clientId + ":" + clientSecret;
//		String encodedCredentials = passwordEncoder.encode(credentials);
		String encodedCredentials = new String(Base64.encodeBase64(credentials.getBytes()));
		
		HttpHeaders httpHeaders = new HttpHeaders();
		httpHeaders.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
		httpHeaders.add("Authorization", "Basic " + encodedCredentials);
		
		HttpEntity<String> request = new HttpEntity<String>(httpHeaders);

		String access_token_url = "http://localhost:8080/oauth/token";
		access_token_url += "?code=" + code;
		access_token_url += "&grant_type=authorization_code";
		access_token_url += "&redirect_uri=http://localhost:9000/login";

		ResponseEntity<String> response = restTemplate.exchange(access_token_url, HttpMethod.POST, request, String.class);

		ObjectMapper mapper = new ObjectMapper();
		JsonNode node = mapper.readTree(response.getBody());
		String token = node.get("access_token").asText();
		
		System.out.println("Access Token Response ---------" + token);
		
		return token;
	}
	
	public Object getUserInfo(String accessToken) throws JsonMappingException, JsonProcessingException {
		String token = "Bearer " + accessToken;
		
		HttpHeaders httpHeaders = new HttpHeaders();
		httpHeaders.add("Authorization", token);
		
		HttpEntity<Object> entity = new HttpEntity<Object>(httpHeaders);
		ResponseEntity<Object> responseEntity = restTemplate.exchange("http://localhost:8181/share/inf-share", HttpMethod.GET, entity, Object.class);
		
		System.out.println("response: " + responseEntity);
		
		Object object = responseEntity.getBody();
		
		System.out.println("response entity: " + object);
		
		return object;
	}

	public String getEmail(String accessToken) throws JsonMappingException, JsonProcessingException {
		String token = "Bearer " + accessToken;
		
		HttpHeaders httpHeaders = new HttpHeaders();
		httpHeaders.add("Authorization", token);
		
		HttpEntity<String> entity = new HttpEntity<String>(httpHeaders);
		ResponseEntity<String> responseEntity = restTemplate.exchange("http://localhost:8181/share/email-share", HttpMethod.GET, entity, String.class);
		
		System.out.println("response: " + responseEntity);
		
		String email = responseEntity.getBody();
		
		System.out.println("response entity: " + email);
		
		return email;
	}
	
	public UserDetails buildUser() {
	    boolean enabled = true;
	    boolean accountNonExpired = true;
	    boolean credentialsNonExpired = true;
	    boolean accountNonLocked = true;
	    List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
	    authorities.add(new SimpleGrantedAuthority("ROLE_USER"));
	    UserDetails userDetail = new User("user", "", enabled, accountNonExpired, credentialsNonExpired, accountNonLocked, authorities);
	    return userDetail;
	  }

}
