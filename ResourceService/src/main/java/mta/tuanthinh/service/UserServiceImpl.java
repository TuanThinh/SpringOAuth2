package mta.tuanthinh.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import mta.tuanthinh.document.User;
import mta.tuanthinh.dto.InfoPersonal;
import mta.tuanthinh.repository.UserRepository;
import mta.tuanthinh.utility.Utilities;

@Service
public class UserServiceImpl implements UserService{

	@Autowired 
	private UserRepository userRepository;
	
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
		return save(user)!=null?true:false;
	}

	@Override
	public Boolean updateBirthday(String birthday) {
		String username = Utilities.getCurrentUser();
		User user = findByUsername(username).get();
		user.setBirthday(birthday);
		return save(user)!=null?true:false;
	}

	@Override
	public Boolean updateGender(String gender) {
		String username = Utilities.getCurrentUser();
		User user = findByUsername(username).get();
		user.setGender(gender);
		return save(user)!=null?true:false;
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
			return save(user)!=null?true:false;
		}
		return false;
		
	}

	@Override
	public Boolean updateEmail(String email) {
		String username = Utilities.getCurrentUser();
		User user = findByUsername(username).get();
		user.setEmail(email);
		return save(user)!=null?true:false;
	}

	@Override
	public Boolean updatePhoneNumber(String phoneNumber) {
		String username = Utilities.getCurrentUser();
		User user = findByUsername(username).get();
		user.setPhoneNumber(phoneNumber);
		return save(user)!=null?true:false;
	}

}
