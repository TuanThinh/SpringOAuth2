package mta.tuanthinh.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import mta.tuanthinh.document.Permission;
import mta.tuanthinh.document.Role;
import mta.tuanthinh.document.User;
import mta.tuanthinh.repository.PermissionRepository;
import mta.tuanthinh.repository.RoleRepository;
import mta.tuanthinh.repository.UserRepository;

@Service
public class PermissionServiceImpl implements PermissionService{

	@Autowired
	private PermissionRepository permissionRepository;
	@Autowired
	private RoleRepository roleRepository;
	@Autowired
	private UserRepository userRepository;
	
	@Override
	public List<Permission> findAll() {
		return permissionRepository.findAll();
	}

	@Override
	public Optional<Permission> findById(String id) {
		return permissionRepository.findById(id);
	}

	@Override
	public Optional<Permission> findByName(String name) {
		return permissionRepository.findByName(name);
	}

	@Override
	public Permission save(Permission permission) {
		return permissionRepository.save(permission);
	}

	@Override
	public Permission update(Permission permission) {
//		Permission check = permissionRepository.save(permission);
//		if(check == null) return null;
		String idPermission = permission.getId();
		List<Role> roles = roleRepository.findByIdPermission(idPermission);
		roles.forEach(role -> {
			List<Permission> permissions = role.getPermissions();
			for (int i = 0; i < permissions.size(); i++) {
				if(permissions.get(i).getId().equals(idPermission)) {
					permissions.remove(i);
				}
			}
			permissions.add(permission);
			role.setPermission(permissions);
			
			String idRole = role.getId();
			List<User> listUser = userRepository.findByIdRole(idRole);
			listUser.forEach(user -> {
				List<Role> listRole = user.getRoles();
				for (int i = 0; i < listRole.size(); i++) {
					if(listRole.get(i).getId().equals(idRole)) {
						listRole.remove(i);
					}
				}
				listRole.add(role);
				user.setRoles(listRole);
				userRepository.save(user);
			});
			roleRepository.save(role);
		});
		return permissionRepository.save(permission);
	}
	
	@Override
	public String deleteById(String id) {
		permissionRepository.deleteById(id);
		return id;
	}

}
