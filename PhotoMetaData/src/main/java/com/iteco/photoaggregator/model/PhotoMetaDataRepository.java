package com.iteco.photoaggregator.model;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.List;
import java.util.UUID;

@Repository
public interface PhotoMetaDataRepository extends JpaRepository<PhotoMetaDataEntity, UUID> {
    Collection<PhotoMetaDataEntity> findByPhotographerId(UUID photographerId);
}
