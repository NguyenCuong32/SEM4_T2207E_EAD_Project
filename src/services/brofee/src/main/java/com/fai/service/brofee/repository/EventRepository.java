package com.fai.service.brofee.repository;

import com.fai.service.brofee.entity.Event;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface EventRepository extends JpaRepository<Event, Long> {

    @Query(value = "SELECT * FROM event WHERE status = :status ORDER BY id DESC LIMIT :limit", nativeQuery = true)
    List<Event> findTopByStatusOrderByIdDesc(@Param("status") int status, @Param("limit") int limit);

}
