package com.code93.puntoplus;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.Manifest;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.Settings;
import android.telephony.SmsManager;
import android.util.Log;
import android.widget.Toast;

import com.code93.puntoplus.BD.ClsConexion;
import com.code93.puntoplus.model.Transacciones.RecargasCelular;
import com.code93.puntoplus.model.Usuario;
import com.firebase.ui.auth.AuthUI;
import com.firebase.ui.auth.IdpResponse;
import com.google.android.gms.auth.api.Auth;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.karumi.dexter.Dexter;
import com.karumi.dexter.MultiplePermissionsReport;
import com.karumi.dexter.PermissionToken;
import com.karumi.dexter.listener.PermissionRequest;
import com.karumi.dexter.listener.multi.MultiplePermissionsListener;

import java.util.Arrays;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private static final int MY_REQUESTE_CODE = 101;
    public static callbackSMS mCallbackSMS;
    ClsConexion conexion;
    public static RecargasCelular recargasCelular;

    List<AuthUI.IdpConfig> proveedores;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        mAuth = FirebaseAuth.getInstance();
        FirebaseUser currentUser = mAuth.getCurrentUser();

        if (currentUser != null) {
            startActivity(new Intent(MainActivity.this, TransaccionActivity.class));
            finish();
        } else {
            proveedores = Arrays.asList(
                    new AuthUI.IdpConfig.PhoneBuilder().build()
            );
            conexion = new ClsConexion(this);
            if (!Build.MODEL.equals("Android SDK built for x86")) {
                showSignInOptions();
            } else {
                startActivity(new Intent(MainActivity.this, TransaccionActivity.class));
                finish();
            }
        }


    }

    private void showSignInOptions() {
        startActivityForResult(
                AuthUI.getInstance().createSignInIntentBuilder()
                .setAvailableProviders(proveedores)
                .setTheme(R.style.AuthTheme)
                .build(),MY_REQUESTE_CODE
        );
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == MY_REQUESTE_CODE) {
            IdpResponse response = IdpResponse.fromResultIntent(data);
            if (resultCode == RESULT_OK) {
                FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
                Toast.makeText(this, "" + user.getPhoneNumber(), Toast.LENGTH_SHORT).show();
                confirmarPermisos();

            }
        }
    }

    private void confirmarPermisos() {
        Dexter.withActivity(this)
                .withPermissions(Manifest.permission.READ_SMS, Manifest.permission.SEND_SMS, Manifest.permission.RECEIVE_SMS)
                .withListener(new MultiplePermissionsListener() {
                    @Override
                    public void onPermissionsChecked(MultiplePermissionsReport report) {
                        if (report.areAllPermissionsGranted()) {
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

    private void confirmarInicioSesion() {
       /* SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        Date dateActual = new Date();
        Date fechaFinal = new Date();
        try {
            fechaFinal = format.parse("2019-12-10");
        } catch (ParseException e) {
            e.printStackTrace();
        }

        if (dateActual.compareTo(fechaFinal) >= 0) {
            Toast.makeText(this, "Requiere actualizar la APP - Contacte a soporte tecnico. +57 3017719100", Toast.LENGTH_SHORT).show();
            finish();
            return;
        }*/


        startActivity(new Intent(MainActivity.this, TransaccionActivity.class));
        finish();

        /*Usuario usuario  = conexion.obtenerUsuarioActual();
        if (usuario.getCel() == null) {
            startActivity(new Intent(MainActivity.this, InformacionRegistroActivity.class));
            finish();
        } else {
            startActivity(new Intent(MainActivity.this, TransaccionActivity.class));
            finish();
        }*/
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

    /*public static void enviarMensaje(final Context context, String telefono, String mensaje) {
        //final String[] dataMensaje = new String[2];
        // Use SmsManager.
        Log.i("SMS to " + telefono, "MENSAJE: " + mensaje);
        SmsManager smsManager = SmsManager.getDefault();
        smsManager.sendTextMessage
                (telefono, null, mensaje,
                        null, null);


        *//*final SpotsDialog dialog = new SpotsDialog(context, "Esperando mensaje de respueta...");
        dialog.show();

        mCallbackSMS = new callbackSMS(){
            @Override
            public String smsRecibido(String emisor, String mensaje) {
                dialog.dismiss();
                *//**//*dataMensaje[0] = emisor;
                dataMensaje[1] = mensaje;*//**//*
                mCallbackSMS = null;
                return null;
            }
        };*//*
    }*/
}
