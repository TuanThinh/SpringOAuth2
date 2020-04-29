package mta.tuanthinh.repository;

import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;

import mta.tuanthinh.document.User;

public interface UserRepository extends MongoRepository<User, String>{
	Optional<User> findByUsername(String username); 
}
