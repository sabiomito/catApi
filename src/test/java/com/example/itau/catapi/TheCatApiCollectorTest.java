package com.example.itau.catapi;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.web.client.RestTemplate;

import java.util.Random;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class TheCatApiCollectorTest {

    private String getRandomString()
    {
        int leftLimit = 48; // numeral '0'
        int rightLimit = 122; // letter 'z'
        int targetStringLength = 10;
        Random random = new Random();

        String randomString = random.ints(leftLimit, rightLimit + 1)
                .filter(i -> (i <= 57 || i >= 65) && (i <= 90 || i >= 97))
                .limit(targetStringLength)
                .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
                .toString();
        return randomString;
    }

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
        assertThat(collector.getImagesUrls(getRandomString())).isInstanceOf(Cat[].class);
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
