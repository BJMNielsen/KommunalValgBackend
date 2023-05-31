package com.example.kommunalvalgbackend.service;

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
}
