package com.code93.puntoplus.BD;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.code93.puntoplus.Tools;
import com.code93.puntoplus.model.Comercio;
import com.code93.puntoplus.model.SMS;
import com.code93.puntoplus.model.SMS_RECV;
import com.code93.puntoplus.model.SMS_SEND;
import com.code93.puntoplus.model.Usuario;

import java.util.ArrayList;

public class ClsConexion extends SQLiteOpenHelper {

    private SQLiteDatabase db;
    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "resto_app.db";

    public ClsConexion(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    private final String TABLE_SMS_SEND = "sms_send";
    private final String COLUMN_SMS_SEND_ID = "send_id";
    private final String COLUMN_SMS_SEND_DESTINO = "send_destino";
    private final String COLUMN_SMS_SEND_MSG = "send_msg";
    private final String COLUMN_SMS_SEND_FECHA = "send_fecha";
    private final String COLUMN_SMS_SEND_HORA = "send_hora";
    private final String COLUMN_SMS_SEND_FECHAHORA = "send_fechahora";

    private final String CREATE_TABLE_SMS_SEND = "create table " + TABLE_SMS_SEND + " (" +
            COLUMN_SMS_SEND_ID + " integer primary key AUTOINCREMENT, " +
            COLUMN_SMS_SEND_DESTINO + " text not null, " +
            COLUMN_SMS_SEND_MSG + " text not null, " +
            COLUMN_SMS_SEND_FECHA + " text not null, " +
            COLUMN_SMS_SEND_HORA + " text not null, " +
            COLUMN_SMS_SEND_FECHAHORA + " text not null);";

    private final String TABLE_SMS_RECV = "sms_recv";
    private final String COLUMN_SMS_RECV_ID = "recv_id";
    private final String COLUMN_SMS_RECV_DESTINO = "recv_destino";
    private final String COLUMN_SMS_RECV_MSG = "recv_msg";
    private final String COLUMN_SMS_RECV_FECHA = "recv_fecha";
    private final String COLUMN_SMS_RECV_HORA = "recv_hora";
    private final String COLUMN_SMS_RECV_FECHAHORA = "recv_fechahora";

    private final String CREATE_TABLE_SMS_RECV = "create table " + TABLE_SMS_RECV + " (" +
            COLUMN_SMS_RECV_ID + " integer primary key AUTOINCREMENT, " +
            COLUMN_SMS_RECV_DESTINO + " text not null, " +
            COLUMN_SMS_RECV_MSG + " text not null, " +
            COLUMN_SMS_RECV_FECHA + " text not null, " +
            COLUMN_SMS_RECV_HORA + " text not null, " +
            COLUMN_SMS_RECV_FECHAHORA + " text not null);";

    private final String TABLE_REGISTRO_USER = "user";
    private final String COLUMN_USER_CEL = "user_cel";
    private final String COLUMN_USER_FECHA = "user_fecha";
    private final String COLUMN_USER_HORA = "user_hora";

    private final String CREATE_TABLE_USER = "create table " + TABLE_REGISTRO_USER + " (" +
            COLUMN_USER_CEL + " text primary key, " +
            COLUMN_USER_FECHA + " text not null, " +
            COLUMN_USER_HORA + " text not null);";

    private final String TABLE_LOGS_USER = "logs_user";
    private final String COLUMN_LOGS_ID = "logs_id";
    private final String COLUMN_LOGS_FECHA_OUT = "logs_out_fecha";
    private final String COLUMN_LOGS_HORA_OUT = "logs_out_hora";

    private final String CREATE_TABLE_LOGS_USER = "create table " + TABLE_LOGS_USER + " (" +
            COLUMN_LOGS_ID + " integer primary key AUTOINCREMENT, " +
            COLUMN_USER_CEL + " text not null, " +
            COLUMN_USER_FECHA + " text not null, " +
            COLUMN_USER_HORA + " text not null, " +
            COLUMN_LOGS_FECHA_OUT + " text, " +
            COLUMN_LOGS_HORA_OUT + " text);";

    private final String TABLE_COMERCIO = "comercio";

    private final String comercio_id = "id";
    private final String comercio_name = "name";
    private final String comercio_documento = "documento";
    private final String comercio_direccion = "direccion";
    private final String comercio_ciudad = "ciudad";
    private final String comercio_estado = "estado";
    private final String comercio_pais = "pais";
    private final String comercio_telefono1 = "telefono1";
    private final String comercio_telefono2 = "telefono2";
    private final String comercio_header1 = "header1";
    private final String comercio_header2 = "header2";
    private final String comercio_footing1 = "footing1";
    private final String comercio_footing2 = "footing2";
    private final String comercio_moneda = "moneda";
    private final String comercio_simboloMoneda = "simboloMoneda";
    private final String comercio_centavos = "centavos";

    private final String CREATE_TABLE_COMERCIOS =
            "CREATE TABLE " + TABLE_COMERCIO +
                    " (" + comercio_id + " INTEGER PRIMARY KEY, " +
                    comercio_name + " TEXT, " +
                    comercio_documento + " TEXT, " +
                    comercio_direccion + " TEXT, " +
                    comercio_ciudad + " TEXT, " +
                    comercio_estado + " TEXT, " +
                    comercio_pais + " TEXT, " +
                    comercio_telefono1 + " TEXT, " +
                    comercio_telefono2 + " TEXT, " +
                    comercio_header1 + " TEXT, " +
                    comercio_header2 + " TEXT, " +
                    comercio_footing1 + " TEXT, " +
                    comercio_footing2 + " TEXT, " +
                    comercio_moneda + " TEXT, " +
                    comercio_simboloMoneda + " TEXT, " +
                    comercio_centavos + " INTEGER)";

    public static final int idComercio = 1;

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE_SMS_SEND);
        db.execSQL(CREATE_TABLE_SMS_RECV);
        db.execSQL(CREATE_TABLE_USER);
        db.execSQL(CREATE_TABLE_LOGS_USER);
        db.execSQL(CREATE_TABLE_COMERCIOS);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        System.out.println("oldVersion SQL " + oldVersion + "newVersion SQL " + newVersion);
    }

    public boolean newSmsSend(SMS_SEND sms_send) {
        boolean ret = false;
        db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_SMS_SEND_DESTINO, sms_send.getDestino());
        values.put(COLUMN_SMS_SEND_MSG, sms_send.getMsg());
        values.put(COLUMN_SMS_SEND_FECHA, sms_send.getFecha());
        values.put(COLUMN_SMS_SEND_HORA, sms_send.getHora());
        values.put(COLUMN_SMS_SEND_FECHAHORA, sms_send.getFechahora());
        try {
            db.insert(TABLE_SMS_SEND, null, values);
            db.close();
            ret = true;
        } catch (SQLException e) {
            e.getCause();
        }
        return ret;
    }

    public boolean newSmsRecv(SMS_RECV sms_recv) {
        boolean ret = false;
        db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_SMS_RECV_DESTINO, sms_recv.getRecv_destino());
        values.put(COLUMN_SMS_RECV_MSG, sms_recv.getRecv_msg());
        values.put(COLUMN_SMS_RECV_FECHA, sms_recv.getRecv_fecha());
        values.put(COLUMN_SMS_RECV_HORA, sms_recv.getRecv_hora());
        values.put(COLUMN_SMS_RECV_FECHAHORA, sms_recv.getRecv_fechahora());
        try {
            db.insert(TABLE_SMS_RECV, null, values);
            db.close();
            ret = true;
        } catch (SQLException e) {
            e.getCause();
        }
        return ret;
    }

    public ArrayList<SMS> getAllSMSSend() {
        ArrayList<SMS> list = new ArrayList<>();
        db = this.getWritableDatabase();
        String query = "SELECT * FROM " + TABLE_SMS_SEND;
        Cursor cursor = db.rawQuery(query, null);
        if (cursor.moveToFirst()) {
            do {
                list.add(new SMS_SEND(cursor.getInt(0), cursor.getString(1),
                        cursor.getString(2), cursor.getString(3), cursor.getString(4),
                        cursor.getString(5)));
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return list;
    }

    public ArrayList<SMS_RECV> getAllSMSRecv() {
        ArrayList<SMS_RECV> list = new ArrayList<>();
        db = this.getWritableDatabase();
        String query = "SELECT * FROM " + TABLE_SMS_RECV;
        Cursor cursor = db.rawQuery(query, null);
        if (cursor.moveToFirst()) {
            do {
                list.add(new SMS_RECV(cursor.getInt(0), cursor.getString(1),
                        cursor.getString(2), cursor.getString(3), cursor.getString(4),
                        cursor.getString(5)));
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return list;
    }


    /*public boolean actualizarVisto(String recv_fechahora, Boolean isVisto) {
        boolean result = false;
        db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        if (isVisto) {
            values.put(COLUMN_SMS_RECV_VISTO, "v");
        } else {
            values.put(COLUMN_SMS_RECV_VISTO, "f");
        }
        try {
            db.update(TABLE_SMS_RECV, values, COLUMN_SMS_RECV_FECHAHORA + "= " + recv_fechahora, null);
            db.close();
            result = true;
        } catch (Exception e) {
            e.printStackTrace();

        }
        return result;
    }*/

    /**
     * @param usuario
     * @return TODO = Change return variable by int
     */
    public boolean ingresoUsuarioDB(Usuario usuario) {
        boolean ret = false;
        db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_USER_CEL, usuario.getCel());
        values.put(COLUMN_USER_FECHA, usuario.getFecha_in());
        values.put(COLUMN_USER_HORA, usuario.getHora_in());
        try {
            db.insert(TABLE_REGISTRO_USER, null, values);
            db.close();
            ret = guardarLogUsuario(usuario);
            if (!ret) {
                eliminarUsuarioDB();
            }
        } catch (SQLException e) {
            e.getCause();
        }
        return ret;
    }

    public boolean guardarLogUsuario(Usuario usuario) {
        boolean ret = false;
        db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_USER_CEL, usuario.getCel());
        values.put(COLUMN_USER_FECHA, usuario.getFecha_in());
        values.put(COLUMN_USER_HORA, usuario.getHora_in());
        try {
            db.insert(TABLE_LOGS_USER, null, values);
            db.close();
            ret = true;
        } catch (SQLException e) {
            e.getCause();
        }
        return ret;
    }

    public boolean eliminarUsuarioDB() {
        Usuario usuario = obtenerUsuarioActual();
        if (usuario != null) {
            db = this.getWritableDatabase();
            db.delete(TABLE_REGISTRO_USER, null, null);
            db.close();
            return actualizarLogUsuario(usuario);
        }
        return false;
    }

    public boolean actualizarLogUsuario(Usuario usuario) {
        boolean ret = false;
        db = this.getWritableDatabase();
        return ret;
    }

    public Usuario obtenerUsuarioActual() {
        Usuario usuario = new Usuario();
        db = this.getWritableDatabase();
        String query = "SELECT * FROM " + TABLE_REGISTRO_USER;
        Cursor cursor = db.rawQuery(query, null);
        if (cursor.moveToFirst()) {
            usuario.setCel(cursor.getString(0));
        }
        db.close();
        return usuario;
    }

    public Comercio getComercioBD () {
        Comercio comercio = new Comercio();
        SQLiteDatabase db = this.getWritableDatabase();

        String query = "SELECT * FROM " + TABLE_COMERCIO + " WHERE "
                + comercio_id + " = " + idComercio;
        Cursor cursor = db.rawQuery(query, null);
        if (cursor.moveToFirst()) {
            comercio.setId(cursor.getInt(cursor.getColumnIndex(comercio_id)));
            comercio.setName(cursor.getString(cursor.getColumnIndex(comercio_name)));
            comercio.setDocumento(cursor.getString(cursor.getColumnIndex(comercio_documento)));
            comercio.setDireccion(cursor.getString(cursor.getColumnIndex(comercio_direccion)));
            comercio.setCiudad(cursor.getString(cursor.getColumnIndex(comercio_ciudad)));
            comercio.setEstado(cursor.getString(cursor.getColumnIndex(comercio_estado)));
            comercio.setPais(cursor.getString(cursor.getColumnIndex(comercio_pais)));
            comercio.setTelefono1(cursor.getString(cursor.getColumnIndex(comercio_telefono1)));
            comercio.setTelefono2(cursor.getString(cursor.getColumnIndex(comercio_telefono2)));
            comercio.setHeader1(cursor.getString(cursor.getColumnIndex(comercio_header1)));
            comercio.setHeader2(cursor.getString(cursor.getColumnIndex(comercio_header2)));
            comercio.setFooting1(cursor.getString(cursor.getColumnIndex(comercio_footing1)));
            comercio.setFooting2(cursor.getString(cursor.getColumnIndex(comercio_footing2)));
            comercio.setMoneda(cursor.getString(cursor.getColumnIndex(comercio_moneda)));
            comercio.setSimboloMoneda(cursor.getString(cursor.getColumnIndex(comercio_simboloMoneda)));
        }

        return comercio;
    }

    public boolean updateColumnStringComercio (String column, String dato) {
        boolean ret = false;
        SQLiteDatabase bd = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(column, dato);

        String where = comercio_id + "=" + idComercio;
        int rta = bd.update(TABLE_COMERCIO, values, where, null);
        if (rta != -1) {
            ret = true;
        }
        bd.close();
        return ret;

    }


}
