package com.naukma.introductionspringproject.service.impl;

import com.naukma.introductionspringproject.entity.TagEntity;
import com.naukma.introductionspringproject.exception.NotFoundException;
import com.naukma.introductionspringproject.model.Tag;
import com.naukma.introductionspringproject.repository.TagRepo;
import com.naukma.introductionspringproject.service.TagService;
import org.apache.logging.log4j.*;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TagServiceImpl implements TagService {
    private final ModelMapper modelMapper;

    private final TagRepo tagRepo;

    @Autowired
    public TagServiceImpl(ModelMapper modelMapper, TagRepo tagRepo) {
        this.modelMapper = modelMapper;
        this.tagRepo = tagRepo;
    }

    @Override
    public Tag createTag(Tag tag) {
        tagRepo.save(modelMapper.map(tag, TagEntity.class));
        return tag;
    }

    @Override
    public Tag readTag(Long id) {
        return modelMapper.map(tagRepo.findById(id).orElseThrow(() -> new NotFoundException("Tag not found by id " + id)), Tag.class);
    }

    @Override
    public void updateTag(Tag tag) {
        Tag tagNew = modelMapper.map(tagRepo.findById(tag.getId()).orElseThrow(() -> new NotFoundException("Tag not found by id " + tag.getId())), Tag.class);
        ThreadContext.put("tagBefore", tagNew.toString());
        tagNew.setName(tag.getName());

        tagRepo.save(modelMapper.map(tagNew, TagEntity.class));
    }

    @Override
    public void deleteTag(Long id) {
        tagRepo.deleteById(id);
    }
}
