package com.iteco.photoaggregator;

import com.iteco.photoaggregator.model.PhotoMetaDataRepository;
import com.iteco.photoaggregator.model.PhotographerEntity;
import com.iteco.photoaggregator.model.PhotographerRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;
import java.util.UUID;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = PresenterApp.class)
public class PresenterTest {

    private final int multiplier = 3;
    private long rowCount;

    @Autowired
    private TestDataGenerator dataGenerator;

    @Autowired
    private PhotographerRepository photographerRepository;

    @Autowired
    private PhotoMetaDataRepository photoMetaDataRepository;

    @Before
    public void init() {
        dataGenerator.deleteData();
        rowCount = dataGenerator.createData(multiplier);
    }

    @Test
    public void testDataGeneratorTest() {
        assertEquals(multiplier + multiplier * multiplier, rowCount);
    }

    @Test
    public void photographersFindAllTest(){
        List<PhotographerEntity> photographers = photographerRepository.findAll();
        int photographersCount = photographers.size();
        assertEquals(multiplier, photographersCount);
    }

    @Test
    public void photoMetaDataFindAll() {
        int photoMetaDataCount = photoMetaDataRepository.findAll().size();
        assertEquals(multiplier * multiplier, photoMetaDataCount);
    }

    @Test
    public void photoMetaDataByPhotographerTest() {
        UUID photographerId = photographerRepository.findAll().get(0).getId();
        int photoByPhotographerCount = photoMetaDataRepository.findByPhotographerId(photographerId).size();
        assertEquals(multiplier, photoByPhotographerCount);
    }
}
