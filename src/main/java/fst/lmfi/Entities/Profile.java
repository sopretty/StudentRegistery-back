/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fst.lmfi.Entities;

/**
 *
 * @author mathieu.jeanmougin
 */
public class Profile {

    public int classYear;
    public String firstname;
    public String lastname;
    public String emailPro;
    public String linkedIn;
    public String company;

    public Profile() {
    }

    public Profile(int classYear, String firstname, String lastname, String emailPro, String linkedIn, String company) {
        this.classYear = classYear;
        this.firstname = firstname;
        this.lastname = lastname;
        this.emailPro = emailPro;
        this.linkedIn = linkedIn;
        this.company = company;
    }
}
