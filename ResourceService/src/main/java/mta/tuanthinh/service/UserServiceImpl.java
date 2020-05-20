package mta.tuanthinh.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
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

	
}
