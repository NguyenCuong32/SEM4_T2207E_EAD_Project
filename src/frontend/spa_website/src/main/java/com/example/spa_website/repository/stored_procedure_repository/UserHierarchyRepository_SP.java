package com.example.spa_website.repository.stored_procedure_repository;

import com.example.spa_website.entity.stored_procedure_entity.UserHierarchyItem_SP;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.query.Procedure;

import java.util.List;

public interface UserHierarchyRepository_SP extends JpaRepository<UserHierarchyItem_SP, Long> {

    @Procedure("get_parents")
    List<UserHierarchyItem_SP> getParentUsers(Long id);

    @Procedure("get_children")
    List<UserHierarchyItem_SP> getChildrenUsers(Long id);

}
