package com.example.kommunalvalgbackend.repository;


import com.example.kommunalvalgbackend.model.Parti;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PartiRepository extends JpaRepository<Parti, Integer> {
    boolean existsPartiByPartiNavn(String partiNavn);
}
