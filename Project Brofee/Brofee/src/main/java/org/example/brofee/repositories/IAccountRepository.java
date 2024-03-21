package org.example.brofee.repositories;

import org.example.brofee.entities.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface IAccountRepository extends JpaRepository<Account, UUID> {
    public Account findAccountByUsername(String userName);
}
