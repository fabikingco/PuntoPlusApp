package com.example.puntoplus;

import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.puntoplus.Adaptador.ConsultaAdapter;
import com.example.puntoplus.BD.ClsConexion;
import com.example.puntoplus.model.SMS;

import java.util.List;

public class ConsultaActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private Toolbar toolbar;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_transaccion);
        initToolbar();
        ClsConexion  clsConexion = new ClsConexion(this);
        ConsultaAdapter consultaAdapter = new ConsultaAdapter(this,clsConexion.getAllSMSRecv());
        recyclerView = findViewById(R.id.recyclerTarjetas);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }

    private void initToolbar() {
        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("PuntoPlus");
    }
}
