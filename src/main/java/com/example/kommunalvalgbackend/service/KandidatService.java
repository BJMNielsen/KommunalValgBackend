package com.example.kommunalvalgbackend.service;

import com.example.kommunalvalgbackend.exception.ResourceAlreadyExistsException;
import com.example.kommunalvalgbackend.exception.ResourceNotFoundException;
import com.example.kommunalvalgbackend.model.Kandidat;
import com.example.kommunalvalgbackend.repository.KandidatRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class KandidatService {

    @Autowired
    KandidatRepository kandidatRepository;

    public List<Kandidat> getAllKandidater() {
        return kandidatRepository.findAll();
    }

    public Kandidat getKandidatById(int id) {
        return kandidatRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Could not find any Kandidat with id: " + id));
    }

    // CREATE
    public ResponseEntity<Kandidat> addKandidat(Kandidat kandidat) {
        // Først tjekker vi om kandidaten allerede eksistere, så vi ikke overrider den hvis den eksistere.
        boolean exists = kandidatRepository.existsById(kandidat.getId());
        if (exists) {
            // Hvis den existere thrower vi en exception der bliver grebet i vores exception controller og lavet til et responseEntity object, som vores frontend også kan håndtere.
            throw new ResourceAlreadyExistsException("Kandidat with id: " + kandidat.getId() + " already exists and therefore can't be added.");
            // Add en exception der kan handle hvis man prøver at lave en kandidat uden et parti der eksistere. Brug resourcenotfoundexception.
        }

        // Så tjekker vi om kandidaten HAR et parti
        boolean hasNoParti = kandidat.getParti() == null;
        if (hasNoParti) {
            throw new ResourceNotFoundException("Kandidat with id: " + kandidat.getId() + " does not have a Parti, and therefore could not be created.");
        }

        // Hvis den IKKE allerede eksistere, så må vi adde den.
        Kandidat newKandidat = kandidatRepository.save(kandidat);
        return new ResponseEntity<>(newKandidat, HttpStatus.OK);
    }

    // UPDATE
    public ResponseEntity<Kandidat> updateKandidat(Kandidat kandidat) {
        // Først tjekker vi om kandidaten allerede eksistere, så vi ikke overrider den hvis den eksistere.
        boolean exists = kandidatRepository.existsById(kandidat.getId());
        if (!exists) {
            // Hvis den ikke existere thrower vi en exception der bliver grebet i vores exception controller og lavet til et responseEntity object, som vores frontend også kan håndtere.
            throw new ResourceNotFoundException("Kandidat with id: " + kandidat.getId() + " does not exist and therefore can't be updated");

        }
        // Hvis kandidaten eksistere, tager vi den nye info fra vores requestbody og overwriter vores kandidat nu med den nye info, dvs vi saver oveni en allerede eksisterende kandidat, bare med ny info.
        Kandidat newKandidat = kandidatRepository.save(kandidat);
        return new ResponseEntity<>(newKandidat, HttpStatus.OK);
    }
}

