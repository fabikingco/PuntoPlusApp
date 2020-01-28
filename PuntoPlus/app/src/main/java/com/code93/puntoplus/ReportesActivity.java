package com.code93.puntoplus;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.code93.puntoplus.Adaptador.AdaptadorMensajesSMS;
import com.code93.puntoplus.BD.ClsConexion;
import com.code93.puntoplus.model.SMS_RECV;

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
