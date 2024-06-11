package com.spaceyx.space_tourism.repository;

import com.spaceyx.space_tourism.model.ReservationEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReservationRepository extends JpaRepository<ReservationEntity, Long> {

}

