package com.prueba.webpokemon.Activities;

import android.app.ProgressDialog;
import android.os.Bundle;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import com.prueba.webpokemon.Network.PokemonLoader;

public class BaseActivity extends AppCompatActivity {

    public PokemonLoader loader;
    private ProgressDialog progressDialog;

    //Esta activity se creo para mostrar un progressDialog al momento de darle click a un pokemon
    // y asi mostrar los datos entonces el usuario no vera una actividad vacia en lo que cargan los
    //datos
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        loader=new PokemonLoader();
        progressDialog= new ProgressDialog(this);
    }

    //Se creo una funcion para mostrar el progreso de cargar
    public void showProgress(){
        progressDialog.setCancelable(false);
        progressDialog.setMessage("Cargando...");
        progressDialog.show();
    }

    //Cuando se muestra y termina el proceso de carga con esta funcion lo quitamos de pantalla
    public void hideProgress(){
        if(progressDialog.isShowing()){
            progressDialog.dismiss();
        }
    }
}
