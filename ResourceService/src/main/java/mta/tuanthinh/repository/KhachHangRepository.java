package mta.tuanthinh.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import mta.tuanthinh.document.KhachHang;

public interface KhachHangRepository extends MongoRepository<KhachHang, String>{

}
