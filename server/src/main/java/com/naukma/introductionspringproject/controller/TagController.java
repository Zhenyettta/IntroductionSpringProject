package com.naukma.introductionspringproject.controller;

import com.naukma.introductionspringproject.config.HttpStatuses;
import com.naukma.introductionspringproject.dto.TagDTO;
import com.naukma.introductionspringproject.model.Tag;
import com.naukma.introductionspringproject.service.TagService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/tags")
@Validated
@io.swagger.v3.oas.annotations.tags.Tag(name = "Tag Management", description = "Operations pertaining to tag in Tag Management")
public class TagController {
    private final ModelMapper modelMapper;
    private final TagService tagService;

    public TagController(ModelMapper modelMapper, TagService tagService) {
        this.modelMapper = modelMapper;
        this.tagService = tagService;
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get a tag by Id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = HttpStatuses.OK),
            @ApiResponse(responseCode = "404", description = HttpStatuses.NOT_FOUND)
    })
    public ResponseEntity<Object> getById(@Parameter(description = "Id value for the tag you need to retrieve") @PathVariable Long id){
        return new ResponseEntity<>(tagService.readTag(id), HttpStatus.OK);
    }

    @PutMapping
    @Operation(summary = "Update a tag")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = HttpStatuses.OK),
            @ApiResponse(responseCode = "404", description = HttpStatuses.NOT_FOUND)
    })
    public ResponseEntity<Object> updateTag(@Parameter(description = "Update tag object") @Valid @RequestBody TagDTO tagDTO){
        tagService.updateTag(modelMapper.map(tagDTO, Tag.class));
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping
    @Operation(summary = "Create a tag")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = HttpStatuses.OK),
            @ApiResponse(responseCode = "409", description = HttpStatuses.CONFLICT)
    })
    public ResponseEntity<Object> createTag(@Parameter(description = "Create tag object") @Valid @RequestBody TagDTO tagDTO){
        tagService.createTag(modelMapper.map(tagDTO, Tag.class));
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete a tag")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = HttpStatuses.OK),
            @ApiResponse(responseCode = "404", description = HttpStatuses.NOT_FOUND)
    })
    public ResponseEntity<Object> deleteTag(@Parameter(description = "Id value for the tag you want to delete") @PathVariable Long id){
        tagService.deleteTag(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}

