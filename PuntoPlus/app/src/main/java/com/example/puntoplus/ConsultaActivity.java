package com.example.puntoplus;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.puntoplus.Adaptador.ConsultaAdapter;
import com.example.puntoplus.BD.ClsConexion;
import com.example.puntoplus.model.SMS;

import java.util.ArrayList;
import java.util.List;

public class ConsultaActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private Toolbar toolbar;
    private TextView tvTitulo;
    private ImageButton button;
    private boolean isSend = false;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_consulta);
        recyclerView = findViewById(R.id.recyclerTarjetas);
        tvTitulo = findViewById(R.id.tvTitulo);
        button = findViewById(R.id.btn);
        initToolbar();


        final ClsConexion clsConexion = new ClsConexion(this);
        llenarAdapter(clsConexion.getAllSMSRecv());


        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isSend) {
                    llenarAdapter(clsConexion.getAllSMSRecv());
                    tvTitulo.setText("Recibidos");
                    button.setImageDrawable(getDrawable(R.drawable.ic_arrow_upward_black_24dp));
                } else {
                    llenarAdapter(clsConexion.getAllSMSSend());
                    tvTitulo.setText("Enviados");
                    button.setImageDrawable(getDrawable(R.drawable.ic_arrow_downward_black_24dp));
                }
                isSend = !isSend;
            }
        });
    }

    private void initToolbar() {
        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Consulta");
    }


    private void llenarAdapter(ArrayList<SMS> sms) {
        if (sms.size() == 0) {
            recyclerView.setVisibility(View.GONE);
        } else {
            recyclerView.setVisibility(View.VISIBLE);
            ConsultaAdapter consultaAdapter = new ConsultaAdapter(this, sms);
            recyclerView.setLayoutManager(new LinearLayoutManager(this));
            recyclerView.setAdapter(consultaAdapter);
        }
    }


}
