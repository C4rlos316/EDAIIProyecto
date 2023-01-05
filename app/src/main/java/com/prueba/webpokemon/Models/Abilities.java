package com.prueba.webpokemon.Models;

import com.google.gson.annotations.SerializedName;

/*Creamos nuestros modelos para bajar las hablidades se crearon 3 variables
* y los nombres que se les dio son tal cual como esta en la base de datos y
* se ocupor el SerializedName para ponerle el nombre que queramos a nuestra
* variable y asi no tener problema al momento de cargar los datos*/
public class Abilities {

    private Ability ability;

    @SerializedName("is_hidden")
    private boolean isHidden;
    private int slot;

    public Ability getAbility() {
        return ability;
    }

    public boolean isHidden() {
        return isHidden;
    }

    public int getSlot() {
        return slot;
    }

}
