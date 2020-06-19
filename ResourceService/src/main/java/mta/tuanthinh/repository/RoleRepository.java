package mta.tuanthinh.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import mta.tuanthinh.document.Role;

public interface RoleRepository extends MongoRepository<Role, String>{
	Optional<Role> findByName(String name);
	
	@Query(value = "{'permissions.id' : ?0}")
	List<Role> findByIdPermission(String idPermission);
}
