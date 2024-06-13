package com.spaceyx.space_tourism.model;

import jakarta.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

@Entity
@Table(name = "reservation")
public class ReservationEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "vol_id", nullable = false)
    @JsonIgnore
    private VolEntity vol;

    @Column(name = "id_vol")
    private Long vId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public VolEntity getVol() {
        return vol;
    }

    public void setVol(VolEntity vol) {
        this.vol = vol;
    }

    public Long getVolId() {
        return vId;
    }

    public void setVolId(Long volId) {
        this.vId = volId;
    }
}

