package com.example.assessment.repository;

import com.example.assessment.dto.UserDto;
import com.example.assessment.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {

    @Query("SELECT c FROM Category c WHERE c NOT IN (SELECT s.category FROM Subscription s WHERE s.user.username = :username)")
    List<Category> findAvailableCategoriesForUser(@Param("username") String username);

    @Query("SELECT s.category FROM Subscription s WHERE s.user.username = :username")
    List<Category> findSubscribedCategoriesForUser(@Param("username") String username);
}
