package com.code93.puntoplus;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.mazenrashed.printooth.Printooth;
import com.mazenrashed.printooth.data.printable.Printable;
import com.mazenrashed.printooth.data.printable.RawPrintable;
import com.mazenrashed.printooth.data.printable.TextPrintable;
import com.mazenrashed.printooth.data.printer.DefaultPrinter;
import com.mazenrashed.printooth.ui.ScanningActivity;
import com.mazenrashed.printooth.utilities.Printing;
import com.mazenrashed.printooth.utilities.PrintingCallback;

import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

import dmax.dialog.SpotsDialog;

public class VentanaConfirmacionActivity extends AppCompatActivity {

    String[] tipoIngreso;
    String data;
    ImageView imageView;
    TextView titulo, tvMensaje;
    String monto;
    String numero;


    ImageView imgStatus;
    TextView tvConexion;
    private Printing printing = null;
    PrintingCallback printingCallback = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ventana_confirmacion);

        imageView = findViewById(R.id.imagen);
        titulo = findViewById(R.id.tvTitulo);
        tvMensaje = findViewById(R.id.tvMensaje);

        if (!Build.MODEL.equals("Android SDK built for x86")) {
            if (Printooth.INSTANCE.hasPairedPrinter())
                printing = Printooth.INSTANCE.printer();
        }

        initStatusPrinter();

        tvConexion = findViewById(R.id.tvConexion);
        tvConexion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!Build.MODEL.equals("Android SDK built for x86")) {
                    if (Printooth.INSTANCE.hasPairedPrinter()) {
                        Printooth.INSTANCE.removeCurrentPrinter();
                        imgStatus.setImageDrawable(getDrawable(R.drawable.ic_remove_circle_red));
                    } else {
                        startActivityForResult(new Intent(VentanaConfirmacionActivity.this, ScanningActivity.class), ScanningActivity.SCANNING_FOR_PRINTER);
                    }
                }
            }
        });

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
            monto = bundle.getString("monto", null);
            numero = bundle.getString("numero", null);
        } else {
            Toast.makeText(this, "El tipo de ingreso no llego o fallo ", Toast.LENGTH_SHORT).show();
            startActivity(new Intent(VentanaConfirmacionActivity.this, MainActivity.class));
            finish();
        }

        titulo.setText("Trasacción finalizada");
        tvMensaje.setText("Si la transaccion fue exitosa puede imprimir el recibo. De lo contrario puede salir.");
    }

    private void initStatusPrinter() {
        imgStatus = findViewById(R.id.imgStatus);
        if (Build.MODEL.equals("NEW9220") || Build.MODEL.equals("Android SDK built for x86") || Build.MODEL.equals("i80")) {
            imgStatus.setImageDrawable(getDrawable(R.drawable.ic_check_circle_green));
        } else {
            if (Printooth.INSTANCE.getPairedPrinter() != null) {
                if (Printooth.INSTANCE.hasPairedPrinter()) {
                    imgStatus.setImageDrawable(getDrawable(R.drawable.ic_check_circle_green));
                }
            }
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

        if (Printooth.INSTANCE.getPairedPrinter() != null) {
            if (Printooth.INSTANCE.hasPairedPrinter()) {
                final SpotsDialog spotsDialog = new SpotsDialog(VentanaConfirmacionActivity.this, "Imprimiendo ticket...");
                spotsDialog.show();

                if (printing!=null)
                    printing.print(obtenerDatosDeImpresion());


                spotsDialog.dismiss();
            } else {
                Toast.makeText(this, "Toast 1 Impresora no disponible", Toast.LENGTH_SHORT).show();
            }
        } else {

        }





    }

    private ArrayList<Printable> obtenerDatosDeImpresion() {
        ArrayList<Printable> al = new ArrayList<>();
        al.add(new RawPrintable.Builder(new byte[]{27, 100, 4}).build()); // feed lines example in raw mode

        al.add( (new TextPrintable.Builder())
                .setText("ECUAMOVIL")
                .setCharacterCode(DefaultPrinter.Companion.getCHARCODE_PC437())
                .setAlignment(DefaultPrinter.Companion.getALIGNMENT_CENTER())
                .setFontSize(DefaultPrinter.Companion.getFONT_SIZE_LARGE())
                .setNewLinesAfter(1)
                .build());
        al.add( (new TextPrintable.Builder())
                .setText("TRANSACCION EXITOSA")
                .setCharacterCode(DefaultPrinter.Companion.getCHARCODE_PC437())
                .setNewLinesAfter(1)
                .build());
        al.add( (new TextPrintable.Builder())
                .setText("Hora: " + Tools.getLocalFormatTime())
                .setCharacterCode(DefaultPrinter.Companion.getCHARCODE_PC437())
                .setNewLinesAfter(1)
                .build());
        al.add( (new TextPrintable.Builder())
                .setText("Fecha: " + Tools.getLocalFormatDate())
                .setCharacterCode(DefaultPrinter.Companion.getCHARCODE_PC437())
                .setNewLinesAfter(1)
                .build());
        al.add( (new TextPrintable.Builder())
                .setText("Operador: " + tipoIngreso[1])
                .setCharacterCode(DefaultPrinter.Companion.getCHARCODE_PC437())
                .setNewLinesAfter(1)
                .build());
        al.add( (new TextPrintable.Builder())
                .setText("Numero: " + numero)
                .setCharacterCode(DefaultPrinter.Companion.getCHARCODE_PC437())
                .setNewLinesAfter(1)
                .build());
        al.add( (new TextPrintable.Builder())
                .setText("Monto: " + monto)
                .setCharacterCode(DefaultPrinter.Companion.getCHARCODE_PC437())
                .setNewLinesAfter(1)
                .build());

        al.add( (new TextPrintable.Builder())
                .setText("Para mas informacion comuniquese a XXX-XXX-XXXX")
                .setCharacterCode(DefaultPrinter.Companion.getCHARCODE_PC437())
                .setNewLinesAfter(1)
                .build());



        return al;
    }
}
