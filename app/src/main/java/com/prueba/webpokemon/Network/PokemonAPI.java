package com.prueba.webpokemon.Network;

import com.prueba.webpokemon.Models.PokemonByIdResponse;
import com.prueba.webpokemon.z.PokemonListResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface PokemonAPI {
    //Parte de la URL
    @GET("pokemon")
    Call<PokemonListResponse> getPokemonList();
    //Para obtener el id de el pokemon y se puedan bajar correctamente.
    @GET("pokemon/{id}")
    Call<PokemonByIdResponse> getPokemonById(@Path("id") String id);

}
