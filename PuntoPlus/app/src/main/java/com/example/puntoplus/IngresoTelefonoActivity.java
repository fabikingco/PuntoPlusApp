package com.example.puntoplus;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.puntoplus.BD.ClsConexion;
import com.example.puntoplus.model.SMS_LAST_Singleton;
import com.example.puntoplus.model.SMS_SEND;
import com.example.puntoplus.model.Usuario;
import com.google.android.material.textfield.TextInputEditText;

import java.util.Timer;
import java.util.TimerTask;

import dmax.dialog.SpotsDialog;

public class IngresoTelefonoActivity extends AppCompatActivity {

    TextView tvTitulo, tvSubtitulo;
    String[] tipoIngreso;
    TextInputEditText editText;
    String data;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ingreso_telefono);

        tvTitulo = findViewById(R.id.tvTitulo);
        tvSubtitulo = findViewById(R.id.tvSubtitulo);
        editText = findViewById(R.id.etData);

        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            data = bundle.getString("tipoIngreso", "default");
            if (data.contains("@")) {
                tipoIngreso = data.split("@");
            } else {
                tipoIngreso = new String[1];
                tipoIngreso[0] = data;
            }
            System.out.println(tipoIngreso[0]);
        }

        if (tipoIngreso[0].equals("registro")) {
            tvTitulo.setText("Registre su telefono");
            tvSubtitulo.setText("Para poder usar el servicio debe registrar su numero.");
        }
        if (tipoIngreso[0].equals(getResources().getString(R.string.recargas_celular))) {
            tvTitulo.setText("Ingresa el numero de telefono");
            tvSubtitulo.setText("La  que vas a realizar sera cargado al siguiente numero");
        }
        if (tipoIngreso[0].equals(getResources().getString(R.string.paquetes_celular))) {
            tvTitulo.setText("Ingresa el numero de telefono");
            tvSubtitulo.setText("El paquete que vas a comprar sera cargado al siguiente numero");
        }

    }

    public void cancelarProceso(View view) {
        startActivity(new Intent(this, MainActivity.class));
        finish();
    }

    public void cargarNumero(View view) {
        final String numero = editText.getText().toString();
        final SpotsDialog spotsDialog = new SpotsDialog(this, "Esperando mensaje de respuesta...");
        if (tipoIngreso[0].equals("registro")) {
            //MainActivity.enviarMensaje(this, "9306", numero);
            spotsDialog.show();
            Timer timer = new Timer();
            timer.schedule(new TimerTask() {
                @Override
                public void run() {
                    spotsDialog.dismiss();
                    validarMensaje(numero, null, null);
                }
            }, 5000);
            /*MainActivity.mCallbackSMS = new callbackSMS(){
                @Override
                public String smsRecibido(String emisor, String mensaje) {
                    spotsDialog.dismiss();
                        *//*dataMensaje[0] = emisor;
                        dataMensaje[1] = mensaje;*//*
                    MainActivity.mCallbackSMS = null;
                    validarMensaje(numero, emisor, mensaje);
                    return null;
                }
            };*/
        }
        if (tipoIngreso[0].equals(getResources().getString(R.string.recargas_celular))) {
            final AlertDialog alertDialog = new AlertDialog.Builder(IngresoTelefonoActivity.this).create();
            alertDialog.setTitle("Punto Plus");
            alertDialog.setMessage("Confirme los datos. \nRecargara $ " + tipoIngreso[2]
                    + " al numero " + numero + " del operador " + tipoIngreso[1]);
            alertDialog.setButton(AlertDialog.BUTTON_POSITIVE, "Aceptar", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int which) {
                    alertDialog.dismiss();
                    final SpotsDialog spotsDialog = new SpotsDialog(IngresoTelefonoActivity.this, "Esperando mensaje de respuesta...");
                    spotsDialog.show();
                    MainActivity.enviarMensaje(IngresoTelefonoActivity.this, "9306", numero);// TODO: 26-Nov-19 enviar mensaje
                    SMS_SEND send = new SMS_SEND();
                    send.setSend_destino("9306");
                    send.setSend_msg(numero);
                    send.setSend_fecha(Tools.getLocalDate());
                    send.setSend_hora(Tools.getLocalTime());
                    send.setSend_fechahora(Tools.getLocalDateTime());

                    final ClsConexion clsConexion = new ClsConexion(IngresoTelefonoActivity.this);
                    clsConexion.newSmsSend(send);
                    Timer timer = new Timer();
                    timer.schedule(new TimerTask() {
                        @Override
                        public void run() {
                            spotsDialog.dismiss();
                            if (SMS_LAST_Singleton.getInstance().isNew()) {
                                SMS_LAST_Singleton.getInstance().setOld();
                                clsConexion.actualizarVisto(SMS_LAST_Singleton.getInstance().getNeew(), true);
                                validarMensaje(null, null, SMS_LAST_Singleton.getInstance().getMensaje());
                            } else {
                                validarMensaje(null, null, "no hay SMS");
                            }

                        }
                    }, 10000);
                }
            });
            alertDialog.setButton(AlertDialog.BUTTON_NEGATIVE, "Cancelar", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    startActivity(new Intent(IngresoTelefonoActivity.this, MainActivity.class));
                    finish();
                }
            });

            alertDialog.show();
        }
        if (tipoIngreso[0].equals(getResources().getString(R.string.paquetes_celular))) {

        }


    }

    private void validarMensaje(String numero, String emisor, String mensaje) {
        //final SpotsDialog spotsDialog = new SpotsDialog(this, "Imprimiendo ticket...");
        if (tipoIngreso[0].equals("registro")) {
            /*if (emisor != null) {
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
            }*/
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
        if (tipoIngreso[0].equals(getResources().getString(R.string.recargas_celular))) {
            // Guardar en base de datos
            Intent intent = new Intent(IngresoTelefonoActivity.this, VentanaConfirmacionActivity.class);
            intent.putExtra("tipoIngreso", data + "@" + mensaje);
            startActivity(intent);
        }
        if (tipoIngreso[0].equals(getResources().getString(R.string.paquetes_celular))) {

        }
    }
}
