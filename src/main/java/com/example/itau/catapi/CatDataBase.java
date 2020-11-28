package com.example.itau.catapi;

import java.sql.*;

public class CatDataBase {

    CatDataBase()
    {
        initializeDB();
    }

    private void initializeDB() {
        try (Connection connection = DriverManager.getConnection("jdbc:sqlite:catDB.db")) {
            Statement statement = connection.createStatement();
            statement.execute("CREATE TABLE IF NOT EXISTS CAT_BREED(origin TEXT NOT NULL,temperament TEXT NOT NULL, ID INTEGER,name TEXT, PRIMARY KEY(ID AUTOINCREMENT))");
            statement.execute("CREATE TABLE IF NOT EXISTS CAT_IMAGE ( ID INTEGER NOT NULL, url TEXT NOT NULL, object TEXT, PRIMARY KEY(ID AUTOINCREMENT))");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }


    public void insertBreed(CatBreed breed)
    {
        try (Connection connection = DriverManager.getConnection("jdbc:sqlite:catDB.db")) {
            PreparedStatement statement = connection.prepareStatement("INSERT INTO CAT_BREED( origin, temperament, name) VALUES (?,?,?)");
            statement.setString(1, breed.getOrigin());
            statement.setString(2, breed.getTemperament());
            statement.setString(3, breed.getName());
            statement.execute();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public void insertCatImageUrl(Cat cat)
    {
        try (Connection connection = DriverManager.getConnection("jdbc:sqlite:catDB.db")) {
            PreparedStatement statement = connection.prepareStatement("INSERT INTO CAT_IMAGE( url , object) VALUES (?,?)");
            statement.setString(1, cat.getUrl());
            statement.setString(2, cat.getObject());
            statement.execute();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

   
}
