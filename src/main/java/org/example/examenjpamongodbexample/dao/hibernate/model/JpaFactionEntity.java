package org.example.examenjpamongodbexample.dao.hibernate.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.examenjpamongodbexample.dao.utilities.Queries;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "faction")
@NamedQueries({
        @NamedQuery(name = "GET_ALL_FACTIONS",
                query = Queries.GET_ALL_FACTIONS)
})
public class JpaFactionEntity {
    @Id
    @Column(name = "fname")
    private String fName;

    @Column
    private String contact;

    @Column
    private String planet;

    @Column(name = "number_controlled_systems")
    private int numControlledSystems;

    @Column(name = "date_last_purchase")
    private LocalDate dateLastPurchase;

    @OneToMany(mappedBy = "factionOne", fetch = FetchType.LAZY)
    private List<JpaBattleEntity> battlesAsFactionOne;

    @OneToMany(mappedBy = "factionTwo", fetch = FetchType.LAZY)
    private List<JpaBattleEntity> battlesAsFactionTwo;

    @OneToMany(mappedBy = "faction", fetch = FetchType.LAZY)
    private List<JpaWeaponFactionEntity> weaponsAndFactions;

}
