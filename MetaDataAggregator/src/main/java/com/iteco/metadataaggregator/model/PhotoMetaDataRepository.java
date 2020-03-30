package com.iteco.metadataaggregator.model;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface PhotoMetaDataRepository extends JpaRepository<PhotoMetaDataEntity, UUID> {
}
