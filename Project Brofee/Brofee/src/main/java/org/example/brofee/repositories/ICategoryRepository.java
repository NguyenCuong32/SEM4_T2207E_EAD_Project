package org.example.brofee.repositories;

import org.example.brofee.entities.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface ICategoryRepository extends JpaRepository<Category, UUID> {
    public Category findCategoryByNameCategory(String nameCategory);
}
