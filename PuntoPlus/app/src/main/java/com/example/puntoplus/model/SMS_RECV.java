package com.example.puntoplus.model;

public class SMS_RECV extends SMS {


    private String recv_visto="f";

    public SMS_RECV() {
        super(true);
    }

    public SMS_RECV(String recv_destino, String recv_msg, String recv_fecha, String recv_hora, String recv_fechahora) {
        super( recv_destino,  recv_msg,  recv_fecha,  recv_hora,  recv_fechahora, true);
    }

    public SMS_RECV(int recv_id, String recv_destino, String recv_msg,String recv_visto, String recv_fecha, String recv_hora, String recv_fechahora) {
        super( recv_id,  recv_destino,  recv_msg,  recv_fecha,  recv_hora,  recv_fechahora, true);
        this.recv_visto=recv_visto;

    }

    public String getRecv_visto() {
        return recv_visto;
    }

    public void setRecv_visto(String recv_visto) {
        this.recv_visto = recv_visto;
    }
}
