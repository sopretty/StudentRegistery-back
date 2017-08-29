package fr.univlorraine.boundary;

import fr.univlorraine.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.ExposesResourceFor;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.Resource;
import org.springframework.hateoas.Resources;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

@RestController
@RequestMapping(value = "/users", produces = MediaType.APPLICATION_JSON_VALUE)
@ExposesResourceFor(User.class)
public class UserRepresentation {
    @Autowired
    UserResource ur;

    @GetMapping
    public ResponseEntity<?> getAllUsers(){
        Iterable<User> allUsers  = ur.findAll();
        return new ResponseEntity<>(userToResource(allUsers), HttpStatus.OK);
    }

    //GET une instance
    @GetMapping(value="/{userid}")
    public ResponseEntity<?> getOneUsers(@PathVariable("userid") Long id){
        return Optional.ofNullable(ur.findOne(id))
                .map(found -> new ResponseEntity(userToResource(found, true),HttpStatus.OK))
                .orElse(new ResponseEntity(HttpStatus.NOT_FOUND));
    }

    //DELETE
    @DeleteMapping(value="/{userid}")
    public ResponseEntity<?> deleteUser(@PathVariable("userid") Long id) {
        ur.delete(id);
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }

    //PUT
    @PutMapping(value="/{userid}")
    public ResponseEntity<?> updateUser(@RequestBody User u, @PathVariable("userid") Long id){
        u.setId(id);
        User user = ur.save(u);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    private Resources<Resource<User>> userToResource(Iterable<User> all) {
        Link selfLink = linkTo(methodOn(UserRepresentation.class).getAllUsers()).withSelfRel();
        List<Resource<User>> users = new ArrayList();
        all.forEach(user -> users.add(userToResource(user, false)));
        return new Resources<>(users,selfLink);
    }

    private Resource<User> userToResource(User s, Boolean collection) {
        Link selfLink = linkTo(UserRepresentation.class).slash(s.getId()).withSelfRel();
        if (collection) {
            Link collectionLink = linkTo(methodOn(UserRepresentation.class).getAllUsers()).withRel("collection");
            return new Resource<>(s,selfLink,collectionLink);
        } else {
            return new Resource<>(s,selfLink);
        }
    }

    //POST
    @PostMapping
    public ResponseEntity<?> saveUser(@RequestBody User s) {
        User saved = ur.save(s);
        HttpHeaders responseHeaders = new HttpHeaders();
        responseHeaders.setLocation(linkTo(UserRepresentation.class).slash(saved.getId()).toUri());
        return new ResponseEntity<>(null, responseHeaders, HttpStatus.CREATED);
    }

}
