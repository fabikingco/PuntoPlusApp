package com.code93.puntoplus.Actividades

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.InputType
import android.widget.Toast
import androidx.appcompat.widget.Toolbar
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.code93.puntoplus.Adaptador.NewAdapterMenus
import com.code93.puntoplus.R
import com.code93.puntoplus.model.Transacciones.Transaccion
import com.code93.puntoplus.model.menuItemsModelo

class SeleccionServiciosActivity : AppCompatActivity(), NewAdapterMenus.OnItemClickListenerMesas {

    var recyclerView: RecyclerView? = null
    val itemMenu: MutableList<menuItemsModelo> = ArrayList()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_seleccion_servicios)

        recyclerView = findViewById(R.id.recyclerTarjetas)
        initToolbar()
        cargarComponentes()
    }

    private fun cargarComponentes() {
        cargarArrayList()
        val adapterMenus = NewAdapterMenus(itemMenu, this)
        adapterMenus.onItemClickListener = this
        recyclerView!!.layoutManager = GridLayoutManager(this, 2)
        recyclerView!!.adapter = adapterMenus
    }

    private fun cargarArrayList() {
        itemMenu.add(menuItemsModelo(resources.getString(R.string.claroFijo),
                R.drawable.claro_hogar))
        itemMenu.add(menuItemsModelo(resources.getString(R.string.claroPlanes),
                R.drawable.claro_planes))
        itemMenu.add(menuItemsModelo(resources.getString(R.string.movistarPlan),
                R.drawable.movistar_logo))
        itemMenu.add(menuItemsModelo(resources.getString(R.string.cnelGuayaquil),
                R.drawable.cnel))
        itemMenu.add(menuItemsModelo(resources.getString(R.string.eeqsa),
                R.drawable.empresa_electrica_quito))
        itemMenu.add(menuItemsModelo(resources.getString(R.string.interagua),
                R.drawable.interagua))
        itemMenu.add(menuItemsModelo(resources.getString(R.string.epmaps),
                R.drawable.epmaps))
        itemMenu.add(menuItemsModelo(resources.getString(R.string.avon),
                R.drawable.avon_logo))
    }

    private fun initToolbar() {
        val toolbar = findViewById<Toolbar>(R.id.toolbar)
        setSupportActionBar(toolbar)
        supportActionBar!!.title = "Pago de servicios"
    }

    override fun onItemClick(item: RecyclerView.ViewHolder?, position: Int, id: Int) {
        val intent = Intent()
        val data = itemMenu!![position].textoItem

        if (data == resources.getString(R.string.claroFijo)) {
            val transaccion = Transaccion()
            transaccion.tipo = "Pago de servicios"
            transaccion.operador = resources.getString(R.string.claroFijo)
            transaccion.name1 = "Número de contrato"
            transaccion.name4 = "Codigo"
            transaccion.contrapartida4  = "CLARO_FIJO"

            intent.setClass(this@SeleccionServiciosActivity, PagoServiciosActivity::class.java)
            intent.putExtra("transaccion", transaccion)
            startActivity(intent)
        }

        if (data == resources.getString(R.string.claroPlanes)) {
            val transaccion = Transaccion()
            transaccion.tipo = "Pago de servicios"
            transaccion.operador = resources.getString(R.string.claroPlanes)
            transaccion.name1 = "Número de contrato"
            transaccion.name4 = "Codigo"
            transaccion.contrapartida4  = "PLANCLARO"

            intent.setClass(this@SeleccionServiciosActivity, PagoServiciosActivity::class.java)
            intent.putExtra("transaccion", transaccion)
            intent.putExtra("tipoText", InputType.TYPE_CLASS_PHONE)
            startActivity(intent)
        }

        if (data == resources.getString(R.string.movistarPlan)) {
            val transaccion = Transaccion()
            transaccion.tipo = "Pago de servicios"
            transaccion.operador = resources.getString(R.string.movistarPlan)
            transaccion.name1 = "Número de teléfono"
            transaccion.name4 = "Codigo"
            transaccion.contrapartida4  = "PLANMOVISTAR"

            intent.setClass(this@SeleccionServiciosActivity, PagoServiciosActivity::class.java)
            intent.putExtra("transaccion", transaccion)
            intent.putExtra("tipoText", InputType.TYPE_CLASS_PHONE)
            startActivity(intent)
        }

        if (data == resources.getString(R.string.cnelGuayaquil)) {
            val transaccion = Transaccion()
            transaccion.tipo = "Pago de servicios"
            transaccion.operador = resources.getString(R.string.cnelGuayaquil)
            transaccion.name1 = "Número de contrato"
            transaccion.name1 = "Número de contrato"
            transaccion.name4 = "Codigo"
            transaccion.contrapartida4  = "CNEL"

            intent.setClass(this@SeleccionServiciosActivity, PagoServiciosActivity::class.java)
            intent.putExtra("transaccion", transaccion)
            startActivity(intent)
        }

        if (data == resources.getString(R.string.eeqsa)) {
            val transaccion = Transaccion()
            transaccion.tipo = "Pago de servicios"
            transaccion.operador = resources.getString(R.string.eeqsa)
            transaccion.name1 = "Número de suministro"
            transaccion.name4 = "Codigo"
            transaccion.contrapartida4  = "Eeq"

            intent.setClass(this@SeleccionServiciosActivity, PagoServiciosActivity::class.java)
            intent.putExtra("transaccion", transaccion)
            startActivity(intent)
        }

        if (data == resources.getString(R.string.interagua)) {
            val transaccion = Transaccion()
            transaccion.tipo = "Pago de servicios"
            transaccion.operador = resources.getString(R.string.interagua)
            transaccion.name1 = "Número de cuenta"
            transaccion.name4 = "Codigo"
            transaccion.contrapartida4  = "Interagua"

            intent.setClass(this@SeleccionServiciosActivity, PagoServiciosActivity::class.java)
            intent.putExtra("transaccion", transaccion)
            startActivity(intent)
        }

        if (data == resources.getString(R.string.epmaps)) {
            val transaccion = Transaccion()
            transaccion.tipo = "Pago de servicios"
            transaccion.operador = resources.getString(R.string.epmaps)
            transaccion.name1 = "Número de contrato"
            transaccion.contrapartida4  = "Epmaps"

            intent.setClass(this@SeleccionServiciosActivity, PagoServiciosActivity::class.java)
            intent.putExtra("transaccion", transaccion)
            startActivity(intent)
        }

        if (data == resources.getString(R.string.avon)) {
            val transaccion = Transaccion()
            transaccion.tipo = "Pago de servicios"
            transaccion.operador = resources.getString(R.string.avon)
            transaccion.name1 = "Número de contrato"
            transaccion.name4 = "Codigo"
            transaccion.contrapartida4  = "Avon"

            intent.setClass(this@SeleccionServiciosActivity, PagoServiciosActivity::class.java)
            intent.putExtra("transaccion", transaccion)
            startActivity(intent)
        }
    }
}
