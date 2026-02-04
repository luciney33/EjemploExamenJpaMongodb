package org.example.examenjpamongodbexample.dao.hibernate.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.examenjpamongodbexample.dao.utilities.Queries;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "weapons")
@NamedQueries({
        @NamedQuery(name = "DELETE_WEAPON",
                query = Queries.DELETE_WEAPON)
})
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
