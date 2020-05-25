package mta.tuanthinh.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import mta.tuanthinh.document.OAuthAccessToken;
import mta.tuanthinh.document.OAuthRefreshToken;
import mta.tuanthinh.document.User;
import mta.tuanthinh.dto.OAuthApproval;
import mta.tuanthinh.repository.OAuthAccessTokenRepository;
import mta.tuanthinh.utility.Utilities;

@Service
public class OAuthAccessTokenServiceImpl implements OAuthAccessTokenService{

	@Autowired
	private OAuthAccessTokenRepository oAuthAccessTokenRepository;
	@Autowired
	private OAuthRefreshTokenService oAuthRefreshTokenService;

	@Override
	public Optional<OAuthAccessToken> findById(String id) {
		return oAuthAccessTokenRepository.findById(id);
	}

	@Override
	public List<OAuthApproval> findClientIdByUsername(String username) {
		return oAuthAccessTokenRepository.findClientIdByUsername(username);
	}

	@Override
	public List<OAuthApproval> findClientIdByCurrentUser() {
		String username = Utilities.getCurrentUser();
		return oAuthAccessTokenRepository.findClientIdByUsername(username);
	}

	@Override
	public String disapproval(String id) {
		OAuthAccessToken accessToken = findById(id).get();
		oAuthRefreshTokenService.deleteByTokenId(accessToken.getRefreshToken());
		oAuthAccessTokenRepository.delete(accessToken);
		return id;
	}

}
