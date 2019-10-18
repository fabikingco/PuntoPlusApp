package com.example.puntoplus;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import java.util.ArrayList;
import java.util.List;

public class TransaccionActivity extends AppCompatActivity {

    RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_transaccion);

        recyclerView = findViewById(R.id.recyclerTarjetas);

        cargarComponentes();
    }

    private void cargarComponentes() {
        List<menuItemsModelo> itemMenu = new ArrayList<>();
        itemMenu.add(new menuItemsModelo("Recargas Celular", R.drawable.tuenti));
        itemMenu.add(new menuItemsModelo("Paquetes celular", R.drawable.tuenti));
        itemMenu.add(new menuItemsModelo("Recarga Simert", R.drawable.simmert));
        itemMenu.add(new menuItemsModelo("Pago servicios", R.drawable.tuenti));



        NewAdapterMenus adapterMenus = new NewAdapterMenus(itemMenu, this);
        recyclerView.setLayoutManager(new GridLayoutManager(this, 2));
        recyclerView.setAdapter(adapterMenus);
    }
}
