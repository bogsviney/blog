package com.nazarov.blog.service;

import com.nazarov.blog.entity.Message;
import com.nazarov.blog.repository.MessageRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MessageService {

    private final MessageRepository messageRepository;

    public MessageService(MessageRepository messageRepository) {
        this.messageRepository = messageRepository;
    }

    public List<Message> findAll() {
        return messageRepository.findAll();
    }

    public Message getById(long id) {
        return messageRepository.getById(id);
    }

    public List<Message> findByTitle(String title) {
        return messageRepository.findByTitle(title);
    }

    public void addMessage(Message message) {
        messageRepository.save(message);
    }

    public void update(String newContent, long id) {
        messageRepository.updateContentById(newContent, id);
    }

    public void deleteByMessageId(long messageId) {
        messageRepository.deleteByMessageId(messageId);
    }

    public List<Message> findAllTopMessages() {
        return messageRepository.findAllTopMessages();
    }

    public void addStar(long id) {
        messageRepository.addStar(id);
    }

    public void deleteStar(long id) {
        messageRepository.deleteStar(id);
    }


}
