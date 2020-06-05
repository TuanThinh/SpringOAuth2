package mta.tuanthinh.service;

import java.util.List;
import java.util.Optional;

import mta.tuanthinh.document.User;
import mta.tuanthinh.dto.InfoPersonal;

public interface UserService {
	List<User> findAll();
	Optional<User> findById(String id);
	Optional<User> findByUsername(String username);
	User save (User user);
	String deleteById(String id);

	InfoPersonal getInfoPersonal();
	Boolean updateFullname(String firstName, String lastName);
	Boolean updateBirthday(String birthday);
	Boolean updateGender(String gender);
	Boolean checkPassword(String exPassword);
	Boolean updatePassword(String exPassword, String newPassword);
	Boolean updateEmail(String email);
	Boolean updatePhoneNumber(String phoneNumber);
}
