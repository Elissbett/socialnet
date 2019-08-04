package com.ebolotina.socialnet.controller;

import com.ebolotina.socialnet.model.Message;
import com.ebolotina.socialnet.model.User;
import com.ebolotina.socialnet.repository.MessageRepository;
import com.ebolotina.socialnet.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

import static com.ebolotina.socialnet.model.Message.message;

@RestController
public class MessageController {

    @Autowired
    private MessageRepository messageRepository;

    @Autowired
    private UserRepository userRepository;

    @GetMapping("/users/{userId}/messages")
    public List<Message> getMessages(@PathVariable Long userId, @RequestParam Long friendId) {
        return messageRepository.getDialog(userId, friendId);
    }

    //TODO: transaction
    //TODO: rethink rest
    //TODO: message only to friend, black list, policies (?)
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/new-message")
    public MessageRequest saveMessage(@RequestBody MessageRequest message) {
        User owner = userRepository.getOne(message.owner);
        User party = userRepository.getOne(message.party);
        messageRepository.save(message().withText(message.text).withOwner(owner).withParty(party)
                .withCreatedDate(new Date(System.currentTimeMillis())).withOutgoing(true).build());
        messageRepository.save(message().withText(message.text).withOwner(party).withParty(owner)
                .withCreatedDate(new Date(System.currentTimeMillis())).withOutgoing(false).build());
        return message;
    }

    public static class MessageRequest {
        public String text;
        public Long owner;
        public Long party;
        public Boolean outgoing;
    }
}
