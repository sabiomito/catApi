package com.example.itau.catapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.sql.*;


@SpringBootApplication
@RestController
public class CatApiApplication {

    public static void main(String[] args) {
        connect();
        SpringApplication.run(CatApiApplication.class, args);
    }
    @GetMapping("/hello")
    public String hello(@RequestParam(value = "name", defaultValue = "World") String name) {
        return String.format("Hello %s!", name);
    }

    @GetMapping("/hello2")
    public String hello2() {
        return "Sera q vai ?";
    }

    @GetMapping("/error")
    public String handleError() {
        //do something like logging
        return "error";
    }

    private static void connect() {
        try (Connection connection = DriverManager.getConnection("jdbc:sqlite:catDB.db")) {

            System.out.println("Conexão realizada !!!!");
            Statement statement = connection.createStatement();
            statement.execute("CREATE TABLE IF NOT EXISTS CAT_BREED(origin TEXT NOT NULL,temperament TEXT NOT NULL, ID INTEGER,name TEXT, PRIMARY KEY(ID AUTOINCREMENT))");
            statement.execute("CREATE TABLE IF NOT EXISTS CAT_IMAGE ( ID INTEGER NOT NULL, BREED INTEGER NOT NULL, url TEXT NOT NULL, object TEXT, PRIMARY KEY(ID AUTOINCREMENT), FOREIGN KEY(BREED) REFERENCES CAT_BREED)");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

}
