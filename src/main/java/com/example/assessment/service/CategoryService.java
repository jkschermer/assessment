package com.example.assessment.service;

import com.example.assessment.entity.Category;
import com.example.assessment.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;

    public List<Category> getAvailableCategories(UserDetails user) {
        return categoryRepository.findAvailableCategoriesForUser(user.getUsername());
    }

    public List<Category> getSubscribedCategories(UserDetails user) {
        return categoryRepository.findSubscribedCategoriesForUser(user.getUsername());
    }
}
