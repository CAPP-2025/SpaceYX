package com.spaceyx.space_tourism.DTO;

import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;

public class RevisionRequest {
    @NotNull(message = "Date is required")
    private LocalDate date;

    @NotNull(message = "Navette ID is required")
    private Long navetteId;

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public Long getNavetteId() {
        return navetteId;
    }

    public void setNavetteId(Long navetteId) {
        this.navetteId = navetteId;
    }
}
