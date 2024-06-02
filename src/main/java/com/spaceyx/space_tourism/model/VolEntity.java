package com.spaceyx.space_tourism.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
public class VolEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private LocalDateTime dateTime;

    @ManyToOne
    @JoinColumn(name = "navette_id", nullable = false)
    @JsonIgnore
    private NavetteEntity navette;

    private Long nId;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Status status;

    public enum Status {
        PASSED, OK, WAITING_FOR_GEARCHECK
    }

    // Getters and Setters

    public VolEntity() {}

    public VolEntity(LocalDateTime dateTime, NavetteEntity navette, Long navetteId, Status status) {
        this.dateTime = dateTime;
        this.navette = navette;
        this.nId = navetteId;
        this.status = status;
    }

    public Long getId() {
        return id;
    }


    public void setId(Long id) {
        this.id = id;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public void setDateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }

    public NavetteEntity getNavette() {
        return navette;
    }

    public void setNavette(NavetteEntity navette) {
        this.navette = navette;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public Long getNavetteId() {
        return nId;
    }

    public void setNavetteId(Long navetteId) {
        this.nId = navetteId;
    }
}
