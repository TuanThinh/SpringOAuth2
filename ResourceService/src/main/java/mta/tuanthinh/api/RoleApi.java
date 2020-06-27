package mta.tuanthinh.api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import mta.tuanthinh.document.Role;
import mta.tuanthinh.service.RoleService;

@RestController
@RequestMapping("/role")
public class RoleApi {
	@Autowired
	private RoleService roleService;
	
	@GetMapping("/all")
	public List<Role> findAll(){
		return roleService.findAll();
	}
	
	@GetMapping("/id/{id}")
	public Role findById(@PathVariable String id) {
		return roleService.findById(id).get();
	}
	
	@GetMapping("/id-permission/{id}")
	public List<Role> findByIdPermission(@PathVariable String id) {
		return roleService.findByIdPermission(id);
	}
	
	@GetMapping("/name/{name}")
	public Role findByName(@PathVariable String name) {
		return roleService.findByName(name).get();
	}
	
	@PostMapping("/add")
	public Role add(@RequestBody Role role) {
		return roleService.save(role);
	}
	
	@PutMapping("/update")
	public Role update(@RequestBody Role role) {
		return roleService.update(role);
	}
	
	@DeleteMapping("/delete/{id}")
	public String deleteById(@PathVariable String id) {
		return roleService.deleteById(id);
	}
}
