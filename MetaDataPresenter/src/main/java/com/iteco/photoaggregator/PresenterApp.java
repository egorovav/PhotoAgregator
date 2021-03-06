package com.iteco.photoaggregator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class PresenterApp {

    //@Autowired
    //private TestDataGenerator dataGenerator;

    public static void main( String[] args ) {
        ConfigurableApplicationContext context =
                SpringApplication.run(PresenterApp.class, args);
    }

//    @Bean
//    CommandLineRunner commandLineRunner(ApplicationContext ctx) {
//        return args -> {
//            dataGenerator.deleteData();
//            dataGenerator.createData(100);
//        };
//    }
}
