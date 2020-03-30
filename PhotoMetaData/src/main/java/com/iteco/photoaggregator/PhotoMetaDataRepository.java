package com.iteco.photoaggregator;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.UUID;

@Repository
public interface PhotoMetaDataRepository extends JpaRepository<PhotoMetaDataEntity, UUID> {

    Collection<PhotoMetaDataEntity> findByPhotographerId(UUID photographerId);
}