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

import mta.tuanthinh.document.Permission;
import mta.tuanthinh.service.PermissionService;

@RestController
@RequestMapping("/permission")
public class PermissionApi {
	@Autowired
	private PermissionService permissionService;
	
	@GetMapping("/all")
	public List<Permission> findAll(){
		return permissionService.findAll();
	}
	
	@GetMapping("/id/{id}")
	public Permission findById(@PathVariable String id) {
		return permissionService.findById(id).get();
	}
	
	@GetMapping("/name/{name}")
	public Permission findByName(@PathVariable String name) {
		return permissionService.findByName(name).get();
	}
	
	@PostMapping("/add")
	public Permission add(@RequestBody Permission permission) {
		return permissionService.save(permission);
	}
	
	@PutMapping("/update")
	public Permission update(@RequestBody Permission permission) {
		return permissionService.update(permission);
	}
	
	@DeleteMapping("/delete/{id}")
	public String deleteById(@PathVariable String id) {
		return permissionService.deleteById(id);
	}
}
