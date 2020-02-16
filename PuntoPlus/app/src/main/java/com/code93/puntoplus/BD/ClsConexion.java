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

    private final String TABLE_TRANSACCIONES = "transacciones";
    private final String COLUMN_ID = "trans_id";
    private final String COLUMN_TIPO = "trans_tipo";
    private final String COLUMN_OPERADOR = "trans_operador";
    private final String COLUMN_MONTO = "trans_monto";
    private final String COLUMN_NAME1 = "trans_name1";
    private final String COLUMN_CONTRAPARTIDA1 = "trans_contrapartida1";
    private final String COLUMN_NAME2 = "trans_name2";
    private final String COLUMN_CONTRAPARTIDA2 = "trans_contrapartida2";
    private final String COLUMN_NAME3 = "trans_name3";
    private final String COLUMN_CONTRAPARTIDA3 = "trans_contrapartida3";
    private final String COLUMN_NAME4 = "trans_name4";
    private final String COLUMN_CONTRAPARTIDA4 = "trans_contrapartida4";

    private final String CREATE_TABLE_TRANSACCIONES =
            "CREATE TABLE " + TABLE_TRANSACCIONES +
                    " (" + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    COLUMN_TIPO + " TEXT NOT NULL, " +
                    COLUMN_OPERADOR + " TEXT NOT NULL, " +
                    COLUMN_MONTO + " TEXT NOT NULL, " +
                    COLUMN_NAME1 + " TEXT, " +
                    COLUMN_CONTRAPARTIDA1 + " TEXT, " +
                    COLUMN_NAME2 + " TEXT, " +
                    COLUMN_CONTRAPARTIDA2 + " TEXT, " +
                    COLUMN_NAME3 + " TEXT, " +
                    COLUMN_CONTRAPARTIDA3 + " TEXT, " +
                    COLUMN_NAME4 + " TEXT, " +
                    COLUMN_CONTRAPARTIDA4 + " TEXT)";

    private final String TABLE_USER = "user";
    private final String COLUMN_USER_CEL = "user_cel";
    private final String COLUMN_USER_FECHA = "user_fecha";
    private final String COLUMN_USER_HORA = "user_hora";

    private final String CREATE_TABLE_USER = "create table " + TABLE_USER + " (" +
            COLUMN_USER_CEL + " text primary key, " +
            COLUMN_USER_FECHA + " text not null, " +
            COLUMN_USER_HORA + " text not null);";

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

    private static final int idComercio = 1;

    private String TABLE_MENUS = "menus";
    private String id_menus = "id_menus";
    private String version_menus = "version_menus";
    private String activo_menus = "activo_menus";
    private String name_menus = "name_menus";

    private String CREATE_TABLE_MENUS =
            "CREATE TABLE " + TABLE_MENUS + " (" +
                    id_menus + " TEXT NOT NULL, " +
                    version_menus + " INTEGER NOT NULL, " +
                    activo_menus + " INTEGER NOT NULL, " +
                    name_menus + " TEXT NOT NULL, " +
                    "PRIMARY KEY(" + id_menus + ")" +
                    ")";

    private String TABLE_ITEMS = "items";
    private String id_items = "id_items";
    private String name_items = "name_items";

    private String CREATE_TABLE_ITEMS =
            "CREATE TABLE " + TABLE_ITEMS + " (" +
                    id_items + " TEXT NOT NULL, " +
                    name_items + " TEXT NOT NULL, " +
                    id_menus + " TEXT NOT NULL, " +
                    "PRIMARY KEY(" + id_items + ")" +
                    ")";

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE_USER);
        db.execSQL(CREATE_TABLE_COMERCIOS);
        db.execSQL(CREATE_TABLE_MENUS);
        db.execSQL(CREATE_TABLE_ITEMS);
        db.execSQL(CREATE_TABLE_TRANSACCIONES);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        System.out.println("oldVersion SQL " + oldVersion + "newVersion SQL " + newVersion);
    }

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
            db.insert(TABLE_USER, null, values);
            db.close();
        } catch (SQLException e) {
            e.getCause();
        }
        return ret;
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
