package com.fai.service.brofee.repository.stored_procedure;

import com.fai.service.brofee.entity.stored_procedure_entity.CommissionService_SP;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.query.Procedure;

import java.time.LocalDateTime;
import java.util.List;

public interface CommissionServiceRepository_SP extends JpaRepository<CommissionService_SP, Long> {

    @Procedure("get_unpaid_commissions_by_user_and_time")
    List<CommissionService_SP> getUnpaidCommissionsByUserAndTime(Long userId, LocalDateTime startDate, LocalDateTime endDate);

    @Procedure("get_commission_services_by_commission_id")
    List<CommissionService_SP> getCommissionServiceByCommissionId(Long commissionId);

}
