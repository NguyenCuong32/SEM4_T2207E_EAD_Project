package org.example.brofee.services;

import org.example.brofee.entities.Account;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface IAccountService extends UserDetailsService {
    public Account findByUserName(String userName);
    public void save(Account account);
}
