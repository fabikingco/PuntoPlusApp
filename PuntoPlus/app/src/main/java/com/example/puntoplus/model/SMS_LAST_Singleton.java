package com.example.puntoplus.model;

public class SMS_LAST_Singleton {
    private String emisor;
    private String mensaje;
    private String old="null";
    private String neew="null";

    private static SMS_LAST_Singleton INSTANCE = new SMS_LAST_Singleton();

    public SMS_LAST_Singleton() {

    }

    public static SMS_LAST_Singleton getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new SMS_LAST_Singleton();
        }
        return INSTANCE;
    }

    public boolean isNew() {
     return !old.equals(neew);
    }


    public String getEmisor() {
        return emisor;
    }

    public void setEmisor(String emisor) {
        this.emisor = emisor;
    }

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }

    public String getOld() {
        return old;
    }

    public void setOld() {
        this.old = neew;
    }

    public String getNeew() {
        return neew;
    }

    public void setNeew(String neew) {
        this.neew = neew;
    }
}
