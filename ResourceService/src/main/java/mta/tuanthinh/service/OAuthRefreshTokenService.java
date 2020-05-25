package mta.tuanthinh.service;

import java.util.List;
import java.util.Optional;

import mta.tuanthinh.document.OAuthRefreshToken;

public interface OAuthRefreshTokenService {
	List<OAuthRefreshToken> findAll();
	Optional<OAuthRefreshToken> findById(String id);
	Optional<OAuthRefreshToken> findByTokenId(String tokenId);
	OAuthRefreshToken save (OAuthRefreshToken oAuthRefreshToken);
	void deleteByTokenId(String tokenId);
}
