package com.code93.puntoplus.Actividades

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.RelativeLayout
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.code93.puntoplus.Adaptador.NewAdapterMenus
import com.code93.puntoplus.Adaptador.NewAdapterMenus.OnItemClickListenerMesas
import com.code93.puntoplus.MainActivity
import com.code93.puntoplus.R
import com.code93.puntoplus.model.Transacciones.Transaccion
import com.code93.puntoplus.model.menuItemsModelo
import java.util.*
import kotlin.collections.ArrayList

class SeleccionOperadorActivity : AppCompatActivity(), OnItemClickListenerMesas {

    var recyclerView: RecyclerView? = null
    val itemMenu: MutableList<menuItemsModelo> = ArrayList()

    var tipoMenu: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_transaccion)
        val info = findViewById<RelativeLayout>(R.id.info)
        info.visibility = View.GONE
        val bundle = intent.extras
        tipoMenu = if (bundle != null) {
            bundle.getString("tipoMenu", "default")
        } else {
            "default"
        }
        recyclerView = findViewById(R.id.recyclerTarjetas)
        initToolbar()
        cargarComponentes()
    }

    private fun initToolbar() {
        val toolbar = findViewById<Toolbar>(R.id.toolbar)
        setSupportActionBar(toolbar)
        supportActionBar!!.title = "Ecuamovil"
    }

    private fun cargarComponentes() {
        cargarArrayList()
        val adapterMenus = NewAdapterMenus(itemMenu, this)
        adapterMenus.onItemClickListener = this@SeleccionOperadorActivity
        recyclerView!!.layoutManager = GridLayoutManager(this, 2)
        recyclerView!!.adapter = adapterMenus
    }

    private fun cargarArrayList() {
        if (tipoMenu == resources.getString(R.string.recargas_simert)) {
            itemMenu.add(menuItemsModelo(resources.getString(R.string.parqueo_directo), R.drawable.simmert))
            itemMenu.add(menuItemsModelo(resources.getString(R.string.recarga_parqueo), R.drawable.simmert))
        }
        if (tipoMenu == resources.getString(R.string.recargas)) {
            itemMenu.add(menuItemsModelo(getString(R.string.claro), R.drawable.claro_logo))
            itemMenu.add(menuItemsModelo(resources.getString(R.string.movistar), R.drawable.movistar_logo))
            itemMenu.add(menuItemsModelo(resources.getString(R.string.cnt), R.drawable.cnt_logo))
            itemMenu.add(menuItemsModelo(resources.getString(R.string.tuenti), R.drawable.tuenti))
            itemMenu.add(menuItemsModelo(resources.getString(R.string.directv), R.drawable.directv))
            itemMenu.add(menuItemsModelo(resources.getString(R.string.tvcable), R.drawable.tvcable))
        }
        if (tipoMenu == resources.getString(R.string.paquetes_celular)) {
            itemMenu.add(menuItemsModelo(getString(R.string.claro), R.drawable.claro_logo))
            itemMenu.add(menuItemsModelo(resources.getString(R.string.movistar), R.drawable.movistar_logo))
            itemMenu.add(menuItemsModelo(resources.getString(R.string.cnt), R.drawable.cnt_logo))
            itemMenu.add(menuItemsModelo(resources.getString(R.string.tuenti), R.drawable.tuenti))
        }
    }

    override fun onItemClick(item: RecyclerView.ViewHolder, position: Int, id: Int) {
        val intent = Intent()
        val data = itemMenu!![position].textoItem
        if (data == resources.getString(R.string.claro)) {
            if (tipoMenu == resources.getString(R.string.paquetes_celular)) {
                val transaccion = Transaccion()
                //transaccion.setId(Tools.getLocalDateTime());
                transaccion.tipo = tipoMenu
                transaccion.operador = resources.getString(R.string.claro)
                intent.setClass(this@SeleccionOperadorActivity, SeleccionPaqueteActivity::class.java)
                intent.putExtra("transaccion", transaccion)
                startActivity(intent)
            } else if (tipoMenu == resources.getString(R.string.recargas)) {
                MainActivity.recargasCelular.setOperador(resources.getString(R.string.claro))
                intent.setClass(this@SeleccionOperadorActivity, RecargasCelularActivity::class.java)
                intent.putExtra("tipoIngreso", tipoMenu + "@" + resources.getString(R.string.claro))
                startActivity(intent)
            }
        }
        if (data == resources.getString(R.string.movistar)) {
            if (tipoMenu == resources.getString(R.string.paquetes_celular)) {
                val transaccion = Transaccion()
                //transaccion.setId(Tools.getLocalDateTime());
                transaccion.tipo = tipoMenu
                transaccion.operador = resources.getString(R.string.movistar)
                intent.setClass(this@SeleccionOperadorActivity, SeleccionPaqueteActivity::class.java)
                intent.putExtra("transaccion", transaccion)
                startActivity(intent)
            } else {
                MainActivity.recargasCelular.setOperador(resources.getString(R.string.movistar))
                intent.setClass(this@SeleccionOperadorActivity, RecargasCelularActivity::class.java)
                intent.putExtra("tipoIngreso", tipoMenu + "@" + resources.getString(R.string.movistar))
                startActivity(intent)
            }
        }
        if (data == resources.getString(R.string.cnt)) {
            if (tipoMenu == resources.getString(R.string.paquetes_celular)) {
                Toast.makeText(this, "Paquetes no disponibles", Toast.LENGTH_SHORT).show()
            } else {
                MainActivity.recargasCelular.setOperador(resources.getString(R.string.cnt))
                intent.setClass(this@SeleccionOperadorActivity, RecargasCelularActivity::class.java)
                intent.putExtra("tipoIngreso", tipoMenu + "@" + resources.getString(R.string.cnt))
                startActivity(intent)
            }
        }
        if (data == resources.getString(R.string.tuenti)) {
            if (tipoMenu == resources.getString(R.string.paquetes_celular)) {
                val transaccion = Transaccion()
                //transaccion.setId(Tools.getLocalDateTime());
                transaccion.tipo = tipoMenu
                transaccion.operador = resources.getString(R.string.tuenti)
                intent.setClass(this@SeleccionOperadorActivity, SeleccionPaqueteActivity::class.java)
                intent.putExtra("transaccion", transaccion)
                startActivity(intent)
            } else {
                MainActivity.recargasCelular.setOperador(resources.getString(R.string.tuenti))
                intent.setClass(this@SeleccionOperadorActivity, RecargasCelularActivity::class.java)
                intent.putExtra("tipoIngreso", tipoMenu + "@" + resources.getString(R.string.tuenti))
                startActivity(intent)
            }
        }
        if (data == resources.getString(R.string.directv)) {
            intent.setClass(this@SeleccionOperadorActivity, RecargasCelularActivity::class.java)
            intent.putExtra("tipoIngreso", tipoMenu + "@" + resources.getString(R.string.directv))
            startActivity(intent)
        }
        if (data == resources.getString(R.string.tvcable)) {
            intent.setClass(this@SeleccionOperadorActivity, RecargasCelularActivity::class.java)
            intent.putExtra("tipoIngreso", tipoMenu + "@" + resources.getString(R.string.tvcable))
            startActivity(intent)
        }
        if (data == resources.getString(R.string.parqueo_directo)) {
            intent.setClass(this@SeleccionOperadorActivity, RecargasSimertActivity::class.java)
            intent.putExtra("tipoIngreso", tipoMenu + "@" + resources.getString(R.string.parqueo_directo))
            startActivity(intent)
        }
        if (data == resources.getString(R.string.recarga_parqueo)) {
            intent.setClass(this@SeleccionOperadorActivity, RecargasSimertActivity::class.java)
            intent.putExtra("tipoIngreso", tipoMenu + "@" + resources.getString(R.string.recarga_parqueo))
            startActivity(intent)
        }
        finish()
    }
}