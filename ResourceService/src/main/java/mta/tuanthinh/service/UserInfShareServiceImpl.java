package mta.tuanthinh.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.util.JSONPObject;

import mta.tuanthinh.document.UserInfShare;
import mta.tuanthinh.dto.InfShare;
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
	
	@Override
	public InfShare getInfShare() {
		String username = Utilities.getCurrentUser();
		UserInfShare userInf = findByUsername(username).get();
		InfShare share = new InfShare();
		int flag = 1;
		Set<Entry<String, String>> fullName = userInf.getFullName().entrySet();
		for (Entry<String, String> entry : fullName) {
			if(entry.getKey().equals("flag")) {
				if(entry.getValue().equals("1")) {
					flag = 1;
				} else {
					flag = 0;
				}
			}
			if(entry.getKey().equals("fullName")) {
				share.setFullName(entry.getValue());
			}
            System.out.println(entry.getKey() + " : " + entry.getValue());
        }
		if(flag == 0) {
			share.setFullName(null);
		}
		
		Set<Entry<String, String>> birthday = userInf.getBirthday().entrySet();
		for (Entry<String, String> entry : birthday) {
			if(entry.getKey().equals("flag")) {
				if(entry.getValue().equals("1")) {
					flag = 1;
				} else {
					flag = 0;
				}
			}
			if(entry.getKey().equals("birthday")) {
				share.setBirthday(entry.getValue());
			}
            System.out.println(entry.getKey() + " : " + entry.getValue());
        }
		if(flag == 0) {
			share.setBirthday(null);
		}
		
		Set<Entry<String, String>> gender = userInf.getGender().entrySet();
		for (Entry<String, String> entry : gender) {
			if(entry.getKey().equals("flag")) {
				if(entry.getValue().equals("1")) {
					flag = 1;
				} else {
					flag = 0;
				}
			}
			if(entry.getKey().equals("gender")) {
				share.setGender(entry.getValue());
			}
            System.out.println(entry.getKey() + " : " + entry.getValue());
        }
		if(flag == 0) {
			share.setGender(null);
		}
		
		Set<Entry<String, String>> phoneNumber = userInf.getPhoneNumber().entrySet();
		for (Entry<String, String> entry : phoneNumber) {
			if(entry.getKey().equals("flag")) {
				if(entry.getValue().equals("1")) {
					flag = 1;
				} else {
					flag = 0;
				}
			}
			if(entry.getKey().equals("phoneNumber")) {
				share.setPhoneNumber(entry.getValue());
			}
            System.out.println(entry.getKey() + " : " + entry.getValue());
        }
		if(flag == 0) {
			share.setPhoneNumber(null);
		}
		
		Set<Entry<String, String>> email = userInf.getEmail().entrySet();
		for (Entry<String, String> entry : email) {
			if(entry.getKey().equals("flag")) {
				if(entry.getValue().equals("1")) {
					flag = 1;
				} else {
					flag = 0;
				}
			}
			if(entry.getKey().equals("email")) {
				share.setEmail(entry.getValue());
			}
            System.out.println(entry.getKey() + " : " + entry.getValue());
        }
		if(flag == 0) {
			share.setEmail(null);
		}
		
		return share;
	}

	@Override
	public String getEmailShare() {
		String username = Utilities.getCurrentUser();
		UserInfShare userInf = findByUsername(username).get();
		Set<Entry<String, String>> email = userInf.getEmail().entrySet();
		String emailInf = null;
		for (Entry<String, String> entry : email) {
			if(entry.getKey().equals("email")) {
				emailInf = entry.getValue();
			}
            System.out.println(entry.getKey() + " : " + entry.getValue());
        }
		return emailInf;
	}
	
}
