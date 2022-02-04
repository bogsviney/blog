package com.nazarov.blog.controller;

import com.nazarov.blog.entity.Tag;
import com.nazarov.blog.service.TagService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/tags")
@Slf4j
public class TagController {

    @Autowired
    private final TagService tagService;

    public TagController(TagService tagService) {
        this.tagService = tagService;
    }

    @GetMapping
    public List<Tag> findAll() {
        log.info("SHOW ALL TAGS: DONE");
        return tagService.findAll();
    }

    @PostMapping
    private void createTag(@RequestBody Tag tag) {
        log.info("TAG " + tag.getName() + " HAS BEEN CREATED");
        tagService.createTag(tag);
    }

    @DeleteMapping("{id}")
    public void deleteTag(@PathVariable long id) {
        tagService.deleteTag(id);
        log.info("TAG WITH ID: " + id + " HAS BEEN DELETED");
    }


}