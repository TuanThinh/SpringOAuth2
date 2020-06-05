package mta.tuanthinh.modal;

import java.io.Serializable;
import java.util.Map;

public class UserInfShare implements Serializable {
	private static final long serialVersionUID = 1L;

	private String id;
	private String username;
	private Map<String, String> fullName;
	private Map<String, String> birthday;
	private Map<String, String> gender;
	private Map<String, String> phoneNumber;
	private Map<String, String> email;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public Map<String, String> getFullName() {
		return fullName;
	}
	public void setFullName(Map<String, String> fullName) {
		this.fullName = fullName;
	}
	public Map<String, String> getBirthday() {
		return birthday;
	}
	public void setBirthday(Map<String, String> birthday) {
		this.birthday = birthday;
	}
	public Map<String, String> getGender() {
		return gender;
	}
	public void setGender(Map<String, String> gender) {
		this.gender = gender;
	}
	public Map<String, String> getPhoneNumber() {
		return phoneNumber;
	}
	public void setPhoneNumber(Map<String, String> phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	public Map<String, String> getEmail() {
		return email;
	}
	public void setEmail(Map<String, String> email) {
		this.email = email;
	}
	
}
