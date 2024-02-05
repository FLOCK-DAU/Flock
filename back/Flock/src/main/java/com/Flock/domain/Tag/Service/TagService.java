package com.Flock.domain.Tag.Service;

import com.Flock.domain.Club.Entity.Club;
import com.Flock.domain.Tag.DTO.TagDto;
import com.Flock.domain.Tag.Entity.Tag;
import com.Flock.domain.Tag.Repository.TagRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
public class TagService {

    /**
     * 태그 저장
     */
    @Autowired
    TagRepository tagRepository;

    public Optional<Tag> findByTagName(String tagName){
        return tagRepository.findByTagName(tagName);
    }

    public List<TagDto> findByClub(Club club){
        return tagRepository.findByClubTags(club.getClubTags())
                .stream().map(TagDto::from).collect(Collectors.toList());
    }

    public Tag save(String tagName){
        Tag tag = Tag.builder()
                .tagName(tagName)
                .build();
        return tagRepository.save(tag);
    }



}
