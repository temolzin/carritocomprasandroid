package com.example.carritocompras;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class MenuCarrito extends AppCompatActivity {

    ListView listViewCarrito;

    private AdaptadorCarrito adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menucarrito);

        asociarCampos();

        adapter = new AdaptadorCarrito(this, Carrito.arrayListCarrito);
        listViewCarrito.setAdapter(adapter);

    }

    private void asociarCampos() {
        listViewCarrito = findViewById(R.id.listViewCarrito);
    }

}
