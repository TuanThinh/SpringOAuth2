package mta.tuanthinh.service;

import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.oauth2.client.OAuth2ClientContext;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.authentication.OAuth2AuthenticationDetails;
import org.springframework.stereotype.Service;

import mta.tuanthinh.document.KhachHang;
import mta.tuanthinh.repository.KhachHangRepository;

@Service
public class KhachHangServiceImpl implements KhachHangService{

	@Autowired
	private KhachHangRepository khachHangRepository;
	
	@Override
	public List<KhachHang> findAll() {
		return khachHangRepository.findAll();
	}

	@Override
	public Optional<KhachHang> findById(String id) {
		return khachHangRepository.findById(id);
	}

	@Override
	public KhachHang save(KhachHang khachhang) {
		return khachHangRepository.save(khachhang);
	}

	@Override
	public String deleteById(String id) {
		khachHangRepository.deleteById(id);
		return id;
	}

	@Override
	public String getUsername() {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		
		Collection<SimpleGrantedAuthority> authorities = (Collection<SimpleGrantedAuthority>) authentication.getAuthorities();
		
		OAuth2AuthenticationDetails oauth= (OAuth2AuthenticationDetails) authentication.getDetails();
		
		OAuth2Authentication oAuth2Authentication = (OAuth2Authentication) authentication;
		
		Set<String> scopes = oAuth2Authentication.getOAuth2Request().getScope();
		
		String username = authentication.getName();
		System.out.println(username);
		return username;
	}
}
