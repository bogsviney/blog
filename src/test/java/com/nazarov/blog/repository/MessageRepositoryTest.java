package com.nazarov.blog.repository;

import com.nazarov.blog.entity.Message;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
class MessageRepositoryTest {

    @Autowired
    private MessageRepository messageRepository;


    @Test
    public void saveMessage() {
        Message message = Message.builder()
                .title("Fireman")
                .content("Совы не то, чем они кажутся")
                .build();
        messageRepository.save(message);
    }

    @Test
    public void printAllMessages() {
        List<Message> messageList = messageRepository.findAll();
        System.out.println("ALL MESSAGES HERE: = " + messageList);
    }

    @Test
    public void printMessagesByTitle() {
        Message message = messageRepository.findByTitle("Fireman");
        System.out.println("WE FIND THIS: " + message);
    }

    @Test
    public void printMessageByTitleContaining() {
        List<Message> messages = messageRepository.findByTitleContaining("Owl");
        System.out.println("LOOOK HERE! ---> " + messages);
    }

    @Test
    public void updateContentByTitle() {
        messageRepository.updateContentByTitle(
                "AAAAAAAAAAAAAAAAAA",
                "Fireman says "
        );
    }

    @Test
    public void updateContentById() {
        messageRepository.updateContentById(
                "AAAAAAAAAAAAAAAAAA",
                6
        );
    }

    @Test
    public void deletingMessageByTitle(){
        messageRepository.deleteByMessageTitle("DolceKabana");

    }

    @Test
    public void deleteMessageById(){
        messageRepository.deleteByMessageId(7);
    }


}