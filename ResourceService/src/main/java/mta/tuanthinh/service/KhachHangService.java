package mta.tuanthinh.service;

import java.util.List;
import java.util.Optional;

import mta.tuanthinh.document.KhachHang;

public interface KhachHangService {
	List<KhachHang> findAll();
	Optional<KhachHang> findById(String id);
	KhachHang save(KhachHang khachhang);
	String deleteById(String id);
}
