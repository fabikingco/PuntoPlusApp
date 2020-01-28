package com.code93.puntoplus.model;

public abstract class SMS {

    protected int id;
    protected boolean isRecv;
    protected String destino;
    protected String msg;
    protected String fecha;
    protected String hora;
    protected String fechahora;

    public SMS( boolean isRecv) {
        this.isRecv = isRecv;
    }

    protected SMS(String destino, String msg, String fecha, String hora, String fechahora, boolean isRecv) {
        this.destino = destino;
        this.msg = msg;
        this.fecha = fecha;
        this.hora = hora;
        this.fechahora = fechahora;
        this.isRecv = isRecv;
    }

    protected SMS(int id, String destino, String msg, String fecha, String hora, String fechahora, boolean isRecv) {
        this.id = id;
        this.destino = destino;
        this.msg = msg;
        this.fecha = fecha;
        this.hora = hora;
        this.fechahora = fechahora;
        this.isRecv = isRecv;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDestino() {
        return destino;
    }

    public void setDestino(String destino) {
        this.destino = destino;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public String getHora() {
        return hora;
    }

    public void setHora(String hora) {
        this.hora = hora;
    }

    public String getFechahora() {
        return fechahora;
    }

    public void setFechahora(String fechahora) {
        this.fechahora = fechahora;
    }

    public boolean isRecv() {
        return isRecv;
    }

    public void setRecv(boolean recv) {
        isRecv = recv;
    }
}
