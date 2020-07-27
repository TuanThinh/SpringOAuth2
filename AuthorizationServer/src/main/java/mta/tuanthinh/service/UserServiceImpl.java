package mta.tuanthinh.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import mta.tuanthinh.document.Role;
import mta.tuanthinh.document.User;
import mta.tuanthinh.document.UserInfShare;
import mta.tuanthinh.form.UserRegister;
import mta.tuanthinh.repository.RoleRepository;
import mta.tuanthinh.repository.UserInfShareRepository;
import mta.tuanthinh.repository.UserRepository;

@Service
public class UserServiceImpl implements UserService{

	@Autowired
	private UserRepository userRepository;
	@Autowired
	private UserInfShareRepository userInfShareRepository;
	@Autowired
	private RoleRepository roleRepository;
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Override
	@Transactional(rollbackFor = Exception.class)
	public Boolean save(UserRegister userRegister) {
		List<Role> roles = new ArrayList<Role>();
		Role role = roleRepository.findByName("ROLE_DEFAULT").get();
		roles.add(role);
		
		User user = new User();
		user.setFirstName(userRegister.getFirstName());
		user.setLastName(userRegister.getLastName());
		user.setUsername(userRegister.getUsername());
		user.setEnabled(true);
		user.setAccountNonExpired(true);
		user.setAccountNonLocked(true);
		user.setCredentialsNonExpired(true);
		user.setPassword(passwordEncoder.encode(userRegister.getPassword()));
		user.setRoles(roles);
		
		UserInfShare infShare = new UserInfShare();
		infShare.setUsername(user.getUsername());
		
		userInfShareRepository.save(infShare);
		Boolean check = userRepository.save(user)!=null?true:false;
		
		return check;
	}

}
