package com.prueba.webpokemon.Models;

/*Creamos nuestro modelo de Pokemon donde vamos a obtener el nombre del pokemon
* y le numero que ocupa este para asi identificar a cada uno y se puedan bajar correctamente los items
* */
public class Pokemon {

    private String name;
    private String url;
    private int number;

    public String getName() {
        return name;
    }

    public int getNumber() {
        //Tomar la url y le agregamos / asi se separa
        String[] urlPartes = url.split("/");

        //Retornamos la url y accedemos a la ultima posicion
        return Integer.parseInt(urlPartes[urlPartes.length - 1]);
    }
}
