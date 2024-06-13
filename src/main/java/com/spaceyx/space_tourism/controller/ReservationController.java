package com.spaceyx.space_tourism.controller;

import com.spaceyx.space_tourism.model.ReservationEntity;
import com.spaceyx.space_tourism.service.ReservationService;

import jakarta.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/reservations")
public class ReservationController {
    @Autowired
    private ReservationService reservationService;

    @GetMapping
    public List<ReservationEntity> getAllReservations() {
        return reservationService.getAllReservations();
    }

    @GetMapping("/{id}")
    public ResponseEntity<ReservationEntity> getReservationById(@PathVariable Long id) {
        ReservationEntity reservation = reservationService.getReservationById(id);
        if (reservation != null) {
        return ResponseEntity.ok(reservation);
    } else {
        return ResponseEntity.notFound().build();
    }
    }

    @PostMapping
    public ResponseEntity<?> createReservation(@RequestBody ReservationEntity reservation) {
        try{
            return ResponseEntity.ok(reservationService.createReservation(reservation));
        } catch (EntityNotFoundException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
