package mta.tuanthinh.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import mta.tuanthinh.document.OAuthAccessToken;

public interface OAuthAccessTokenRepository extends MongoRepository<OAuthAccessToken, String>{

}
