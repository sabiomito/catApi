package com.example.itau.catapi;

import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;


@Service
public class TheCatApiCollector {

    private final RestTemplate restTemplate;

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

    public String testApi(){
        String url = "https://api.thecatapi.com/v1/breeds?api";
        return this.restTemplate.getForObject(url, String.class);
    }

    public void testApiRequest(){
        System.out.println(getBreeds()[0].toString());
    }
}
