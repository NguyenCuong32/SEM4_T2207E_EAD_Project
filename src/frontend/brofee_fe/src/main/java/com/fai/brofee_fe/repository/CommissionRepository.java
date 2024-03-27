package com.fai.brofee_fe.repository;

import com.fai.brofee_fe.entity.Commission;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.query.Procedure;

import java.time.LocalDateTime;
import java.util.List;

public interface CommissionRepository extends JpaRepository<Commission, Long> {

    List<Commission> findByRecipientId(Long recipientId);

    @Procedure("create_commission_payment_for_user")
    void createCommissionPaymentForUser(Long userId, LocalDateTime startDate, LocalDateTime endDate);

}
