/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fst.lmfi.Services;

import fst.lmfi.Entities.User;
import java.util.List;

/**
 *
 * @author mathieu.jeanmougin
 */

public interface UserService {
    
    User createUser(User p);
    
    User updateUser(User p);
    
    void deleteUser(User p);

    User getOne(String id);
    
    List<User> getAll();
    
}
