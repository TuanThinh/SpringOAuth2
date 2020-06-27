package mta.tuanthinh.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import mta.tuanthinh.document.OAuthAccessToken;
import mta.tuanthinh.dto.OAuthApproval;

public interface OAuthAccessTokenRepository extends MongoRepository<OAuthAccessToken, String>{
	 @Query(value = "{ 'username' :  ?0, 'clientId': {$exists: true, $nin: ['5e4c0153d066b209d86f50b5']} }", fields = "{ 'id':1, 'username':1, 'clientId':1, 'token.scope':1, 'token.expiration':1, 'refreshToken':1 }")
	 List<OAuthApproval> findClientIdByUsername(String username);
	 
}
