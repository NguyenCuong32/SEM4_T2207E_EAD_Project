package com.fai.service.brofee.repository;

import com.fai.service.brofee.entity.Category;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CategoryRepository extends JpaRepository<Category, Long> {

    @Query(value = "SELECT * FROM category c WHERE c.deleted_at IS NULL", nativeQuery = true)
    List<Category> findNoneDeleted();





}
