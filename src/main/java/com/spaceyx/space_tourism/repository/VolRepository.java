package com.spaceyx.space_tourism.repository;

import com.spaceyx.space_tourism.model.VolEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VolRepository extends JpaRepository<VolEntity, Long> {
}
