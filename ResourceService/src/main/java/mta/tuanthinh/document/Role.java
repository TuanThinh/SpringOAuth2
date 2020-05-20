package mta.tuanthinh.document;

import java.io.Serializable;
import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;


@Document(collection = "role")
public class Role implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Id
	private String id;

    private String name;
	
	@Field(name = "permissions")
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
