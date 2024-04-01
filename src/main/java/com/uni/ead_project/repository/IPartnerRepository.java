package com.uni.ead_project.repository;

import com.uni.ead_project.entity.PartnerEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IPartnerRepository extends JpaRepository <PartnerEntity, String> {
}
