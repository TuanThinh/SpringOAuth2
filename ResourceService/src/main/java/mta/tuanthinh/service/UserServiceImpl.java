package mta.tuanthinh.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import mta.tuanthinh.document.Role;
import mta.tuanthinh.document.User;
import mta.tuanthinh.document.UserInfShare;
import mta.tuanthinh.dto.InfoPersonal;
import mta.tuanthinh.repository.RoleRepository;
import mta.tuanthinh.repository.UserInfShareRepository;
import mta.tuanthinh.repository.UserRepository;
import mta.tuanthinh.utility.Utilities;

@Service
public class UserServiceImpl implements UserService{

	@Autowired 
	private UserRepository userRepository;
	@Autowired
	private RoleRepository roleRepository;
	@Autowired
	private UserInfShareRepository userInfShareRepository;
	
	@Override
	public List<User> findAll() {
		return userRepository.findAll();
	}

	@Override
	public Optional<User> findById(String id) {
		return userRepository.findById(id);
	}

	@Override
	public User save(User user) {
		List<Role> listRole = new ArrayList<Role>();
		user.getRoles().forEach(roles -> {
			Role role = roleRepository.findById(roles.getId()).get();
			listRole.add(role);
		});
		user.setRoles(listRole);
		if(user.getPassword() != null && !user.getPassword().equals("")) {
//			PasswordEncoder encoder = PasswordEncoderFactories.createDelegatingPasswordEncoder();
			String encoded = new BCryptPasswordEncoder().encode(user.getPassword());
			user.setPassword(encoded);
		}
		
		UserInfShare userInfShare = new UserInfShare(user.getUsername());
		userInfShareRepository.save(userInfShare);
		return userRepository.save(user);
	}
	
	@Override
	public User update(User user) {
		Optional<User> optional = findById(user.getId());
		optional.orElseThrow(() -> new UsernameNotFoundException("Does not exist user"));
		User updateUser = optional.get();
		
		List<Role> listRole = new ArrayList<Role>();
		user.getRoles().forEach(roles -> {
			Role role = roleRepository.findById(roles.getId()).get();
			listRole.add(role);
		});
		user.setRoles(listRole);
		if(user.getPassword() != null && !user.getPassword().equals("")) {
//			PasswordEncoder encoder = PasswordEncoderFactories.createDelegatingPasswordEncoder();
			String encoded = new BCryptPasswordEncoder().encode(user.getPassword());
			user.setPassword(encoded);
		} else {
			user.setPassword(updateUser.getPassword());
		}
		
		return userRepository.save(user);
	}

	@Override
	public String deleteById(String id) {
		userRepository.deleteById(id);
		return id;
	}

	@Override
	public Optional<User> findByUsername(String username) {
		return userRepository.findByUsername(username);
	}

	@Override
	public InfoPersonal getInfoPersonal() {
		String username = Utilities.getCurrentUser();
		User user = findByUsername(username).get();
		return new InfoPersonal(user.getFirstName(), user.getLastName(), user.getBirthday(), user.getGender(), 
				user.getPhoneNumber(), user.getEmail(), user.getUsername(), user.getPassword());
	}

	@Override
	public Boolean updateFullname(String firstName, String lastName) {
		String username = Utilities.getCurrentUser();
		User user = findByUsername(username).get();
		user.setFirstName(firstName);
		user.setLastName(lastName);
		return userRepository.save(user)!=null?true:false;
	}

	@Override
	public Boolean updateBirthday(String birthday) {
		String username = Utilities.getCurrentUser();
		User user = findByUsername(username).get();
		user.setBirthday(birthday);
		return userRepository.save(user)!=null?true:false;
	}

	@Override
	public Boolean updateGender(String gender) {
		String username = Utilities.getCurrentUser();
		User user = findByUsername(username).get();
		user.setGender(gender);
		return userRepository.save(user)!=null?true:false;
	}

	@Override
	public Boolean checkPassword(String exPassword) {
		String username = Utilities.getCurrentUser();
		User user = findByUsername(username).get();
		BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
		return passwordEncoder.matches(exPassword, user.getPassword());
	}

	@Override
	public Boolean updatePassword(String exPassword, String newPassword) {
		String username = Utilities.getCurrentUser();
		User user = findByUsername(username).get();
		BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
		if(passwordEncoder.matches(exPassword, user.getPassword())) {
			user.setPassword(passwordEncoder.encode(newPassword));
			return userRepository.save(user)!=null?true:false;
		}
		return false;
		
	}

	@Override
	public Boolean updateEmail(String email) {
		String username = Utilities.getCurrentUser();
		User user = findByUsername(username).get();
		user.setEmail(email);
		return userRepository.save(user)!=null?true:false;
	}

	@Override
	public Boolean updatePhoneNumber(String phoneNumber) {
		String username = Utilities.getCurrentUser();
		User user = findByUsername(username).get();
		user.setPhoneNumber(phoneNumber);
		return userRepository.save(user)!=null?true:false;
	}

}
