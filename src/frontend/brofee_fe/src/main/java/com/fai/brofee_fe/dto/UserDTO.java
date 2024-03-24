package com.fai.brofee_fe.dto;

import com.fai.brofee_fe.entity.Role;
import com.fai.brofee_fe.entity.User;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDTO {
    private Long id;
    private String code;
    private String phone;
    private String name;
    private String email;
    private String avatar;
    private String address;
    private BigDecimal totalTransaction;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private LocalDateTime deletedAt;

    public UserDTO(Long id, String code, String phone, String name, String email, String avatar, String address, BigDecimal totalTransaction, LocalDateTime createdAt, LocalDateTime updatedAt, LocalDateTime deletedAt) {
        this.id = id;
        this.code = code;
        this.phone = phone;
        this.name = name;
        this.email = email;
        this.avatar = avatar;
        this.address = address;
        this.totalTransaction = totalTransaction;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.deletedAt = deletedAt;
    }

    private Set<Role> roles = new HashSet<>();

}
