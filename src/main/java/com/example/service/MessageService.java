package com.example.service;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.example.entity.Message;
import com.example.exception.InvalidMessageException;
import com.example.repository.MessageRepository;

@Service
public class MessageService {
    private MessageRepository messageRepository;

    @Autowired
    public MessageService(MessageRepository messageRepository) {
        this.messageRepository = messageRepository;
    }

    public Message createMessage(Message newMessage) {
        if (newMessage.getMessageText().trim().length() <= 0) {
            throw new InvalidMessageException("Message cannot be left blank!");
        }
        if (newMessage.getMessageText().length() > 255) {
            throw new InvalidMessageException("Message cannot be over 255 characters");
        }
        return messageRepository.save(newMessage);
    }

    public List<Message> findAllMessages() {
        return messageRepository.findAll();
    }

    public Message findMessageById(int id) {
        Optional<Message> oMessage = messageRepository.findById(id);
        if (oMessage.isPresent()) {
            return oMessage.get();
        } 
        return null;
    }
}
