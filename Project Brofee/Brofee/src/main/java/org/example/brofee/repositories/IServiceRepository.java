package org.example.brofee.repositories;

import org.example.brofee.entities.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.example.brofee.entities.Service;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface IServiceRepository extends JpaRepository<Service, UUID> {
    public Service findServicesByName(String nameService);
}
