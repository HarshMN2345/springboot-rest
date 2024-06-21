package com.harshmahajan.Rest.user;

import jakarta.validation.Valid;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.nio.file.attribute.UserPrincipalNotFoundException;
import java.util.List;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

//@RestController
public class UserController {
    private UserDaoService service;
    public UserController(UserDaoService service) {
        this.service = service;
    }
    @GetMapping(path="/users")
    public List<User> retrieveAll(){
        return service.getUsers();
    }
    @GetMapping(path="/users/{id}")
    public EntityModel<User>retrieveUser(@PathVariable int id) {
        User user= service.findOne(id);
        if(user==null){
            throw new UserNotFoundException("id:"+id);
        }
        EntityModel <User> model = EntityModel.of(user);
        WebMvcLinkBuilder link=linkTo(methodOn(this.getClass()).retrieveAll());
        model.add(link.withRel("all-users"));
        return model;
    }
    @DeleteMapping(path="/users/{id}")
    public void DeleteUser(@PathVariable int id) {
        service.DeleteById(id);
    }
    @PostMapping(path="/users")
    public ResponseEntity<User> createUser(@Valid @RequestBody User user) {
       User SavedUser=service.save(user);
       URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(SavedUser.getId()).toUri();
       return ResponseEntity.created(location).build();

    }

}
