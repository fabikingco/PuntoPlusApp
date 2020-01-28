package com.code93.puntoplus;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.code93.puntoplus.Adaptador.NewAdapterMenus;
import com.code93.puntoplus.BD.ClsConexion;
import com.code93.puntoplus.model.Transacciones.RecargasCelular;
import com.code93.puntoplus.model.menuItemsModelo;
import com.socsi.smartposapi.printer.Align;
import com.socsi.smartposapi.printer.FontLattice;
import com.socsi.smartposapi.printer.PrintRespCode;
import com.socsi.smartposapi.printer.Printer2;
import com.socsi.smartposapi.printer.TextEntity;

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
        getSupportActionBar().setTitle("Recargas Ecuador");
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.item_ConfigComercio) {
            Toast.makeText(this, "Configuracion del comercio", Toast.LENGTH_SHORT).show();
        } else if (item.getItemId() == R.id.item_CerrarSesion) {
            ClsConexion conexion = new ClsConexion(this);
            conexion.eliminarUsuarioDB();
            startActivity(new Intent(this, MainActivity.class));
        } else {
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
        itemMenu.add(new menuItemsModelo(getResources().getString(R.string.recargas_celular), R.drawable.recargas_celular));
        itemMenu.add(new menuItemsModelo(getResources().getString(R.string.paquetes_celular), R.drawable.paquetes_celular));
        itemMenu.add(new menuItemsModelo(getResources().getString(R.string.recargas_simert), R.drawable.simmert));
        itemMenu.add(new menuItemsModelo(getResources().getString(R.string.pagos_de_servicio), R.drawable.pagos_servicios));
        itemMenu.add(new menuItemsModelo(getResources().getString(R.string.reportes), R.drawable.pagos_servicios));
    }

    @Override
    public void onItemClick(RecyclerView.ViewHolder item, int position, int id) {
        //Toast.makeText(this, "" + itemMenu.get(position).getTextoItem() + " " + id, Toast.LENGTH_SHORT).show();
        Intent intent = new Intent();
        String data = itemMenu.get(position).getTextoItem();
        if (data.equals(getResources().getString(R.string.recargas_celular))) {
            MainActivity.recargasCelular = new RecargasCelular();
            intent.setClass(TransaccionActivity.this, SeleccionOperador.class);
            intent.putExtra("tipoMenu", getResources().getString(R.string.recargas_celular));
            startActivity(intent);
        }
        if (data.equals(getResources().getString(R.string.paquetes_celular))) {
            intent.setClass(TransaccionActivity.this, SeleccionOperador.class);
            intent.putExtra("tipoMenu", getResources().getString(R.string.paquetes_celular));
            startActivity(intent);

        }
        if (data.equals(getResources().getString(R.string.recargas_simert))) {
            Toast.makeText(this, "No disponible", Toast.LENGTH_SHORT).show();
        }
        if (data.equals(getResources().getString(R.string.pagos_de_servicio))) {
            try {
                Printer2 print = Printer2.getInstance();
                print.appendTextEntity2(new TextEntity("Aplicacion de prueba", null, false, Align.CENTER));
                print.appendTextEntity2(new TextEntity("Aplicacion de prueba", null, true, Align.CENTER));
                print.appendTextEntity2(new TextEntity("EIGHT", FontLattice.EIGHT, true, Align.CENTER));
                print.appendTextEntity2(new TextEntity("SIXTEEN", FontLattice.SIXTEEN, true, Align.CENTER));
                print.appendTextEntity2(new TextEntity("TWNTY FOUR", FontLattice.TWENTY_FOUR, true, Align.CENTER));
                print.appendTextEntity2(new TextEntity("THIRTY TWO", FontLattice.THIRTY_TWO, true, Align.CENTER));
                print.appendTextEntity2(new TextEntity("FORTY EIGHT", FontLattice.FORTY_EIGHT, true, Align.CENTER));
                print.appendTextEntity2(new TextEntity(" ", null, false, null));
                print.appendTextEntity2(new TextEntity(" ", null, false, null));
                print.appendTextEntity2(new TextEntity(" ", null, false, null));
                print.appendTextEntity2(new TextEntity(" ", null, false, null));
                print.appendTextEntity2(new TextEntity(" ", null, false, null));

                PrintRespCode printRespCode = print.startPrint();
//
                if (printRespCode != PrintRespCode.Print_Success) {
                    if (printRespCode == PrintRespCode.Printer_PaperLack || printRespCode == PrintRespCode.print_Unknow) {
                        Toast.makeText(TransaccionActivity.this, "Printer is out of paper", Toast.LENGTH_SHORT).show();
                    }
                    else
                        Toast.makeText(TransaccionActivity.this, "Print failed", Toast.LENGTH_SHORT).show();
                }
                Toast.makeText(this, "No disponible", Toast.LENGTH_SHORT).show();
            } catch (Exception e) {
                Toast.makeText(this, "Impresora no disponible " + e, Toast.LENGTH_SHORT).show();
            }
        }
        if (data.equals(getResources().getString(R.string.reportes))) {
            startActivity(new Intent(TransaccionActivity.this, ReportesActivity.class));
        }
    }

    @Override
    public void onBackPressed() {
        doExitApp();
    }

    private long exitTime = 0;

    public void doExitApp() {
        if ((System.currentTimeMillis() - exitTime) > 2000) {
            Toast.makeText(this, "Press again to exit app", Toast.LENGTH_SHORT).show();
            exitTime = System.currentTimeMillis();
        } else {
            finish();
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        MainActivity.recargasCelular = null;
    }
}
