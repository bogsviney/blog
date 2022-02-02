package com.nazarov.blog.controller;

import com.nazarov.blog.entity.Message;
import com.nazarov.blog.entity.Tag;
import com.nazarov.blog.service.TagService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/posts")
@Slf4j
public class TagController {

    @Autowired
    private final TagService tagService;

    public TagController(TagService tagService) {
        this.tagService = tagService;
    }

    @GetMapping("tags")                            //какая ссылка?
    public List<Tag> findAll(){
        log.info("SHOW ALL TAGS: DONE");
        return tagService.findAll();
    }


}