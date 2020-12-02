package com.example.itau.catapi;

import java.util.ArrayList;
import java.util.Arrays;

//As apis podem ser geradas e armazenadas em um banco e atreladas a um usuário mas decidi colocar apenas a chaves de teste
public class ApiKeyManager {
    private static ArrayList<String> keys = new ArrayList<>(Arrays.asList("PUBLIC_TEST_API_KEY"));;

    public static Boolean validateApiKey(String key)
    {
        return keys.contains(key);
    }

}
