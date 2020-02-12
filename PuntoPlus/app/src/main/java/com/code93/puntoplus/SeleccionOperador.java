package com.code93.puntoplus;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.code93.puntoplus.Actividades.RecargasCelularActivity;
import com.code93.puntoplus.Actividades.RecargasSimertActivity;
import com.code93.puntoplus.Adaptador.NewAdapterMenus;
import com.code93.puntoplus.model.menuItemsModelo;

import java.util.ArrayList;
import java.util.List;

public class SeleccionOperador extends AppCompatActivity implements NewAdapterMenus.OnItemClickListenerMesas {

    RecyclerView recyclerView;
    List<menuItemsModelo> itemMenu;
    Toolbar toolbar;
    String tipoMenu;
    RelativeLayout info;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_transaccion);

        info = findViewById(R.id.info);
        info.setVisibility(View.GONE);

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
        getSupportActionBar().setTitle("Ecuamovil");
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
        if (tipoMenu.equals(getResources().getString(R.string.recargas_simert))) {
            itemMenu.add(new menuItemsModelo(getResources().getString(R.string.parqueo_directo), R.drawable.simmert));
            itemMenu.add(new menuItemsModelo(getResources().getString(R.string.recarga_parqueo), R.drawable.simmert));
        } else {
            itemMenu.add(new menuItemsModelo(getString(R.string.claro), R.drawable.claro_logo));
            itemMenu.add(new menuItemsModelo(getResources().getString(R.string.movistar), R.drawable.movistar_logo));
            itemMenu.add(new menuItemsModelo(getResources().getString(R.string.cnt), R.drawable.cnt_logo));
            itemMenu.add(new menuItemsModelo(getResources().getString(R.string.tuenti), R.drawable.tuenti));
        }
    }

    @Override
    public void onItemClick(RecyclerView.ViewHolder item, int position, int id) {
        Intent intent = new Intent();
        String data = itemMenu.get(position).getTextoItem();
        if (data.equals(getResources().getString(R.string.claro))) {
            if (tipoMenu.equals(getResources().getString(R.string.paquetes_celular))) {
                Toast.makeText(this, "Paquetes no disponibles", Toast.LENGTH_SHORT).show();
            } else  if (tipoMenu.equals(getResources().getString(R.string.recargas_celular))){
                MainActivity.recargasCelular.setOperador(getResources().getString(R.string.claro));
                intent.setClass(SeleccionOperador.this, RecargasCelularActivity.class);
                intent.putExtra("tipoIngreso", tipoMenu + "@" + getResources().getString(R.string.claro));
                startActivity(intent);
            }
        }
        if (data.equals(getResources().getString(R.string.movistar))) {
            if (tipoMenu.equals(getResources().getString(R.string.paquetes_celular))) {
                Toast.makeText(this, "Paquetes no disponibles", Toast.LENGTH_SHORT).show();
            } else {
                MainActivity.recargasCelular.setOperador(getResources().getString(R.string.movistar));
                intent.setClass(SeleccionOperador.this, RecargasCelularActivity.class);
                intent.putExtra("tipoIngreso", tipoMenu + "@" + getResources().getString(R.string.movistar));
                startActivity(intent);
            }
        }
        if (data.equals(getResources().getString(R.string.cnt))) {
            if (tipoMenu.equals(getResources().getString(R.string.paquetes_celular))) {
                Toast.makeText(this, "Paquetes no disponibles", Toast.LENGTH_SHORT).show();
            } else {
                MainActivity.recargasCelular.setOperador(getResources().getString(R.string.cnt));
                intent.setClass(SeleccionOperador.this, RecargasCelularActivity.class);
                intent.putExtra("tipoIngreso", tipoMenu + "@" + getResources().getString(R.string.cnt));
                startActivity(intent);
            }
        }
        if (data.equals(getResources().getString(R.string.tuenti))) {
            if (tipoMenu.equals(getResources().getString(R.string.paquetes_celular))) {
                Toast.makeText(this, "Paquetes no disponibles", Toast.LENGTH_SHORT).show();
            } else {
                MainActivity.recargasCelular.setOperador(getResources().getString(R.string.tuenti));
                intent.setClass(SeleccionOperador.this, RecargasCelularActivity.class);
                intent.putExtra("tipoIngreso", tipoMenu + "@" + getResources().getString(R.string.tuenti));
                startActivity(intent);
            }
        }
        if (data.equals(getResources().getString(R.string.parqueo_directo))){
            intent.setClass(SeleccionOperador.this, RecargasSimertActivity.class);
            intent.putExtra("tipoIngreso", tipoMenu + "@" + getResources().getString(R.string.parqueo_directo));
            startActivity(intent);
        }
        if (data.equals(getResources().getString(R.string.recarga_parqueo))){
            intent.setClass(SeleccionOperador.this, RecargasSimertActivity.class);
            intent.putExtra("tipoIngreso", tipoMenu + "@" + getResources().getString(R.string.recarga_parqueo));
            startActivity(intent);
        }
        finish();
    }
}
