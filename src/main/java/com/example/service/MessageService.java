package com.example.service;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
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

    public Integer deleteMessageById(int id) {
        Optional<Message> oMessage = messageRepository.findById(id);
        if (oMessage.isPresent()) {
            messageRepository.deleteById(id);
            return 1;
        } 
        return 0;
    }

    public Integer updateMessage(int messageId, Message messageText) {
        Optional<Message> oMessage = messageRepository.findById(messageId);
        if (oMessage.isPresent() && messageText.getMessageText().trim().length() > 0 && messageText.getMessageText().length() < 255) {
            Message message = oMessage.get();
            message.setMessageText(messageText.getMessageText());
            messageRepository.save(message);
            return 1;
        } else {
            return 0;
        }
    }

    public List<Message> findMessagesByAccount(int accountId) {
        return messageRepository.findAllMessagesByPostedBy(accountId);
    }
}
