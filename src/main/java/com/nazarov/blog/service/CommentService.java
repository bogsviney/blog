package com.nazarov.blog.service;

import com.nazarov.blog.entity.Comment;
import com.nazarov.blog.entity.Message;
import com.nazarov.blog.repository.CommentRepository;
import com.nazarov.blog.repository.MessageRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Slf4j
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

    public Comment create(Comment comment, long messageId) {
        Message message = messageRepository.getById(messageId);
        comment.setMessage(message);
        commentRepository.save(comment);
        log.info("COMMENT SAVED");
        return comment;
    }

    public List<Comment> findAllCommentsByMessageId(long messageId) {
        Message message = messageRepository.getById(messageId);
        log.info("HERE IS ALL COMMENTS FOR MESSAGE WITH ID: " + messageId);
        return message.getComments();
    }


}
