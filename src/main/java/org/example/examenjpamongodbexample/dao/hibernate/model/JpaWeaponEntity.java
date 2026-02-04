package org.example.examenjpamongodbexample.dao.hibernate.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "weapons")
public class JpaWeaponEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private int id;

    @Column(name = "wname")
    private String wName;

    @Column(name = "wprice")
    private Double wPrice;

    @OneToMany(mappedBy = "weapon", fetch = FetchType.LAZY)
    private List<JpaWeaponFactionEntity> weaponsAndFactions;


}
