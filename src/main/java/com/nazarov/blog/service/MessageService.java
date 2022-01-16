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

    public void addMessage(Message message) {
        messageRepository.save(message);
    }

    public void update(String newContent, int messageId) {
        messageRepository.updateContentById(newContent, messageId);
    }

    public void delete(int messageId){
        messageRepository.deleteByMessageId(messageId);
    }

}
