package com.Flock.domain.Club.Service;

import com.Flock.domain.Club.Entity.Club;
import com.Flock.domain.Club.Entity.ClubTag;
import com.Flock.domain.Club.Repository.ClubTagRepository;
import com.Flock.domain.Tag.Entity.Tag;
import com.Flock.domain.Tag.Service.TagService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional
public class ClubTagService {

    @Autowired
    TagService tagService;

    @Autowired
    ClubTagRepository clubTagRepository;

    public void saveClubTag(Club club, List<String> tagNames){
        if(tagNames.size() == 0) return;

        tagNames.stream()
                .map(tag -> tagService.findByTagName(tag)
                        .orElseGet(() -> tagService.save(tag))
                )
                .forEach(tag -> mapTagToClub(club,tag));

    }

    public List<ClubTag> findByTagName(String tag){
        List<ClubTag> clubTag = clubTagRepository.findByTagName(tag);
        return clubTag;
    }

    private Long mapTagToClub(Club club, Tag tag){
        ClubTag clubTag = ClubTag.builder()
                .club(club)
                .tag(tag)
                .build();
        return clubTagRepository.save(clubTag).getId();
    }

    public List<ClubTag> findByClub(Club club){
        return clubTagRepository.findByClub(club);
    }

}
