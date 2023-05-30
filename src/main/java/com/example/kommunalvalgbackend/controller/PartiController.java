package com.example.kommunalvalgbackend.controller;

import com.example.kommunalvalgbackend.model.Parti;
import com.example.kommunalvalgbackend.service.PartiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PartiController {

    @Autowired
    PartiService partiService;

}
