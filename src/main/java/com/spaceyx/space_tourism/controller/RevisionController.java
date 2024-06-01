package com.spaceyx.space_tourism.controller;

import com.spaceyx.space_tourism.model.RevisionEntity;
import com.spaceyx.space_tourism.service.RevisionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
    public RevisionEntity createRevision(@RequestBody RevisionEntity revision) {
        return revisionService.save(revision);
    }

    @DeleteMapping("/{id}")
    public void deleteRevision(@PathVariable Long id) {
        revisionService.deleteById(id);
    }
}

