package com.spaceyx.space_tourism.repository;

import com.spaceyx.space_tourism.model.RevisionEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RevisionRepository extends JpaRepository<RevisionEntity, Long> {
}
