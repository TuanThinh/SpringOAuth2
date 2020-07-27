package mta.tuanthinh.repository;

import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;

import mta.tuanthinh.document.UserInfShare;

public interface UserInfShareRepository extends MongoRepository<UserInfShare, String>{
	Optional<UserInfShare> findByUsername(String username);
}
