package com.nazarov.blog.repository;

import com.nazarov.blog.entity.Message;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
public interface MessageRepository extends JpaRepository<Message, Long> {

    List<Message> findByTitleContaining(String title);

    @Query("select u from Message u where u.title = ?1")
    List<Message> findByTitle(String title);

    @Query("select u from Message u where message_id = ?1")
    Message findById(int id);

    @Modifying
    @Transactional
    @Query(
            value = "update tbl_message set content = ?1 where message_id = ?2",
            nativeQuery = true
    )
    int updateContentById(String newContent,int id);

    @Modifying
    @Transactional
    @Query(
            value = "update tbl_message set content = ?1 where title= ?2",
            nativeQuery = true
    )
    int updateContentByTitle(String newContent,String title);

    @Modifying
    @Transactional
    @Query(
            value = "delete from tbl_message where title = ?1",
            nativeQuery = true
    )
    void deleteByMessageTitle(String title);

    @Modifying
    @Transactional
    @Query(
            value = "delete from tbl_message where message_id = ?1",
            nativeQuery = true
    )
    void deleteByMessageId(int id);
}
