package com.nazarov.blog.repository;

import com.nazarov.blog.entity.Message;
import com.nazarov.blog.entity.Tag;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.transaction.Transactional;
import java.util.List;

@SpringBootTest
class MessageRepositoryTest {

    @Autowired
    private MessageRepository messageRepository;
    @Autowired
    private TagRepository tagRepository;

    @Test
    @Transactional
    public void addTagToMessage() {
        Tag tagOne = tagRepository.getById(1L);
        Tag tagTwo = tagRepository.getById(2L);

        Message message = messageRepository.getById(30L);

        message.getTags().add(tagOne);
        message.getTags().add(tagTwo);

        tagOne.getMessages().add(message);
        tagTwo.getMessages().add(message);

        messageRepository.save(message);
    }

    @Test
    public void saveMessage() {
        Message messageOne = Message.builder()
                .title("SCIENCE")
                .content("British scientists: cannibalism will save the planet")
                .star(true)
                .build();
        messageRepository.save(messageOne);

        Message messageTwo = Message.builder()
                .title("NEWS")
                .content("UFO landed near Zhytomyr")
                .star(true)
                .build();
        messageRepository.save(messageTwo);

        Message messageThree = Message.builder()
                .title("MONEY")
                .content("Bitcoin is fake")
                .star(false)
                .build();
        messageRepository.save(messageThree);
    }

    @Test
    public void printAllMessages() {
        List<Message> messageList = messageRepository.findAll();
        System.out.println("ALL MESSAGES HERE: = " + messageList);
    }

    @Test
    public void printMessagesByTitle() {
        List<Message> messages = messageRepository.findByTitle("NEWS");
        System.out.println("WE FIND THIS: " + messages);
    }

    @Test
    public void printMessagesById() {
        messageRepository.findById(6L);
        System.out.println("HERE IS THE MESSAGE BY ID = ");
    }

    @Test
    public void printMessageByTitleContaining() {
        List<Message> messages = messageRepository.findByTitleContaining("SCI");
        System.out.println("LOOOK HERE! ---> " + messages);
    }

    @Test
    public void updateContentByTitle() {
        messageRepository.updateContentByTitle(
                "British scientists: cannibalism will save the planet?",
                "SCIENCE"
        );
    }

    @Test
    public void updateContentById() {
        messageRepository.updateContentById(
                "UPDATED CONTENT!",
                25L
        );
    }

    @Test
    public void deletingMessageByTitle() {
        messageRepository.deleteByMessageTitle("JAVA");
    }

    @Test
    public void deleteMessageById() {
        messageRepository.deleteByMessageId(18L);
    }
}