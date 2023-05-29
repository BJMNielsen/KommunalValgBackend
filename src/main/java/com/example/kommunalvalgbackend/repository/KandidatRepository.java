package com.example.kommunalvalg.repository;

import com.example.kommunalvalg.model.Kandidat;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface KandidatRepository extends JpaRepository<Kandidat, Integer> {

}
