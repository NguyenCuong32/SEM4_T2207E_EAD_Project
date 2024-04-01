package com.example.spa_website.repository;

import com.example.spa_website.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CategoryRepository extends JpaRepository<Category, Long> {

    @Query(value = "SELECT * FROM category c WHERE c.deleted_at IS NULL", nativeQuery = true)
    List<Category> findNoneDeleted();


}
