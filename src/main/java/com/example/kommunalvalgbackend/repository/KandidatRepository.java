package com.example.kommunalvalgbackend.repository;


import com.example.kommunalvalgbackend.model.Kandidat;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface KandidatRepository extends JpaRepository<Kandidat, Integer> {

    // Vi laver en custom metode vi kalder existsPartiByName og en custom query til den, for at undgå for meget autowiring.
    // Vi selecter fra Parti p tabel hvor partinavn = det partinavn vi har givet med i metoden.
    // Den her query skal returne true eller false, så vi hvis den finder et partinavn i Parti tabellen p, der stemmer med den String partiNavn vi gav med, så stiger count fra 0 til 1 eller 2 eller 3 whatever afhængigt af hvor mange den finder, og derved returner den true, da count > 0.
    @Query("""
        SELECT CASE WHEN COUNT(p) > 0 THEN true ELSE false END FROM Parti p WHERE p.partiNavn = :partiNavn
        """)
    boolean existsPartiByName(@Param("partiNavn") String partiNavn); // Vi mapper vores String partiNavn til en @param hvilket gør at den kan bruges i en custom query vi nu skriver.

    // husk at JPA skal autosuggest navnet på metoden for at det virker.
    List<Kandidat> findAllByParti_PartiNavn(String partiNavn);
}
