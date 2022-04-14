package com.cesi.apireservation.controller;

import com.cesi.apireservation.dto.ConcertDTO;
import com.cesi.apireservation.service.ConcertService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("concerts")
public class ConcertController {

    @Autowired
    private ConcertService concertService;

    @GetMapping("")
    public ResponseEntity<List<ConcertDTO>> getAll() {
            return ResponseEntity.ok(concertService.getConcerts());
    }

    @PostMapping("create")
    public ResponseEntity<?> save(@RequestBody ConcertDTO concertDTO) {
        try {
            return ResponseEntity.ok(concertService.save(concertDTO));
        }catch (Exception ex) {
            return ResponseEntity.status(500).body(ex.getMessage());
        }
    }
}
