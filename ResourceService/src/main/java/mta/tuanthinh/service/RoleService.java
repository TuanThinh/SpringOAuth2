package mta.tuanthinh.service;

import java.util.List;
import java.util.Optional;

import mta.tuanthinh.document.Role;

public interface RoleService {
	List<Role> findAll();
	Optional<Role> findById(String id);
	Optional<Role> findByName(String name);
	Role save(Role role);
	String deleteById(String id);
	
	List<Role> findByIdPermission(String idPermission);
}
