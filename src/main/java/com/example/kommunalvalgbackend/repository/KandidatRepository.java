package com.example.kommunalvalgbackend.repository;


import com.example.kommunalvalgbackend.model.Kandidat;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface KandidatRepository extends JpaRepository<Kandidat, Integer> {

}
