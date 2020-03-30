package com.iteco.metadataaggregator;

import com.iteco.metadataaggregator.model.TestDataGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class App 
{
    @Autowired
    private TestDataGenerator dataGenerator;

    public static void main( String[] args ) {
        SpringApplication.run(App.class, args);
    }

    @Bean
    CommandLineRunner commandLineRunner(ApplicationContext ctx) {
        return args -> {
            dataGenerator.deleteData();
            dataGenerator.createData(3);
        };
    }
}
