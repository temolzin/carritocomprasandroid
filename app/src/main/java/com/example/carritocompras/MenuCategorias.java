package com.example.carritocompras;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;

public class MenuCategorias extends AppCompatActivity {

    ListView listViewCategorias;
    ArrayList<CategoriaProducto> arrayCategorias;
    private AdaptadorCategoria adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menucategorias);

        asociarCampos();
        agregarArreglos();

        adapter = new AdaptadorCategoria(this,arrayCategorias);
        listViewCategorias.setAdapter(adapter);

        listViewCategorias.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(getApplicationContext(), MenuProducto.class);
                intent.putExtra("objCategoria", (CategoriaProducto) arrayCategorias.get(position));
                intent.putExtra("idCategoria", (int) arrayCategorias.get(position).getIdcategoria());
                startActivity(intent);
            }
        });
    }

    private void asociarCampos() {
        listViewCategorias = findViewById(R.id.listViewCategorias);
    }

    private void agregarArreglos() {
        CategoriaProducto celulares = new CategoriaProducto(1,R.drawable.phonee,"Celulares","Departamento Télefono");
        CategoriaProducto pcescritorio = new CategoriaProducto(2,R.drawable.desktop,"Computadoras Escritorio", "Departamento PC de Escritorio");
        CategoriaProducto laptops = new CategoriaProducto(3,R.drawable.laptop,"Laptops", "Departamento PC Portátiles");
        CategoriaProducto impresoras = new CategoriaProducto(4,R.drawable.printers,"Impresoras", "Departamento impresoras");

        this.arrayCategorias = new ArrayList<CategoriaProducto>();
        this.arrayCategorias.add(celulares);
        this.arrayCategorias.add(pcescritorio);
        this.arrayCategorias.add(laptops);
        this.arrayCategorias.add(impresoras);
    }
}
