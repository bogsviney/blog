package com.nazarov.blog.service;

import com.nazarov.blog.entity.Comment;
import com.nazarov.blog.entity.Message;
import com.nazarov.blog.repository.CommentRepository;
import com.nazarov.blog.repository.MessageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;


@Service
public class CommentService {

    @Autowired
    private final CommentRepository commentRepository;
    @Autowired
    private final MessageRepository messageRepository;

    public CommentService(CommentRepository commentRepository, MessageRepository messageRepository) {
        this.commentRepository = commentRepository;
        this.messageRepository = messageRepository;
    }



}
