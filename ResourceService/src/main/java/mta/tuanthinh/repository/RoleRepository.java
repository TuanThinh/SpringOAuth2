package mta.tuanthinh.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import mta.tuanthinh.document.Role;

public interface RoleRepository extends MongoRepository<Role, String>{

}
