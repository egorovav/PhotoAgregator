package com.iteco.photoaggregator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

@Component
public class AppInit implements ApplicationRunner {

    private final TestDataGenerator dataGenerator;

    @Autowired
    public AppInit(TestDataGenerator dataGenerator) {
        this.dataGenerator = dataGenerator;
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
        dataGenerator.deleteData();
        dataGenerator.createData(100);
    }
}
