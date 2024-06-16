package com.spaceyx.space_tourism.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.spaceyx.space_tourism.model.entity.RevisionEntity;

public interface RevisionRepository extends JpaRepository<RevisionEntity, Long> {
}
