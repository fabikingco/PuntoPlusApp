package com.code93.puntoplus.Actividades

import android.app.AlertDialog
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.text.InputType
import android.view.View
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.FragmentTransaction
import com.code93.puntoplus.BD.ClsConexion
import com.code93.puntoplus.Fragments.DialogDataFragment
import com.code93.puntoplus.MainActivity
import com.code93.puntoplus.R
import com.code93.puntoplus.Tools
import com.code93.puntoplus.VentanaConfirmacionActivity
import com.code93.puntoplus.model.Transacciones.Transaccion
import dmax.dialog.SpotsDialog
import kotlinx.android.synthetic.main.activity_pago_servicios.*
import java.util.*

class PagoServiciosActivity : AppCompatActivity() {

    var tvTitulo: TextView? = null
    var tvSubtitulo : TextView? = null
    var tvTituloContrapartida1: TextView? = null
    var tvDataContrapartida1 : TextView? = null
    var tvTituloContrapartida2: TextView? = null
    var tvDataContrapartida2 : TextView? = null
    var tvTituloContrapartida3: TextView? = null
    var tvDataContrapartida3 : TextView? = null
    var tvTituloMonto : TextView? = null
    var tvDataMonto : TextView? = null
    var imageViewTrans : ImageView? = null
    var transaccion : Transaccion? = null

    var lyContrapartida1 : LinearLayout? = null
    var lyContrapartida2 : LinearLayout? = null
    var lyContrapartida3 : LinearLayout? = null
    var lyMonto : LinearLayout? = null

    var consulta : Boolean = true
    var tipoTexto = 1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pago_servicios)

        findView()

        val bundle = intent.extras
        if (bundle != null) {
            transaccion = bundle.getSerializable("transaccion") as Transaccion?
            tipoTexto = bundle.getInt("tipoText", InputType.TYPE_CLASS_TEXT)
        } else {
            Toast.makeText(this, "El tipo de ingreso no llego o fallo ", Toast.LENGTH_SHORT).show()
            startActivity(Intent(this@PagoServiciosActivity, MainActivity::class.java))
            finish()
        }

        setImageView()

        tvTitulo?.text = transaccion?.tipo
        tvSubtitulo?.text = transaccion?.operador

        if (transaccion?.name1 != null) {
            tvTituloContrapartida1?.text = transaccion?.name1
        } else {
            lyContrapartida1?.visibility = View.GONE
        }

        if (transaccion?.name2 != null) {
            tvTituloContrapartida2?.text = transaccion?.name2
        } else {
            lyContrapartida2?.visibility = View.GONE
        }

        if (transaccion?.name3 != null) {
            tvTituloContrapartida3?.text = transaccion?.name3
        } else {
            lyContrapartida3?.visibility = View.GONE
        }

        onClickLinears()
    }

    private fun onClickLinears() {
        lyContrapartida1!!.setOnClickListener {
            val fragmentManager = supportFragmentManager
            val dialog = DialogDataFragment.newInstance(tvTituloContrapartida1!!.text.toString(),
                    Tools.checkNull(tvDataContrapartida1!!.text.toString()))
            dialog.setRequestCode(RecargasCelularActivity.DIALOG_QUEST_CODE)
            dialog.setInputType(tipoTexto)
            dialog.setMaxLeng(25)
            val transaction = fragmentManager.beginTransaction()
            transaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
            transaction.add(android.R.id.content, dialog).addToBackStack(null).commit()
            dialog.setmListener { requestCode, obj ->
                tvDataContrapartida1!!.text = obj.toString()
                tvTituloContrapartida1!!.setTextColor(resources.getColor(R.color.grey_80))
            }
        }

        lyContrapartida2!!.setOnClickListener {

        }

        lyContrapartida3!!.setOnClickListener {

        }

        lyMonto!!.setOnClickListener {
            if (consulta) {
                val alertDialog = AlertDialog.Builder(this@PagoServiciosActivity).create()
                alertDialog.setTitle("ALERTA")
                alertDialog.setMessage("Es probable que requiera enviar primero un mensaje de consulta para consultar el valor a pagar. " +
                        "\n¿Desea digitar el monto a pagar?")
                alertDialog.setButton(AlertDialog.BUTTON_POSITIVE, "Digitar monto") { dialog, which ->
                    val fragmentManager = supportFragmentManager
                    val dialog = DialogDataFragment.newInstance(tvTituloMonto!!.text.toString(),
                            Tools.checkNull(tvDataMonto!!.text.toString()))
                    dialog.setRequestCode(RecargasCelularActivity.DIALOG_QUEST_CODE)
                    dialog.setInputType(InputType.TYPE_CLASS_NUMBER)
                    dialog.setMonto(true)
                    val transaction = fragmentManager.beginTransaction()
                    transaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
                    transaction.add(android.R.id.content, dialog).addToBackStack(null).commit()
                    dialog.setmListener { requestCode, obj ->
                        tvDataMonto!!.text = obj.toString()
                        tvTituloMonto!!.setTextColor(resources.getColor(R.color.grey_80))
                    }
                }
                alertDialog.setButton(AlertDialog.BUTTON_NEGATIVE, "Cancelar") { dialog, which ->
                    alertDialog.dismiss()
                }

                alertDialog.show()
            } else {
                val fragmentManager = supportFragmentManager
                val dialog = DialogDataFragment.newInstance(tvTituloMonto!!.text.toString(),
                        Tools.checkNull(tvDataMonto!!.text.toString()))
                dialog.setRequestCode(RecargasCelularActivity.DIALOG_QUEST_CODE)
                dialog.setInputType(InputType.TYPE_CLASS_NUMBER)
                dialog.setMonto(true)
                val transaction = fragmentManager.beginTransaction()
                transaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
                transaction.add(android.R.id.content, dialog).addToBackStack(null).commit()
                dialog.setmListener { requestCode, obj ->
                    tvDataMonto!!.text = obj.toString()
                    tvTituloMonto!!.setTextColor(resources.getColor(R.color.grey_80))
                }
            }
        }

        btnGenerarSms.setOnClickListener {
            generarSMS()
        }
    }

    private fun generarSMS() {
        if (!validacionDeCamposLlenos()) {
            Toast.makeText(this, "Valide que todos los campos esten completos", Toast.LENGTH_SHORT).show()
            return
        }

        val alertDialog = AlertDialog.Builder(this@PagoServiciosActivity).create()
        alertDialog.setTitle("Ecuamovil")
        if (tvDataMonto!!.text == null || tvDataMonto!!.text.toString().trim { it <= ' ' } == "") {
            alertDialog.setMessage("Enviara mensaje para Consulta o Pago del servicio " + transaccion?.operador)
        } else {
            alertDialog.setMessage("Enviara mensaje para el pago del servicio " + transaccion?.operador
                    + " por un valor de $" + tvDataMonto?.text.toString())
        }

        alertDialog.setButton(AlertDialog.BUTTON_POSITIVE, "Aceptar") { dialog, which ->
            var builder = StringBuilder()
            if (consulta) {
                builder = armarMensajeConsulta()
            } else {
                builder = armarMensajePago()
            }

            val smsIntent = Intent(Intent.ACTION_VIEW, Uri.fromParts("sms", "9305", null))
            smsIntent.putExtra("sms_body", builder.toString())
            startActivity(smsIntent)

            val spotsDialog = SpotsDialog(this@PagoServiciosActivity, "Esperando mensaje de respuesta")
            spotsDialog.show()
            val timer = Timer()
            timer.schedule(object : TimerTask() {
                override fun run() {
                    spotsDialog.dismiss()
                    if (tvDataMonto!!.text == null || tvDataMonto!!.text.toString().trim { it <= ' ' } == "") {
                        ajustarVistaParaPago()
                    } else{
                        guardarTransaccion()
                    }
                }
            }, 10000)

        }
        alertDialog.setButton(AlertDialog.BUTTON_NEGATIVE, "Cancelar") { dialog, which ->
            alertDialog.dismiss()
        }

        alertDialog.show()


    }

    private fun guardarTransaccion() {

        transaccion?.id = Tools.getLocalDateTime()
        transaccion?.monto = tvDataMonto!!.text.toString()
        transaccion?.fecha = Tools.getLocalFormatDate()
        transaccion?.hora = Tools.getLocalFormatTime()
        val clsConexion = ClsConexion(applicationContext)
        if (clsConexion.ingresarRegistroTransaccion(transaccion)) {
            val intent = Intent(this@PagoServiciosActivity, VentanaConfirmacionActivity::class.java)
            intent.putExtra("transaccion", transaccion)
            startActivity(intent)
        } else {
            Toast.makeText(this, "No fue posible guardar transaccion en bd", Toast.LENGTH_SHORT).show()
        }
    }

    private fun ajustarVistaParaPago() {

        consulta = false

    }

    private fun armarMensajePago(): StringBuilder {
        val mensajePago = StringBuilder()
        mensajePago.append(transaccion?.contrapartida4)
        mensajePago.append(" ")
        mensajePago.append(transaccion?.contrapartida1)

        return mensajePago
    }

    private fun armarMensajeConsulta(): StringBuilder {
        val mensajeConsulta = StringBuilder()
        mensajeConsulta.append(transaccion?.contrapartida4)
        mensajeConsulta.append(" ")
        mensajeConsulta.append(tvDataContrapartida1?.text.toString())

        return mensajeConsulta
    }

    private fun validacionDeCamposLlenos(): Boolean {
        if (transaccion?.name1 != null) {
            if (tvDataContrapartida1!!.text == null || tvDataContrapartida1!!.text.toString().trim { it <= ' ' } == "") {
                tvTituloContrapartida1!!.setTextColor(resources.getColor(R.color.red_900))
                return false
            }
        }

        if (!consulta) {
            if (tvDataMonto!!.text == null || tvDataMonto!!.text.toString().trim { it <= ' ' } == "") {
                tvTituloMonto!!.setTextColor(resources.getColor(R.color.red_900))
                return false
            }
        }

        return true
    }

    private fun setImageView() {
        if (transaccion!!.operador == resources.getString(R.string.claroFijo)) {
            imageViewTrans!!.setImageDrawable(getDrawable(R.drawable.claro_hogar))
        }
        if (transaccion!!.operador == resources.getString(R.string.claroPlanes)) {
            imageViewTrans!!.setImageDrawable(getDrawable(R.drawable.claro_planes))
        }
        if (transaccion!!.operador == resources.getString(R.string.cntFijo)) {
            imageViewTrans!!.setImageDrawable(getDrawable(R.drawable.cnt_logo))
        }
        if (transaccion!!.operador == resources.getString(R.string.cnelGuayaquil)) {
            imageViewTrans!!.setImageDrawable(getDrawable(R.drawable.cnel))
        }
        if (transaccion!!.operador == resources.getString(R.string.eeqsa)) {
            imageViewTrans!!.setImageDrawable(getDrawable(R.drawable.empresa_electrica_quito))
        }
        if (transaccion!!.operador == resources.getString(R.string.interagua)) {
            imageViewTrans!!.setImageDrawable(getDrawable(R.drawable.interagua))
        }
        if (transaccion!!.operador == resources.getString(R.string.epmaps)) {
            imageViewTrans!!.setImageDrawable(getDrawable(R.drawable.epmaps))
        }
        if (transaccion!!.operador == resources.getString(R.string.avon)) {
            imageViewTrans!!.setImageDrawable(getDrawable(R.drawable.avon_logo))
        }
    }

    private fun findView() {
        tvTitulo = findViewById(R.id.tvTitulo)
        tvSubtitulo = findViewById(R.id.tvSubtitulo)
        tvTituloContrapartida1 = findViewById(R.id.tvTituloContrapartida1)
        tvDataContrapartida1 = findViewById(R.id.tvDataContrapartida1)
        tvTituloContrapartida2 = findViewById(R.id.tvTituloContrapartida2)
        tvDataContrapartida2 = findViewById(R.id.tvDataContrapartida2)
        tvTituloContrapartida3 = findViewById(R.id.tvTituloContrapartida3)
        tvDataContrapartida3 = findViewById(R.id.tvDataContrapartida3)
        tvTituloMonto = findViewById(R.id.tvTituloMonto)
        tvDataMonto = findViewById(R.id.tvDataMonto)
        imageViewTrans = findViewById(R.id.imageViewTrans)

        lyContrapartida1 = findViewById(R.id.lyContrapartida1)
        lyContrapartida2 = findViewById(R.id.lyContrapartida2)
        lyContrapartida3 = findViewById(R.id.lyContrapartida3)
        lyMonto = findViewById(R.id.lyMonto)
    }
}
