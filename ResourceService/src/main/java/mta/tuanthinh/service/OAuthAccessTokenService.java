package mta.tuanthinh.service;

import java.util.List;
import java.util.Optional;

import mta.tuanthinh.document.OAuthAccessToken;
import mta.tuanthinh.document.User;
import mta.tuanthinh.dto.OAuthApproval;

public interface OAuthAccessTokenService {
//	List<OAuthAccessToken> findAll();
	Optional<OAuthAccessToken> findById(String id);
//	List<OAuthAccessToken> findByUsername(String username);
//	List<OAuthAccessToken> findByClientId(String clientId);
//	User save (OAuthAccessToken oAuthAccessToken);
//	String deleteById(String id);
	
	List<OAuthApproval> findClientIdByUsername(String username);
	List<OAuthApproval> findClientIdByCurrentUser();
	String disapproval(String id);
}
