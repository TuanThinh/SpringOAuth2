package mta.tuanthinh.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import mta.tuanthinh.document.UserInfShare;
import mta.tuanthinh.repository.UserInfShareRepository;
import mta.tuanthinh.utility.Utilities;

@Service
public class UserInfShareServiceImpl implements UserInfShareService{
	@Autowired
	private UserInfShareRepository userInfShareRepository;


	@Override
	public List<UserInfShare> findAll() {
		return userInfShareRepository.findAll();
	}

	@Override
	public Optional<UserInfShare> findById(String id) {
		return userInfShareRepository.findById(id);
	}

	@Override
	public Optional<UserInfShare> findByUsername(String username) {
		return userInfShareRepository.findByUsername(username);
	}

	@Override
	public UserInfShare save(UserInfShare entity) {
		return userInfShareRepository.save(entity);
	}

	@Override
	public String deleteById(String id) {
		userInfShareRepository.deleteById(id);
		return id;
	}

	@Override
	public UserInfShare getProfileForCurrentUser() {
		String username = Utilities.getCurrentUser();
		UserInfShare share = findByUsername(username).get();
		return share;
	}

	@Override
	public Boolean updateFullname(String fullName, String flag) {
		UserInfShare share = getProfileForCurrentUser();
		Map<String, String> map = new HashMap<String, String>();
		map.put("fullName", fullName);
		map.put("flag", flag);
		share.setFullName(map);
		return save(share)!=null?true:false;
	}

	@Override
	public Boolean updateBirthday(String birthday, String flag) {
		UserInfShare share = getProfileForCurrentUser();
		Map<String, String> map = new HashMap<String, String>();
		map.put("birthday", birthday);
		map.put("flag", flag);
		share.setBirthday(map);
		return save(share)!=null?true:false;
	}

	@Override
	public Boolean updateGender(String gender, String flag) {
		UserInfShare share = getProfileForCurrentUser();
		Map<String, String> map = new HashMap<String, String>();
		map.put("gender", gender);
		map.put("flag", flag);
		share.setGender(map);
		return save(share)!=null?true:false;
	}

	@Override
	public Boolean updateEmail(String email, String flag) {
		UserInfShare share = getProfileForCurrentUser();
		Map<String, String> map = new HashMap<String, String>();
		map.put("email", email);
		map.put("flag", flag);
		share.setEmail(map);
		return save(share)!=null?true:false;
	}

	@Override
	public Boolean updatePhoneNumber(String phoneNumber, String flag) {
		UserInfShare share = getProfileForCurrentUser();
		Map<String, String> map = new HashMap<String, String>();
		map.put("phoneNumber", phoneNumber);
		map.put("flag", flag);
		share.setPhoneNumber(map);
		return save(share)!=null?true:false;
	}
	
}
