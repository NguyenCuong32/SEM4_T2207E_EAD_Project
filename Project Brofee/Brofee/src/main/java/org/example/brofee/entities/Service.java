package org.example.brofee.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.UUID;

@Entity
@Table(name = "services")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Service {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private UUID id;

    @Column(name = "name")
    private String name;

    @Column(name = "description")
    private String description;

    @Column(name = "price")
    private BigDecimal price;

    @Column(name = "level1")
    private float level1;

    @Column(name = "level2")
    private float level2;

    @Column(name = "level3")
    private float level3;

    @Column(name = "level4")
    private float level4;

    @Column(name = "level5")
    private float level5;

    @Column(name = "status")
    private int status;

    @ManyToOne
    @JoinColumn(name = "category")
    private Category category;

}
