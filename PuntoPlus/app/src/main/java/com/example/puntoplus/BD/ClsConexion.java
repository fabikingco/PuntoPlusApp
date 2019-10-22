package com.example.puntoplus.BD;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class ClsConexion extends SQLiteOpenHelper {

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
}
