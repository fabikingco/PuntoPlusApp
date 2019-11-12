package com.example.puntoplus;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.puntoplus.BD.ClsConexion;
import com.example.puntoplus.model.Usuario;
import com.google.android.material.textfield.TextInputEditText;

import dmax.dialog.SpotsDialog;

public class IngresoTelefonoActivity extends AppCompatActivity {

    TextView tvTitulo, tvSubtitulo;
    String[] tipoIngreso;
    TextInputEditText editText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ingreso_telefono);

        tvTitulo = findViewById(R.id.tvTitulo);
        tvSubtitulo = findViewById(R.id.tvSubtitulo);
        editText = findViewById(R.id.etData);

        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            String data = bundle.getString("tipoIngreso", "default");
            if (data.contains("@")) {
                tipoIngreso = data.split("@");
            } else {
                tipoIngreso = new String[1];
                tipoIngreso[0] = data;
            }
            System.out.println(tipoIngreso[0]);
        }

        switch (tipoIngreso[0]) {
            case "registro":
                tvTitulo.setText("Registre su telefono");
                tvSubtitulo.setText("Para poder usar el servicio debe registrar su numero.");
                break;
            case "recargas":
                tvTitulo.setText("Ingresa el numero de telefono");
                tvSubtitulo.setText("La  que vas a realizar sera cargado al siguiente numero");
                break;
            case "paquetes":
                tvTitulo.setText("Ingresa el numero de telefono");
                tvSubtitulo.setText("El paquete que vas a comprar sera cargado al siguiente numero");
                break;
        }

    }

    public void cancelarProceso(View view) {
        finish();
    }

    public void cargarNumero(View view) {
        final String numero = editText.getText().toString();
        final SpotsDialog dialog = new SpotsDialog(this, "Esperando mensaje de respuesta...");
        switch (tipoIngreso[0]) {
            case "registro":
                MainActivity.enviarMensaje(this, "9306", numero);
                dialog.show();
                MainActivity.mCallbackSMS = new callbackSMS(){
                    @Override
                    public String smsRecibido(String emisor, String mensaje) {
                        dialog.dismiss();
                        /*dataMensaje[0] = emisor;
                        dataMensaje[1] = mensaje;*/
                        MainActivity.mCallbackSMS = null;
                        validarMensaje(numero, emisor, mensaje, tipoIngreso[0]);
                        return null;
                    }
                };


                /*String[] mensaje = MainActivity.enviarMensaje(this, "9306", numero);
                if (mensaje != null) {
                    if (mensaje[0].equals("9306")) {
                        if (mensaje[1].equals("registrado exitosamente")) {
                            ClsConexion conexion = new ClsConexion(this);
                            Usuario usuario = new Usuario();
                            usuario.setCel(numero);
                            usuario.setFecha_in(Tools.getLocalDate());
                            usuario.setHora_in(Tools.getLocalTime());
                            if (conexion.ingresoUsuarioDB(usuario)) {
                                startActivity(new Intent(IngresoTelefonoActivity.this, TransaccionActivity.class));
                            } else {
                                Toast.makeText(this, "Fallo registro en BD", Toast.LENGTH_SHORT).show();
                            }

                        }
                    }
                }*/
                break;
            case "recargas":

                break;
        }


    }

    private void validarMensaje(String numero, String emisor, String mensaje, String tipoIngreso) {
        switch (tipoIngreso){
            case "registro":
                if (emisor != null) {
                    if (emisor.equals("9306")) {
                        if (mensaje.equals("registrado exitosamente")) {
                            ClsConexion conexion = new ClsConexion(this);
                            Usuario usuario = new Usuario();
                            usuario.setCel(numero);
                            usuario.setFecha_in(Tools.getLocalDate());
                            usuario.setHora_in(Tools.getLocalTime());
                            if (conexion.ingresoUsuarioDB(usuario)) {
                                startActivity(new Intent(IngresoTelefonoActivity.this, TransaccionActivity.class));
                            } else {
                                Toast.makeText(this, "Fallo registro en BD", Toast.LENGTH_SHORT).show();
                            }
                        }else {
                            Toast.makeText(this, "mensaje distinto", Toast.LENGTH_SHORT).show();
                        }
                    }else {
                        Toast.makeText(this, "no es el receptor esperado", Toast.LENGTH_SHORT).show();
                    }
                }else {
                    Toast.makeText(this, "fallo", Toast.LENGTH_SHORT).show();
                }
                break;
            case "recarga":
                break;
        }
    }
}
