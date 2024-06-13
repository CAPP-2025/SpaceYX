package com.spaceyx.space_tourism.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.*;
import java.time.LocalDateTime;
import java.util.Set;

@Entity
@Table(name = "vol")
public class VolEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name= "vol_date", nullable = false)
    private LocalDateTime dateTime;

    @ManyToOne
    @JoinColumn(name = "navette_id", nullable = false)
    @JsonIgnore
    private NavetteEntity navette;

    @Column(name = "id_navette")
    private Long nId;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Status status;

    @OneToMany(mappedBy = "vol", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonIgnore
    private Set<ReservationEntity> reservations;

    public enum Status {
        PASSED, OK, WAITING_FOR_GEARCHECK
    }

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

    public Set<ReservationEntity> getReservations() {
        return reservations;
    }

    public void setReservations(Set<ReservationEntity> reservations) {
        this.reservations = reservations;
    }
}
