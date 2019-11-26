package com.example.puntoplus;

import android.annotation.TargetApi;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.telephony.SmsMessage;
import android.util.Log;
import android.widget.Toast;

import com.example.puntoplus.BD.ClsConexion;
import com.example.puntoplus.model.SMS_LAST_Singleton;
import com.example.puntoplus.model.SMS_RECV;

public class MySmsReceiver extends BroadcastReceiver {

    private static final String TAG =
            MySmsReceiver.class.getSimpleName();
    public static final String pdu_type = "pdus";

    @TargetApi(Build.VERSION_CODES.M)
    @Override
    public void onReceive(Context context, Intent intent) {
        // Get the SMS message.
        Bundle bundle = intent.getExtras();
        SmsMessage[] msgs;
        String strMessage = "";
        String strNumeroEmisor = "";
        String strMensanje = "";
        String format = bundle.getString("format");
        // Retrieve the SMS message received.
        Object[] pdus = (Object[]) bundle.get(pdu_type);
        if (pdus != null) {
            // Check the Android version.
            boolean isVersionM =
                    (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M);
            // Fill the msgs array.
            msgs = new SmsMessage[pdus.length];
            for (int i = 0; i < msgs.length; i++) {
                // Check Android version and use appropriate createFromPdu.
                if (isVersionM) {
                    // If Android version M or newer:
                    msgs[i] = SmsMessage.createFromPdu((byte[]) pdus[i], format);
                } else {
                    // If Android version L or older:
                    msgs[i] = SmsMessage.createFromPdu((byte[]) pdus[i]);
                }
                // Build the message to show.
                strMessage += "SMS from " + msgs[i].getOriginatingAddress();
                strMessage += " :" + msgs[i].getMessageBody() + "\n";
                strNumeroEmisor = msgs[i].getOriginatingAddress();
                strMensanje = msgs[i].getMessageBody();
                // Log and display the SMS message.
                Log.d(TAG, "onReceive: " + strMessage);
                //Toast.makeText(context, strMessage, Toast.LENGTH_LONG).show();

                if (MainActivity.mCallbackSMS != null) {
                    MainActivity.mCallbackSMS.smsRecibido(strNumeroEmisor, strMensanje);
                }

                if (strNumeroEmisor != null && strNumeroEmisor.equals("9305")) {
                    ClsConexion conexion = new ClsConexion(context);
                    SMS_RECV recv = new SMS_RECV();

                    recv.setRecv_destino(strNumeroEmisor);
                    SMS_LAST_Singleton.getInstance().setEmisor(strNumeroEmisor);

                    recv.setRecv_msg(strMensanje);
                    SMS_LAST_Singleton.getInstance().setMensaje(strMensanje);

                    recv.setRecv_fecha(Tools.getLocalDate());
                    recv.setRecv_hora(Tools.getLocalTime());

                    recv.setRecv_fechahora(Tools.getLocalDateTime());
                    SMS_LAST_Singleton.getInstance().setNeew(Tools.getLocalDateTime());
                    conexion.newSmsRecv(recv);
                }
            }
        }
    }
}
