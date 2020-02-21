package com.code93.puntoplus.Actividades;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import com.code93.puntoplus.Adaptador.AdaptadorPaquetes;
import com.code93.puntoplus.MainActivity;
import com.code93.puntoplus.R;
import com.code93.puntoplus.VentanaConfirmacionActivity;
import com.code93.puntoplus.model.Transacciones.PaquetesDatos;
import com.code93.puntoplus.model.Transacciones.Transaccion;

import java.util.ArrayList;

public class SeleccionPaqueteActivity extends AppCompatActivity {

    ArrayList<PaquetesDatos> paquetesDatos;
    private Transaccion transaccion;
    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_seleccion_paquete);
        paquetesDatos = new ArrayList<>();
        initToolbar();
        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            transaccion = (Transaccion) bundle.getSerializable("transaccion");
        } else {
            Toast.makeText(this, "El tipo de ingreso no llego o fallo ", Toast.LENGTH_SHORT).show();
            startActivity(new Intent(SeleccionPaqueteActivity.this, MainActivity.class));
            finish();
        }

        if (transaccion != null) {
            if (transaccion.getOperador() != null) {
                if (transaccion.getOperador().equals(getResources().getString(R.string.claro))) {
                    paquetesClaro();
                }
                if (transaccion.getOperador().equals(getResources().getString(R.string.movistar))) {
                    paquetesMovistar();
                }
                if (transaccion.getOperador().equals(getResources().getString(R.string.cnt))) {
                    paquetesCnt();
                }
                if (transaccion.getOperador().equals(getResources().getString(R.string.tuenti))) {
                    paquetesTuenti();
                }

                cargarRecyclerView();
            } else {
                Toast.makeText(this, "No llego operador", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(SeleccionPaqueteActivity.this, MainActivity.class));
                finish();
            }
        }else {
            Toast.makeText(this, "No llego transaccion", Toast.LENGTH_SHORT).show();
            startActivity(new Intent(SeleccionPaqueteActivity.this, MainActivity.class));
            finish();
        }
    }

    private void cargarRecyclerView() {
        RecyclerView recyclerView = findViewById(R.id.recyclerView);
        AdaptadorPaquetes adaptadorPaquetes = new AdaptadorPaquetes(paquetesDatos, transaccion.getOperador(), getApplicationContext());
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(adaptadorPaquetes);
    }

    public void paquetesClaro(){
        paquetesDatos.add(new PaquetesDatos("Ilimitado 100", "USD 5.00", "Paquete con muchos miutos", "COD12"));
        paquetesDatos.add(new PaquetesDatos("Ilimitado 100", "USD 5.00", "Paquete con muchos miutos", "COD12"));
        paquetesDatos.add(new PaquetesDatos("Ilimitado 100", "USD 5.00", "Paquete con muchos miutos", "COD12"));
        paquetesDatos.add(new PaquetesDatos("Ilimitado 100", "USD 5.00", "Paquete con muchos miutos", "COD12"));
        paquetesDatos.add(new PaquetesDatos("Ilimitado 100", "USD 5.00", "Paquete con muchos miutos", "COD12"));
        paquetesDatos.add(new PaquetesDatos("Ilimitado 100", "USD 5.00", "Paquete con muchos miutos", "COD12"));
    }

    private void paquetesMovistar() {
        paquetesDatos.add(new PaquetesDatos("PAQ YOUTUBE 1", "USD 1.00",
                "Bono YouTube $1 con 3 horas para ver videos.",
                "PIMY1"));
        paquetesDatos.add(new PaquetesDatos("PAQUETE 1", "USD 1.00",
                "Combo $1 con 100 MEGAS Minutos Ilimitados a Movistar 10 Minutos a Todas las operadoras WhatsApp GRATIS para Llamadas & Chat. Vigencia 1 día.",
                "PIM1"));
        paquetesDatos.add(new PaquetesDatos("PAQUETE 10", "USD 10.00",
                "Combo $10 con 3 GIGAS Minutos Ilimitados a Movistar 100 Minutos a Todas las operadoras WhatsApp GRATIS para Llamadas & Chat. Vigencia 30 días.",
                "PIM10"));
        paquetesDatos.add(new PaquetesDatos("PAQUETE 3", "USD 3.00",
                "Combo $3 con 1 GIGA Minutos Ilimitados a Movistar 30 Minutos a Todas las operadoras WhatsApp GRATIS para Llamadas & Chat. Vigencia 7 días.",
                "PIM3"));
        paquetesDatos.add(new PaquetesDatos("PAQUETE 5", "USD 5.00",
                "Combo $5 con 1 GIGA Minutos Ilimitados a Movistar 100 Minutos a Todas las operadoras WhatsApp GRATIS para Llamadas & Chat. Vigencia 30 días.\t",
                "PIM5"));
        paquetesDatos.add(new PaquetesDatos("REDS SOCIALES 1", "USD 1.00",
                "Bono Redes sociales $1 con 1 GIGA para Facebook e Instagram. Vigencia 24 horas.",
                "PIMS1"));
        paquetesDatos.add(new PaquetesDatos("", "USD 5.00",
                "Paquete con muchos miutos",
                "COD12"));
    }

    private void paquetesCnt() {
        paquetesDatos.add(new PaquetesDatos("", "USD 5.00",
                "Paquete con muchos miutos",
                "COD12"));
        paquetesDatos.add(new PaquetesDatos("", "USD 5.00",
                "Paquete con muchos miutos",
                "COD12"));
        paquetesDatos.add(new PaquetesDatos("", "USD 5.00",
                "Paquete con muchos miutos",
                "COD12"));
    }

    private void paquetesTuenti() {
        paquetesDatos.add(new PaquetesDatos("COMODIN DATOS 1", "USD 1.00",
                "Combo $1 con 200 MEGAS Vigencia 1 día.",
                "TUE1"));
        paquetesDatos.add(new PaquetesDatos("COMODIN DATOS 2", "USD 2.00",
                "Combo $2 con 400 MEGAS Vigencia 30 días.",
                "TUE2"));
        paquetesDatos.add(new PaquetesDatos("COMODIN DATOS 4", "USD 4.00",
                "Combo $4 con 800 MEGAS Vigencia 30 días.",
                "TUE4"));
        paquetesDatos.add(new PaquetesDatos("COMODIN MINUTOS", "USD 1.00",
                "10 MINUTOS VIGENCIA DE 1 DIA",
                "TUEV"));
        paquetesDatos.add(new PaquetesDatos("PAQUETE 10", "USD 10.00",
                "Combo $10 con 5 GB Vigencia 30 días. Whatsapp Gratis incluido llamadas Spotify sin comsumir datos 80 Minutos",
                "TUE10"));
    }

    private void initToolbar() {
        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Paquetes");
    }


}
