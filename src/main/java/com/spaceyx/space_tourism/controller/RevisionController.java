package com.spaceyx.space_tourism.controller;

import com.spaceyx.space_tourism.service.RevisionService;
import com.spaceyx.space_tourism.model.DTO.RevisionRequest;
import com.spaceyx.space_tourism.model.entity.RevisionEntity;

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
        return revisionService.getAllRevisions();
    }

    @PostMapping
    public ResponseEntity<?> createRevision(@Valid @RequestBody RevisionRequest revisionRequest) {
        try {
            return ResponseEntity.ok(revisionService.createRevision(revisionRequest));
        } catch (EntityNotFoundException e) {
            return ResponseEntity.badRequest().body(" Navette not found with ID: " + revisionRequest.getNavetteId());

        }
    }

    @DeleteMapping("/{id}")
    public void deleteRevision(@PathVariable Long id) {
        revisionService.deleteRevision(id);
    }
}
