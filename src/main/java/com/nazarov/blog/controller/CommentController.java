package com.nazarov.blog.controller;

import com.nazarov.blog.entity.Comment;
import com.nazarov.blog.entity.Message;
import com.nazarov.blog.repository.CommentRepository;
import com.nazarov.blog.service.CommentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("{id}/comments")
@Slf4j
public class CommentController {

    @Autowired
    private final CommentRepository commentRepository;
    @Autowired
    private final CommentService commentService;

    public CommentController(CommentRepository commentRepository, CommentService commentService) {
        this.commentRepository = commentRepository;
        this.commentService = commentService;
    }
//
//    @PostMapping
//    public void addComment(@RequestBody Comment comment, @RequestParam Long messageId) {
//        commentService.addComment(comment, messageId);
//        log.info("COMMENT HAS BEEN ADDED");
//    }


}
