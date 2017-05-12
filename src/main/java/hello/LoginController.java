package hello;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/rest")
@Configuration
@CrossOrigin(origins = "http://localhost:4200", maxAge = 3600)
class LoginController {

    @Autowired
    UserService us;
    
    @RequestMapping(value = "/{id}",
            method = RequestMethod.GET,
            produces = "application/json")
    public List<User> get(@PathVariable String id) {
        List<User> users = new ArrayList<User>();
        if (!this.isNumeric(id)) {
            for (User p : us.getAll()) {
                users.add(p);
            }
        } else {
            users.add(us.getOne(new Long(id)));
        }
        return users;
    }

    @RequestMapping(value = "/{id}",
            method = RequestMethod.DELETE,
            produces = "application/json")
    public Iterable<User> delete(@PathVariable String id) {
        if (isNumeric(id)) {
            this.us.deleteUser(this.us.getOne(new Long(id)));
            return this.us.getAll();
        }
        return null;
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
    public User createPokemon(@RequestBody User p) {
        return this.us.createUser(p);
    }

    //Test si un string est numérique
    public boolean isNumeric(String s) {
        return s.matches("[-+]?\\d*\\.?\\d+");
    }
}
