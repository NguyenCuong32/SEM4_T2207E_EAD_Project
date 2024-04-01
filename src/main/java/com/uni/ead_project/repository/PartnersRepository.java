package com.uni.ead_project.repository;

import com.uni.ead_project.entity.PartnerEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PartnersRepository extends JpaRepository <PartnerEntity, String> {
}
