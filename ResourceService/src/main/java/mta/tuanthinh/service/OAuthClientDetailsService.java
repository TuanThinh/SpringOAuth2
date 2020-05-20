package mta.tuanthinh.service;

import java.util.List;
import java.util.Optional;

import mta.tuanthinh.document.OAuthClientDetails;

public interface OAuthClientDetailsService {
	Optional<OAuthClientDetails> findById(String id);
	List<OAuthClientDetails> findByUsername(String username);
	List<OAuthClientDetails> findAll();
	OAuthClientDetails save(OAuthClientDetails authClientDetails);
	String deleteById(String id);
	
	List<OAuthClientDetails> getClientForCurrentUser();
	OAuthClientDetails saveClientForCurrentUser(OAuthClientDetails authClientDetails);
	OAuthClientDetails updateClientForCurrentUser(OAuthClientDetails authClientDetails);
}
