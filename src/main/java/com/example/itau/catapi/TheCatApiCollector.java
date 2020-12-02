package com.example.itau.catapi;

import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;


@Service
public class TheCatApiCollector {

    private final RestTemplate restTemplate;

    public RestTemplate getRestTemplate() {
        return restTemplate;
    }

    public TheCatApiCollector() {
         restTemplate = new RestTemplateBuilder(rt-> rt.getInterceptors().add((request, body, execution) -> {
            request.getHeaders().add("x-api-key", "d40cdc46-df68-4dac-882d-41353c4d4838");
            return execution.execute(request, body);
        })).build();
    }

    public CatBreed[] getBreeds(){
        String url = "https://api.thecatapi.com/v1/breeds";
        return this.restTemplate.getForObject(url, CatBreed[].class);

    }

    public Cat[] getImagesUrls(String breedId){
        String url = "https://api.thecatapi.com/v1/images/search?breed_ids={0}&limit={1}";
        return this.restTemplate.getForObject(url, Cat[].class,breedId,3);
    }

    public Cat[] getImagesUrlsWithSunglasses(){
        String url = "https://api.thecatapi.com/v1/images/search?category_ids={0}&limit={1}";
        return this.restTemplate.getForObject(url, Cat[].class,4,3);
    }

    public Cat[] getImagesUrlsWithHats(){
        String url = "https://api.thecatapi.com/v1/images/search?category_ids={0}&limit={1}";
        return this.restTemplate.getForObject(url, Cat[].class,1,3);
    }
}
