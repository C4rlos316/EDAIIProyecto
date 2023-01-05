package com.prueba.webpokemon.Network;


import com.prueba.webpokemon.Models.PokemonRespuesta;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;
//Creamos nuestro interface para obtener todos los pokemons y esto sirve para que avance de 20 en 20 la bajada de datos
//Esto se pone por que en la url que tiene la Api son las palabras clave y asi tener los datos correctos

public interface PokeApiServices {
    //Nuestro get por que solo vamos a obtener los datos
    @GET("pokemon")
    Call<PokemonRespuesta> obtenerListaPokemon(@Query("limit") int limit,@Query("offset") int offset);
    //limit=20 pokemones offset=despues de que numero vamos a tomar la lista
    //Se pone Query para saber que sigue siendo parte de la URL
}
