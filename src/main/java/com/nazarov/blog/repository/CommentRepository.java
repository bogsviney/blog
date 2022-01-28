package com.nazarov.blog.repository;

import com.nazarov.blog.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface CommentRepository extends JpaRepository<Comment, Long> {

    @Query("select u from Comment u where message_id = ?1 and comment_id = ?2")
    Comment getOneCommentToPost(long messageId, long commentId);

}
