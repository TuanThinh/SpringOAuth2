package mta.tuanthinh.repository;

import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;

import mta.tuanthinh.document.Permission;

public interface PermissionRepository extends MongoRepository<Permission, String>{
	Optional<Permission> findByName(String name);
}
