package com.spaceyx.space_tourism.controller;

import com.spaceyx.space_tourism.service.VolService;
import com.spaceyx.space_tourism.model.DTO.VolRequest;
import com.spaceyx.space_tourism.model.entity.VolEntity;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;

import java.util.List;

@RestController
@RequestMapping("/vols")
public class VolController {
    @Autowired
    private VolService volService;

    @GetMapping
    public List<VolEntity> getAllVols() {
        return volService.getAllVols();
    }

    @GetMapping("/{id}")
    public ResponseEntity<VolEntity> getVolById(@PathVariable Long id) {
        VolEntity vol = volService.getVolById(id);
        if (vol != null) {
            return ResponseEntity.ok(vol);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public ResponseEntity<?> createVol(@Valid @RequestBody VolRequest volRequest) {
        try {
            return ResponseEntity.ok(volService.createVol(volRequest));
        } catch (EntityNotFoundException e) {
            return ResponseEntity.badRequest().body(" Navette not found with ID: " + volRequest.getNavetteId());
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateVol(@PathVariable Long id, @Valid @RequestBody VolRequest volRequest) {
        VolEntity vol = volService.getVolById(id);
        if (vol != null) {
            try {
                System.out.println("Email sent to users: Vol with id " + id + " has been modified.");
                vol.setDateTime(volRequest.getDateTime());
                vol.setNavetteId(volRequest.getNavetteId());
                return ResponseEntity.ok(volService.updateVol(vol));
            } catch (EntityNotFoundException e) {
                return ResponseEntity.badRequest().body("Navette not found with ID: " + volRequest.getNavetteId());
            } catch (IllegalArgumentException e) {
                return ResponseEntity.badRequest().body(e.getMessage());
            }
        } else {
            return ResponseEntity.badRequest().body("Vol not found with ID: " + id);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteVol(@PathVariable Long id) {
        try {
            volService.deleteVol(id);
            return ResponseEntity.noContent().build();
        } catch (EntityNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }
}
