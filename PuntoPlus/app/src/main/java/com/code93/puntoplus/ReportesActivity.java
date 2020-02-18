package com.code93.puntoplus;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.code93.puntoplus.Adaptador.AdaptadorTransacciones;
import com.code93.puntoplus.BD.ClsConexion;
import com.code93.puntoplus.model.Transacciones.Transaccion;

import java.util.ArrayList;

public class ReportesActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    ClsConexion clsConexion;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reportes);

        clsConexion = new ClsConexion(getApplicationContext());

        ArrayList<Transaccion> transaccions = clsConexion.getAllTransacciones();

        for (Transaccion transaccion : transaccions) {
            System.out.println("id = " + transaccion.getId());
        }
        recyclerView = findViewById(R.id.rvMensajes);
        AdaptadorTransacciones adaptador = new AdaptadorTransacciones(transaccions, getApplicationContext());
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(adaptador);
    }
}
