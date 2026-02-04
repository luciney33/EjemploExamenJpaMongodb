package org.example.examenjpamongodbexample.dao.hibernate.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "weapons_factions")
public class JpaWeaponFactionEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private int id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "name_faction")
    private JpaFactionEntity faction;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_weapon")
    private JpaWeaponEntity weapon;

    @OneToMany(mappedBy = "weaponFaction", fetch = FetchType.LAZY)
    private List<JpaSaleEntity> sales;

}
