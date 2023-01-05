package com.prueba.webpokemon.Models;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/*Se crea nuestro modelo para mostrar los detalels  de cada pokemon
* creamos las variables de nombre,peso,tamanio,habilidades,id
* Con esto se crean igual sus getters de cada variable y asi al momento de
* bajar la informacion de nuestra API sea mostrado en nuestros TextView*/
public class PokemonByIdResponse {

    private List<Abilities> abilities;

    private String name;

    private int id;

    private int weight;

    private int height;

    public int getHeight() {
        return height;
    }

    @SerializedName("base_experience")
    private int baseExperience;

    public List<Abilities> getAbilities() {
        return abilities;
    }

    public String getName() {
        return name;
    }

    public int getId() {
        return id;
    }

    public int getBaseExperience() {
        return baseExperience;
    }

    public int getWeight() {
        return weight;
    }


}
