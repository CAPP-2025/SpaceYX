package com.spaceyx.space_tourism.service;

import com.spaceyx.space_tourism.model.ReservationEntity;
import com.spaceyx.space_tourism.model.VolEntity;
import com.spaceyx.space_tourism.repository.ReservationRepository;
import com.spaceyx.space_tourism.repository.VolRepository;

import jakarta.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ReservationService {
    @Autowired
    private ReservationRepository reservationRepository;

    @Autowired
    private VolRepository volRepository;

    public List<ReservationEntity> getAllReservations() {
        return reservationRepository.findAll();
    }

    public ReservationEntity getReservationById(Long id) {
        return reservationRepository.findById(id).orElse(null);
    }

    public ReservationEntity createReservation(ReservationEntity reservation) {
        Optional<VolEntity> volOptional = volRepository.findById(reservation.getVolId());
        if (!volOptional.isPresent()) {
            throw new EntityNotFoundException("Vol not found with ID: " + (reservation.getVolId()));
        }

        VolEntity vol = volOptional.get();
        reservation.setVol(vol);

        if (vol.getStatus() == VolEntity.Status.PASSED) {
            throw new IllegalArgumentException("Cannot reserve a seat on a passed flight");
        }

        if (vol.getReservations().size() >= vol.getNavette().getCapacite()) {
            throw new IllegalArgumentException("No more seats available on this flight");
        }

        for (ReservationEntity res : vol.getReservations()) {
            if (res.getVolId() == reservation.getVolId()) {
                throw new IllegalArgumentException("Cannot reserve the same vol twice");
            }
        }

        return reservationRepository.save(reservation);
    }
}
