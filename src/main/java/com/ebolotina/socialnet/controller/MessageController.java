package com.ebolotina.socialnet.controller;

import com.ebolotina.socialnet.model.Message;
import com.ebolotina.socialnet.model.User;
import com.ebolotina.socialnet.repository.MessageRepository;
import com.ebolotina.socialnet.repository.UserRepository;
import com.ebolotina.socialnet.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import java.util.Date;
import java.util.List;

import static com.ebolotina.socialnet.model.Message.message;

@RestController
public class MessageController {

    @Autowired
    private MessageRepository messageRepository;

    @Autowired
    private MessageService messageService;

    @GetMapping("/users/{userId}/messages")
    public List<Message> getMessages(@PathVariable Long userId, @RequestParam Long friendId) {

        return messageRepository.getDialog(userId, friendId);
    }

    //TODO: transaction
    //TODO: rethink rest
    //TODO: message only to friend, black list, policies (?)
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/new-message")
    public Message saveMessage(@RequestBody MessageRequest message) {
        return messageService.createMessage(message);
    }

    public static class MessageRequest {
        public String text;
        public Long owner;
        public Long party;
        public Boolean outgoing;
    }
}
