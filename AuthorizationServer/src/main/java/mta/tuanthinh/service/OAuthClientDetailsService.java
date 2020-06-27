package mta.tuanthinh.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.provider.ClientDetails;
import org.springframework.security.oauth2.provider.ClientDetailsService;
import org.springframework.security.oauth2.provider.ClientRegistrationException;
import org.springframework.stereotype.Service;

import mta.tuanthinh.repository.OAuthClientDetailsRepository;

@Service
public class OAuthClientDetailsService implements ClientDetailsService{
	@Autowired
	private OAuthClientDetailsRepository oauthClientDetailsRepository;
	
	public OAuthClientDetailsService(OAuthClientDetailsRepository authClientRepository) {
        this.oauthClientDetailsRepository = authClientRepository;
    }

	@Override
	public ClientDetails loadClientByClientId(String id) throws ClientRegistrationException {
		return oauthClientDetailsRepository.findById(id).orElseThrow(IllegalArgumentException::new);
	}

}
