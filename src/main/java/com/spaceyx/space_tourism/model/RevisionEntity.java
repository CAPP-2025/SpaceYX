package com.spaceyx.space_tourism.model;


import jakarta.persistence.*;
import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "revision")
public class RevisionEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private LocalDate date;

    @ManyToOne
    @JoinColumn(name = "navette_id", nullable = false)
    @JsonIgnore
    private NavetteEntity navette;

    @Column(name = "id_navette")
    private Long nId;

    // Getters and Setters

    public RevisionEntity() {}

    public RevisionEntity(LocalDate date, NavetteEntity navette, Long navetteId) {
        this.date = date;
        this.navette = navette;
        this.nId = navetteId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public NavetteEntity getNavette() {
        return navette;
    }

    public void setNavette(NavetteEntity navette) {
        this.navette = navette;
    }

    public Long getNavetteId() {
        return nId;
    }

    public void setNavetteId(Long navetteId) {
        this.nId = navetteId;
    }
}
