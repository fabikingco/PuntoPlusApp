package com.example.puntoplus;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.example.puntoplus.Adaptador.NewAdapterMenus;
import com.example.puntoplus.model.menuItemsModelo;

import java.util.ArrayList;
import java.util.List;

public class TransaccionActivity extends AppCompatActivity implements NewAdapterMenus.OnItemClickListenerMesas {

    RecyclerView recyclerView;
    List<menuItemsModelo> itemMenu;
    Toolbar toolbar;
    String tipoMenu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_transaccion);

        Bundle bundle = getIntent().getExtras();
        if (bundle != null){
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

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            default:
                Toast.makeText(this, "" + item.getItemId(), Toast.LENGTH_SHORT).show();
        }
        return super.onOptionsItemSelected(item);
    }

    private void cargarComponentes() {

        cargarArrayList();




        NewAdapterMenus adapterMenus = new NewAdapterMenus(itemMenu, this);
        adapterMenus.setOnItemClickListener(TransaccionActivity.this);
        recyclerView.setLayoutManager(new GridLayoutManager(this, 2));
        recyclerView.setAdapter(adapterMenus);

    }

    private void cargarArrayList() {
        itemMenu = new ArrayList<>();
        switch (tipoMenu){
            case "recargas":
            case "paquetes":
                itemMenu.add(new menuItemsModelo(getString(R.string.claro), R.drawable.claro_logo));
                itemMenu.add(new menuItemsModelo(getResources().getString(R.string.movistar), R.drawable.movistar_logo));
                itemMenu.add(new menuItemsModelo(getResources().getString(R.string.cnt), R.drawable.cnt_logo));
                itemMenu.add(new menuItemsModelo(getResources().getString(R.string.tuenti), R.drawable.tuenti));
                break;
            default:
                itemMenu.add(new menuItemsModelo(getString(R.string.recargas_celular), R.drawable.recargas_celular));
                itemMenu.add(new menuItemsModelo(getResources().getString(R.string.paquetes_celular), R.drawable.paquetes_celular));
                itemMenu.add(new menuItemsModelo(getResources().getString(R.string.recargas_simert), R.drawable.simmert));
                itemMenu.add(new menuItemsModelo(getResources().getString(R.string.pagos_de_servicio), R.drawable.pagos_servicios));
                break;

        }
    }

    @Override
    public void onItemClick(RecyclerView.ViewHolder item, int position, int id) {
        Toast.makeText(this, "" + itemMenu.get(position).getTextoItem() + " " + id, Toast.LENGTH_SHORT).show();
        Intent intent = new Intent();
        String data = itemMenu.get(position).getTextoItem();
        if (data.equals(getResources().getString(R.string.recargas_celular))) {
            intent.setClass(TransaccionActivity.this, TransaccionActivity.class);
            intent.putExtra("tipoMenu", "recargas");
            startActivity(intent);
        }
        if (data.equals(getResources().getString(R.string.paquetes_celular))) {
            intent.setClass(TransaccionActivity.this, TransaccionActivity.class);
            intent.putExtra("tipoMenu", "paquetes");
            startActivity(intent);

        }
        if (data.equals(getResources().getString(R.string.recargas_simert))) {

        }
        if (data.equals(getResources().getString(R.string.pagos_de_servicio))) {

        }
        if (data.equals(getResources().getString(R.string.claro))) {
            intent.setClass(TransaccionActivity.this, IngresoTelefonoActivity.class);
            intent.putExtra("tipoIngreso", tipoMenu + "@" + getResources().getString(R.string.claro));
            startActivity(intent);
        }
        if (data.equals(getResources().getString(R.string.movistar))) {
            intent.setClass(TransaccionActivity.this, IngresoTelefonoActivity.class);
            intent.putExtra("tipoIngreso", tipoMenu + "@" + getResources().getString(R.string.movistar));
            startActivity(intent);
        }
        if (data.equals(getResources().getString(R.string.cnt))) {
            intent.setClass(TransaccionActivity.this, IngresoTelefonoActivity.class);
            intent.putExtra("tipoIngreso", tipoMenu + "@" + getResources().getString(R.string.cnt));
            startActivity(intent);
        }
        if (data.equals(getResources().getString(R.string.tuenti))) {
            intent.setClass(TransaccionActivity.this, IngresoTelefonoActivity.class);
            intent.putExtra("tipoIngreso", tipoMenu + "@" + getResources().getString(R.string.tuenti));
            startActivity(intent);
        }
    }


}
