package com.example.kommunalvalgbackend.controller;

import com.example.kommunalvalgbackend.model.Kandidat;
import com.example.kommunalvalgbackend.service.KandidatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
public class KandidatController {

    @Autowired
    KandidatService kandidatService;

    @GetMapping("/kandidater")
    public List<Kandidat> getAllKandidater(){
        return kandidatService.getAllKandidater();
    }

    @GetMapping("/kandidat/{id}")
    public Kandidat getKandidat(@PathVariable int id){
        return kandidatService.getKandidatById(id);
    }

    @PostMapping("/kandidat")
    public ResponseEntity<Kandidat> addKandidat(@RequestBody Kandidat kandidat){
        return kandidatService.addKandidat(kandidat);
    }

    @PutMapping("/kandidat")
    public ResponseEntity<Kandidat> updateKandidat(@RequestBody Kandidat kandidat){
        return kandidatService.updateKandidat(kandidat);
    }

}
