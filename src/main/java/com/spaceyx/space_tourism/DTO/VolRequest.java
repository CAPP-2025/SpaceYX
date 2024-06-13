package com.spaceyx.space_tourism.DTO;


import java.time.LocalDateTime;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;


public class VolRequest {

    @Column(nullable = false)
    @NotNull(message = "Date is required")
    private LocalDateTime dateTime;

    @NotNull(message = "Navette ID is required")
    private Long navetteId;

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public void setDateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }

    public Long getNavetteId() {
        return navetteId;
    }

    public void setNavetteId(Long navetteId) {
        this.navetteId = navetteId;
    }
}
