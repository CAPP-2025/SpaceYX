package com.spaceyx.space_tourism.service;

import com.spaceyx.space_tourism.model.RevisionEntity;
import com.spaceyx.space_tourism.repository.RevisionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RevisionService {
    @Autowired
    private RevisionRepository revisionRepository;

    public List<RevisionEntity> findAll() {
        return revisionRepository.findAll();
    }

    public RevisionEntity save(RevisionEntity revision) {
        return revisionRepository.save(revision);
    }

    public void deleteById(Long id) {
        revisionRepository.deleteById(id);
    }
}
