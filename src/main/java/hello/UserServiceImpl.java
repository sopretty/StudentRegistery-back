/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hello;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
    
}
