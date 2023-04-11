package com.example.assessment.response;

import com.example.assessment.entity.Category;

import java.util.List;

public class CategoryResponse {
    private List<Category> availableCategories;
    private List<Category> subscribedCategories;

    public CategoryResponse(List<Category> availableCategories, List<Category> subscribedCategories) {
        this.availableCategories = availableCategories;
        this.subscribedCategories = subscribedCategories;
    }
}
