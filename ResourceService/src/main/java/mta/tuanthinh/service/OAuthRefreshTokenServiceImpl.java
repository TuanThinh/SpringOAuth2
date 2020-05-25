package mta.tuanthinh.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import mta.tuanthinh.document.OAuthRefreshToken;
import mta.tuanthinh.repository.OAuthRefreshTokenRepository;

@Service
public class OAuthRefreshTokenServiceImpl implements OAuthRefreshTokenService{

	@Autowired
	private OAuthRefreshTokenRepository oAuthRefreshTokenRepository;
	
	@Override
	public List<OAuthRefreshToken> findAll() {
		return oAuthRefreshTokenRepository.findAll();
	}

	@Override
	public Optional<OAuthRefreshToken> findById(String id) {
		return oAuthRefreshTokenRepository.findById(id);
	}

	@Override
	public Optional<OAuthRefreshToken> findByTokenId(String tokenId) {
		return oAuthRefreshTokenRepository.findByTokenId(tokenId);
	}

	@Override
	public OAuthRefreshToken save(OAuthRefreshToken oAuthRefreshToken) {
		return oAuthRefreshTokenRepository.save(oAuthRefreshToken);
	}

	@Override
	public void deleteByTokenId(String tokenId) {
		OAuthRefreshToken oAuthRefreshToken = findByTokenId(tokenId).get();
		oAuthRefreshTokenRepository.delete(oAuthRefreshToken);
		
	}

}
