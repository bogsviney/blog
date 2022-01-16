package com.nazarov.blog.controller;


import com.nazarov.blog.entity.Message;
import com.nazarov.blog.repository.MessageRepository;
import com.nazarov.blog.service.MessageService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("api/v1/posts")
@Slf4j
public class MessageController {

    private final MessageRepository messageRepository;
    private final MessageService messageService;

    public MessageController(MessageRepository messageRepository, MessageService messageService) {
        this.messageRepository = messageRepository;
        this.messageService = messageService;
    }

    @GetMapping
    public List<Message> findAll() {
        log.info("SHOW ALL MESSAGES: DONE");
        return messageService.findAll();
    }

    @PostMapping
    public void addMessage(@RequestBody Message message) {
        messageService.addMessage(message);
        log.info("MESSAGE HAS BEEN ADDED");
    }


    @PutMapping("/{id}")
    public void editMessage(@PathVariable int messageId, @RequestBody String newContent) {
        messageService.update(newContent,messageId);
        log.info("MESSAGE HAS BEEN UPDATED");
    }

    @DeleteMapping("/{id}")
    public void deleteMessage(@RequestParam(value="id") int messageId){
        messageService.delete(messageId);
        log.info("MESSAGE HAS BEEN DELETED");
    }


}
