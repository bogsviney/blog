package com.nazarov.blog.repository;

import com.nazarov.blog.entity.Comment;
import com.nazarov.blog.entity.Message;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;
import java.util.List;

@SpringBootTest
class CommentRepositoryTest {

    @Autowired
    CommentRepository commentRepository;

    @Autowired
    MessageRepository messageRepository;

    @Test
    public void saveComment() {
        Comment comment = Comment.builder()
                .text("WOW! THIS IS NEW COMMENT LOL)")
                .message(messageRepository.getById(28L))
                .creationDate(LocalDateTime.now())
                .build();
        commentRepository.save(comment);
    }

}