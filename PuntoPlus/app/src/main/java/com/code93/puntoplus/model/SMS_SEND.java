package com.code93.puntoplus.model;

public class SMS_SEND extends SMS{


    public SMS_SEND() {
        super(false);
    }

    public SMS_SEND(String send_destino, String send_msg, String send_fecha, String send_hora, String send_fechahora) {
        super(send_destino, send_msg, send_fecha, send_hora, send_fechahora, false);

    }

    public SMS_SEND(int send_id, String send_destino, String send_msg, String send_fecha, String send_hora, String send_fechahora) {
        super(send_id,send_destino, send_msg, send_fecha, send_hora, send_fechahora, false);
        }

}
