package com.fai.brofee_fe.repository;

import com.fai.brofee_fe.dto.CategoryDTO;
import com.fai.brofee_fe.entity.Category;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface CategoryRepository extends JpaRepository<Category, Long> {

    // deleted
    @Query("SELECT c FROM Category c WHERE c.deletedAt IS NULL")
    Page<Category> findNotDeletedCategories(Pageable pageable);

    @Query("SELECT c FROM Category c WHERE c.updatedAt IS NULL AND c.deletedAt IS NULL")
    Page<Category> findNotUpdatedCategories(Pageable pageable);

    @Query("SELECT c FROM Category c WHERE c.updatedAt IS NOT NULL AND c.deletedAt IS NULL")
    Page<Category> findUpdatedCategories(Pageable pageable);

    @Query("SELECT c FROM Category c WHERE c.deletedAt IS NOT NULL")
    Page<Category> findDeletedCategories(Pageable pageable);


}
