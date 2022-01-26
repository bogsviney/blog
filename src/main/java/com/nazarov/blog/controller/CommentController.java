package com.nazarov.blog.controller;

import com.nazarov.blog.entity.Comment;
import com.nazarov.blog.entity.Message;
import com.nazarov.blog.repository.CommentRepository;
import com.nazarov.blog.service.CommentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/posts")
@Slf4j
public class CommentController {

    //    @Autowired
//    private final CommentRepository commentRepository;
    @Autowired
    private final CommentService commentService;

    public CommentController(CommentRepository commentRepository, CommentService commentService) {
//        this.commentRepository = commentRepository;
        this.commentService = commentService;
    }

    @PostMapping("{id}/comments")
    public Comment create(@RequestBody Comment comment, @PathVariable long messageId) {
        log.info("COMMENT HAS BEEN ADDED");
        Comment savedComment = commentService.create(comment, messageId);

        return savedComment;
    }


}
