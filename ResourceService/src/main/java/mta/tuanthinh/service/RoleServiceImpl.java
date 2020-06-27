package mta.tuanthinh.service;

import java.util.ArrayList;
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
public class RoleServiceImpl implements RoleService{
	
	@Autowired
	private RoleRepository roleRepository;
	@Autowired
	private PermissionRepository permissionRepository;
	@Autowired
	private UserRepository userRepository;

	@Override
	public List<Role> findAll() {
		return roleRepository.findAll();
	}

	@Override
	public Optional<Role> findById(String id) {
		return roleRepository.findById(id);
	}

	@Override
	public Optional<Role> findByName(String name) {
		return roleRepository.findByName(name);
	}

	@Override
	public Role save(Role role) {
		List<Permission> listPermission = new ArrayList<Permission>();
		role.getPermissions().forEach(permissions -> {
			Permission permission = permissionRepository.findById(permissions.getId()).get();
			listPermission.add(permission);
		});
		role.setPermission(listPermission);
		return roleRepository.save(role);
	}
	
	@Override
	public Role update(Role role) {
		List<Permission> listPermission = new ArrayList<Permission>();
		role.getPermissions().forEach(permissions -> {
			Permission permission = permissionRepository.findById(permissions.getId()).get();
			listPermission.add(permission);
		});
		role.setPermission(listPermission);
		
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
		return roleRepository.save(role);
	}

	@Override
	public String deleteById(String id) {
		roleRepository.deleteById(id);
		return id;
	}

	@Override
	public List<Role> findByIdPermission(String idPermission) {
		return roleRepository.findByIdPermission(idPermission);
	}

}
