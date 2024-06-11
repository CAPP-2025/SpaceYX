package com.spaceyx.space_tourism.controller;

import com.spaceyx.space_tourism.model.ReservationEntity;
import com.spaceyx.space_tourism.service.ReservationService;

import jakarta.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/reservations")
public class ReservationController {
    @Autowired
    private ReservationService reservationService;

    @GetMapping
    public List<ReservationEntity> getAllReservations() {
        return reservationService.findAll();
    }

    @GetMapping("/{id}")
    public Optional<ReservationEntity> getReservationById(@PathVariable Long id) {
        return reservationService.findById(id);
    }

    @PostMapping
    public ResponseEntity<?> createReservation(@RequestBody ReservationEntity reservation) {
        try{
            return ResponseEntity.ok(reservationService.save(reservation));
        } catch (EntityNotFoundException e) {
            return ResponseEntity.badRequest().body("Vol not found with ID: " + (reservation.getVolId()));
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
