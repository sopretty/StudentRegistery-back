package fst.lmfi.Repositories;

import fst.lmfi.Entities.User;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface UserRepository extends MongoRepository<User, String> {

	   List<User> findByEmail(String email);
}
