package com.example.kommunalvalgbackend.service;

import com.example.kommunalvalgbackend.exception.ResourceNotFoundException;
import com.example.kommunalvalgbackend.model.Parti;
import com.example.kommunalvalgbackend.repository.PartiRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PartiService {

    @Autowired
    PartiRepository partiRepository;

    public List<Parti> getAllPartier() {
        return partiRepository.findAll();
    }

    public Parti getPartyByName(String partyName) {
        return partiRepository.findByPartiNavn(partyName).orElseThrow(()-> new ResourceNotFoundException("No Party within the database has the Name: " + partyName));
    }
}
