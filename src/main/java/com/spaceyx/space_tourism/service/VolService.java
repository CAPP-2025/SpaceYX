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
import java.util.Comparator;

@Service
public class VolService {
    @Autowired
    private VolRepository volRepository;

    @Autowired
    private NavetteRepository navetteRepository;

    public List<VolEntity> getAllVols() {
        return volRepository.findAll();
    }

    public VolEntity getVolById(Long id) {
        return volRepository.findById(id).orElse(null);
    }

    public VolEntity createVol(VolRequest volRequest) {
        // Business logic for status setting
        List<VolEntity> allVols = volRepository.findAll();
        // Check if there is a vol in all vols that is in the same month as the one being created
        for (VolEntity vol : allVols) {
        if (vol.getDateTime().getMonth() == volRequest.getDateTime().getMonth() &&
            vol.getDateTime().getYear() == volRequest.getDateTime().getYear()) {
            throw new IllegalArgumentException("No more than one space flight per month (any shuttle combined)! This is prohibited by the FAA (Federal Aviation Administration)");
        }
    }

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
            if (revisionDoneAfterLastVol(navette, volRequest.getDateTime())) {
                vol.setStatus(VolEntity.Status.OK);
            } else {
                vol.setStatus(VolEntity.Status.WAITING_FOR_GEARCHECK);
            }
        }


        return volRepository.save(vol);
    }

    public VolEntity updateVol(VolEntity vol) {
        return volRepository.save(vol);
    }

    public void deleteVol(Long id) {
        VolEntity vol = getVolById(id);
        if (vol != null) {
            System.out.println("Email sent to users: Vol with id " + id + " has been canceled.");
            volRepository.delete(vol);
        }
        else {
            throw new EntityNotFoundException("Vol not found with ID: " + id);
        }
    }

    // Additional business logic as needed

    private boolean revisionDoneAfterLastVol(NavetteEntity navette, LocalDateTime dateTime) {
        Optional<VolEntity> lastVolOptional = navette.getVols().stream()
        .filter(vol -> vol.getDateTime().isBefore(dateTime))
        .max(Comparator.comparing(VolEntity::getDateTime));

        if (!lastVolOptional.isPresent()) {
        return true;
        }

        VolEntity lastVol = lastVolOptional.get();
        if (navette.getRevisions().isEmpty()) {
            return true;
        }

        return navette.getRevisions().stream()
        .anyMatch(revision -> revision.getDate().isAfter(lastVol.getDateTime().toLocalDate()) 
                && revision.getDate().isBefore(dateTime.toLocalDate()));

    }
}

