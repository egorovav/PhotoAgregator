package com.iteco.photoaggregator;

import com.iteco.photoaggregator.model.PhotoMetadataEntity;
import com.iteco.photoaggregator.model.PhotoMetaDataRepository;
import com.iteco.photoaggregator.model.PhotographerEntity;
import com.iteco.photoaggregator.model.PhotographerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Component
public class TestDataGenerator {


    private final PhotographerRepository photographerRepository;
    private final PhotoMetaDataRepository photoMetaDataRepository;

    @Autowired
    public TestDataGenerator(PhotographerRepository photographerRepository, PhotoMetaDataRepository photoMetaDataRepository) {
        this.photographerRepository = photographerRepository;
        this.photoMetaDataRepository = photoMetaDataRepository;
    }

    public void deleteData() {
        photoMetaDataRepository.deleteAllInBatch();
        photographerRepository.deleteAllInBatch();
    }

    public long createData(int multiplier) {
        long totalRows = 0;

        List<PhotographerEntity> photographers = new ArrayList<>();
        for(int i = 0; i < multiplier; i++) {
            photographers.add(new PhotographerEntity("Photographer " + i));
        }

        photographerRepository.saveAll(photographers);
        photographers = photographerRepository.findAll();
        totalRows += photographers.size();
        List<PhotoMetadataEntity> photosMetaData = new ArrayList<>();
        for(PhotographerEntity photographer : photographers) {
            for(int i = 0; i < multiplier; i++) {
                photosMetaData.add(new PhotoMetadataEntity(
                            photographer.getId(),
                            "Photo " + i + " by " + photographer.getName(),
                            0.0,
                            0.0,
                            Timestamp.valueOf(LocalDateTime.now())
                        ));
            }
        }

        photoMetaDataRepository.saveAll(photosMetaData);
        photosMetaData = photoMetaDataRepository.findAll();
        totalRows += photosMetaData.size();

        return totalRows;
    }
}
