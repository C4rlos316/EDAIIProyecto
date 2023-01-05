package com.prueba.webpokemon.z;

import com.google.gson.annotations.SerializedName;
import com.prueba.webpokemon.z.PokemonResponse;

import java.util.List;

/*Nuestro modelo de la lista de pokemon */
public class PokemonListResponse {

    @SerializedName("results")
    private List<PokemonResponse> pokemonList;

    public List<PokemonResponse> getPokemonList() {
        return pokemonList;
    }
}
