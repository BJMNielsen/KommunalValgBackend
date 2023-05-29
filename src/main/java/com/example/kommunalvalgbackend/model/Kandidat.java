package com.example.kommunalvalg.model;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Kandidat {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String navn;

    @ManyToOne // Many kandidater til ét parti
    @JoinColumn(name = "parti_navn", referencedColumnName = "id") // Vi laver med joinColumn en kolonne i vores Kandidat tabel, der hedder partinavn (name = "parti_navn") og indeholder værdier fra Parti klassens "id" kolonne (referencedColumnName = "id")
    private Parti parti;

}
