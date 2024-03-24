package com.fai.brofee_fe.repository;

import com.fai.brofee_fe.dto.UserDetail.UserDetailDTO;
import com.fai.brofee_fe.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;
@Repository
public interface UserRepository extends JpaRepository<User, Long> {



    Optional<User> findByEmail(String email);

    Optional<User> findByPhone(String phone);

    Optional<User> findByCodeOrPhone(String code, String phone);

    Boolean existsByCode(String code);


    @Query(value = "CALL Proc_ListUserSearch(:pageNumber,:pageSize,:search);", nativeQuery = true)
    List<Object[]> findUser(@Param("pageNumber") int pageNumber , @Param("pageSize") int pageSize,@Param("search") String search );

    @Query(value = "CALL Proc_CountListUserSearch(:search);", nativeQuery = true)
    Long countFindUser(@Param("search") String search );


    @Query(value = "CALL Proc_DetailInfoUser(:user_id);", nativeQuery = true)
    Object finDetailUser(@Param("user_id") Long userId );


    @Query(value = "CALL Proc_ReferralsListForUser(:user_id);", nativeQuery = true)
    List<Object> findReferralsListForUser(@Param("user_id") Long userId );

    @Override
    long count();


}







