package com.example.puntoplus;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.puntoplus.Adaptador.ConsultaAdapter;
import com.example.puntoplus.Adaptador.NewAdapterMenus;
import com.example.puntoplus.model.menuItemsModelo;

import java.util.ArrayList;
import java.util.List;

public class SeleccionOperador extends AppCompatActivity implements NewAdapterMenus.OnItemClickListenerMesas {

    RecyclerView recyclerView;
    List<menuItemsModelo> itemMenu;
    Toolbar toolbar;
    String tipoMenu;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_transaccion);

        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            tipoMenu = bundle.getString("tipoMenu", "default");
        } else {
            tipoMenu = "default";
        }

        recyclerView = findViewById(R.id.recyclerTarjetas);
        initToolbar();
        cargarComponentes();
    }

    private void initToolbar() {
        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("PuntoPlus");
    }

    private void cargarComponentes() {
        cargarArrayList();
        NewAdapterMenus adapterMenus = new NewAdapterMenus(itemMenu, this);
        adapterMenus.setOnItemClickListener(SeleccionOperador.this);
        recyclerView.setLayoutManager(new GridLayoutManager(this, 2));
        recyclerView.setAdapter(adapterMenus);
    }

    private void cargarArrayList() {
        itemMenu = new ArrayList<>();
        itemMenu.add(new menuItemsModelo(getString(R.string.claro), R.drawable.claro_logo));
        itemMenu.add(new menuItemsModelo(getResources().getString(R.string.movistar), R.drawable.movistar_logo));
        itemMenu.add(new menuItemsModelo(getResources().getString(R.string.cnt), R.drawable.cnt_logo));
        itemMenu.add(new menuItemsModelo(getResources().getString(R.string.tuenti), R.drawable.tuenti));
        itemMenu.add(new menuItemsModelo(getResources().getString(R.string.Consulta), R.drawable.tuenti));
    }

    @Override
    public void onItemClick(RecyclerView.ViewHolder item, int position, int id) {
        Intent intent = new Intent();
        String data = itemMenu.get(position).getTextoItem();
        if (data.equals(getResources().getString(R.string.claro))) {
            if (tipoMenu.equals(getResources().getString(R.string.paquetes_celular))) {
                Toast.makeText(this, "Paquetes no disponibles", Toast.LENGTH_SHORT).show();
            } else {
                intent.setClass(SeleccionOperador.this, IngresoMontoActivity.class);
                intent.putExtra("tipoIngreso", tipoMenu + "@" + getResources().getString(R.string.claro));
                startActivity(intent);
            }
        }
        if (data.equals(getResources().getString(R.string.movistar))) {
            if (tipoMenu.equals(getResources().getString(R.string.paquetes_celular))) {
                Toast.makeText(this, "Paquetes no disponibles", Toast.LENGTH_SHORT).show();
            } else {
                intent.setClass(SeleccionOperador.this, IngresoMontoActivity.class);
                intent.putExtra("tipoIngreso", tipoMenu + "@" + getResources().getString(R.string.movistar));
                startActivity(intent);
            }
        }
        if (data.equals(getResources().getString(R.string.cnt))) {
            if (tipoMenu.equals(getResources().getString(R.string.paquetes_celular))) {
                Toast.makeText(this, "Paquetes no disponibles", Toast.LENGTH_SHORT).show();
            } else {
                intent.setClass(SeleccionOperador.this, IngresoMontoActivity.class);
                intent.putExtra("tipoIngreso", tipoMenu + "@" + getResources().getString(R.string.cnt));
                startActivity(intent);
            }
        }
        if (data.equals(getResources().getString(R.string.tuenti))) {
            if (tipoMenu.equals(getResources().getString(R.string.paquetes_celular))) {
                Toast.makeText(this, "Paquetes no disponibles", Toast.LENGTH_SHORT).show();
            } else {
                intent.setClass(SeleccionOperador.this, IngresoMontoActivity.class);
                intent.putExtra("tipoIngreso", tipoMenu + "@" + getResources().getString(R.string.tuenti));
                startActivity(intent);
            }
        }

        if (data.equals(getResources().getString(R.string.Consulta))) {

            intent.setClass(SeleccionOperador.this, ConsultaActivity.class);
            startActivity(intent);

        }
    }
}
