package com.example.carritocompras;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class MenuProducto extends AppCompatActivity {

    ListView listViewProductos;
    ArrayList<Producto> arrayListProducto;
    CategoriaProducto objCategoria;
    AdaptadorProducto adaptadorProducto;
    int idCategoria;
    Button buttonVerCarrito;
    TextView textViewCantidadCarrito;
    ArrayList<Producto> arrayListProductosIdCategoria;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menuproductos);
        asociarCampos();

        //Se inicia con el valor de las piezas ya agregadas en otras categorias
        textViewCantidadCarrito.setText(String.valueOf(Carrito.noPiezas));

        Bundle parametros = this.getIntent().getExtras();
        objCategoria = (CategoriaProducto) parametros.get("objCategoria");
        idCategoria = (int) parametros.get("idCategoria");

        adaptadorProducto = new AdaptadorProducto(this, getProductosbyIdCategoria(objCategoria.getIdcategoria()));
        listViewProductos.setAdapter(adaptadorProducto);

        verCarritoCompras();

        //Evento donde se agregan los productos al carrito y se muestra el alert para insertar la cantidad
        listViewProductos.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                final Producto producto = arrayListProductosIdCategoria.get(position);
                AlertDialog.Builder builder = new AlertDialog.Builder(MenuProducto.this);
                LayoutInflater inflater = getLayoutInflater();
                View viewAlert = inflater.inflate(R.layout.alert_carrito,null);
                builder.setView(viewAlert);

                final AlertDialog dialog = builder.create();
                dialog.show();

                final TextView textViewNombreProducto = viewAlert.findViewById(R.id.textViewNombreProducto);
                TextView textViewCantidadStock = viewAlert.findViewById(R.id.textViewCantidadStock);
                TextView textViewPrecioProducto = viewAlert.findViewById(R.id.textViewPrecioProducto);
                final EditText editTextCantidadCarrito = viewAlert.findViewById(R.id.editTextCantidadCarrito);
                ImageView imageViewProductoAlert = viewAlert.findViewById(R.id.imageViewProductoAlert);
                Button buttonCancelarProducto = viewAlert.findViewById(R.id.buttonCancelarProducto);
                Button buttonAgregarProducto = viewAlert.findViewById(R.id.buttonAgrregarProducto);

                textViewNombreProducto.setText(producto.getNombre());
                textViewCantidadStock.setText("Stock: " + producto.getCantidad());
                textViewPrecioProducto.setText("Precio: $" + producto.getPrecio());

                imageViewProductoAlert.setImageResource(producto.getImagen());

                buttonAgregarProducto.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if(editTextCantidadCarrito.getText().toString().equals("")) {
                            Toast.makeText(MenuProducto.this, "Debes ingresar una cantidad para agregar el producto", Toast.LENGTH_SHORT).show();
                        } else {
                            if(producto.getCantidad() < Integer.parseInt(editTextCantidadCarrito.getText().toString())) {
                                Toast.makeText(MenuProducto.this, "Sólo hay en existencia " + producto.getCantidad() + " piezas", Toast.LENGTH_SHORT).show();
                            } else {
                                producto.setCantidad(producto.getCantidad() - Integer.parseInt(editTextCantidadCarrito.getText().toString()));
                                Carrito.arrayListCarrito.add(new Producto(producto.getIdProducto(), producto.getIdCategoria(), producto.getImagen(), producto.getNombre(), producto.getDescripcion(), Integer.parseInt(editTextCantidadCarrito.getText().toString()), producto.getPrecio()));
                                Carrito.noPiezas += Integer.parseInt(editTextCantidadCarrito.getText().toString());
                                textViewCantidadCarrito.setText(String.valueOf(Carrito.noPiezas));
                                Toast.makeText(MenuProducto.this, "Se agrego al carro de compras", Toast.LENGTH_SHORT).show();
                                verCarritoCompras();
                                dialog.dismiss();
                            }
                        }
                    }
                });

                buttonCancelarProducto.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.dismiss();
                    }
                });
            }
        });
    }

    private void asociarCampos() {
        listViewProductos = findViewById(R.id.listViewProductos);
        buttonVerCarrito = findViewById(R.id.buttonVerCarritoProducto);
        textViewCantidadCarrito = findViewById(R.id.textViewCantidadVerCarrito);
    }

    private void verCarritoCompras() {
        //Validación que agrega evento al botón del carrito de compras
        if(!textViewCantidadCarrito.getText().toString().equals("0")) {
            buttonVerCarrito.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(getApplicationContext(), MenuCarrito.class);
                    startActivity(intent);
                }
            });
        } else {
            buttonVerCarrito.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(MenuProducto.this, "Para ver el carrito necesitas agregar algún producto", Toast.LENGTH_SHORT).show();
                }
            });
        }
    }

    private ArrayList<Producto> getProductosbyIdCategoria(int idCategoria) {
        arrayListProducto = new ArrayList<Producto>();
        Producto phone1 = new Producto(1,1,R.drawable.iphone6,"IPhone 6s", "Iphone 6 versión s", 5, 5200.00);
        Producto phone2 = new Producto(2,1,R.drawable.xiaomi9,"Xiaomi 9", "Xiaomi versión 9", 2, 4000.00);
        Producto phone3 = new Producto(3,1,R.drawable.samsungs6,"Samsung S6 Plus", "Samsung S6 versión PLUS", 10, 6000.00);
        Producto phone4 = new Producto(4,1,R.drawable.huaweip30,"Huawei P30 PRO", "Huawei P30 versión PRO", 9, 12000.00);
        Producto phone5 = new Producto(5,1,R.drawable.huaweiy7,"Huawei Y7", "Huawei versión Y7", 7, 9000.00);

        Producto laptop1 = new Producto(6,2,R.drawable.hppavilon14,"Hp Pavilon", "HP Pavilon versión 14", 2, 6000.00);
        Producto laptop2 = new Producto(7,2,R.drawable.lgp530,"LG Modelo P", "LG versión P530", 5, 18000.00);

        Producto desktop1 = new Producto(8,3,R.drawable.aceraspireone,"Acer Aspire", "Acer versión Aspire One", 1, 12000.00);
        Producto desktop2 = new Producto(9,3,R.drawable.asusvivoaio,"Asus Vivo", "Asus versión AIO", 4, 7000.00);
        Producto desktop3 = new Producto(10,3,R.drawable.acerazpirezc,"Acer Aspire", "Acer Aspire versión ZC", 11, 14000.00);
        Producto desktop4 = new Producto(11,3,R.drawable.imac,"IMac", "IMac versión 10.5", 2, 25000.00);

        Producto printer1 = new Producto(12,4,R.drawable.epsonl545,"Epson", "Epson versión L545", 1, 5000.00);
        Producto printer2 = new Producto(13,4,R.drawable.hp615,"HP 615", "HP versión 615", 4, 3000.00);
        Producto printer3 = new Producto(14,4,R.drawable.samsungml1865w,"Samsung 1186W", "Samsung versión 1186W", 5, 4500.00);
        Producto printer4 = new Producto(15,4,R.drawable.samsungslc430,"Samsung LC430", "Samsung versión LC430", 23, 1200.00);

        arrayListProducto.add(phone1);
        arrayListProducto.add(phone2);
        arrayListProducto.add(phone3);
        arrayListProducto.add(phone4);
        arrayListProducto.add(phone5);

        arrayListProducto.add(laptop1);
        arrayListProducto.add(laptop2);

        arrayListProducto.add(desktop1);
        arrayListProducto.add(desktop2);
        arrayListProducto.add(desktop3);
        arrayListProducto.add(desktop4);

        arrayListProducto.add(printer1);
        arrayListProducto.add(printer2);
        arrayListProducto.add(printer3);
        arrayListProducto.add(printer4);

        arrayListProductosIdCategoria = new ArrayList<Producto>();

        for(int i = 0; i < arrayListProducto.size(); i++) {
            if(arrayListProducto.get(i).getIdCategoria() == idCategoria) {
                arrayListProductosIdCategoria.add(arrayListProducto.get(i));
            }
        }

        return arrayListProductosIdCategoria;
    }
}
