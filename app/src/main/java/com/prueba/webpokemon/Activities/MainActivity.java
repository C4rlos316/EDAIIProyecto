package com.prueba.webpokemon.Activities;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;

import com.prueba.webpokemon.Adapter.ListaAdapter;
import com.prueba.webpokemon.Models.Pokemon;
import com.prueba.webpokemon.Models.PokemonRespuesta;
import com.prueba.webpokemon.Network.PokeApiServices;
import com.prueba.webpokemon.R;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends BaseActivity {

    //Se crea la instacia para retrofit
    private Retrofit retrofit;
    ListaAdapter adapter;
    //Estas variables serviran para Dar saltos de 20 en 20 ya que la API carga de 20 elementos
    private int offset;
    private boolean aptoCargar;
    RecyclerView recyclerView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Casteamos nuestro recyclerView y le pasamos el adapter
        recyclerView=findViewById(R.id.recyclerView);
        adapter=new ListaAdapter(this);
        recyclerView.setAdapter(adapter);
        recyclerView.setHasFixedSize(true);


        //Creamos un GridLayout para que se muestre de 3 elementos el recycler.
        final GridLayoutManager layoutManager = new GridLayoutManager(this,3);
        recyclerView.setLayoutManager(layoutManager);
        /*Ocupamos una funcion del recyclerview donde al hacer scroll atraves de el vamos ir
        * obteniendo el numero total de elementos visibles y el total */

        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                //Usamos un if donde si del lado vertical es mayor a 0 obtendremos unos valores.
                if (dy>0){
                    int visibleItemCount= layoutManager.getChildCount();
                    int totalItemCount= layoutManager.getItemCount();
                    int pastVisibleItem= layoutManager.findFirstVisibleItemPosition();
                    /*En nuestro siguiente if vamos a tener la condicion si los items mostrados son
                    * iguales o mayores a 20 se agregara el la url un incremento de 20 ya que nuestra url
                    * al mostrar los elementos es de 20 en 20 entonces con esto aseguramos que cuando llegue
                    * al limite se cargaran los siguientes 20 y luego loss otros 20 asi hasta terminar con los
                    * items que hay en la base de datos.*/
                    if (aptoCargar){
                        if (visibleItemCount+pastVisibleItem>=totalItemCount){
                            aptoCargar=false;
                            offset +=20;
                            obtenerDatos(offset);
                        }
                    }
                }
            }
        });

        /*Instanciamos retrofit y le pasamos la url que va a ser ocupada para bajar la informacion
        * esos datos que vamos a bajar los convertimos en GSon a traves de objetos */
        retrofit=new Retrofit.Builder().baseUrl("https://pokeapi.co/api/v2/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        aptoCargar=true;
        offset = 0;

        obtenerDatos(offset);
    }
    private void obtenerDatos(int offset) {
        /*Hacemos uso de nuestra interfaz y usamos retrofit */
        PokeApiServices services=retrofit.create(PokeApiServices.class);
        //con esto vamos a recibir la respuesta al metodo
        Call<PokemonRespuesta> pokemonRespuestaCall=services.obtenerListaPokemon(20,offset);
        //Usamos enqueue para manejar nuestros resultados

        pokemonRespuestaCall.enqueue(new Callback<PokemonRespuesta>() {
            //Aqui obtenemos la respuesta a la consulta solicitada
            @Override
            public void onResponse(Call<PokemonRespuesta> call, Response<PokemonRespuesta> response) {
                aptoCargar=true;
                //Si la informacion es correcta nos baja la informacion y podemos ir ocupandola
                if (response.isSuccessful()){
                    PokemonRespuesta pokemonRespuesta= response.body();
                    ArrayList<Pokemon> listaPokemon=pokemonRespuesta.getResults();
                    adapter.adicionarPokemon(listaPokemon);
                }
                //En caso que no nos muestre un error
                else {
                    Log.e("","onResponse"+response.errorBody());
                }
            }
            //Aqui nos muestra algun error que hay surgido al momento de bajar la informacion
            @Override
            public void onFailure(Call<PokemonRespuesta> call, Throwable t) {
                aptoCargar=true;
                Log.e("Error:",t.getMessage());
            }
        });


    }

}
