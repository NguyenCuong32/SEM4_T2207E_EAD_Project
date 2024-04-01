package com.fai.brofee_fe.repository;

import com.fai.brofee_fe.entity.Service;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ServiceRepository extends JpaRepository<Service, Long> {
    Page<Service> findByCategory_Id(Long categoryId, Pageable pageable);

    Page<Service> findByName(String name, Pageable pageable);

    Page<Service> findByNameContaining(String name, Pageable pageable);

    List<Service> findByIdIn(List<Long> ids);

}
