package com.code93.puntoplus.Actividades

import android.content.Intent
import android.os.Bundle
import android.text.InputType
import android.view.View
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.FragmentTransaction
import com.code93.puntoplus.Fragments.DialogDataFragment
import com.code93.puntoplus.MainActivity
import com.code93.puntoplus.R
import com.code93.puntoplus.Tools
import com.code93.puntoplus.model.Transacciones.Transaccion
import kotlinx.android.synthetic.main.activity_pago_servicios.*

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


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pago_servicios)

        findView()

        val bundle = intent.extras
        if (bundle != null) {
            transaccion = bundle.getSerializable("transaccion") as Transaccion?
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

        if (transaccion?.monto != null) {
            tvDataMonto?.text = transaccion?.monto
        } else {
            lyMonto?.visibility = View.GONE
        }

        onClickLinears()
    }

    private fun onClickLinears() {
        lyContrapartida1!!.setOnClickListener {
            val fragmentManager = supportFragmentManager
            val dialog = DialogDataFragment.newInstance(tvTituloContrapartida1!!.text.toString(),
                    Tools.checkNull(tvDataContrapartida1!!.text.toString()))
            dialog.setRequestCode(RecargasCelularActivity.DIALOG_QUEST_CODE)
            dialog.setInputType(InputType.TYPE_CLASS_TEXT)
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

        btnGenerarSms.setOnClickListener {
            generarSMS()
        }
    }

    private fun generarSMS() {
        if (!validacionDeCamposLlenos()) {
            Toast.makeText(this, "Valide que todos los campos esten completos", Toast.LENGTH_SHORT).show()
            return
        }


    }

    private fun validacionDeCamposLlenos(): Boolean {
        if (tvDataContrapartida1!!.text == null || tvDataContrapartida1!!.text.toString().trim { it <= ' ' } == "") {
            tvTituloContrapartida1!!.setTextColor(resources.getColor(R.color.red_900))
            return false
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
