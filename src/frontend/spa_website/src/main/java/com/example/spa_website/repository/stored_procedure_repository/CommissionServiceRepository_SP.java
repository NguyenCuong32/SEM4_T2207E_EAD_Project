package com.example.spa_website.repository.stored_procedure_repository;

import com.example.spa_website.entity.stored_procedure_entity.CommissionService_SP;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.query.Procedure;

import java.time.LocalDateTime;
import java.util.List;

public interface CommissionServiceRepository_SP extends JpaRepository<CommissionService_SP, Long> {

    @Procedure("get_commission_services_by_commission_id")
    List<CommissionService_SP> getCommissionServiceByCommissionId(Long commissionId);

}
