package com.fai.service.brofee.repository;

import com.fai.service.brofee.entity.Service;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ServiceRepository extends JpaRepository<Service, Long> {
    Page<Service> findByCategory_Id(Long categoryId, Pageable pageable);

    Page<Service> findByName(String name, Pageable pageable);

    Page<Service> findByNameContainingAndDeletedAtIsNull(String name, Pageable pageable);

    Page<Service> findByDeletedAtIsNull(Pageable pageable);

    Page<Service> findByCategory_IdAndDeletedAtIsNull(Long categoryId,Pageable pageable);


    List<Service> findByIdIn(List<Long> ids);

}
