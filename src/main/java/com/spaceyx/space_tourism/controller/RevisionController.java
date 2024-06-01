package com.spaceyx.space_tourism.controller;

import com.spaceyx.space_tourism.model.RevisionEntity;
import com.spaceyx.space_tourism.service.RevisionService;
import com.spaceyx.space_tourism.DTO.RevisionRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;



import java.util.List;

import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/revisions")
public class RevisionController {
    @Autowired
    private RevisionService revisionService;

    @GetMapping
    public List<RevisionEntity> getAllRevisions() {
        return revisionService.findAll();
    }

    @PostMapping
    public ResponseEntity<?> createRevision(@Valid @RequestBody RevisionRequest revisionRequest) {
        try{
            return ResponseEntity.ok(revisionService.save(revisionRequest));
        } catch (EntityNotFoundException e) {
            return ResponseEntity.badRequest().body(" Navette not found with ID: " + revisionRequest.getNavetteId());
        
        }
    }

    @DeleteMapping("/{id}")
    public void deleteRevision(@PathVariable Long id) {
        revisionService.deleteById(id);
    }
}

