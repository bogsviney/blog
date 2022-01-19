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
    private List<Message> findAll() {
        log.info("SHOW ALL MESSAGES: DONE");
        return messageService.findAll();
    }

    @GetMapping(params = {"title"})
    private List<Message> findAllByTitle(String title) {
        log.info("SHOW BY TITLE: DONE");
        return messageService.findByTitle(title);
    }

    @GetMapping("{id}")
    public Message findById(@PathVariable int id) {
        log.info("HERE IS THE MESSAGE WITH ID: " + id);
        return messageService.findById(id);
    }

    @GetMapping("star")
    public List<Message> findAllTopMessages() {
        log.info("HERE IS ALL TOP RATED MESSAGES");
        return messageService.findAllTopMessages();
    }

    @PostMapping
    public void addMessage(@RequestBody Message message) {
        messageService.addMessage(message);
        log.info("MESSAGE HAS BEEN ADDED");
    }

    @PutMapping("{id}")
    public void editMessage(@PathVariable int id, @RequestBody String newContent) {
        messageService.update(newContent, id);
        log.info("MESSAGE HAS BEEN UPDATED");
    }

    @PutMapping("{id}/star")
    public void addStar(@PathVariable int id) {
        log.info("STAR added to post with ID " + id);
        messageService.addStar(id);
    }

    @DeleteMapping("{id}")
    public void deleteMessage(@PathVariable int id) {
        messageService.deleteByMessageId(id);
        log.info("MESSAGE HAS BEEN DELETED");
    }

    @DeleteMapping("{id}/star")
    public void deleteStar(@PathVariable int id) {
        messageService.deleteStar(id);
        log.info("STAR has been deleted from the post with ID " + id);
    }

}
