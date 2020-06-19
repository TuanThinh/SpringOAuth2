package mta.tuanthinh.service;

import java.util.List;
import java.util.Optional;

import mta.tuanthinh.document.Permission;

public interface PermissionService {
	List<Permission> findAll();
	Optional<Permission> findById(String id);
	Optional<Permission> findByName(String name);
	Permission save(Permission permisson);
	Permission update(Permission permisson);
	String deleteById(String id);
}
