package com.example.itau.catapi;

public class BreedsResponse extends JsonResponse{
    private CatBreed[] breeds;

    public BreedsResponse(CatBreed[] breeds) {
        super(JsonResponse.STATUS_OK, "");
        this.breeds = breeds;
    }

    public CatBreed[] getBreeds() {
        return breeds;
    }

    public void setBreeds(CatBreed[] breeds) {
        this.breeds = breeds;
    }

    public static String breedNotFoundByName(String name)
    {
        return "{\"status\":\""+STATUS_FAILED+"\",\"errorMessage\":\"Raça de gato "+name+" não existe em nossa base.\"}";
    }

    public static String breedNotFoundByTemperament(String temperament)
    {
        return "{\"status\":\""+STATUS_FAILED+"\",\"errorMessage\":\"Temperamento "+temperament+" não existe em nenhuma raça da nossa base.\"}";
    }

    public static String breedNotFoundByOrigin(String origin)
    {
        return "{\"status\":\""+STATUS_FAILED+"\",\"errorMessage\":\"Não existe nenhuma raça com origem em "+origin+" em nossa base.\"}";
    }

    public static String breedsNotFound()
    {
        return "{\"status\":\""+STATUS_FAILED+"\",\"errorMessage\":\"Não existe nenhuma raça em nossa base.\"}";
    }
}
