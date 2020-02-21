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
import com.code93.puntoplus.SeleccionOperador;
import com.code93.puntoplus.VentanaConfirmacionActivity;
import com.code93.puntoplus.model.Transacciones.PaquetesDatos;
import com.code93.puntoplus.model.Transacciones.Transaccion;

import java.util.ArrayList;

public class SeleccionPaqueteActivity extends AppCompatActivity implements AdaptadorPaquetes.OnItemClickListenerPaquetes{

    ArrayList<PaquetesDatos> paquetesDatos;
    private Transaccion transaccion;
    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_seleccion_paquete);
        paquetesDatos = new ArrayList<>();

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

        initToolbar();
    }

    private void cargarRecyclerView() {
        RecyclerView recyclerView = findViewById(R.id.recyclerView);
        AdaptadorPaquetes adaptadorPaquetes = new AdaptadorPaquetes(paquetesDatos, transaccion.getOperador(), getApplicationContext());
        adaptadorPaquetes.setOnItemClickListener(this);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(adaptadorPaquetes);
    }

    public void paquetesClaro(){
        paquetesDatos.add(new PaquetesDatos("PAQUETE 1 GIGA 30 dias", "5",
                "1GIGA + 100 Minutos a todos + Llamadas ilimitadas a 5 numeros Claro + Whatsapp y Messenger gratis 30 dias a $5",
                "PIC5"));
        paquetesDatos.add(new PaquetesDatos("PAQUETE 2 GIGAS 30 dias", "10",
                "3 GIGAS + 200 minutos a todos + llamadas ilimitadas a Claro a 5 numeros + Whatsapp y mesenger gratis 30 dias a $10",
                "PIC10"));
        paquetesDatos.add(new PaquetesDatos("PAQUETE Ilimitado 1 dia", "1",
                "50 Megas + Mensajes y LLAMADAS ILIMITADAS a Claro movil 1 dia a $1",
                "PIC1"));
        paquetesDatos.add(new PaquetesDatos("PAQUETE Ilimitado 2 dias", "2",
                "300 Megas + Mensajes y LLAMADAS ILIMITADAS a numeros Claro + Whatsapp + messenger gratis 2 dias a $2",
                "PIC2"));
        paquetesDatos.add(new PaquetesDatos("PAQUETE Ilimitado 3 dias ", "3",
                "1.5 Gb + LLAMADAS ILIMITADAS a numeros claro + 30 minutos a todas las operadoras + Whatsapp + Messenger gratis 3 dias a $3",
                "PIC3"));
        paquetesDatos.add(new PaquetesDatos("PAQUETE Ilimitado 6 dias", "6",
                "3 GIGAS CLARO RECARGA 3GIGAS $6 - 7 Dias 60 Minutos a todas las operadoras, Whatsapp y Messenger gratis",
                "PIC6"));
        paquetesDatos.add(new PaquetesDatos("CLARO FÚTBOL", "5",
                "1 $ PARTIDO X FECHA + 2GB por fecha 5 $ PARTIDO + GOLES X MES + 10GB 30 dias",
                "PAQFUT"));
    }

    private void paquetesMovistar() {
        paquetesDatos.add(new PaquetesDatos("PAQ YOUTUBE 1", "1",
                "Bono YouTube $1 con 3 horas para ver videos.",
                "PIMY1"));
        paquetesDatos.add(new PaquetesDatos("PAQUETE 1", "1",
                "Combo $1 con 100 MEGAS Minutos Ilimitados a Movistar 10 Minutos a Todas las operadoras WhatsApp GRATIS para Llamadas & Chat. Vigencia 1 día.",
                "PIM1"));
        paquetesDatos.add(new PaquetesDatos("PAQUETE 10", "10",
                "Combo $10 con 3 GIGAS Minutos Ilimitados a Movistar 100 Minutos a Todas las operadoras WhatsApp GRATIS para Llamadas & Chat. Vigencia 30 días.",
                "PIM10"));
        paquetesDatos.add(new PaquetesDatos("PAQUETE 3", "3",
                "Combo $3 con 1 GIGA Minutos Ilimitados a Movistar 30 Minutos a Todas las operadoras WhatsApp GRATIS para Llamadas & Chat. Vigencia 7 días.",
                "PIM3"));
        paquetesDatos.add(new PaquetesDatos("PAQUETE 5", "5",
                "Combo $5 con 1 GIGA Minutos Ilimitados a Movistar 100 Minutos a Todas las operadoras WhatsApp GRATIS para Llamadas & Chat. Vigencia 30 días.",
                "PIM5"));
        paquetesDatos.add(new PaquetesDatos("REDS SOCIALES 1", "1",
                "Bono Redes sociales $1 con 1 GIGA para Facebook e Instagram. Vigencia 24 horas.",
                "PIMS1"));
    }

    private void paquetesCnt() {

    }

    private void paquetesTuenti() {
        paquetesDatos.add(new PaquetesDatos("COMODIN DATOS 1", "1",
                "Combo $1 con 200 MEGAS Vigencia 1 día.",
                "TUE1"));
        paquetesDatos.add(new PaquetesDatos("COMODIN DATOS 2", "2",
                "Combo $2 con 400 MEGAS Vigencia 30 días.",
                "TUE2"));
        paquetesDatos.add(new PaquetesDatos("COMODIN DATOS 4", "4",
                "Combo $4 con 800 MEGAS Vigencia 30 días.",
                "TUE4"));
        paquetesDatos.add(new PaquetesDatos("COMODIN MINUTOS", "1",
                "10 MINUTOS VIGENCIA DE 1 DIA",
                "TUEV"));
        paquetesDatos.add(new PaquetesDatos("PAQUETE 10", "10",
                "Combo $10 con 5 GB Vigencia 30 días. Whatsapp Gratis incluido llamadas Spotify sin comsumir datos 80 Minutos",
                "TUE10"));
        paquetesDatos.add(new PaquetesDatos("PAQUETE 5", "5",
                "Combo $5 con 1,5 GB Vigencia 30 días. Whatsapp Gratis incluido llamadas Spotify sin comsumir datos 30 Minutos",
                "TUE5"));
        paquetesDatos.add(new PaquetesDatos("YOUFLIX 1", "1",
                "PARA YOUTUBE Y NETFLIX. 3 HORAS",
                "TUEY"));

    }

    private void initToolbar() {
        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Paquetes");
    }


    @Override
    public void onItemClick(RecyclerView.ViewHolder item, int position, int id) {
        PaquetesDatos paquete = paquetesDatos.get(position);
        Toast.makeText(this, "" + paquete.getTitulo(), Toast.LENGTH_SHORT).show();
        
        transaccion.setContrapartida2(paquete.getTitulo());
        transaccion.setMonto(paquete.getMonto());
        transaccion.setContrapartida3(paquete.getDescripcion());
        transaccion.setName4("Codigo");
        transaccion.setContrapartida4(paquete.getCodigo());
        
        Intent intent = new Intent();
        intent.setClass(SeleccionPaqueteActivity.this, RecargaPaquetesActivity.class);
        intent.putExtra("transaccion", transaccion);
        startActivity(intent);
    
    }
}
