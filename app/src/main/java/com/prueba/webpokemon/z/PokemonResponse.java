package com.prueba.webpokemon.z;

//Modelo para mostrar la respuesta de los ele,entos que se vayan bajando
public class PokemonResponse {

    private String name;
    private String url;

    public PokemonResponse(String name, String url) {
        this.name = name;
        this.url = url;
    }

    public String getName() {
        return name;
    }

    public String getUrl() {
        return url;
    }
}