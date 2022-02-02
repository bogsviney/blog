package com.nazarov.blog.repository;

import com.nazarov.blog.entity.Message;
import com.nazarov.blog.entity.Tag;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.transaction.Transactional;
import java.util.List;

@SpringBootTest
class TagRepositoryTest {

    @Autowired
    private TagRepository tagRepository;
    @Autowired
    private MessageRepository messageRepository;

    @Test
    public void saveTag(){
        Tag tagOne = Tag.builder()
                .name("#FUNNY")
                .build();
        tagRepository.save(tagOne);

        Tag tagTwo = Tag.builder()
                .name("#NEWS")
                .build();
        tagRepository.save(tagTwo);

        Tag tagThree = Tag.builder()
                .name("#IT")
                .build();
        tagRepository.save(tagThree);
    }

    @Test
    public void printAllTags(){
        List<Tag> tags = tagRepository.findAll();
        System.out.println("Here is tags: " + tags);
    }


 }