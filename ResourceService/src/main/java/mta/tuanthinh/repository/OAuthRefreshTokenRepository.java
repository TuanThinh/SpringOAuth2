package mta.tuanthinh.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import mta.tuanthinh.document.OAuthRefreshToken;

public interface OAuthRefreshTokenRepository extends MongoRepository<OAuthRefreshToken, String>{

}
