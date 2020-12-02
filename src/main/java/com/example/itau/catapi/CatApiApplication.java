package com.example.itau.catapi;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.sql.*;


@SpringBootApplication
@RestController
public class CatApiApplication {

    ObjectMapper jsonMapper = new ObjectMapper();

    public static void main(String[] args) {
        SpringApplication.run(CatApiApplication.class, args);
    }

    @GetMapping("/hello")
    public String hello(@RequestParam(value = "name", defaultValue = "World") String name) {
        return String.format("Hello %s!", name);
    }

    @GetMapping("/getDataFromTheCatApi")
    public String getDataFromTheCatApi() {
        try {
            TheCatApiCollector catApiGetter = new TheCatApiCollector();
            CatDataBase catDataBase = new CatDataBase();
            for (Cat cat : catApiGetter.getImagesUrlsWithHats()) {
                System.out.println(cat.toString());
                catDataBase.insertCatImageUrl(cat);
            }
            for (CatBreed breed : catApiGetter.getBreeds()) {
                System.out.println(breed.toString());
                catDataBase.insertBreed(breed);
            }
            return "SUCESS";
        }catch (SQLException e)
        {
            return String.format("ERROR -- "+e);
        }
    }

    @GetMapping("/breeds")
    public String getBreeds(@RequestParam(value = "name", defaultValue = "") String name,@RequestParam(value = "temperament", defaultValue = "") String temperament,@RequestParam(value = "origin", defaultValue = "") String origin) {
        if(!name.equals("") || !temperament.equals("") || !origin.equals(""))
        {
            return "Não implementado ainda name="+name+" temperament="+temperament+" origin="+origin;
        }
        try {
            String result = "";
            CatDataBase catDataBase = new CatDataBase();
            for (CatBreed breed : catDataBase.getBreeds()) {
                result += breed.toString()+"<br>";
            }
            return result;
        }catch (SQLException e)
        {
            return String.format("ERROR -- "+e);
        }
    }

    @GetMapping("/breedByName")
    public String getBreedByName(@RequestParam(value = "name", defaultValue = "") String name) {

        try {
            CatDataBase catDataBase = new CatDataBase();
            return catDataBase.getBreedByName(name).toString();
        }catch (SQLException e)
        {
            return String.format("ERROR -- "+e);
        }
    }

    @GetMapping("/breedsBytemperament")
    public String getBreedsByTemperament(@RequestParam(value = "temperament", defaultValue = "") String temperament) {
        try {
            String result = "";
            CatDataBase catDataBase = new CatDataBase();
            for (CatBreed breed : catDataBase.getBreedsByTemperament(temperament)) {
                result += breed.toString()+"<br>";
            }
            return result;
        }catch (SQLException e)
        {
            return String.format("ERROR -- "+e);
        }
    }


    @GetMapping("/breedsByOrigin")
    public String getBreedsByOrigin(@RequestParam(value = "origin", defaultValue = "") String origin) {
        try {
            String result = "";
            CatDataBase catDataBase = new CatDataBase();
            for (CatBreed breed : catDataBase.getBreedsByOrigin(origin)) {
                result += breed.toString()+"<br>";
            }
            return result;
        }catch (SQLException e)
        {
            return String.format("ERROR -- "+e);
        }
    }


    @GetMapping(value = "/welcome", produces = MediaType.TEXT_HTML_VALUE)
    @ResponseBody
    public String welcomeAsHTML() {
        return "<html>\n" + "<header><title>Welcome</title></header>\n" +
                "<body>\n" + "Hello world\n" + "</body>\n" + "</html>";
    }

}
