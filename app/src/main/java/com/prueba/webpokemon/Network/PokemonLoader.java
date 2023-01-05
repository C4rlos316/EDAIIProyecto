package com.prueba.webpokemon.Network;

import com.prueba.webpokemon.Models.PokemonByIdResponse;
import com.prueba.webpokemon.z.PokemonListResponse;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/*Esta es nuestra base para conectar a la API ya que aqui tenemos nuestra URl base cargamos los datos
* los parseamos a Json y hacemos la llamada con retrofit.*/
public class PokemonLoader implements PokemonAPI {

    PokemonAPI pokemonAPI;

    final String URL_BASE="https://pokeapi.co/api/v2/";

    public PokemonLoader() {

        //Gson para convertir el Json
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(URL_BASE).addConverterFactory(GsonConverterFactory.create()).build();

        //Para poder hacer llamadas
        pokemonAPI=retrofit.create(PokemonAPI.class);
    }

    //Cargar la lista de todos los elementos.
    @Override
    public Call<PokemonListResponse> getPokemonList() {
        return pokemonAPI.getPokemonList();
    }


    //Cargar nuevo pokemon
    @Override
    public Call<PokemonByIdResponse> getPokemonById(String id) {
        return pokemonAPI.getPokemonById(id);
    }


}
