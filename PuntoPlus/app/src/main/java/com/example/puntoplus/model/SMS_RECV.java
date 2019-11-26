package com.example.puntoplus.model;

public class SMS_RECV {

    int recv_id;
    String recv_destino;
    String recv_msg;
    String recv_fecha;
    String recv_hora;
    String recv_fechahora;
    String recv_visto="f";

    public SMS_RECV() {
    }

    public SMS_RECV(String recv_destino, String recv_msg, String recv_fecha, String recv_hora, String recv_fechahora) {
        this.recv_destino = recv_destino;
        this.recv_msg = recv_msg;
        this.recv_fecha = recv_fecha;
        this.recv_hora = recv_hora;
        this.recv_fechahora = recv_fechahora;
    }

    public SMS_RECV(int recv_id, String recv_destino, String recv_msg,String recv_visto, String recv_fecha, String recv_hora, String recv_fechahora) {
        this.recv_id = recv_id;
        this.recv_destino = recv_destino;
        this.recv_msg = recv_msg;
        this.recv_visto= recv_visto;
        this.recv_fecha = recv_fecha;
        this.recv_hora = recv_hora;
        this.recv_fechahora = recv_fechahora;
    }

    public int getRecv_id() {
        return recv_id;
    }

    public void setRecv_id(int recv_id) {
        this.recv_id = recv_id;
    }

    public String getRecv_destino() {
        return recv_destino;
    }

    public void setRecv_destino(String recv_destino) {
        this.recv_destino = recv_destino;
    }

    public String getRecv_msg() {
        return recv_msg;
    }

    public void setRecv_msg(String recv_msg) {
        this.recv_msg = recv_msg;
    }

    public String getRecv_fecha() {
        return recv_fecha;
    }

    public void setRecv_fecha(String recv_fecha) {
        this.recv_fecha = recv_fecha;
    }

    public String getRecv_hora() {
        return recv_hora;
    }

    public void setRecv_hora(String recv_hora) {
        this.recv_hora = recv_hora;
    }

    public String getRecv_fechahora() {
        return recv_fechahora;
    }

    public void setRecv_fechahora(String recv_fechahora) {
        this.recv_fechahora = recv_fechahora;
    }

    public String getRecv_visto() {
        return recv_visto;
    }

    public void setRecv_visto(String recv_visto) {
        this.recv_visto = recv_visto;
    }
}
