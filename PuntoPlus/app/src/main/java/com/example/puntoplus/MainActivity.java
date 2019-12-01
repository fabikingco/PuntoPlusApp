package com.example.puntoplus;

import androidx.appcompat.app.AppCompatActivity;

import android.Manifest;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.Settings;
import android.telephony.SmsManager;
import android.widget.Button;
import android.widget.EditText;

import com.example.puntoplus.BD.ClsConexion;
import com.example.puntoplus.model.SMS_SEND;
import com.example.puntoplus.model.Usuario;
import com.karumi.dexter.Dexter;
import com.karumi.dexter.MultiplePermissionsReport;
import com.karumi.dexter.PermissionToken;
import com.karumi.dexter.listener.PermissionRequest;
import com.karumi.dexter.listener.multi.MultiplePermissionsListener;

import java.util.List;

import dmax.dialog.SpotsDialog;

public class MainActivity extends AppCompatActivity {

    Button btnEnviarMensaje;
    EditText etTelefono, etMensaje;
    public static callbackSMS mCallbackSMS;
    ClsConexion conexion;
    SpotsDialog dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        conexion = new ClsConexion(this);
        confirmarPermisos();
    }

    private void confirmarInicioSesion() {
        Usuario usuario  = conexion.obtenerUsuarioActual();
        if (usuario.getCel() == null) {
            startActivity(new Intent(MainActivity.this, InformacionRegistroActivity.class));
            finish();
        } else {
            startActivity(new Intent(MainActivity.this, TransaccionActivity.class));
            finish();
        }
    }

    private void confirmarPermisos() {
        Dexter.withActivity(this)
                .withPermissions(Manifest.permission.READ_SMS, Manifest.permission.SEND_SMS, Manifest.permission.RECEIVE_SMS)
                .withListener(new MultiplePermissionsListener() {
                    @Override
                    public void onPermissionsChecked(MultiplePermissionsReport report) {
                        if (report.areAllPermissionsGranted()) {
                            /*Toast.makeText(MainActivity.this, "Todos los permisos garantizados", Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(MainActivity.this, InformacionRegistroActivity.class));
                            //startActivity(new Intent(MainActivity.this, IngresoMontoActivity.class));
                            finish();*/
                            confirmarInicioSesion();
                        }
                        if (report.isAnyPermissionPermanentlyDenied()) {
                            showSettingsDialog();
                        }
                    }
                    @Override
                    public void onPermissionRationaleShouldBeShown(List<PermissionRequest> permissions, PermissionToken token) {
                        token.continuePermissionRequest();
                    }
                }).check();
    }



    void showSettingsDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Grant Permissions");
        builder.setMessage("This app needs permission to use this feature. You can grant them in app settings.");
        builder.setPositiveButton("GOTO SETTINGS", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
                MainActivity.this.openSettings();
            }
        });
        builder.setNegativeButton(getString(android.R.string.cancel), new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });
        builder.show();

    }

    void openSettings() {
        Intent intent = new Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
        Uri uri = Uri.fromParts("package", getPackageName(), null);
        intent.setData(uri);
        startActivityForResult(intent, 101);
    }

    public static void enviarMensaje(final Context context, String telefono, String mensaje) {
        //final String[] dataMensaje = new String[2];
        // Use SmsManager.
        SmsManager smsManager = SmsManager.getDefault();
        smsManager.sendTextMessage
                (telefono, null, mensaje,
                        null, null);

        /*final SpotsDialog dialog = new SpotsDialog(context, "Esperando mensaje de respueta...");
        dialog.show();

        mCallbackSMS = new callbackSMS(){
            @Override
            public String smsRecibido(String emisor, String mensaje) {
                dialog.dismiss();
                *//*dataMensaje[0] = emisor;
                dataMensaje[1] = mensaje;*//*
                mCallbackSMS = null;
                return null;
            }
        };*/



    }
}
