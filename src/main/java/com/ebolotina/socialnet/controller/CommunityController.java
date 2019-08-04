package com.ebolotina.socialnet.controller;

import com.ebolotina.socialnet.model.Community;
import com.ebolotina.socialnet.model.User;
import com.ebolotina.socialnet.repository.CommunityRepository;
import com.ebolotina.socialnet.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class CommunityController {

    @Autowired
    private CommunityRepository communityRepository;

    @Autowired
    private UserRepository userRepository;

    @GetMapping("/communities/{communityId}/")
    public Community getCommunity(@PathVariable Long communityId) {
        return communityRepository.getOne(communityId);
    }

    @GetMapping("/users/{userId}/communities")
    public List<Community> getCommunitiesByUser(@PathVariable Long userId) {
//        User user = userRepository.getOne(userId);
        return communityRepository.findByUser(userId);
    }
}
