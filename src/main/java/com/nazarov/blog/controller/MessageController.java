package com.nazarov.blog.controller;

import com.nazarov.blog.entity.Message;
import com.nazarov.blog.entity.Tag;
import com.nazarov.blog.repository.MessageRepository;
import com.nazarov.blog.repository.TagRepository;
import com.nazarov.blog.service.MessageService;
import com.nazarov.blog.service.TagService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("api/v1/posts")
@Slf4j
public class MessageController {

    @Autowired
    private final MessageService messageService;
    @Autowired
    MessageRepository messageRepository;
    @Autowired
    private final TagRepository tagRepository;
    @Autowired
    private final TagService tagService;

    public MessageController(MessageRepository messageRepository, MessageService messageService, TagRepository tagRepository, TagService tagService) {
        this.messageService = messageService;
        this.tagRepository = tagRepository;
        this.tagService = tagService;
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

    @GetMapping(params = {"tag"})
    private List<Message> findMessagesByTagName(String tag) {
        Tag targetTag = tagRepository.findByName(tag);
        log.info("SHOW BY TAG: " + tag + " DONE");
        return targetTag.getMessages();
    }

    @GetMapping(path = "tags")
    private List<Message> findMessagesByFewTagsName(@RequestParam Map<String, String> tagNames) {
        String tagOne = tagNames.get("tag1");
        String tagTwo = tagNames.get("tag2");
        Tag targetTagOne = tagRepository.findByName(tagOne);
        List<Message> result = targetTagOne.getMessages();
        Tag targetTagTwo = tagRepository.findByName(tagTwo);
        result.addAll(targetTagTwo.getMessages());
        log.info("SHOW MESSAGES BY TAGS: " + tagOne + "  and  " + tagTwo + " DONE");
        return result;
    }


    @GetMapping("{id}")
    public Message getById(@PathVariable long id) {
        log.info("HERE IS THE MESSAGE WITH ID: " + id);
        return messageService.getById(id);
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
    public void editMessage(@PathVariable long id, @RequestBody String newContent) {
        messageService.update(newContent, id);
        log.info("MESSAGE HAS BEEN UPDATED");
    }

    @PutMapping("{id}/star")
    public void addStar(@PathVariable long id) {
        log.info("STAR added to post with ID " + id);
        messageService.addStar(id);
    }

    @PutMapping("{id}/{tagName}")
    public void addTagToMessage(@PathVariable long id, @PathVariable String tagName) {
        Tag tagToAdd = tagRepository.findByName(tagName);
        Message message = messageService.getById(id);
        if (tagToAdd == null) {
            tagToAdd = Tag.builder()
                    .name(tagName)
                    .build();
            tagService.createTag(tagToAdd);
            message.getTags().add(tagToAdd);
            messageRepository.save(message);
        } else {
            message.getTags().add(tagToAdd);
            tagToAdd.getMessages().add(message);
            messageRepository.save(message);
        }
        log.info("TAG " + tagName + " added to post with ID " + id);
    }

    @DeleteMapping("{id}/{tagName}")
    public void deleteTagFromPost(@PathVariable long id, @PathVariable String tagName) {
        Tag tagToRemove = tagRepository.findByName(tagName);
        Message message = messageService.getById(id);
        message.getTags().remove(tagToRemove);
        tagToRemove.getMessages().remove(message);
        messageRepository.save(message);
        tagRepository.save(tagToRemove);
        log.info("TAG " + tagName + " deleted from post with ID " + id);
    }

    @DeleteMapping("{id}")
    public void deleteMessage(@PathVariable long id) {
        messageService.deleteByMessageId(id);
        log.info("MESSAGE HAS BEEN DELETED");
    }

    @DeleteMapping("{id}/star")
    public void deleteStar(@PathVariable long id) {
        messageService.deleteStar(id);
        log.info("STAR has been deleted from the post with ID " + id);
    }

//    @GetMapping("{id}/full") //WHAT IS THIS?
//    public Message showFullMessage(@PathVariable long id){
//        return messageService.getById(id);
//    }


}
