package com.naukma.introductionspringproject.service;

import com.naukma.introductionspringproject.model.Tag;

public interface TagService {
    Tag createTag(Tag tag);

    Tag readTag(Long id);

    void updateTag(Tag tag);

    void deleteTag(Long id);
}
