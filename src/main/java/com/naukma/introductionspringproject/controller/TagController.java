package com.naukma.introductionspringproject.controller;

import com.naukma.introductionspringproject.dto.MealDTO;
import com.naukma.introductionspringproject.dto.TagDTO;
import com.naukma.introductionspringproject.model.Meal;
import com.naukma.introductionspringproject.model.Tag;
import com.naukma.introductionspringproject.service.TagService;
import jakarta.validation.Valid;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/tags")
@Validated
public class TagController {
    private final ModelMapper modelMapper;
    private final TagService tagService;

    public TagController(ModelMapper modelMapper, TagService tagService) {
        this.modelMapper = modelMapper;
        this.tagService = tagService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> getById(@PathVariable Long id){
        return new ResponseEntity<>(tagService.readTag(id), HttpStatus.OK);
    }

    @PutMapping
    public ResponseEntity<Object> updateTag(@Valid @RequestBody TagDTO tagDTO){
        tagService.updateTag(modelMapper.map(tagDTO, Tag.class));
        return new ResponseEntity<>(HttpStatus.OK);
    }
    @PostMapping
    public ResponseEntity<Object> createTag(@Valid @RequestBody TagDTO tagDTO){
        tagService.createTag(modelMapper.map(tagDTO, Tag.class));
        return new ResponseEntity<>(HttpStatus.OK);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteTag(@PathVariable Long id){
        tagService.deleteTag(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}

