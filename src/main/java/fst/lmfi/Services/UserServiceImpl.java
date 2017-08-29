package fst.lmfi.Services;

import fst.lmfi.Entities.User;
import fst.lmfi.Repositories.UserRepository;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 *
 * @author mathieu.jeanmougin
 */
@Service(value = "UserService")
public class UserServiceImpl implements UserService {

	@Autowired
	UserRepository rp;

	@Override
	public User getOne(String id) {
		return this.rp.findOne(id);
	}

	@Override
	public List<User> getAll() {
		List<User> lp = new ArrayList();
		for (User p : this.rp.findAll()) {
			lp.add(p);
		}
		return lp;
	}

	@Override
	public User createUser(User p) {
		return this.rp.save(p);
	}

	@Override
	public User updateUser(User p) {
		return this.rp.save(p);
	}

	@Override
	public void deleteUser(User p) {
		this.rp.delete(p);
	}

	public boolean checkConnexion(String email, String password) {
		boolean res = false;
		List<User> users = this.rp.findByEmail(email);
		String encryptedPassword = this.encryptPassword(password);
		System.out.println(users.size());
		System.out.println(encryptedPassword);
		if (users.size() == 1) {
			if (password.equals(encryptedPassword)) {
				res = true;
			}
		}
		return res;
	}
	
	public String encryptPassword(String password){
		int i = 0;
		String hashedPassword = "";
		while (i < 10) {
			BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
			hashedPassword = passwordEncoder.encode(password);
			i++;
		}
		return hashedPassword;
	}

}
