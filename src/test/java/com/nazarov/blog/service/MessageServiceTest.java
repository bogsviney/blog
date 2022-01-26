package com.nazarov.blog.service;

import com.nazarov.blog.entity.Message;
import com.nazarov.blog.repository.MessageRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class MessageServiceTest {

    @Mock
    private MessageRepository messageRepository;

    @Test
    void testFindAll() {
        MessageService messageService = new MessageService(messageRepository);

        List<Message> messages = new ArrayList<>();

        Message messageOne = Message.builder()
                .messageId(1L)
                .title("Hello Blog!")
                .content("Check-check! This is a test of our blog")
                .star(false)
                .build();
        messages.add(messageOne);

        Message messageTwo = Message.builder()
                .messageId(2L)
                .title("Breaking News")
                .content("Allien UFO landed near Zhytomyr")
                .star(true)
                .build();
        messages.add(messageTwo);

        Message messageThree = Message.builder()
                .messageId(3L)
                .title("Cooking")
                .content("Today i will show you how to make a really excellent lasagna")
                .star(true)
                .build();
        messages.add(messageThree);

        Mockito.when(messageRepository.findAll()).thenReturn(messages);

        List<Message> actual = messageService.findAll();
        assertNotNull(actual);
        assertEquals(3, actual.size());
        assertEquals("Today i will show you how to make a really excellent lasagna", actual.get(2).getContent());
        assertSame(messageOne, actual.get(0));
    }

    @Test
    void testGetById() {
        Message message = Message.builder()
                .messageId(7L)
                .title("Breaking News")
                .content("Allien UFO landed near Zhytomyr")
                .star(true)
                .build();

        Mockito.when(messageRepository.getById(7L)).thenReturn(message);

        Message actual = messageRepository.getById(7L);

        assertEquals("Breaking News", actual.getTitle());
        assertEquals("Allien UFO landed near Zhytomyr", actual.getContent());
        assertTrue(actual.isStar());
    }


    @Test
    void testFindByTitle() {
        List<Message> messages = new ArrayList<>();

        Message message = Message.builder()
                .messageId(1L)
                .title("Hello Blog!")
                .content("Check-check! This is a test of our blog")
                .star(false)
                .build();

        messages.add(message);

        Mockito.when(messageRepository.findByTitle("Hello Blog!")).thenReturn(messages);

        List<Message> actual = messageRepository.findByTitle("Hello Blog!");

        assertEquals("Check-check! This is a test of our blog", actual.get(0).getContent());
        assertEquals(1L, actual.get(0).getMessageId());
    }


    @Test
    void testFindAllTopMessages() {
        List<Message> messages = new ArrayList<>();

        Message messageTwo = Message.builder()
                .messageId(2L)
                .title("Breaking News")
                .content("Allien UFO landed near Zhytomyr")
                .star(true)
                .build();
        messages.add(messageTwo);

        Message messageThree = Message.builder()
                .messageId(3L)
                .title("Cooking")
                .content("Today i will show you how to make a really excellent lasagna")
                .star(true)
                .build();
        messages.add(messageThree);

        Mockito.when(messageRepository.findAllTopMessages()).thenReturn(messages);

        List<Message> actual = messageRepository.findAllTopMessages();

        assertEquals(2, actual.size());
        assertEquals("Cooking", actual.get(1).getTitle());
        assertEquals("Allien UFO landed near Zhytomyr", actual.get(0).getContent());
        assertSame(messageTwo, actual.get(0));
    }
}