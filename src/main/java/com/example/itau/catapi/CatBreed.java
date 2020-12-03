package com.example.itau.catapi;

import java.io.Serializable;

public class CatBreed implements Serializable {
    private String origin;
    private String name;
    private String temperament;
    private String id;

    public CatBreed()
    {
        origin = "";
        name = "";
        temperament = "";
    }

    public CatBreed(String origin, String name, String temperament) {
        this.origin = origin;
        this.name = name;
        this.temperament = temperament;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getOrigin() {
        return origin;
    }

    public void setOrigin(String origin) {
        this.origin = origin;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTemperament() {
        return temperament;
    }

    public void setTemperament(String temperament) {
        this.temperament = temperament;
    }

    @Override
    public String toString() {
        return "CatBreed{" +
                "origin='" + origin + '\'' +
                ", name='" + name + '\'' +
                ", temperament='" + temperament + '\'' +
                '}';
    }
}
