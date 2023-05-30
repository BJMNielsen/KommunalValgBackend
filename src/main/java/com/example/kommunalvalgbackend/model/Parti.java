package com.example.kommunalvalgbackend.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;


@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Parti {

    public Parti(int id, String partiNavn, char partiSymbol){
        this.id = id;
        this.partiNavn = partiNavn;
        this.partiSymbol = partiSymbol;
    }
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(unique = true, nullable = false)
    private String partiNavn;

    private char partiSymbol;

    // Ét parti til mange Kandidater.
    @OneToMany(mappedBy = "parti", cascade = CascadeType.REMOVE) // mappedBy = "parti" referere til private Parti parti variablen i kandidat klassen, dvs vi mapper vores liste af kandidater, til den variabel. Vi laver en "bidirectional relationship" mellem klasserne, hvor parti'et er den "ejende" side og kandidat er den "inverse" side. CascadeType gør at når man sletter et parti, så bliver kandidaterne der høre til partiet i tabellen også slettet.
    @JsonBackReference // Forhindre eternal loop reference mellem kandidater og parti.
    private List<Kandidat> kandidater;


}
