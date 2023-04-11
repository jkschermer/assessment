package com.example.assessment.service;

import com.example.assessment.dto.UserDto;
import com.example.assessment.entity.Category;
import com.example.assessment.entity.Subscription;
import com.example.assessment.repository.CategoryRepository;
import com.example.assessment.repository.SubscriptionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private SubscriptionRepository subscriptionRepository;

    public List<Category> getAvailableCategories(UserDto user) {
        List<Category> availableCategories = categoryRepository.findAvailableCategoriesForUser(user.getUsername());
        List<Category> subscribedCategories = categoryRepository.findSubscribedCategoriesForUser(user.getUsername());
        availableCategories.addAll(subscribedCategories);
        return availableCategories;
    }

    public List<Category> getSubscribedCategories(String username) {
        return subscriptionRepository.findByUsername(username)
                .stream()
                .map(Subscription::getCategory)
                .collect(Collectors.toList());
    }
}
