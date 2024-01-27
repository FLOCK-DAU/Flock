package com.Flock.domain.Category.DTO;

import com.Flock.domain.Category.Entity.Category;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CategoryResponseDto {
    Integer categoryId;
    String categoryName;

    public static CategoryResponseDto from(Category category){
        return new CategoryResponseDto(
                category.getId(),
                category.getCategoryName()
        );
    }

}
