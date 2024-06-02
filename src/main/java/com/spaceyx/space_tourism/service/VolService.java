package com.spaceyx.space_tourism.service;

import com.spaceyx.space_tourism.model.NavetteEntity;
import com.spaceyx.space_tourism.model.VolEntity;
import com.spaceyx.space_tourism.repository.VolRepository;
import com.spaceyx.space_tourism.repository.NavetteRepository;

import jakarta.persistence.EntityNotFoundException;

import com.spaceyx.space_tourism.DTO.VolRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class VolService {
    @Autowired
    private VolRepository volRepository;

    @Autowired
    private NavetteRepository navetteRepository;

    public List<VolEntity> findAll() {
        return volRepository.findAll();
    }

    public VolEntity findById(Long id) {
        return volRepository.findById(id).orElse(null);
    }

    public VolEntity save(VolRequest volRequest) {
        // Business logic for status setting
        Optional<NavetteEntity> navetteOptional = navetteRepository.findById(volRequest.getNavetteId());
        if (!navetteOptional.isPresent()) {
            throw new EntityNotFoundException("Navette not found with ID: " + volRequest.getNavetteId());
        }

        NavetteEntity navette = navetteOptional.get();
        VolEntity vol = new VolEntity();

        vol.setDateTime(volRequest.getDateTime());
        vol.setNavette(navette);
        vol.setNavetteId(volRequest.getNavetteId());

        if (volRequest.getDateTime().isBefore(LocalDateTime.now())) {
            vol.setStatus(VolEntity.Status.PASSED);
        } else {
            vol.setStatus(VolEntity.Status.WAITING_FOR_GEARCHECK); // This would need further business logic to determine OK or WAITING_FOR_GEARCHECK
        }

        return volRepository.save(vol);
    }

    public void deleteById(Long id) {
        volRepository.deleteById(id);
    }

    // Additional business logic as needed
}

