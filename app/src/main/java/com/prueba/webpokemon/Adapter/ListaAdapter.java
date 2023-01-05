
package com.prueba.webpokemon.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.prueba.webpokemon.Activities.DetailsActivity;
import com.prueba.webpokemon.Models.Pokemon;
import com.prueba.webpokemon.R;

import java.util.ArrayList;

public class ListaAdapter extends RecyclerView.Adapter<ListaAdapter.ViewHolder> {
    //Se crean las variables a usar
    private ArrayList<Pokemon> pokemons;
    private Context context;
    //Creamos nuestro constructor
    public ListaAdapter(Context context) {
        this.context=context;
        pokemons=new ArrayList<>();
    }
    //Aqui se carga la vista de nuestro adaptar que sera la que se va a mostrar en nuestro recyclerView
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_pokemongame,parent,false);
        return new ViewHolder(view);
    }


    //Aqui le damos valores a nuestros objetos creados para mostrar en la vista y que es lo que va a llevar cada uno
    //Asi como agregar la imagen y cuando se le de click a un elemento pasar los datos a la siguiente pantalla
    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, int position) {
        Pokemon p = pokemons.get(position);
        holder.txtNombre.setText(p.getName());
        Glide.with(context).load("https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/shiny/"
                +p.getNumber()+".png")
                .centerCrop()
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(holder.imgPokemon);
        //Nuestro holder sirve para pasar de la actividad principal a nuestra siguiente pantalla con los datos
        //Que queramos pasar y usar

        holder.imgPokemon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String pokemonId = pokemons.get(holder.getAdapterPosition()).getName();
                int pokemonImg=pokemons.get(holder.getAdapterPosition()).getNumber();

                Intent intent = new Intent(context, DetailsActivity.class);
                intent.putExtra("pokemon_id",pokemonId);
                intent.putExtra("pokemon_img",pokemonImg);
                context.startActivity(intent);
            }
        });
    }
    //Nos regresra el valor de la lista
    @Override
    public int getItemCount() {
        return pokemons.size();
    }

    /*ESte metodo va agregando los pokemons y adiciona la informacion nueva sin que se reemplace de la anterior*/
    public void adicionarPokemon(ArrayList<Pokemon> listaPokemon) {
        pokemons.addAll(listaPokemon);
        notifyDataSetChanged();
    }

    //Aqui casteamos nuestros elementos de la vista y los conectamos con sus variables y objetos indicados
    public class ViewHolder extends RecyclerView.ViewHolder {
        private ImageView imgPokemon;
        private TextView txtNombre;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imgPokemon=itemView.findViewById(R.id.fotoImagenView);
            txtNombre=itemView.findViewById(R.id.nombreTextView);
        }
    }
}
