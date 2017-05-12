/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hello;

import org.springframework.data.annotation.Id;

/**
 *
 * @author mathieu.jeanmougin
 */
public class User {

    @Id
    public String id;

    public String email;
    public String password;

    public User() {
    }

    public User(String email, String password) {
        this.email = email;
        this.password = password;
    }
}
