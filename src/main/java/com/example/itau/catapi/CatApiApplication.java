package com.example.itau.catapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.*;
import java.sql.*;
import java.util.Collections;


@SpringBootApplication
@RestController
public class CatApiApplication {

    public static void main(String[] args) {
        SpringApplication app = new SpringApplication(CatApiApplication.class);
        app.setDefaultProperties(Collections
                .singletonMap("server.port", "80"));
        app.run(args);
        
        SpringApplication.run(CatApiApplication.class, args);
    }

    private Boolean isStringNumberAndLettersOnly(String str){
        for(Character letter : str.toCharArray())
        {
            if(!Character.isLetterOrDigit(letter))
            {
                return false;
            }
        }
        return true;
    }

    @GetMapping("/getDataFromTheCatApi")
    public String getDataFromTheCatApi(@RequestHeader("X-API-KEY")  String value) {
        if(!ApiKeyManager.validateApiKey((value)))
        {
            return JsonResponse.getApiKeyError();//TODO: implementar um interceptor para que esse codigo nao se repita entre cada url da api
        }
        try {
            TheCatApiCollector catApiGetter = new TheCatApiCollector();
            CatDataBase catDataBase = new CatDataBase();
            for (Cat cat : catApiGetter.getImagesUrlsWithHats()) {
                System.out.println(cat.toString());
                catDataBase.insertCatImageUrl(cat);
            }
            for (Cat cat : catApiGetter.getImagesUrlsWithSunglasses()) {
                System.out.println(cat.toString());
                catDataBase.insertCatImageUrl(cat);
            }
            for (CatBreed breed : catApiGetter.getBreeds()) {
                System.out.println(breed.toString());
                catDataBase.insertBreed(breed);
                for (Cat cat : catApiGetter.getImagesUrls(breed.getName())) {
                    System.out.println(cat.toString());
                    catDataBase.insertCatImageUrl(cat);
                }
            }
           return new JsonResponse(JsonResponse.STATUS_OK,"").toString();
        }catch (SQLException e)
        {
            return new JsonResponse(JsonResponse.STATUS_FAILED,e.getMessage()).toString();
        }
    }

    @GetMapping("/breeds")
    public String getBreeds(@RequestHeader("X-API-KEY")  String value) {
        if(!ApiKeyManager.validateApiKey((value)))
        {
            return JsonResponse.getApiKeyError();//TODO: implementar um interceptor para que esse codigo nao se repita entre cada url da api
        }
        try {
            CatDataBase catDataBase = new CatDataBase();
            CatBreed[] breedsFound = catDataBase.getBreeds();
            if(breedsFound.length == 0)
            {
                return BreedsResponse.breedsNotFound();
            }
            return new BreedsResponse(breedsFound).toString();
        }catch (SQLException e)
        {
            return new JsonResponse(JsonResponse.STATUS_FAILED,e.getMessage()).toString();
        }
    }

    @GetMapping("/breedByName")
    public String getBreedByName(@RequestParam(value = "name", defaultValue = "") String name,@RequestHeader("X-API-KEY")  String value) {
        if(!ApiKeyManager.validateApiKey((value)))
        {
            return JsonResponse.getApiKeyError();//TODO: implementar um interceptor para que esse codigo nao se repita entre cada url da api
        }
        if(!isStringNumberAndLettersOnly(name))
        {
            return JsonResponse.invalidArgument("name");
        }
        try {
            CatDataBase catDataBase = new CatDataBase();
            CatBreed breedFound = catDataBase.getBreedByName(name);
            if(breedFound == CatDataBase.BREED_NOT_FOUND)
            {
                return BreedsResponse.breedNotFoundByName(name);
            }
            return new BreedsResponse(new CatBreed[]{breedFound}).toString();
        }catch (SQLException e)
        {
            return new JsonResponse(JsonResponse.STATUS_FAILED,e.getMessage()).toString();
        }
    }

    @GetMapping("/breedsBytemperament")
    public String getBreedsByTemperament(@RequestParam(value = "temperament", defaultValue = "") String temperament,@RequestHeader("X-API-KEY")  String value) {
        if(!ApiKeyManager.validateApiKey((value)))
        {
            return JsonResponse.getApiKeyError();//TODO: implementar um interceptor para que esse codigo nao se repita entre cada url da api
        }
        if(!isStringNumberAndLettersOnly(temperament))
        {
            return JsonResponse.invalidArgument("temperament");
        }
        try {
            CatDataBase catDataBase = new CatDataBase();
            CatBreed[] breedsFound = catDataBase.getBreedsByTemperament(temperament);
            if(breedsFound.length == 0)
            {
                return BreedsResponse.breedNotFoundByTemperament(temperament);
            }
            return new BreedsResponse(breedsFound).toString();
        }catch (SQLException e)
        {
            return new JsonResponse(JsonResponse.STATUS_FAILED,e.getMessage()).toString();
        }
    }


    @GetMapping("/breedsByOrigin")
    public String getBreedsByOrigin(@RequestParam(value = "origin", defaultValue = "") String origin,@RequestHeader("X-API-KEY")  String value) {
        if(!ApiKeyManager.validateApiKey((value)))
        {
            return JsonResponse.getApiKeyError();//TODO: implementar um interceptor para que esse codigo nao se repita entre cada url da api
        }
        if(!isStringNumberAndLettersOnly(origin))
        {
            return JsonResponse.invalidArgument("origin");
        }
        try {
            CatDataBase catDataBase = new CatDataBase();
            CatBreed[] breedsFound = catDataBase.getBreedsByOrigin(origin);
            if(breedsFound.length == 0)
            {
                return BreedsResponse.breedNotFoundByOrigin(origin);
            }
            return new BreedsResponse(breedsFound).toString();
        }catch (SQLException e)
        {
            return new JsonResponse(JsonResponse.STATUS_FAILED,e.getMessage()).toString();
        }
    }


    /*@GetMapping(value = "/welcome", produces = MediaType.TEXT_HTML_VALUE)
    @ResponseBody
    public String welcomeAsHTML() {
        return "<html>\n" + "<header><title>Welcome</title></header>\n" +
                "<body>\n" + "Hello world\n" + "</body>\n" + "</html>";
    }*/

}
