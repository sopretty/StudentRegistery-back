package fst.lmfi.Entities;

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
    public boolean admin;
    
    public Profile profile;

    public User() {
    }

    public User(String email, String password, boolean admin, Profile profile) {
        this.email = email;
        this.password = password;
        this.admin = admin;
        this.profile = profile;
    }

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public boolean isAdmin() {
		return admin;
	}

	public void setAdmin(boolean admin) {
		this.admin = admin;
	}

	public Profile getProfile() {
		return profile;
	}

	public void setProfile(Profile profile) {
		this.profile = profile;
	}
    
}
