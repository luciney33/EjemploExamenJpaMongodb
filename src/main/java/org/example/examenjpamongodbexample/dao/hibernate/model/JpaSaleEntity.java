package org.example.examenjpamongodbexample.dao.hibernate.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "sales")
public class JpaSaleEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private int id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_weapons_faction")
    private JpaWeaponFactionEntity weaponFaction;

    private String units;

    @Column(name = "sldate")
    private LocalDate saleDate;
}
