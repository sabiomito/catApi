package com.example.itau.catapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.sql.*;


@SpringBootApplication
@RestController
public class CatApiApplication {

    public static void main(String[] args) {
        //connect();
        TheCatApiCollector catApiGetter = new TheCatApiCollector();
        CatDataBase catDataBase = new CatDataBase();
        for (CatBreed breed: catApiGetter.getBreeds()) {
            System.out.println(breed.toString());
            catDataBase.insertBreed(breed);
        }
        SpringApplication.run(CatApiApplication.class, args);
    }
    @GetMapping("/hello")
    public String hello(@RequestParam(value = "name", defaultValue = "World") String name) {
        return String.format("Hello %s!", name);
    }

}
