package org.example.brofee.repositories;

import org.example.brofee.entities.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IRoleRepository extends JpaRepository<Role, Long> {
    public Role findByName(String name);
}
