package com.harshmahajan.Rest.user;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Size;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity(name="user_details")
public class User {
    @Id
    private Integer id;

    public List<Post> getPosts() {
        return posts;
    }

    public void setPosts(List<Post> posts) {
        this.posts = posts;
    }

    @Size(min = 2, max = 20,message="name should have more than 2 characters and less than 20 characters")
    private String name;
    @Past
    private LocalDate birthdate;
    @OneToMany(mappedBy = "user")
    @JsonIgnore
    private List<Post> posts;
    public User(Integer id, String name, LocalDate birthdate) {
        this.id = id;
        this.name = name;
        this.birthdate = birthdate;
    }

    public User() {

    }


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setBirthdate(LocalDate birthdate) {
        this.birthdate = birthdate;
    }

    public String getName() {
        return name;
    }

    public LocalDate getBirthdate() {
        return birthdate;
    }
}
