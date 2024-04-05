package com.fai.service.brofee.repository;

import com.fai.service.brofee.entity.CategoryRevenue;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.query.Procedure;

import java.util.List;

public interface CategoryRepository_SP extends JpaRepository<CategoryRevenue, Long> {

    @Procedure("GetCategoryRevenue")
    List<CategoryRevenue> getCategoryRevenue(int pageNum, int pageSize, String searchKeyword);
}
