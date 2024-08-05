package com.example.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.entity.Account;
import com.example.entity.Message;
import com.example.service.AccountService;
import com.example.service.MessageService;

import java.util.Optional;
import java.util.List;

/**
 * TODO: You will need to write your own endpoints and handlers for your controller using Spring. The endpoints you will need can be
 * found in readme.md as well as the test cases. You be required to use the @GET/POST/PUT/DELETE/etc Mapping annotations
 * where applicable as well as the @ResponseBody and @PathVariable annotations. You should
 * refer to prior mini-project labs and lecture materials for guidance on how a controller may be built.
 */
@RestController
public class SocialMediaController {
    
    private AccountService accountService;
    private MessageService messageService;

    @Autowired
    public SocialMediaController (AccountService accountService, MessageService messageService) {
        this.accountService = accountService;
        this.messageService = messageService;
    }

    @PostMapping("/register")
    public ResponseEntity<Account> register (@RequestBody Account newAccount){
        return new ResponseEntity<>(accountService.register(newAccount), HttpStatus.OK); 
    }

    @PostMapping("/login")
    public ResponseEntity<Account> login (@RequestBody Account loginAccount) {
        return new ResponseEntity<>(accountService.login(loginAccount), HttpStatus.OK);
    }

    @PostMapping("/messages")
    public ResponseEntity<Message> createMessage (@RequestBody Message newMessage) {
        Optional<Account> check = accountService.findAccountById(newMessage.getPostedBy());
        if (check.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(messageService.createMessage(newMessage), HttpStatus.OK);
    }
    
    @GetMapping("/messages") 
    public ResponseEntity<List<Message>> getAllMessages(){
        return new ResponseEntity<>(messageService.findAllMessages(), HttpStatus.OK);
    }

    @GetMapping("/messages/{messageId}")
    public ResponseEntity<Message> findMessageById (@PathVariable int messageId) {
        Message check = messageService.findMessageById(messageId);
        return new ResponseEntity<>(check, HttpStatus.OK);
    }

    @DeleteMapping("/messages/{messageId}")
    public ResponseEntity<Integer> deleteMessageById (@PathVariable int messageId) {
        Integer check = messageService.deleteMessageById(messageId);
        if (check == 1) {
            return new ResponseEntity<>(check, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PatchMapping("/messages/{messageId}")
    public ResponseEntity<Integer> updateMessage (@PathVariable int messageId, @RequestBody Message messageText) {
        Integer check = messageService.updateMessage(messageId, messageText);
        if (check == 1) {
            return new ResponseEntity<>(check, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        
    }

    @GetMapping("/accounts/{accountId}/messages") 
    public ResponseEntity<List<Message>> getMessagesByAccount(@PathVariable int accountId){
        return new ResponseEntity<>(messageService.findMessagesByAccount(accountId), HttpStatus.OK);
    }

}
