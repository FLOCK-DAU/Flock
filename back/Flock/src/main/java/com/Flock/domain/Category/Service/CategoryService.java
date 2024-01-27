package com.Flock.domain.Category.Service;

import com.Flock.domain.Category.DTO.CategoryResponseDto;
import com.Flock.domain.Category.Entity.Category;
import com.Flock.domain.Category.Repository.CategoryRepository;
import com.Flock.domain.Category.DTO.CategoryRequestDto;
import com.Flock.domain.Response.CommonResponse;
import com.Flock.domain.Response.ListResponse;
import com.Flock.domain.Response.ResponseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

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
        List<CategoryResponseDto> categories = categoryRepository.findAll()
                .stream().map(CategoryResponseDto::from).collect(Collectors.toList());

        return responseService.getListResponse(categories);
    }
}
