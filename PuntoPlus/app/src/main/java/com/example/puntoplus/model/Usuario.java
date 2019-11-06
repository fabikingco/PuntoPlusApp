package com.example.puntoplus.model;

public class Usuario {

    private String cel;
    private String fecha_in;
    private String hora_in;
    private String fecha_out;
    private String hora_out;

    public Usuario() {
    }

    public String getCel() {
        return cel;
    }

    public void setCel(String cel) {
        this.cel = cel;
    }

    public String getFecha_in() {
        return fecha_in;
    }

    public void setFecha_in(String fecha_in) {
        this.fecha_in = fecha_in;
    }

    public String getHora_in() {
        return hora_in;
    }

    public void setHora_in(String hora_in) {
        this.hora_in = hora_in;
    }

    public String getFecha_out() {
        return fecha_out;
    }

    public void setFecha_out(String fecha_out) {
        this.fecha_out = fecha_out;
    }

    public String getHora_out() {
        return hora_out;
    }

    public void setHora_out(String hora_out) {
        this.hora_out = hora_out;
    }
}
