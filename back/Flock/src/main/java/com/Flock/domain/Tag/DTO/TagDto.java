package com.Flock.domain.Tag.DTO;

import com.Flock.domain.Tag.Entity.Tag;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class TagDto {
    Long id;
    String tagName;

    public static TagDto from(Tag tag){
        return new TagDto(
                tag.getId(),
                tag.getTagName()
        );
    }
}
