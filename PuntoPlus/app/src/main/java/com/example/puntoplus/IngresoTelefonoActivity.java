package com.example.puntoplus;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.google.android.material.textfield.TextInputEditText;

public class IngresoTelefonoActivity extends AppCompatActivity {

    TextView tvTitulo, tvSubtitulo;
    String[] tipoIngreso;
    TextInputEditText editText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ingreso_telefono);

        tvTitulo = findViewById(R.id.tvTitulo);
        tvSubtitulo = findViewById(R.id.tvSubtitulo);
        editText = findViewById(R.id.etData);

        Bundle bundle = getIntent().getExtras();
        if (bundle != null){
            String data = bundle.getString("tipoIngreso", "default");
            if (data.contains("@")) {
                tipoIngreso = data.split("@");
            } else {
                tipoIngreso = new String[1];
                tipoIngreso[0] = data;
            }
            System.out.println(tipoIngreso[0]);
        }

        switch (tipoIngreso[0]) {
            case "registro":
                tvTitulo.setText("Registre su telefono");
                tvSubtitulo.setText("Para poder usar el servicio debe registrar su numero.");
                break;
            case "recargas":
                tvTitulo.setText("Ingresa el numero de telefono");
                tvSubtitulo.setText("La  que vas a realizar sera cargado al siguiente numero");
                break;
            case "paquetes":
                tvTitulo.setText("Ingresa el numero de telefono");
                tvSubtitulo.setText("El paquete que vas a comprar sera cargado al siguiente numero");
                break;
        }

    }

    public void cancelarProceso(View view) {
        finish();
    }

    public void cargarNumero(View view) {
        String numero = editText.getText().toString();
        MainActivity.enviarMensaje(this, numero, "Hola mundo");
    }
}
