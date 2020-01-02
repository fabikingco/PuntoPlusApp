package com.example.puntoplus;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.socsi.smartposapi.printer.Align;
import com.socsi.smartposapi.printer.FontLattice;
import com.socsi.smartposapi.printer.PrintRespCode;
import com.socsi.smartposapi.printer.Printer2;
import com.socsi.smartposapi.printer.TextEntity;

import java.util.Timer;
import java.util.TimerTask;

import dmax.dialog.SpotsDialog;

public class VentanaConfirmacionActivity extends AppCompatActivity {

    String[] tipoIngreso;
    String data;
    ImageView imageView;
    TextView titulo, tvMensaje;
    String emisor;
    String mensaje;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ventana_confirmacion);

        imageView = findViewById(R.id.imagen);
        titulo = findViewById(R.id.tvTitulo);
        tvMensaje = findViewById(R.id.tvMensaje);

        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            data = bundle.getString("tipoIngreso", "default");
            if (data.contains("@")) {
                tipoIngreso = data.split("@");
            } else {
                tipoIngreso = new String[1];
                tipoIngreso[0] = data;
                Toast.makeText(this, "" + tipoIngreso[0], Toast.LENGTH_SHORT).show();
            }
            emisor = bundle.getString("emisor", null);
            mensaje = bundle.getString("mensaje", null);
        } else {
            Toast.makeText(this, "El tipo de ingreso no llego o fallo ", Toast.LENGTH_SHORT).show();
            startActivity(new Intent(VentanaConfirmacionActivity.this, MainActivity.class));
            finish();
        }

        if (emisor != null) {
            tvMensaje.setText(emisor + ": " + mensaje);
        } else {
            titulo.setText("No se obtuvo mensaje de respuesta");
            tvMensaje.setText("Puede verficicar en registros mas tarde la confirmacion de la " + tipoIngreso[0]);
        }
    }

    @Override
    public void onBackPressed() {
        doExitApp();
    }

    private long exitTime = 0;

    public void doExitApp() {
        if ((System.currentTimeMillis() - exitTime) > 2000) {
            Toast.makeText(this, "Presione nuevamente para volver al menu principal", Toast.LENGTH_SHORT).show();
            exitTime = System.currentTimeMillis();
        } else {
            finish();
        }
    }

    public void cancelarProceso(View view) {
        startActivity(new Intent(this, MainActivity.class));
        finish();
    }

    public void imprimir(View view) {
        // Validar dispositivo.

        final SpotsDialog spotsDialog = new SpotsDialog(VentanaConfirmacionActivity.this, "Imprimiendo ticket...");
        spotsDialog.show();

        Printer2 print = Printer2.getInstance();
        print.appendTextEntity2(new TextEntity("Aplicacion de prueba", null, true, Align.CENTER));
        print.appendTextEntity2(new TextEntity("Sistema de recargas", FontLattice.THIRTY_TWO, true, Align.CENTER));
        print.appendTextEntity2(new TextEntity(" ", null, false, null));
        print.appendTextEntity2(new TextEntity("Hora: " + Tools.getLocalFormatTime()
                + "Fecha: " + Tools.getLocalFormatDate(), FontLattice.SIXTEEN, true, Align.CENTER));
        print.appendTextEntity2(new TextEntity("Operador: "
                + MainActivity.recargasCelular.getOperador(), null, false, null));
        print.appendTextEntity2(new TextEntity("Numero de telefono: "
                + MainActivity.recargasCelular.getNumero(), null, false, null));
        print.appendTextEntity2(new TextEntity("Monto recargado: $ "
                + MainActivity.recargasCelular.getMonto(), null, false, null));

        print.appendTextEntity2(new TextEntity(" ", null, false, null));
        print.appendTextEntity2(new TextEntity(" ", null, false, null));
        print.appendTextEntity2(new TextEntity(" ", null, false, null));
        print.appendTextEntity2(new TextEntity(" ", null, false, null));

        PrintRespCode printRespCode = print.startPrint();
//
        if (printRespCode != PrintRespCode.Print_Success) {
            spotsDialog.dismiss();
            if (printRespCode == PrintRespCode.Printer_PaperLack || printRespCode == PrintRespCode.print_Unknow) {
                Toast.makeText(VentanaConfirmacionActivity.this, "Printer is out of paper", Toast.LENGTH_SHORT).show();
            }
            else
                Toast.makeText(VentanaConfirmacionActivity.this, "Print failed", Toast.LENGTH_SHORT).show();
        } else {
            spotsDialog.dismiss();
            startActivity(new Intent(VentanaConfirmacionActivity.this, MainActivity.class));
            finish();
        }
        Toast.makeText(this, "No disponible", Toast.LENGTH_SHORT).show();


        /*Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {

            }
        }, 5000);*/
    }
}
