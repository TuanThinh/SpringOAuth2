//package mta.tuanthinh.conf;
//
//import java.util.Arrays;
//import java.util.HashMap;
//import java.util.List;
//import java.util.Map;
//
//import org.bouncycastle.util.encoders.Base64Encoder;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.HttpEntity;
//import org.springframework.http.HttpHeaders;
//import org.springframework.http.HttpMethod;
//import org.springframework.http.MediaType;
//import org.springframework.http.ResponseEntity;
//import org.springframework.stereotype.Component;
//import org.springframework.web.bind.annotation.PathVariable;
//import org.springframework.web.client.RestTemplate;
//
//import mta.tuanthinh.modal.KhachHang;
//
//@Component
//public class ResourceConf<T> {
//
//	@Autowired
//	RestTemplate restTemplate;
//	
//	public <T extends Object> List<T> sendListResult(String url) {
//
//		HttpHeaders httpHeaders = new HttpHeaders();
//		httpHeaders.add("Authorization", AccessToken.getAccessToken());
//		
//		HttpEntity<T> khachHangEntity = new HttpEntity<T>(httpHeaders);
//		ResponseEntity<T[]> responseEntity = restTemplate.exchange(url, HttpMethod.GET, khachHangEntity, T[].class);
//		
//		System.out.println("response: " + responseEntity);
//		
//		List<T> kh = Arrays.asList(responseEntity.getBody());
//		
//		System.out.println("response entity: " + kh);
//		
//		return kh;
//	}
//	
//	public T testB(@PathVariable String id) {
//		String url = "http://localhost:8181/api/khach-hang/id/{id}";
//		Map<String, String> params = new HashMap<>();
//	    params.put("id", id);
//	    
//		HttpHeaders httpHeaders = new HttpHeaders();
//		httpHeaders.add("Authorization", AccessToken.getAccessToken());
//		
//		HttpEntity<T> khachHangEntity = new HttpEntity<T>(httpHeaders);
//		ResponseEntity<T> responseEntity = restTemplate.exchange(url, HttpMethod.GET, khachHangEntity, T.class, id);
//		
//		System.out.println("response: " + responseEntity);
//		
//		return responseEntity.getBody();
//	}
//	
//	public String testC() {
//		String url = "http://localhost:8181/api/khach-hang/add";
//		
//		KhachHang kh = new KhachHang("Nguyễn Văn", "B", "Không có", "Đếck có", "Miễn hỏi");
//	    
//		HttpHeaders httpHeaders = new HttpHeaders();
//		httpHeaders.setContentType(MediaType.APPLICATION_JSON);
//		httpHeaders.add("Authorization", AccessToken.getAccessToken());
//		
//		HttpEntity<KhachHang> entity = new HttpEntity<KhachHang>(kh, httpHeaders);
//		
//		ResponseEntity<String> responseEntity = restTemplate.exchange(url, HttpMethod.POST, entity, String.class);
//		
//		System.out.println("response: " + responseEntity);
//		
//		return responseEntity.getBody();
//	}
//	
//	public String testE() {
//		String url = "http://localhost:8181/api/khach-hang/edit";
//		
//		KhachHang kh = new KhachHang("5eb4224bae03d56106f5087f", "Nguyễn Văn", "B", "Không có", "Đếck có", "Miễn hỏi.......");
//	    
//		HttpHeaders httpHeaders = new HttpHeaders();
//		httpHeaders.setContentType(MediaType.APPLICATION_JSON);
//		httpHeaders.add("Authorization", AccessToken.getAccessToken());
//		
//		HttpEntity<KhachHang> entity = new HttpEntity<KhachHang>(kh, httpHeaders);
//		
//		ResponseEntity<String> responseEntity = restTemplate.exchange(url, HttpMethod.PUT, entity, String.class);
//		
//		System.out.println("response: " + responseEntity);
//		
//		return responseEntity.getBody();
//	}
//	
//	public String testD(@PathVariable String id) {
//		String url = "http://localhost:8181/api/khach-hang/delete/{id}";
//		
//		Map<String, String> params = new HashMap<>();
//	    params.put("id", id);
//	    
//		HttpHeaders httpHeaders = new HttpHeaders();
//		httpHeaders.add("Authorization", AccessToken.getAccessToken());
//		
//		HttpEntity<String> khachHangEntity = new HttpEntity<String>(httpHeaders);
//		ResponseEntity<String> responseEntity = restTemplate.exchange(url, HttpMethod.DELETE, khachHangEntity, String.class, params);
//		
//		System.out.println("response: " + responseEntity);
//		
//		return responseEntity.getBody();
//	}
//}
