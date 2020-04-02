package com.iteco.photoaggregator.model;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.UUID;

@Repository
public interface PhotoMetaDataRepository extends JpaRepository<PhotoMetadataEntity, UUID> {
    Collection<PhotoMetadataEntity> findByPhotographerId(UUID photographerId);
    Page<PhotoMetadataEntity> findByPhotographerId(UUID photographerId, Pageable pageable);
}
