package com.example.assessment.service;

import com.example.assessment.entity.Category;
import com.example.assessment.entity.Subscription;
import com.example.assessment.repository.SubscriptionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class SubscriptionService {

    @Autowired
    private SubscriptionRepository repository;

    public List<Category> getSubscriptions(UserDetails user) {
        return repository.findByUser_Username(user.getUsername())
                .stream()
                .map(Subscription::getCategory)
                .collect(Collectors.toList());
    }
}
