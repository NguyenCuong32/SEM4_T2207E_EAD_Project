package com.uni.ead_project.repository;

import com.uni.ead_project.entity.EventEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IEventRepository extends JpaRepository<EventEntity,String> {

}
