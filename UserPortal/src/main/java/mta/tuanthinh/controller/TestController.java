package mta.tuanthinh.controller;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;


import mta.tuanthinh.modal.KhachHang;
import mta.tuanthinh.utility.Utilities;

@RestController
@RequestMapping("/test")
public class TestController {
	@Autowired
	RestTemplate restTemplate;
	
	@Value("${resource-service}")
	private String resource;
	
	@RequestMapping("/a")
	@ResponseBody
	public List<KhachHang> testA() {
		System.out.println("---------------------" + resource);
		
		
		HttpHeaders httpHeaders = new HttpHeaders();
		httpHeaders.add("Authorization", Utilities.getAccessToken());
		
		HttpEntity<KhachHang> khachHangEntity = new HttpEntity<KhachHang>(Utilities.getHeader());
		ResponseEntity<KhachHang[]> responseEntity = restTemplate.exchange("http://localhost:8181/api/khach-hang/all", HttpMethod.GET, khachHangEntity, KhachHang[].class);
		
		System.out.println("response: " + responseEntity);
		
		List<KhachHang> kh = Arrays.asList(responseEntity.getBody());
		
		System.out.println("response entity: " + kh);
		
		return kh;
	}
	
	@RequestMapping("/b/{id}")
	@ResponseBody
	public String testB(@PathVariable String id) {
		String url = "http://localhost:8181/api/khach-hang/id/{id}";
		Map<String, String> params = new HashMap<>();
	    params.put("id", id);
	    
		HttpHeaders httpHeaders = new HttpHeaders();
		httpHeaders.add("Authorization", Utilities.getAccessToken());
		
		HttpEntity<String> khachHangEntity = new HttpEntity<String>(httpHeaders);
		ResponseEntity<String> responseEntity = restTemplate.exchange(url, HttpMethod.GET, khachHangEntity, String.class, id);
		
		System.out.println("response: " + responseEntity);
		
//		List<KhachHang> kh = Arrays.asList(responseEntity.getBody());
		
//		System.out.println("response entity: " + kh);
		
		return responseEntity.getBody();
	}
	
	@RequestMapping("/c")
	@ResponseBody
	public String testC() {
		String url = "http://localhost:8181/api/khach-hang/add";
		
		KhachHang kh = new KhachHang("Nguyễn Văn", "B", "Không có", "Đếck có", "Miễn hỏi");
//		Map<String, Object> map = new HashMap<>();
//		map.put("firstName", "Nguyễn Văn");
//		map.put("lastName", "B");
//		map.put("diaChi", "Không có");
//		map.put("dienThoai", "Đếck có");
//		map.put("email", "Miễn hỏi");
	    
		HttpHeaders httpHeaders = new HttpHeaders();
		httpHeaders.setContentType(MediaType.APPLICATION_JSON);
		httpHeaders.add("Authorization", Utilities.getAccessToken());
		
//		HttpEntity<String> khachHangEntity = new HttpEntity<String>(httpHeaders);
		HttpEntity<KhachHang> entity = new HttpEntity<KhachHang>(kh, httpHeaders);
		
		ResponseEntity<String> responseEntity = restTemplate.exchange(url, HttpMethod.POST, entity, String.class);
		
		System.out.println("response: " + responseEntity);
		
//		List<KhachHang> kh = Arrays.asList(responseEntity.getBody());
		
//		System.out.println("response entity: " + kh);
		
		return responseEntity.getBody();
	}
	
	@RequestMapping("/e")
	@ResponseBody
	public String testE() {
		String url = "http://localhost:8181/api/khach-hang/edit";
		
		KhachHang kh = new KhachHang("5eb4224bae03d56106f5087f", "Nguyễn Văn", "B", "Không có", "Đếck có", "Miễn hỏi.......");
//		Map<String, Object> map = new HashMap<>();
//		map.put("firstName", "Nguyễn Văn");
//		map.put("lastName", "B");
//		map.put("diaChi", "Không có");
//		map.put("dienThoai", "Đếck có");
//		map.put("email", "Miễn hỏi");
	    
		HttpHeaders httpHeaders = new HttpHeaders();
		httpHeaders.setContentType(MediaType.APPLICATION_JSON);
		httpHeaders.add("Authorization", Utilities.getAccessToken());
		
//		HttpEntity<String> khachHangEntity = new HttpEntity<String>(httpHeaders);
		HttpEntity<KhachHang> entity = new HttpEntity<KhachHang>(kh, httpHeaders);
		
		ResponseEntity<String> responseEntity = restTemplate.exchange(url, HttpMethod.PUT, entity, String.class);
		
		System.out.println("response: " + responseEntity);
		
//		List<KhachHang> kh = Arrays.asList(responseEntity.getBody());
		
//		System.out.println("response entity: " + kh);
		
		return responseEntity.getBody();
	}
	
	@RequestMapping("/d/{id}")
	@ResponseBody
	public String testD(@PathVariable String id) {
		String url = "http://localhost:8181/api/khach-hang/delete/{id}";
		
//		String url = "http://localhost:8181/api/khach-hang/id/{id}";
		Map<String, String> params = new HashMap<>();
	    params.put("id", id);
	    
		HttpHeaders httpHeaders = new HttpHeaders();
		httpHeaders.add("Authorization", Utilities.getAccessToken());
		
		HttpEntity<String> khachHangEntity = new HttpEntity<String>(httpHeaders);
		ResponseEntity<String> responseEntity = restTemplate.exchange(url, HttpMethod.DELETE, khachHangEntity, String.class, params);
		
		System.out.println("response: " + responseEntity);
		
//		List<KhachHang> kh = Arrays.asList(responseEntity.getBody());
		
//		System.out.println("response entity: " + kh);
		
		return responseEntity.getBody();
	}
}
