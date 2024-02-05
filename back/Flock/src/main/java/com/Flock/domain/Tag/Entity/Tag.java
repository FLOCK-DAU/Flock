package com.Flock.domain.Tag.Entity;

import com.Flock.domain.Club.Entity.ClubTag;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Tag {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "tag_id")
    private Long id;

    @Column(unique = true,nullable = false)
    private String tagName;

    @OneToMany(mappedBy = "tag", fetch = FetchType.LAZY)
    private List<ClubTag> clubTags;


}
