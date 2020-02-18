package com.code93.puntoplus;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Activity;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.code93.puntoplus.Adaptador.NewAdapterMenus;
import com.code93.puntoplus.model.Transacciones.RecargasCelular;
import com.code93.puntoplus.model.menuItemsModelo;
import com.google.firebase.auth.FirebaseAuth;
import com.mazenrashed.printooth.Printooth;
import com.mazenrashed.printooth.data.printable.Printable;
import com.mazenrashed.printooth.data.printable.RawPrintable;
import com.mazenrashed.printooth.data.printable.TextPrintable;
import com.mazenrashed.printooth.data.printer.DefaultPrinter;
import com.mazenrashed.printooth.ui.ScanningActivity;
import com.mazenrashed.printooth.utilities.Printing;
import com.mazenrashed.printooth.utilities.PrintingCallback;

import java.util.ArrayList;
import java.util.List;

public class TransaccionActivity extends AppCompatActivity implements NewAdapterMenus.OnItemClickListenerMesas {

    RecyclerView recyclerView;
    List<menuItemsModelo> itemMenu;
    Toolbar toolbar;
    String tipoMenu;
    ImageView imgStatus;
    TextView tvConexion;

    private Printing printing = null;
    PrintingCallback printingCallback = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_transaccion);
        if (!Build.MODEL.equals("Android SDK built for x86") && !Build.MODEL.equals("Android SDK built for x86_64")) {
            if (Printooth.INSTANCE.hasPairedPrinter())
                printing = Printooth.INSTANCE.printer();
        }

        initStatusPrinter();
        tvConexion = findViewById(R.id.tvConexion);
        tvConexion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!Build.MODEL.equals("Android SDK built for x86") && !Build.MODEL.equals("Android SDK built for x86_64")) {
                    if (Printooth.INSTANCE.hasPairedPrinter()) {
                        Printooth.INSTANCE.removeCurrentPrinter();
                        imgStatus.setImageDrawable(getDrawable(R.drawable.ic_remove_circle_red));
                    } else {
                        startActivityForResult(new Intent(TransaccionActivity.this, ScanningActivity.class), ScanningActivity.SCANNING_FOR_PRINTER);
                    }
                }
            }
        });

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

    private void initStatusPrinter() {
        imgStatus = findViewById(R.id.imgStatus);
        if (Build.MODEL.equals("Android SDK built for x86") || Build.MODEL.equals("Android SDK built for x86_64")) {
            imgStatus.setImageDrawable(getDrawable(R.drawable.ic_check_circle_green));
        } else {
            if (Printooth.INSTANCE.getPairedPrinter() != null) {
                if (Printooth.INSTANCE.hasPairedPrinter()) {
                    imgStatus.setImageDrawable(getDrawable(R.drawable.ic_check_circle_green));
                }
            }
        }
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
            if (!Build.MODEL.equals("Android SDK built for x86") && !Build.MODEL.equals("Android SDK built for x86_64")) {
                FirebaseAuth mAuth = FirebaseAuth.getInstance();
                mAuth.signOut();
            }
            Toast.makeText(this, "Cerrar sesión", Toast.LENGTH_SHORT).show();
            startActivity(new Intent(this, MainActivity.class));
            finish();
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
        itemMenu.add(new menuItemsModelo(getResources().getString(R.string.recargas), R.drawable.recargas_celular));
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
        if (data.equals(getResources().getString(R.string.recargas))) {
            MainActivity.recargasCelular = new RecargasCelular();
            intent.setClass(TransaccionActivity.this, SeleccionOperador.class);
            intent.putExtra("tipoMenu", getResources().getString(R.string.recargas));
            startActivity(intent);
        }
        if (data.equals(getResources().getString(R.string.paquetes_celular))) {
            intent.setClass(TransaccionActivity.this, SeleccionOperador.class);
            intent.putExtra("tipoMenu", getResources().getString(R.string.paquetes_celular));
            startActivity(intent);

        }
        if (data.equals(getResources().getString(R.string.recargas_simert))) {
            MainActivity.recargasCelular = new RecargasCelular();
            intent.setClass(TransaccionActivity.this, SeleccionOperador.class);
            intent.putExtra("tipoMenu", getResources().getString(R.string.recargas_simert));
            startActivity(intent);

        }
        if (data.equals(getResources().getString(R.string.pagos_de_servicio))) {
            Toast.makeText(this, "No disponible", Toast.LENGTH_SHORT).show();
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

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Log.d("xxx", "onActivityResult "+requestCode);

        if (requestCode == ScanningActivity.SCANNING_FOR_PRINTER && resultCode == Activity.RESULT_OK) {
            printing = Printooth.INSTANCE.printer();
            initListeners();
            //printSomePrintable();
        }
        initStatusPrinter();
    }

    private void initListeners() {
        if (printing!=null && printingCallback==null) {
            Log.d("xxx", "initListeners ");
            printingCallback = new PrintingCallback() {

                public void connectingWithPrinter() {
                    Toast.makeText(getApplicationContext(), "Connecting with printer", Toast.LENGTH_SHORT).show();
                    Log.d("xxx", "Connecting");
                }
                public void printingOrderSentSuccessfully() {
                    Toast.makeText(getApplicationContext(), "printingOrderSentSuccessfully", Toast.LENGTH_SHORT).show();
                    Log.d("xxx", "printingOrderSentSuccessfully");
                }
                public void connectionFailed(@NonNull String error) {
                    Toast.makeText(getApplicationContext(), "connectionFailed :"+error, Toast.LENGTH_SHORT).show();
                    Log.d("xxx", "connectionFailed : "+error);
                }
                public void onError(@NonNull String error) {
                    Toast.makeText(getApplicationContext(), "onError :"+error, Toast.LENGTH_SHORT).show();
                    Log.d("xxx", "onError : "+error);
                }
                public void onMessage(@NonNull String message) {
                    Toast.makeText(getApplicationContext(), "onMessage :" +message, Toast.LENGTH_SHORT).show();
                    Log.d("xxx", "onMessage : "+message);
                }
            };

            Printooth.INSTANCE.printer().setPrintingCallback(printingCallback);
        }
    }
}
