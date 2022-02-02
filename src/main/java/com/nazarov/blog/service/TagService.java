package com.nazarov.blog.service;

import com.nazarov.blog.entity.Tag;
import com.nazarov.blog.repository.TagRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TagService {

    private final TagRepository tagRepository;

    public TagService(TagRepository tagRepository) {
        this.tagRepository = tagRepository;
    }

    public List<Tag> findAll() {
        return tagRepository.findAll();
    }

    public void addTag(Tag tag) {
        tagRepository.save(tag);
    }

    public void deleteTag(long tagId) {
        tagRepository.deleteById(tagId);
    }
}
