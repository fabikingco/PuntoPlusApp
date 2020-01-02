package com.example.puntoplus;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

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

        recyclerView = findViewById(R.id.rvMensajes);



    }
}
