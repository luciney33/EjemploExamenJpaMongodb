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
@Table(name = "spies")
@NamedQueries({
        @NamedQuery(name = "GET_SPY",
                query = Queries.GET_SPY)
})
public class JpaSpyEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private int id;

    @Column(name = "sname")
    private String sName;

    @Column(name = "srace")
    private  String sRace;

}
