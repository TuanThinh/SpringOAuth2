package mta.tuanthinh.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import mta.tuanthinh.document.UserInfShare;
import mta.tuanthinh.repository.UserInfShareRepository;

@Service
public class UserInfShareServiceImpl implements UserInfShareService{
	@Autowired
	private UserInfShareRepository userInfShareRepository;

	@Override
	public List<UserInfShare> findAll() {
		return userInfShareRepository.findAll();
	}
	
	
}
