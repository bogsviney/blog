package com.nazarov.blog.controller;


import com.nazarov.blog.entity.Message;
import com.nazarov.blog.repository.MessageRepository;
import com.nazarov.blog.service.MessageService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.crossstore.ChangeSetPersister;
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
    private List<Message> findAll() {
        log.info("SHOW ALL MESSAGES: DONE");
        return messageService.findAll();
    }

    @GetMapping("{id}")
    public Message findById(@PathVariable int id) {
        return messageService.findById(id);
    }

    @PostMapping
    public void addMessage(@RequestBody Message message) {
        messageService.addMessage(message);
        log.info("MESSAGE HAS BEEN ADDED");
    }


    @PutMapping("api/v1/posts/{id}")
    public void editMessage(@PathVariable int messageId, @RequestBody String newContent) {
        messageService.update(newContent, messageId);
        log.info("MESSAGE HAS BEEN UPDATED");
    }

    @DeleteMapping("api/v1/posts/{id}")
    public void deleteMessage(@PathVariable("id") int messageId) {
        messageService.delete(messageId);
        log.info("MESSAGE HAS BEEN DELETED");
    }


//    поиск постов с тайтлом :title
//    @GetMapping("api/v1/posts?title=:title")
//    public void findByTitle(String title){
//        messageService.findByTitle(title);
//        log.info("WE FIND MESSAGE BY TITLE: " + title);
//    }


//    возвращаем все посты отсортированные по тайтлу
//    @GetMapping("posts?sort=title")


}
