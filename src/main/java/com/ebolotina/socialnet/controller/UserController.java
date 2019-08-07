package com.ebolotina.socialnet.controller;

import com.ebolotina.socialnet.model.User;
import com.ebolotina.socialnet.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Set;


@RestController
public class UserController {

    //TODO: what's is autowired
    @Autowired
    private UserRepository repository;

    //TODO: models for rest
    @GetMapping("/users")
    public List<User> findAll() {
        return repository.findAll();
    }

    @GetMapping("/user/{id}")
    public User findUser(@PathVariable Long id) {
        User user = repository.getOne(id);
        return user;
    }

    @GetMapping("/users/{id}/friends")
    public Set<User> getFriends(@PathVariable Long id) {
        return repository.getOne(id).getFriends();
    }


}
