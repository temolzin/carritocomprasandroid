package com.example.carritocompras;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import java.lang.reflect.WildcardType;
import java.util.ArrayList;

public class MenuCarrito extends AppCompatActivity {

    ListView listViewCarrito;
    Button buttonPagar;
    String[] tiposPago = {"Credito", "Efectivo", "Cheque"};

    private AdaptadorCarrito adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menucarrito);

        asociarCampos();

        adapter = new AdaptadorCarrito(this, Carrito.arrayListCarrito);
        listViewCarrito.setAdapter(adapter);

        buttonPagar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(MenuCarrito.this);
                LayoutInflater inflater = getLayoutInflater();
                View viewAlert = inflater.inflate(R.layout.alert_pagar,null);
                builder.setView(viewAlert);

                final AlertDialog dialog = builder.create();
                dialog.show();

                final TextView textViewTotal = viewAlert.findViewById(R.id.textViewTotalPago);
                TextView textViewNoPiezas = viewAlert.findViewById(R.id.textViewNoPiezas);
                final EditText editTextPago = viewAlert.findViewById(R.id.editTextPago);

                Button buttonPagarTicket = viewAlert.findViewById(R.id.buttonPagarTicket);
                Button buttonCancelarPago = viewAlert.findViewById(R.id.buttonCancelarPago);

                Spinner spinnerTipoPago = viewAlert.findViewById(R.id.spinnerTipoPago);
                spinnerTipoPago.setAdapter(new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_spinner_dropdown_item,tiposPago));

                Double total = 0.00;
                int piezas = 0;
                for (Producto producto : Carrito.arrayListCarrito) {
                    piezas += producto.getCantidad();
                    total += (producto.getPrecio() * producto.getCantidad());
                }

                final Double totalFinal = total;

                textViewTotal.setText("Total: $" + String.valueOf(total));
                textViewNoPiezas.setText("Piezas: " + piezas);

                buttonPagarTicket.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if(Double.parseDouble(editTextPago.getText().toString()) < totalFinal) {
                            Toast.makeText(MenuCarrito.this, "El pago ingresado es menor a el total de compra", Toast.LENGTH_SHORT).show();
                        } else {
                            Double pagocliente = Double.parseDouble(editTextPago.getText().toString());
                            Double pagocambio = pagocliente - totalFinal;

                            AlertDialog.Builder builder = new AlertDialog.Builder(MenuCarrito.this);
                            LayoutInflater inflater = getLayoutInflater();
                            View viewAlert = inflater.inflate(R.layout.alert_pago_cambio,null);
                            builder.setView(viewAlert);
                            TextView textViewCambio = viewAlert.findViewById(R.id.textViewPagoCambio);
                            textViewCambio.setText("Gracias por comprar con nosotros, su cambio es: $" + pagocambio);
                            Button buttonAceptarCambio = viewAlert.findViewById(R.id.buttonPagarAceptar);
                            final AlertDialog dialog = builder.create();
                            dialog.show();

                            buttonAceptarCambio.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    dialog.dismiss();
                                    Intent intent = new Intent(getApplicationContext(), MenuCategorias.class);
                                    startActivity(intent);
                                    Carrito.noPiezas = 0;
                                    Carrito.arrayListCarrito.clear();
                                }
                            });
                        }
                    }
                });

                buttonCancelarPago.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.dismiss();
                    }
                });
            }
        });
    }

    private void asociarCampos() {
        listViewCarrito = findViewById(R.id.listViewCarrito);
        buttonPagar = findViewById(R.id.buttonPagar);
    }

}
