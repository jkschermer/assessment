package com.example.assessment.controller;

import com.example.assessment.dto.UserDto;
import com.example.assessment.entity.Category;
import com.example.assessment.response.CategoryResponse;
import com.example.assessment.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    @GetMapping("/categories")
    public CategoryResponse getCategories(@AuthenticationPrincipal UserDto user) {
        List<Category> availableCategories = categoryService.getAvailableCategories(user);
        List<Category> subscribedCategories = categoryService.getSubscribedCategories(user.getUsername());
        return new CategoryResponse(availableCategories, subscribedCategories);
    }
}
