package com.spaceyx.space_tourism.model;


import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
public class RevisionEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private LocalDate date;

    @ManyToOne
    @JoinColumn(name = "navette_id", nullable = false)
    private NavetteEntity navette;

    // Getters and Setters

    public RevisionEntity() {}

    public RevisionEntity(LocalDate date, NavetteEntity navette) {
        this.date = date;
        this.navette = navette;
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
}
