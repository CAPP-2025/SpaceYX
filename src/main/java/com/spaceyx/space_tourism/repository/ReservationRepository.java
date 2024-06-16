package com.spaceyx.space_tourism.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.spaceyx.space_tourism.model.entity.ReservationEntity;

public interface ReservationRepository extends JpaRepository<ReservationEntity, Long> {

}
