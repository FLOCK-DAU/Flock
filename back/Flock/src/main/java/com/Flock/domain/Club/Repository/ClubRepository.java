package com.Flock.domain.Club.Repository;

import com.Flock.domain.Club.DTO.ClubListDto;
import com.Flock.domain.Club.Entity.Club;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ClubRepository extends JpaRepository<Club,Long> {

    @Query("select new com.Flock.domain.Club.DTO.ClubListDto(c.id,c.title,c.secret,c.isRecruitment,c.gender) from Club c ")
    List<ClubListDto> findClubs();
}
