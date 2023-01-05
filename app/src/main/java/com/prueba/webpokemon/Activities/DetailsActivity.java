package com.prueba.webpokemon.Activities;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.RecyclerView;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.prueba.webpokemon.Models.Abilities;
import com.prueba.webpokemon.Models.Ability;
import com.prueba.webpokemon.Models.PokemonByIdResponse;
import com.prueba.webpokemon.R;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DetailsActivity extends BaseActivity {

    TextView txtNombrePokemon,txtExperience,txtAbilites,txtPeso,txtAltura;
    ImageView imgPokemon;
    int pokemonUrlImg;

    /*Esta actividad nos muestra los detalles de el pokemon al que se le da click
    * Aqui vamos a encontrar toda la informacion de cada uno con una imagen, su nombre.
    * peso,tamanio,altura */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);

        Toolbar toolbarback=findViewById(R.id.toolbar);
        setSupportActionBar(toolbarback);


        /* Aqui casteamos nuestros elementos de la pantalla
        * son textView y una imageView para mostrar la imagen de nuestro pokemon*/
        txtNombrePokemon=findViewById(R.id.nombreDetail);
        txtAbilites=findViewById(R.id.txtHabilidad);
        txtExperience=findViewById(R.id.txtExperiencia);
        txtPeso=findViewById(R.id.txtPeso);
        txtAltura=findViewById(R.id.txtAltura);
        imgPokemon=findViewById(R.id.imgSprite);

        /*Estas 2 variables son strings y un int que lo pasamos a traves de un intent
        * de la actividad pasada y aqui recuperamos el id del pokemon y la url del pokemon*/
        String pokemonID=getIntent().getStringExtra("pokemon_id");
        pokemonUrlImg=getIntent().getIntExtra("pokemon_img",0);
        getSupportActionBar().setTitle(pokemonID);

        //Hacemos nuestra llamada Call de retrofit para saber que pokemon es el que vamos a bajar la informacion
        Call<PokemonByIdResponse> call = loader.getPokemonById(pokemonID);
        //Mostramos nuestroProgressDialog para bajar datos
        showProgress();
        /*Bajamos nuestros datos que necesitamos por medio de enqueue que tiene 2 metodos
        * el Response para saber que todoo esta correcto y es ahi donde vamos a mostrar nuestros datos
        * y el onFailure donde si hay un error nos dira cual es dependiendo de nuestra base de datos que bajemos
        * Le pasamos nuestros PokemonIDResponse que es un modelo de los datos a bajar y haremos uso de los getters para
        * mostrar nuestros datos en los textView */
        call.enqueue(new Callback<PokemonByIdResponse>() {
            @Override
            public void onResponse(Call<PokemonByIdResponse> call, Response<PokemonByIdResponse> response) {
                //Quitamos nuestro progressDialog una vez que cargaron los datos bien
                hideProgress();
                Log.e("DEBUG_POKEMON",response.body().getName());
                /* Aqui mostramos nuestros textView con la informacion correspondiente de cada uno
                * y bajamos los datos con los getters y se los pasamos por medio de un
                * setText y asi en nuestra pantalla se mostrara cada uno.*/

                txtNombrePokemon.setText(response.body().getName());
                txtExperience.setText("XP: "+response.body().getBaseExperience());
                txtPeso.setText(""+response.body().getWeight()+" kg");
                txtAltura.setText(""+response.body().getHeight()+" m");
                /*Para cargar la iamgen usamos la libreria Glide donde se le pasa la url y esta la convierte
                * a imagen le damos nuestras caracteristicas que queramos y asi se muestra en la pantalla*/
                Glide.with(DetailsActivity.this).load("https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/shiny/"
                        +pokemonUrlImg+".png")
                        .centerCrop()
                        .diskCacheStrategy(DiskCacheStrategy.ALL)
                        .into(imgPokemon);
                /*Para las Habilidades como en la base de datos estan en forma de array dentro de un array
                * lo que hacemos es crear un modelo con los parametros del array*/
                List<Ability> abilityList=new ArrayList<>();
                StringBuilder sb= new StringBuilder();
                /*Y como cada pokemon tiene diferentes habilidades no son las mismas para todos entonces con un
                * ciclo for lo que hacemos es recorrer todas esas habilidades y las vamos a ir guardando en nuestra
                * lista que se creo*/
                for(Abilities ability:response.body().getAbilities()){
                    abilityList.add(ability.getAbility());
                }
                /*Cuando se terminen de agregar las hablidades a la lista se crea otro ciclo for
                * ahora para agregar esas hablidadesa un StringBuilder que despues lo vamos a mostrar en
                * nuestro TextView*/
                for(Ability ability:abilityList){
                    sb.append(ability.getName()+"\n");
                }
                txtAbilites.setText(sb.toString());
            }
            //Funcion sirve en caso que haya un error nos muestre en consola cual es.
            @Override
            public void onFailure(Call<PokemonByIdResponse> call, Throwable t) {
                hideProgress();
                Log.e("DEBUG_POKEMON",t.getMessage());

            }
        });
    }
}