package org.example.examenjpamongodbexample.dao.hibernate.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.examenjpamongodbexample.dao.utilities.Queries;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "battles")
@NamedQueries({
        @NamedQuery(name = "GET_ALL_BATTLES",
                query = Queries.GET_ALL_BATTLES)
})

public class JpaBattleEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private int id;

    @Column(name = "bname")
    private String bName;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "faction_one")
    private JpaFactionEntity factionOne;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "faction_two")
    private JpaFactionEntity factionTwo;

    @Column(name = "bplace")
    private String bPlace;

    @Column(name = "bdate")
    private LocalDate bDate;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_spy")
    private JpaSpyEntity spy;

}
