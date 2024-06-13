package com.spaceyx.space_tourism.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import java.util.Set;

@Entity
@Table(name = "navette")
public class NavetteEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "navette_id")
    @JsonProperty(access = Access.READ_ONLY)
    private Long id;

    @NotNull(message = "Nom is required")
    @NotBlank(message = "Nom is required")
    @Column(nullable = false)
    private String nom;

    @Min(value = 3, message = "Capacite must be at least 3")
    @Max(value = 5, message = "Capacite must be at most 5")
    @NotNull(message = "Capacite is required")
    @Column(nullable = false)
    private int capacite;


    @NotNull(message = "Status is required")
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Status status;

    @OneToMany(mappedBy = "navette", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonProperty(access = Access.READ_ONLY)
    private Set<RevisionEntity> revisions;

    @OneToMany(mappedBy = "navette", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonIgnore
    private Set<VolEntity> vols;

    public enum Status {
        OK, OBSOLETE
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public int getCapacite() {
        return capacite;
    }

    public void setCapacite(int capacite) {
        this.capacite = capacite;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public Set<RevisionEntity> getRevisions() {
        return revisions;
    }

    public void setRevisions(Set<RevisionEntity> revisions) {
        this.revisions = revisions;
    }

    public Set<VolEntity> getVols() {
        return vols;
    }

    public void setVols(Set<VolEntity> vols) {
        this.vols = vols;
    }
}
