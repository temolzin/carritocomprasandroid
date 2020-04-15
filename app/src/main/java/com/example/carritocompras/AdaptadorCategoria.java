package com.example.carritocompras;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class AdaptadorCategoria extends BaseAdapter {

    private ArrayList<CategoriaProducto> arrayListCategorias;
    private Context context;

    public AdaptadorCategoria(Context context, ArrayList<CategoriaProducto> arrayListCategorias) {
        this.arrayListCategorias = arrayListCategorias;
        this.context = context;
    }

    @Override
    public int getCount() {
        return arrayListCategorias.size();
    }

    @Override
    public Object getItem(int position) {
        return arrayListCategorias.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        CategoriaProducto item = (CategoriaProducto) getItem(position);

        convertView = LayoutInflater.from(context).inflate(R.layout.item_categoria, null);
        ImageView imageViewFoto = convertView.findViewById(R.id.imageViewPrincipal);
        TextView textViewTitulo = convertView.findViewById(R.id.textViewTitulo);
        TextView textViewDescripcion  = convertView.findViewById(R.id.textViewContenido);

        imageViewFoto.setImageResource(item.getImagen());
        textViewTitulo.setText(item.getNombre());
        textViewDescripcion.setText(item.getDescripcion());

        return convertView;
    }
}
