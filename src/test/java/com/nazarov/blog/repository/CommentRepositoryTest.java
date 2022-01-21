package com.nazarov.blog.repository;

import com.nazarov.blog.entity.Comment;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.sql.Timestamp;
import java.time.LocalDateTime;


@SpringBootTest
class CommentRepositoryTest {

    @Autowired
    CommentRepository commentRepository;


    @Test
    public void saveComment(){
        Comment comment = Comment.builder()
                .text("!!!THIS IS A NEW COMMENT!!!")
                .build();
        commentRepository.save(comment);
    }

}