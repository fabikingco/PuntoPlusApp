package com.code93.puntoplus.Actividades;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import com.code93.puntoplus.MainActivity;
import com.code93.puntoplus.R;
import com.code93.puntoplus.VentanaConfirmacionActivity;
import com.code93.puntoplus.model.Transacciones.PaquetesDatos;
import com.code93.puntoplus.model.Transacciones.Transaccion;

import java.util.ArrayList;

public class SeleccionPaqueteActivity extends AppCompatActivity {

    ArrayList<PaquetesDatos> paquetesDatos;
    private Transaccion transaccion;

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

    public void paquetesClaro(){
        paquetesDatos.add(new PaquetesDatos("Ilimitado 100", "USD 5.00", "Paquete con muchos miutos", "COD12"));
        paquetesDatos.add(new PaquetesDatos("Ilimitado 100", "USD 5.00", "Paquete con muchos miutos", "COD12"));
        paquetesDatos.add(new PaquetesDatos("Ilimitado 100", "USD 5.00", "Paquete con muchos miutos", "COD12"));
        paquetesDatos.add(new PaquetesDatos("Ilimitado 100", "USD 5.00", "Paquete con muchos miutos", "COD12"));
        paquetesDatos.add(new PaquetesDatos("Ilimitado 100", "USD 5.00", "Paquete con muchos miutos", "COD12"));
        paquetesDatos.add(new PaquetesDatos("Ilimitado 100", "USD 5.00", "Paquete con muchos miutos", "COD12"));
    }

    private void paquetesMovistar() {
    }

    private void paquetesCnt() {
    }

    private void paquetesTuenti() {
    }


}
