package com.Flock.domain.Category.Service;

import com.Flock.domain.Category.Entity.Category;
import com.Flock.domain.Category.Repository.CategoryRepository;
import com.Flock.domain.Category.dto.CategoryRequestDto;
import com.Flock.domain.Response.CommonResponse;
import com.Flock.domain.Response.ListResponse;
import com.Flock.domain.Response.ResponseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryService {

    @Autowired
    CategoryRepository categoryRepository;

    @Autowired
    ResponseService responseService;

    public CommonResponse insertCategory(CategoryRequestDto categoryRequestDto) {
        Category category = Category.builder()
                .categoryName(categoryRequestDto.getCategoryName())
                .build();


        categoryRepository.save(category);

        CommonResponse response = new CommonResponse();

        response.setSuccessResponse();
        return response;
    }

    public ListResponse getCategories() {
        List<Category> categories = categoryRepository.findAll();

        return responseService.getListResponse(categories);
    }
}
