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

    private CatBreed getRandomBreed()
    {
        CatBreed breed = new CatBreed();
        breed.setTemperament(StringUtils.getRandomString());
        breed.setOrigin(StringUtils.getRandomString());
        breed.setName(StringUtils.getRandomString());
        return breed;
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
    void insertBreed() {
        try{
            dataBase.insertBreed(getRandomBreed());
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
    void  getBreedInfo(){
        try{
            dataBase.getBreedInfo(StringUtils.getRandomString());
        }catch (SQLException e){
            fail(e.toString());
        }
    }

    @Test
    void  getBreedsByTemperament() {
        try{
            dataBase.getBreedsByTemperament("friendly");
        }catch (SQLException e){
            fail(e.toString());
        }
    }

    @Test
    void  getBreedsByorigin() {
        try{
            dataBase.getBreedsByorigin(StringUtils.getRandomString());
        }catch (SQLException e){
            fail(e.toString());
        }
    }


}
