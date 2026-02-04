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
@Table(name = "spies")
public class JpaSpyEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private int id;

    @Column(name = "sname")
    private String sName;

    @Column(name = "srace")
    private  String sRace;

    @OneToMany(mappedBy = "spy", fetch = FetchType.LAZY)
    private List<JpaBattleEntity> battles;
}
