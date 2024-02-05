package com.Flock.domain.Tag.Repository;

import com.Flock.domain.Club.Entity.Club;
import com.Flock.domain.Club.Entity.ClubTag;
import com.Flock.domain.Tag.DTO.TagDto;
import com.Flock.domain.Tag.Entity.Tag;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TagRepository extends JpaRepository<Tag, Long> {

    @Query("select t from Tag t where t.id = ?1")
    List<Tag> findTagsById(Long clubId);

    @Query("select t from Tag t where t.tagName = ?1")
    Optional<Tag> findByTagName(String tagName);



    @Query("select t from Tag t join fetch t.clubTags ct where ct in ?1 ")
    List<Tag> findByClubTags(List<ClubTag> clubTags);
}
