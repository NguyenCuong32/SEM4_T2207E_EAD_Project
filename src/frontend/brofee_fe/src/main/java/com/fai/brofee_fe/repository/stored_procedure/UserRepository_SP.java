package com.fai.brofee_fe.repository.stored_procedure;

import com.fai.brofee_fe.entity.stored_procedure_entity.User_SP;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.query.Procedure;

import java.util.List;

public interface UserRepository_SP extends JpaRepository<User_SP, Long> {

    @Procedure("get_users_with_pagination")
    List<User_SP> getUsersWithPagination(int size, int page, String searchTerm);

    @Procedure("get_user_by_code")
    User_SP getUserByCode(String code);
}
