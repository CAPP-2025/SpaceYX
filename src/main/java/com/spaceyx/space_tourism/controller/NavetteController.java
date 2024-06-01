package com.spaceyx.space_tourism.controller;

import com.spaceyx.space_tourism.model.NavetteEntity;
import com.spaceyx.space_tourism.model.NavetteEntity.Status;
import com.spaceyx.space_tourism.service.NavetteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/navettes")
public class NavetteController {
    @Autowired
    private NavetteService navetteService;

    @GetMapping
    public List<NavetteEntity> getAllNavettes() {
        return navetteService.findAll();
    }

    @GetMapping("/{id}")
    public Optional<NavetteEntity> getNavetteById(@PathVariable Long id) {
        return navetteService.findById(id);
    }

    @PostMapping
    public NavetteEntity createNavette(@Valid @RequestBody NavetteEntity navette) {
        return navetteService.save(navette);
    }

    @DeleteMapping("/{id}")
    public void deleteNavette(@PathVariable Long id) {
        navetteService.deleteById(id);
    }

    @PutMapping("/{id}/status")
    public NavetteEntity updateNavetteStatus(@PathVariable Long id, @RequestBody Status status) {
        return navetteService.updateStatus(id, status);
    }
}

