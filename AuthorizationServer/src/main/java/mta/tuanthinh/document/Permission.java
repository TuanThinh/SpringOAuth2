package mta.tuanthinh.document;

import java.io.Serializable;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Document(collection = "permission")
public class Permission implements Serializable {
	private static final long serialVersionUID = 1L;
	@Id
	@Field(name = "_id")
    private String id;
	
	@Field(name = "name")
    private String name;
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

    
    
}
