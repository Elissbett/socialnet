package com.ebolotina.socialnet.controller;

import com.ebolotina.socialnet.model.User;
import com.ebolotina.socialnet.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Set;


@RestController
public class UserController {

    //TODO: what's is autowired
    @Autowired
    private UserRepository repository;

    //TODO: models for rest
    @GetMapping("/users")
    List<User> findAll() {
        return repository.findAll();
    }

    @GetMapping("/users/{id}/friends")
    Set<User> getFriends(@PathVariable Long id, HttpServletResponse res) {
        return repository.getOne(id).getFriends();
    }


}
