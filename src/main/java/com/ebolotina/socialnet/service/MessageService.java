package com.ebolotina.socialnet.service;

import com.ebolotina.socialnet.controller.MessageController;
import com.ebolotina.socialnet.model.Message;
import com.ebolotina.socialnet.model.User;
import com.ebolotina.socialnet.repository.MessageRepository;
import com.ebolotina.socialnet.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

import static com.ebolotina.socialnet.model.Message.message;

@Service
public class MessageService {

    @Autowired
    private MessageRepository messageRepository;

    @Autowired
    private UserRepository userRepository;

    public MessageService(MessageRepository m, UserRepository u) {
        messageRepository = m;
        userRepository = u;
    }

    @Transactional
    public Message createMessage(MessageController.MessageRequest message) {
        User owner = userRepository.getOne(message.owner);
        User party = userRepository.getOne(message.party);
        Message resultMessage = message().withText(message.text).withOwner(owner).withParty(party)
                .withCreatedDate(new Date(System.currentTimeMillis())).withOutgoing(true).build();
        messageRepository.save(resultMessage);
        messageRepository.save(message().withText(message.text).withOwner(party).withParty(owner)
                .withCreatedDate(new Date(System.currentTimeMillis())).withOutgoing(false).build());
        return resultMessage;
    }

    @Transactional
    public Message createMessage(User owner, User party, String text) {
        Message resultMessage = message().withText(text).withOwner(owner).withParty(party)
                .withCreatedDate(new Date(System.currentTimeMillis())).withOutgoing(true).build();
        messageRepository.save(resultMessage);
        messageRepository.save(message().withText(text).withOwner(party).withParty(owner)
                .withCreatedDate(new Date(System.currentTimeMillis())).withOutgoing(false).build());
        return resultMessage;
    }

}
