package com.fai.service.brofee.entity.stored_procedure_entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserHierarchyItem_SP {

    @Id
    private Long id;

    private String phone;

    private String name;

    private String email;

    @Column(name = "referrer_id")
    private Long referrerId;

    @Column(name = "user_level")
    private Integer userLevel;

}
