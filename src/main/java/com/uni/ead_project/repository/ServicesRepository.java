package com.uni.ead_project.repository;

import com.uni.ead_project.entity.ServiceEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ServicesRepository extends JpaRepository<ServiceEntity, String> {
}
