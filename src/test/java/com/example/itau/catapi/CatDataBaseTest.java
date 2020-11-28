package com.example.itau.catapi;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.web.client.RestTemplate;

import java.sql.SQLException;
import java.util.Random;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.fail;

@SpringBootTest
class CatDataBaseTest {


    @Autowired
    private CatDataBase dataBase;

    private Cat getRandomCat()
    {
        Cat cat = new Cat();
        cat.setObject(StringUtils.getRandomString());
        cat.setBreed(StringUtils.getRandomString());
        cat.setId(StringUtils.getRandomString());
        cat.setUrl(StringUtils.getRandomString());
        return cat;
    }

    @Test
    void CatDataBaseConstructor() {
        try{
            CatDataBase data = new CatDataBase();
        }catch (SQLException e){
            fail(e.toString());
        }
    }

    @Test
    void insertCatImageUrl() {
        try{
            dataBase.insertCatImageUrl(getRandomCat());
        }catch (SQLException e){
            fail(e.toString());
        }
    }

    @Test
    void getBreeds() {
        try{
            assertThat(dataBase.getBreeds()).isInstanceOf(CatBreed[].class);
        }catch (SQLException e){
            fail(e.toString());
        }
    }

    @Test
    void  getBreedInfo(){//CatBreed
        try{
            dataBase.getBreedInfo(StringUtils.getRandomString());
        }catch (SQLException e){
            fail(e.toString());
        }
    }

    @Test
    void  getBreedsByTemperament() {//CatBreed[]
        throw new UnsupportedOperationException();
    }

    @Test
    void  getBreedsByorigin() {//CatBreed[]
        throw new UnsupportedOperationException();
    }


}
