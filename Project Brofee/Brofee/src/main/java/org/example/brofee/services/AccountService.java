package org.example.brofee.services;

import jakarta.annotation.PostConstruct;
import org.example.brofee.entities.Account;
import org.example.brofee.entities.Role;
import org.example.brofee.repositories.IAccountRepository;
import org.example.brofee.repositories.IRoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.stream.Collectors;

@Service
public class AccountService implements IAccountService{
    private IAccountRepository iAccountRepository;
    private IRoleRepository iRoleRepository;
    @Autowired
    public AccountService(IAccountRepository iAccountRepository, IRoleRepository iRoleRepository) {
        this.iAccountRepository = iAccountRepository;
        this.iRoleRepository = iRoleRepository;
    }

    @Override
    public Account findByUserName(String userName) {
        return iAccountRepository.findAccountByUsername(userName);
    }

    //Do chua tao trang Dang Ki nen ta them CSDL thu cong
    //Cho no chay 1 lan roi cmt lai khong tu dong no tao ra nhieu
//    @PostConstruct
//    public void insertUser(){
//        Role role1 = new Role();
//        role1.setName("ROLE_ADMIN");
//        Collection<Role> roles = new ArrayList<>();
//        roles.add(role1);
//
//        Account user1 = new Account();
//        user1.setUsername("admin");
//        user1.setPassword("$2a$10$zXx.7eRQDzBJ7zdjqq42VutrH5AMlXUzSkChGqtuBezFmlIcvDDbS");  //Abcd@1234
//        user1.setEnabled(true);
//        user1.setRoles(roles);
//
//        iAccountRepository.save(user1);
//    }

    @Override
    public void save(Account account) {
        iAccountRepository.save(account);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Account account = iAccountRepository.findAccountByUsername(username);
        if(account==null){
            throw new UsernameNotFoundException("Invalid username or password.");
        }
        return new org.springframework.security.core.userdetails.User(account.getUsername(), account.getPassword(), roleAuthorities(account.getRoles()));
        //Trả về 1 đối tượng UserDetails chứa thông tin về người dùng, bao gồm tên người dùng, mật khẩu và danh sách các quyền (authorities) của người dùngr
    }

    //Phuong thuc nay nhan vao tap hop cac Role va tra ve tap hop cac quyen
    private Collection<? extends GrantedAuthority> roleAuthorities(Collection<Role> roles) {
        return roles.stream().map(
                role -> new SimpleGrantedAuthority(role.getName()))
                .collect(Collectors.toList());
    }
}
