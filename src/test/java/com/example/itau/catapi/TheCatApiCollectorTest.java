package com.example.itau.catapi;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.web.client.RestTemplate;

import java.util.Random;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class TheCatApiCollectorTest {


    @Autowired
    private TheCatApiCollector collector;

    @Test
    void restTemplateConstructor() {
        assertThat(collector.getRestTemplate()).isInstanceOf(RestTemplate.class);
    }

    @Test
    void getBreeds() {
        assertThat(collector.getBreeds()).isInstanceOf(CatBreed[].class);
    }

    @Test
    void getImagesUrls(){
        assertThat(collector.getImagesUrls(StringUtils.getRandomString())).isInstanceOf(Cat[].class);
    }

    @Test
    void getImagesUrlsWithSunglasses(){
        assertThat(collector.getImagesUrlsWithSunglasses()).isInstanceOf(Cat[].class);
    }

    @Test
    void getImagesUrlsWithHats(){
        assertThat(collector.getImagesUrlsWithHats()).isInstanceOf(Cat[].class);
    }

}
