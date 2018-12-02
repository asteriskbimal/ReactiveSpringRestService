package com.reactive.couchbase;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Application implements CommandLineRunner {

    public static void main(String[] args){

        SpringApplication application = new SpringApplication(Application.class);
        application.run("");

    }

    @Override
    public void run(String... args) {

    }

}
