package com.example.itau.catapi;

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

    public static void main(String[] args) {
        SpringApplication.run(CatApiApplication.class, args);
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
           return new JsonResponse(JsonResponse.STATUS_OK,"").toString();
        }catch (SQLException e)
        {
            return new JsonResponse(JsonResponse.STATUS_FAILED,e.getMessage()).toString();
        }
    }

    @GetMapping("/breeds")
    public String getBreeds(@RequestParam(value = "name", defaultValue = "") String name,@RequestParam(value = "temperament", defaultValue = "") String temperament,@RequestParam(value = "origin", defaultValue = "") String origin) {
        if(!name.equals("") || !temperament.equals("") || !origin.equals(""))
        {
            return "Não implementado ainda name="+name+" temperament="+temperament+" origin="+origin;
        }
        try {
            CatDataBase catDataBase = new CatDataBase();
            return new BreedsResponse(catDataBase.getBreeds()).toString();
        }catch (SQLException e)
        {
            return String.format("ERROR -- "+e);
        }
    }

    @GetMapping("/breedByName")
    public String getBreedByName(@RequestParam(value = "name", defaultValue = "") String name) {

        try {
            CatDataBase catDataBase = new CatDataBase();
            return new BreedsResponse(new CatBreed[]{catDataBase.getBreedByName(name)}).toString();
        }catch (SQLException e)
        {
            return new JsonResponse(JsonResponse.STATUS_FAILED,e.getMessage()).toString();
        }
    }

    @GetMapping("/breedsBytemperament")
    public String getBreedsByTemperament(@RequestParam(value = "temperament", defaultValue = "") String temperament) {
        try {
            CatDataBase catDataBase = new CatDataBase();
            return new BreedsResponse(catDataBase.getBreedsByTemperament(temperament)).toString();
        }catch (SQLException e)
        {
            return new JsonResponse(JsonResponse.STATUS_FAILED,e.getMessage()).toString();
        }
    }


    @GetMapping("/breedsByOrigin")
    public String getBreedsByOrigin(@RequestParam(value = "origin", defaultValue = "") String origin) {
        try {
            CatDataBase catDataBase = new CatDataBase();
            return new BreedsResponse(catDataBase.getBreedsByOrigin(origin)).toString();
        }catch (SQLException e)
        {
            return new JsonResponse(JsonResponse.STATUS_FAILED,e.getMessage()).toString();
        }
    }


    @GetMapping(value = "/welcome", produces = MediaType.TEXT_HTML_VALUE)
    @ResponseBody
    public String welcomeAsHTML() {
        return "<html>\n" + "<header><title>Welcome</title></header>\n" +
                "<body>\n" + "Hello world\n" + "</body>\n" + "</html>";
    }

}
