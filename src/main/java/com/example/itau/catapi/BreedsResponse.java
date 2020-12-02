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
}
