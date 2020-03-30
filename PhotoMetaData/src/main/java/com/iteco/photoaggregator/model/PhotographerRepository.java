package com.iteco.photoaggregator.model;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface PhotographerRepository extends JpaRepository<PhotographerEntity, UUID> {
}
