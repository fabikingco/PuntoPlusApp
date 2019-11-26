package com.example.puntoplus;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Timer;
import java.util.TimerTask;

import dmax.dialog.SpotsDialog;

public class VentanaConfirmacionActivity extends AppCompatActivity {

    String[] tipoIngreso;
    String data;
    ImageView imageView;
    TextView titulo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ventana_confirmacion);

        imageView = findViewById(R.id.imagen);
        titulo = findViewById(R.id.tvTitulo);

        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            data = bundle.getString("tipoIngreso", "default");
            if (data.contains("@")) {
                tipoIngreso = data.split("@");
            } else {
                tipoIngreso = new String[1];
                tipoIngreso[0] = data;
                Toast.makeText(this, "" + tipoIngreso[0], Toast.LENGTH_SHORT).show();
            }
        } else {
            Toast.makeText(this, "El tipo de ingreso no llego o fallo ", Toast.LENGTH_SHORT).show();
            startActivity(new Intent(VentanaConfirmacionActivity.this, MainActivity.class));
            finish();
        }

        int ultimo = tipoIngreso.length;

        if (tipoIngreso[ultimo - 1].equals("exitosa")){
            imageView.setImageDrawable(getDrawable(R.drawable.result_success));
            titulo.setText(tipoIngreso[0] + " exitosa");
        }


    }

    @Override
    public void onBackPressed() {
        doExitApp();
    }

    private long exitTime = 0;

    public void doExitApp() {
        if ((System.currentTimeMillis() - exitTime) > 2000) {
            Toast.makeText(this, "Presione nuevamente para volver al menu principal", Toast.LENGTH_SHORT).show();
            exitTime = System.currentTimeMillis();
        } else {
            finish();
        }
    }

    public void cancelarProceso(View view) {
        startActivity(new Intent(this, MainActivity.class));
        finish();
    }

    public void imprimir(View view) {
        final SpotsDialog spotsDialog = new SpotsDialog(VentanaConfirmacionActivity.this, "Imprimiendo ticket...");
        spotsDialog.show();
        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                spotsDialog.dismiss();
                startActivity(new Intent(VentanaConfirmacionActivity.this, MainActivity.class));
                finish();
            }
        }, 5000);
    }
}
