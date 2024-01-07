package com.Flock.domain.Category.Controller;

import com.Flock.domain.Category.Service.CategoryService;
import com.Flock.domain.Category.dto.CategoryRequestDto;
import com.Flock.domain.Response.CommonResponse;
import com.Flock.domain.Response.ListResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CategoryController {

    @Autowired
    CategoryService categoryService;

    @PostMapping("/api/category")
    public CommonResponse addCategory(@RequestBody CategoryRequestDto categoryRequestDto){

        CommonResponse response =categoryService.insertCategory(categoryRequestDto);

        return response;
    }

    @GetMapping("/api/category")
    public ListResponse getCategories(){
        ListResponse response = categoryService.getCategories();

        return response;
    }
}
