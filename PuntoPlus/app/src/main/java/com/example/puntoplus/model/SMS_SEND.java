package com.example.puntoplus.model;

public class SMS_SEND {

    private int send_id;
    private String send_destino;
    private String send_msg;
    private String send_fecha;
    private String send_hora;
    private String send_fechahora;

    public SMS_SEND() {
    }

    public SMS_SEND(String send_destino, String send_msg, String send_fecha, String send_hora, String send_fechahora) {
        this.send_destino = send_destino;
        this.send_msg = send_msg;
        this.send_fecha = send_fecha;
        this.send_hora = send_hora;
        this.send_fechahora = send_fechahora;
    }

    public SMS_SEND(int send_id, String send_destino, String send_msg, String send_fecha, String send_hora, String send_fechahora) {
        this.send_id = send_id;
        this.send_destino = send_destino;
        this.send_msg = send_msg;
        this.send_fecha = send_fecha;
        this.send_hora = send_hora;
        this.send_fechahora = send_fechahora;
    }

    public int getSend_id() {
        return send_id;
    }

    public void setSend_id(int send_id) {
        this.send_id = send_id;
    }

    public String getSend_destino() {
        return send_destino;
    }

    public void setSend_destino(String send_destino) {
        this.send_destino = send_destino;
    }

    public String getSend_msg() {
        return send_msg;
    }

    public void setSend_msg(String send_msg) {
        this.send_msg = send_msg;
    }

    public String getSend_fecha() {
        return send_fecha;
    }

    public void setSend_fecha(String send_fecha) {
        this.send_fecha = send_fecha;
    }

    public String getSend_hora() {
        return send_hora;
    }

    public void setSend_hora(String send_hora) {
        this.send_hora = send_hora;
    }

    public String getSend_fechahora() {
        return send_fechahora;
    }

    public void setSend_fechahora(String send_fechahora) {
        this.send_fechahora = send_fechahora;
    }
}
