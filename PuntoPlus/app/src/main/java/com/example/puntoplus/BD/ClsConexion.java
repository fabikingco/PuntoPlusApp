package com.example.puntoplus.BD;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.puntoplus.model.SMS_RECV;
import com.example.puntoplus.model.SMS_SEND;

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

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE_SMS_SEND);
        db.execSQL(CREATE_TABLE_SMS_RECV);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public boolean newSmsSend(SMS_SEND sms_send) {
        boolean ret = false;
        db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_SMS_SEND_DESTINO, sms_send.getSend_destino());
        values.put(COLUMN_SMS_SEND_MSG, sms_send.getSend_msg());
        values.put(COLUMN_SMS_SEND_FECHA, sms_send.getSend_fecha());
        values.put(COLUMN_SMS_SEND_HORA, sms_send.getSend_hora());
        values.put(COLUMN_SMS_RECV_FECHAHORA, sms_send.getSend_fechahora());
        try {
            db.insert(TABLE_SMS_SEND, null, values);
            db.close();
            ret = true;
        } catch (SQLException e){
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
        values.put(COLUMN_SMS_SEND_FECHA, sms_recv.getRecv_fecha());
        values.put(COLUMN_SMS_SEND_HORA, sms_recv.getRecv_hora());
        values.put(COLUMN_SMS_RECV_FECHAHORA, sms_recv.getRecv_fechahora());
        try {
            db.insert(TABLE_SMS_SEND, null, values);
            db.close();
            ret = true;
        } catch (SQLException e){
            e.getCause();
        }
        return ret;
    }

    public ArrayList<SMS_SEND> getAllSMSSend() {
        ArrayList<SMS_SEND> list = new ArrayList<>();
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
}
