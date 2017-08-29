package fst.lmfi.Controllers;

import fst.lmfi.Entities.Connexion;
import fst.lmfi.Entities.User;
import fst.lmfi.Services.UserServiceImpl;

import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
@Configuration
@CrossOrigin(origins = "http://localhost:4200", maxAge = 3600)
class LoginController {

	@Autowired
	UserServiceImpl us;

	@RequestMapping(value = "/{id}", method = RequestMethod.GET, produces = "application/json")
	public User get(@PathVariable String id, HttpServletResponse resp) {
		User u = this.us.getOne(id);
		if (u == null) {
			resp.setStatus(HttpServletResponse.SC_NOT_FOUND);
		}
		return u;
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE, produces = "application/json")
	public Iterable<User> delete(@PathVariable String id) {
		User u = this.us.getOne(id);
		if (u != null) {
			this.us.deleteUser(u);
		}
		return this.us.getAll();
	}

	@RequestMapping(method = RequestMethod.GET, produces = "application/json")
	public Iterable<User> getAll() {
		return this.us.getAll();
	}

	@RequestMapping(method = RequestMethod.POST, consumes = "application/json", produces = "application/json")
	public User createUser(@RequestBody User p) {
		return this.us.createUser(p);
	}

	@RequestMapping(value = "/connexion", method = RequestMethod.POST, consumes = "application/json", produces = "application/json")
	public boolean checkConnexion(@RequestBody Connexion c) {
		return this.us.checkConnexion(c.getEm(), c.getPw());
	}
}
