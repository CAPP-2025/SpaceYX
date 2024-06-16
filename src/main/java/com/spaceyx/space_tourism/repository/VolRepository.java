package com.spaceyx.space_tourism.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.spaceyx.space_tourism.model.entity.VolEntity;

@Repository
public interface VolRepository extends JpaRepository<VolEntity, Long> {
}
