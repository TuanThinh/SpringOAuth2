package mta.tuanthinh.modal;

import java.io.Serializable;
import java.util.List;


public class Role implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private String id;
    private String name;
    private List<Permission> permissions;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public List<Permission> getPermissions() {
		return permissions;
	}
	public void setPermission(List<Permission> permission) {
		this.permissions = permission;
	}
    
    
}
