package com.example.carritocompras;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class AdaptadorCarrito extends BaseAdapter {

    private ArrayList<Producto> arrayListProductos;
    private Context context;

    public AdaptadorCarrito(Context context, ArrayList<Producto> arrayListProductos) {
        this.arrayListProductos = arrayListProductos;
        this.context = context;
    }

    @Override
    public int getCount() {
        return arrayListProductos.size();
    }

    @Override
    public Object getItem(int position) {
        return arrayListProductos.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Producto item = (Producto) getItem(position);

        convertView = LayoutInflater.from(context).inflate(R.layout.item_carrito, null);
        ImageView imageViewFoto = convertView.findViewById(R.id.imageViewCarrito);
        TextView textViewTitulo = convertView.findViewById(R.id.textViewTituloProductoCarrito);
        TextView textViewDescripcion  = convertView.findViewById(R.id.textViewContenidoProductoCarrito);
        TextView textViewCantidad  = convertView.findViewById(R.id.textViewCantidadCarrito);
        TextView textViewPrecio  = convertView.findViewById(R.id.textViewPrecioProducto);
        TextView textViewSubTotal  = convertView.findViewById(R.id.textViewSubTotal);
        Double subtotal = item.getCantidad() * item.getPrecio();
        imageViewFoto.setImageResource(item.getImagen());
        textViewTitulo.setText(item.getNombre());
        textViewDescripcion.setText("Descripción: " + item.getDescripcion());
        textViewCantidad.setText("Cantidad: " + item.getCantidad());
        textViewPrecio.setText("Precio: $ " + item.getPrecio());
        textViewSubTotal.setText("SubTotal: $" + subtotal);

        return convertView;
    }
}
