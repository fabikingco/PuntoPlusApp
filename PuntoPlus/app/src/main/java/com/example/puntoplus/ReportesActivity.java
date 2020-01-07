package com.example.puntoplus;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.example.puntoplus.Adaptador.AdaptadorMensajesSMS;
import com.example.puntoplus.BD.ClsConexion;
import com.example.puntoplus.model.SMS_RECV;

import java.util.ArrayList;

public class ReportesActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    ArrayList<SMS_RECV> arrayList;
    ClsConexion clsConexion;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reportes);

        clsConexion = new ClsConexion(getApplicationContext());

        arrayList = clsConexion.getAllSMSRecv();

        System.out.println("Elementos SMS " + arrayList.size());

        recyclerView = findViewById(R.id.rvMensajes);

        obtenerMensajesBD();

        AdaptadorMensajesSMS adaptador = new AdaptadorMensajesSMS(arrayList);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(adaptador);



    }

    private void obtenerMensajesBD() {
        arrayList = clsConexion.getAllSMSRecv();
    }
}
