package mta.tuanthinh.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import mta.tuanthinh.document.OAuthClientDetails;

public interface OAuthClientDetailsRepository extends MongoRepository<OAuthClientDetails, String>{
	List<OAuthClientDetails> findByUsername(String username);
}
