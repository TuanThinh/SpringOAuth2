package mta.tuanthinh.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import mta.tuanthinh.document.OAuthClientDetails;
import mta.tuanthinh.document.Scope;
import mta.tuanthinh.repository.OAuthClientDetailsRepository;
import mta.tuanthinh.repository.ScopeRepository;
import mta.tuanthinh.utility.Utilities;

@Service
public class OauthClientDetailsServiceImpl implements OAuthClientDetailsService{

	@Autowired
	private OAuthClientDetailsRepository OAuthClientDetailsRepository;

	@Autowired
	private ScopeRepository scopeRepository;
	
	@Override
	public Optional<OAuthClientDetails> findById(String id) {
		return OAuthClientDetailsRepository.findById(id);
	}

	@Override
	public List<OAuthClientDetails> findByUsername(String username) {
		return OAuthClientDetailsRepository.findByUsername(username);
	}

	@Override
	public List<OAuthClientDetails> findAll() {
		return OAuthClientDetailsRepository.findAll();
	}

	@Override
	public OAuthClientDetails save(OAuthClientDetails authClientDetails) {
		return OAuthClientDetailsRepository.save(authClientDetails);
	}

	@Override
	public String deleteById(String id) {
		OAuthClientDetailsRepository.deleteById(id);
		return id;
	}

	@Override
	public List<OAuthClientDetails> getClientForCurrentUser() {
		String username = Utilities.getCurrentUser();
		List<OAuthClientDetails> getListClient = findByUsername(username);
		return getListClient;
	}
	
	@Override
	public OAuthClientDetails saveClientForCurrentUser(OAuthClientDetails oauthClientDetails) {
		String username = Utilities.getCurrentUser();
		if(oauthClientDetails.getAccessTokenValidity() == null) {
			oauthClientDetails.setAccessTokenValidity(10000);
		}
		if(oauthClientDetails.getRefreshTokenValidity() == null) {
			oauthClientDetails.setRefreshTokenValidity(3600);
		}
		if(oauthClientDetails.getGrantTypes() == null) {
			oauthClientDetails.setGrantTypes("authorization_code,password,refresh_token,implicit");
		}
		if(oauthClientDetails.getUsername() == null) {
			oauthClientDetails.setUsername(username);
		}
		if(oauthClientDetails.getScopes() != null) {
			List<Scope> listScope = new ArrayList<>();
			oauthClientDetails.getScopes().forEach(i -> {
				Scope scope = scopeRepository.findById(i.getId()).get();
				listScope.add(scope);
			});
			oauthClientDetails.setScopes(listScope);
		}
		if(oauthClientDetails.getClientSecret() != null && !oauthClientDetails.getClientSecret().equals("")) {
//			PasswordEncoder encoder = PasswordEncoderFactories.createDelegatingPasswordEncoder();
			String encoded = new BCryptPasswordEncoder().encode(oauthClientDetails.getClientSecret());
			oauthClientDetails.setClientSecret(encoded);
		}
		return save(oauthClientDetails);
	}

	@Override
	public OAuthClientDetails updateClientForCurrentUser(OAuthClientDetails oauthClientDetails) {
		Optional<OAuthClientDetails> optional = findById(oauthClientDetails.getId());
		optional.orElseThrow(() -> new UsernameNotFoundException("Username or password wrong"));
		OAuthClientDetails client = optional.get();
		
		if(oauthClientDetails.getClientId() != null && oauthClientDetails.getClientId() != "") {
			client.setClientId(oauthClientDetails.getClientId());
		}
		if(oauthClientDetails.getClientSecret() != null && !oauthClientDetails.getClientSecret().equals("")) {
//			PasswordEncoder encoder = PasswordEncoderFactories.createDelegatingPasswordEncoder();
			String encoded = new BCryptPasswordEncoder().encode(oauthClientDetails.getClientSecret());
			oauthClientDetails.setClientSecret(encoded);
		}
		if(oauthClientDetails.getRedirectUris() != null) {
			client.setRedirectUris(oauthClientDetails.getRedirectUris());
		}
		if(oauthClientDetails.getScopes() != null) {
			List<Scope> listScope = new ArrayList<>();
			oauthClientDetails.getScopes().forEach(i -> {
				Scope scope = scopeRepository.findById(i.getId()).get();
				listScope.add(scope);
			});
			client.setScopes(listScope);
		}
		
		return save(client);
	}

}
