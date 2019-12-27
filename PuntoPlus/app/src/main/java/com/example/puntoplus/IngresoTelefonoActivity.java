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
            //MainActivity.enviarMensaje(this, "9305", numero);
            spotsDialog.show();
            Timer timer = new Timer();
            timer.schedule(new TimerTask() {
                @Override
                public void run() {
                    spotsDialog.dismiss();
                    validarMensaje(numero, null, null);
                }
            }, 5000);
        }
        if (tipoIngreso[0].equals(getResources().getString(R.string.recargas_celular))) {
            final AlertDialog alertDialog = new AlertDialog.Builder(IngresoTelefonoActivity.this).create();
            alertDialog.setTitle("Ecuamovil");
            alertDialog.setMessage("Confirme los datos. \nRecargara $ " + tipoIngreso[2]
                    + " al numero " + numero + " del operador " + tipoIngreso[1]);
            alertDialog.setButton(AlertDialog.BUTTON_POSITIVE, "Aceptar", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int which) {
                    alertDialog.dismiss();
                    String oper = armarOperador(tipoIngreso[1]);
                    String monto = armarMonto(tipoIngreso[2]);
                    StringBuilder builder = new StringBuilder();
                    builder.append("Rec");
                    builder.append(oper);
                    builder.append(" ");
                    builder.append(monto);
                    builder.append(" ");
                    builder.append(numero);
                    MainActivity.enviarMensaje(IngresoTelefonoActivity.this, "9305", builder.toString());
                    final SpotsDialog spotsDialog = new SpotsDialog(IngresoTelefonoActivity.this, "Esperando mensaje de respuesta...");
                    spotsDialog.show();
                    /*Timer timer = new Timer();
                    timer.schedule(new TimerTask() {
                        @Override
                        public void run() {
                            spotsDialog.dismiss();
                            validarMensaje(null, null, null);
                        }
                    }, 5000);*/
                    MainActivity.mCallbackSMS = new callbackSMS(){
                        @Override
                        public String smsRecibido(String emisor, String mensaje) {
                            spotsDialog.dismiss();
                            MainActivity.mCallbackSMS = null;
                            validarMensaje(numero, emisor, mensaje);
                            return null;
                        }
                    };
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

    private String armarMonto(String s) {
        if (s.substring(s.length()-2).equals("00")) {
            return s.substring(0, s.length() -3);
        } else {
            return s;
        }
    }

    private String armarOperador(String s) {
        if (s.equals(getResources().getString(R.string.claro))) {
            return "cla";
        }
        if (s.equals(getResources().getString(R.string.movistar))) {
            return "mov";
        }
        if (s.equals(getResources().getString(R.string.cnt))) {
            return "cnt";
        }
        if (s.equals(getResources().getString(R.string.tuenti))) {
            return "tue";
        }
        return s;
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
            intent.putExtra("tipoIngreso", data);
            intent.putExtra("emisor", emisor);
            intent.putExtra("mensaje", mensaje);
            startActivity(intent);
        }
        if (tipoIngreso[0].equals(getResources().getString(R.string.paquetes_celular))) {

        }
    }
}
