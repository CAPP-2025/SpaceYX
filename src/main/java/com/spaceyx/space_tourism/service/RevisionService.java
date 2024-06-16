package com.spaceyx.space_tourism.service;

import com.spaceyx.space_tourism.repository.RevisionRepository;
import com.spaceyx.space_tourism.repository.NavetteRepository;

import jakarta.persistence.EntityNotFoundException;

import com.spaceyx.space_tourism.DTO.RevisionRequest;
import com.spaceyx.space_tourism.model.entity.NavetteEntity;
import com.spaceyx.space_tourism.model.entity.RevisionEntity;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RevisionService {
    @Autowired
    private RevisionRepository revisionRepository;

    @Autowired
    private NavetteRepository navetteRepository;

    public List<RevisionEntity> getAllRevisions() {
        return revisionRepository.findAll();
    }

    public RevisionEntity createRevision(RevisionRequest revisionRequest) {
        Optional<NavetteEntity> navetteOptional = navetteRepository.findById(revisionRequest.getNavetteId());
        if (!navetteOptional.isPresent()) {
            throw new EntityNotFoundException("Navette not found with ID: " + revisionRequest.getNavetteId());
        }

        NavetteEntity navette = navetteOptional.get();

        RevisionEntity revision = new RevisionEntity();
        revision.setDate(revisionRequest.getDate());
        revision.setNavette(navette);
        revision.setNavetteId(revisionRequest.getNavetteId());

        return revisionRepository.save(revision);
    }

    public void deleteRevision(Long id) {
        revisionRepository.deleteById(id);
    }
}
