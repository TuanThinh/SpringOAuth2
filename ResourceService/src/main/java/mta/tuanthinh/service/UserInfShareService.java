package mta.tuanthinh.service;

import java.util.List;
import java.util.Optional;

import mta.tuanthinh.document.UserInfShare;
import mta.tuanthinh.dto.InfShare;

public interface UserInfShareService {
	List<UserInfShare> findAll();
	Optional<UserInfShare> findById(String id);
	Optional<UserInfShare> findByUsername(String username);
	UserInfShare save(UserInfShare entity);
	String deleteById(String id);
	
	UserInfShare getProfileForCurrentUser();
	Boolean updateFullname(String fullName, String flag);
	Boolean updateBirthday(String birthday, String flag);
	Boolean updateGender(String gender, String flag);
	Boolean updateEmail(String email, String flag);
	Boolean updatePhoneNumber(String phoneNumber, String flag);
	
	InfShare getInfShare();
	String getEmailShare();
}
