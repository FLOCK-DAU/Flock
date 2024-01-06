package com.Flock.domain.Category.Service;

import com.Flock.domain.Category.Entity.Category;
import com.Flock.domain.Category.Repository.CategoryRepository;
import com.Flock.domain.Category.dto.CategoryRequestDto;
import com.Flock.domain.Response.CommonResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CategoryService {

    @Autowired
    CategoryRepository categoryRepository;

    public CommonResponse insertCategory(CategoryRequestDto categoryRequestDto) {
        Category category = Category.builder()
                .categoryName(categoryRequestDto.getCategoryName())
                .build();


        categoryRepository.save(category);

        CommonResponse response = new CommonResponse();

        response.setSuccessResponse();
        return response;
    }

}
