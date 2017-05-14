package hello;

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
    UserService us;
    
    @RequestMapping(value = "/{id}",
            method = RequestMethod.GET,
            produces = "application/json")
    public User get(@PathVariable String id, HttpServletResponse resp) {
        User u = this.us.getOne(id);
        if(u == null){
            resp.setStatus(HttpServletResponse.SC_NOT_FOUND);
        }
        return u;
    }

    @RequestMapping(value = "/{id}",
            method = RequestMethod.DELETE,
            produces = "application/json")
    public Iterable<User> delete(@PathVariable String id) {
        this.us.deleteUser(this.us.getOne(id));
        return this.us.getAll();
    }

    @RequestMapping(
            method = RequestMethod.GET,
            produces = "application/json")
    public Iterable<User> getAll() {
        return this.us.getAll();
    }

    @RequestMapping(
            method = RequestMethod.POST,
            consumes = "application/json",
            produces = "application/json")
    public User createUser(@RequestBody User p) {
        return this.us.createUser(p);
    }
}
