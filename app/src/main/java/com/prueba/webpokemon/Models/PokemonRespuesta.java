package com.prueba.webpokemon.Models;

import java.util.ArrayList;

/*Nuestro modelo de la lista de pokemon y los atributos que vienen la pagina */
public class PokemonRespuesta {

    private ArrayList<Pokemon> results;

    public ArrayList<Pokemon> getResults() {
        return results;
    }

    public void setResults(ArrayList<Pokemon> results) {
        this.results = results;
    }
}
