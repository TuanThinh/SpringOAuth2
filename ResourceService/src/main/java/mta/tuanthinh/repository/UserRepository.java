package mta.tuanthinh.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import mta.tuanthinh.document.User;

public interface UserRepository extends MongoRepository<User, String>{
	Optional<User> findByUsername(String username);
	
	@Query(value = "{'roles.id': ?0}")
	List<User> findByIdRole(String idRole);
}
