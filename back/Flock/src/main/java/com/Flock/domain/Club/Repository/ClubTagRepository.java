package com.Flock.domain.Club.Repository;

import com.Flock.domain.Club.Entity.Club;
import com.Flock.domain.Club.Entity.ClubTag;
import com.Flock.domain.Tag.Entity.Tag;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ClubTagRepository extends JpaRepository<ClubTag,Long> {

    @Query("SELECT ct FROM ClubTag ct JOIN FETCH ct.tag WHERE ct.club = ?1")
    List<ClubTag> findByClub(Club club);

    @Query("select ct from ClubTag ct where ct.tag.tagName like concat('%', ?1 , '%')")
    ClubTag findByTagName(String tag);
}
