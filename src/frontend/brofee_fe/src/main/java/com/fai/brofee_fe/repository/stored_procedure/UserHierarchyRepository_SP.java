package com.fai.brofee_fe.repository.stored_procedure;

import com.fai.brofee_fe.entity.stored_procedure_entity.UserHierarchyItem_SP;
import com.fai.brofee_fe.entity.stored_procedure_entity.User_SP;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.query.Procedure;

import java.util.List;

public interface UserHierarchyRepository_SP extends JpaRepository<UserHierarchyItem_SP, Long> {

    @Procedure("get_parents")
    List<UserHierarchyItem_SP> getParentUsers(Long id);

    @Procedure("get_children")
    List<UserHierarchyItem_SP> getChildrenUsers(Long id);

}
