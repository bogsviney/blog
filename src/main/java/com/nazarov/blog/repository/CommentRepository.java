package com.nazarov.blog.repository;

import com.nazarov.blog.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comment, Long> {

//    List<Comment> findAllCommentsByMessageId(long id);



}
