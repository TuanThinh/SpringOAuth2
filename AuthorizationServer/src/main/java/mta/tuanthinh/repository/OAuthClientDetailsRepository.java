package mta.tuanthinh.repository;

import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;

import mta.tuanthinh.document.OAuthClientDetails;

public interface OAuthClientDetailsRepository extends MongoRepository<OAuthClientDetails, String>{
	Optional<OAuthClientDetails> findByClientId(String clientId);
}
