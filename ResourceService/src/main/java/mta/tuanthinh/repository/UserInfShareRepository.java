package mta.tuanthinh.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import mta.tuanthinh.document.UserInfShare;

public interface UserInfShareRepository extends MongoRepository<UserInfShare, String>{

}
