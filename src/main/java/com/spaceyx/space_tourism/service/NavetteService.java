package com.spaceyx.space_tourism.service;

import com.spaceyx.space_tourism.model.NavetteEntity;
import com.spaceyx.space_tourism.model.NavetteEntity.Status;
import com.spaceyx.space_tourism.repository.NavetteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class NavetteService {
    @Autowired
    private NavetteRepository navetteRepository;

    public List<NavetteEntity> getAllNavettes() {
        return navetteRepository.findAll();
    }

    public NavetteEntity getNavetteById(Long id) {
        return navetteRepository.findById(id).orElse(null);
    }

    public NavetteEntity createNavette(NavetteEntity navette) {
        return navetteRepository.save(navette);
    }

    public void deleteNavette(Long id) {
        navetteRepository.deleteById(id);
    }

    public NavetteEntity updateNavetteStatus(Long id, Status status) {
        Optional<NavetteEntity> navetteOpt = navetteRepository.findById(id);
        if (navetteOpt.isPresent()) {
            NavetteEntity navette = navetteOpt.get();
            navette.setStatus(status);
            return navetteRepository.save(navette);
        }
        throw new RuntimeException("Navette not found");
    }
}

