package com.example.itau.catapi;

import org.springframework.stereotype.Service;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Service
public class CatDataBase {

    public static CatBreed BREED_NOT_FOUND = new CatBreed("NotFound","NotFound","NotFound");

    public CatDataBase() throws SQLException
    {
        initializeDB();
    }

    private void initializeDB() throws SQLException {
        Connection connection = DriverManager.getConnection("jdbc:sqlite:catDB.db");
        Statement statement = connection.createStatement();
        statement.execute("CREATE TABLE IF NOT EXISTS CAT_BREED(origin TEXT NOT NULL,temperament TEXT NOT NULL, ID INTEGER,name TEXT, PRIMARY KEY(ID AUTOINCREMENT))");
        statement.execute("CREATE TABLE IF NOT EXISTS CAT_IMAGE ( ID INTEGER NOT NULL, url TEXT NOT NULL, object TEXT, PRIMARY KEY(ID AUTOINCREMENT))");
    }


    public void insertBreed(CatBreed breed) throws SQLException
    {
        Connection connection = DriverManager.getConnection("jdbc:sqlite:catDB.db");
        PreparedStatement statement = connection.prepareStatement("INSERT INTO CAT_BREED( origin, temperament, name) VALUES (?,?,?)");
        statement.setString(1, breed.getOrigin());
        statement.setString(2, breed.getTemperament());
        statement.setString(3, breed.getName());
        statement.execute();
    }

    public void insertCatImageUrl(Cat cat) throws SQLException
    {
        Connection connection = DriverManager.getConnection("jdbc:sqlite:catDB.db");
        PreparedStatement statement = connection.prepareStatement("INSERT INTO CAT_IMAGE( url , object) VALUES (?,?)");
        statement.setString(1, cat.getUrl());
        statement.setString(2, cat.getObject());
        statement.execute();
    }

    public CatBreed[] getBreeds() throws SQLException
    {
        Connection connection = DriverManager.getConnection("jdbc:sqlite:catDB.db");
        PreparedStatement stmt = connection.prepareStatement("select * from CAT_BREED");
        ResultSet resultSet = stmt.executeQuery();
        return parseResultSetToCatBreedArray(resultSet);
    }

    public CatBreed getBreedInfo(String breedName) throws SQLException
    {
        Connection connection = DriverManager.getConnection("jdbc:sqlite:catDB.db");
        PreparedStatement stmt = connection.prepareStatement("select * from CAT_BREED WHERE name = ?");
        stmt.setString(1,breedName);
        ResultSet resultSet = stmt.executeQuery();
        if (resultSet.next()){
            CatBreed breed = new CatBreed(resultSet.getString("origin"),resultSet.getString("name"),resultSet.getString("temperament"));
            System.out.println( breed.toString());
            return breed;
        }else
        {
            return BREED_NOT_FOUND;
        }
    }

    public CatBreed[] getBreedsByTemperament(String temperament) throws SQLException
    {
        Connection connection = DriverManager.getConnection("jdbc:sqlite:catDB.db");
        PreparedStatement stmt = connection.prepareStatement("select * from CAT_BREED WHERE temperament LIKE ?");
        stmt.setString(1,"%"+temperament+"%");
        ResultSet resultSet = stmt.executeQuery();
        return parseResultSetToCatBreedArray(resultSet);
    }

    public CatBreed[] getBreedsByorigin(String origin) throws SQLException
    {
        Connection connection = DriverManager.getConnection("jdbc:sqlite:catDB.db");
        PreparedStatement stmt = connection.prepareStatement("select * from CAT_BREED WHERE origin = ?");
        stmt.setString(1,origin);
        ResultSet resultSet = stmt.executeQuery();
        return parseResultSetToCatBreedArray(resultSet);
    }

    private CatBreed[] parseResultSetToCatBreedArray(ResultSet resultSet) throws SQLException
    {
        List<CatBreed> results = new ArrayList<CatBreed>();
        while (resultSet.next()) {
            CatBreed breed = new CatBreed(resultSet.getString("origin"),resultSet.getString("name"),resultSet.getString("temperament"));
            results.add(breed);
            System.out.println( breed.toString());
        }
        CatBreed[] resultArray = new CatBreed[results.size()];
        for(int i=0;i<results.size();i++)
        {
            resultArray[i] = results.get(i);
        }
        return resultArray;
    }

}
