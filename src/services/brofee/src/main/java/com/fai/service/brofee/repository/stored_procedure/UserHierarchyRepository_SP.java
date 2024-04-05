package com.fai.service.brofee.repository.stored_procedure;

import com.fai.service.brofee.entity.stored_procedure_entity.UserHierarchyItem_SP;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.query.Procedure;

import java.util.List;

public interface UserHierarchyRepository_SP extends JpaRepository<UserHierarchyItem_SP, Long> {

    @Procedure("get_parents")
    List<UserHierarchyItem_SP> getParentUsers(Long id);

    @Procedure("get_children")
    List<UserHierarchyItem_SP> getChildrenUsers(Long id);

}
