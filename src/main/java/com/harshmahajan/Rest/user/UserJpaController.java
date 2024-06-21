package com.harshmahajan.Rest.user;

import jakarta.validation.Valid;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
public class UserJpaController {
    private UserDaoService service;
    private UserRepository repository;
    private PostRepository postRepository;
    public UserJpaController(UserDaoService service, UserRepository repository,PostRepository postRepository) {
        this.service = service;
        this.repository = repository;
        this.postRepository = postRepository;
    }
    @GetMapping(path="/jpa/users")
    public List<User> retrieveAll(){
        return repository.findAll();
    }
    @GetMapping(path="/jpa/users/{id}")
    public EntityModel<User> retrieveUser(@PathVariable int id) {
        User user= repository.findById(id).orElse(null);
        if(user==null){
            throw new UserNotFoundException("id:"+id);
        }
        EntityModel <User> model = EntityModel.of(user);
        WebMvcLinkBuilder link=linkTo(methodOn(this.getClass()).retrieveAll());
        model.add(link.withRel("all-users"));
        return model;
    }
    @DeleteMapping(path="/jpa/users/{id}")
    public void DeleteUser(@PathVariable int id) {
        repository.deleteById(id);
    }
    @GetMapping(path="/jpa/users/{id}/post")
    public List<Post> RetrievePostUser(@PathVariable int id) {
        Optional<User> user= repository.findById(id);
        if(user.isEmpty()){
            throw new UserNotFoundException("id:"+id);
        }
       return user.get().getPosts();

    }
    @PostMapping(path="/jpa/users")
    public ResponseEntity<User> createUser(@Valid @RequestBody User user) {
        User SavedUser=repository.save(user);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(SavedUser.getId()).toUri();
        return ResponseEntity.created(location).build();
    }
    @PostMapping(path="/jpa/users/{id}/post")
    public ResponseEntity<Object> createPostUser(@PathVariable int id,@Valid @RequestBody Post post) {
        User user= repository.findById(id).orElse(null);
        if(user==null){
            throw new UserNotFoundException("id:"+id);
        }
        post.setUser(user);
        Post saved=postRepository.save(post);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(saved.getId()).toUri();
        return ResponseEntity.created(location).build();

    }

}
