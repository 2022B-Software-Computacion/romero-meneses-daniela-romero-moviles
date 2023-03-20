package com.example.examencafeteria

import android.app.DownloadManager.Query
import android.content.DialogInterface
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.ContextMenu
import android.view.MenuItem
import android.view.View
import android.widget.*
import androidx.appcompat.app.AlertDialog
import com.example.examencafeteria.clases.TiposCafe
import com.google.firebase.firestore.QueryDocumentSnapshot
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class TiposCafeActivity : AppCompatActivity() {
    var arreglo: ArrayList<TiposCafe> = arrayListOf()
    var itemSeleccionado = 0
    var query:Query? = null
    var nombreDocumentoCafeteria: String=""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tipos_cafe)

        nombreDocumentoCafeteria = intent.getStringExtra("name").toString()

        //editar el nombre del super que está en la interfaz supermercado
        val nombreSuper = findViewById<TextView>(R.id.txt_name_super)
        nombreSuper.setText(nombreDocumentoCafeteria)

        //lista de productos
        val listProducto = findViewById<ListView>(R.id.list_productos)
        val adaptador: ArrayAdapter<TiposCafe> = ArrayAdapter(
            this, //contexto
            android.R.layout.simple_list_item_1, //como se va a ver
            arreglo
        )
        listProducto.adapter = adaptador
        adaptador.notifyDataSetChanged()

        val botonCrearProducto = findViewById<Button>(R.id.btn_interfaz_crear_productos)
        botonCrearProducto.setOnClickListener{
            val intent = Intent(this, CrearTiposCafe::class.java)
            intent.putExtra("name", "${nombreDocumentoCafeteria}")
            startActivity(intent)
        }
        val botonVerProductos = findViewById<Button>(R.id.btn_ver_productos)
        botonVerProductos.setOnClickListener{
            consultar(adaptador)
        }
        registerForContextMenu(listProducto)
    }
    override fun onCreateContextMenu(
        menu: ContextMenu?,
        v: View?,
        menuInfo: ContextMenu.ContextMenuInfo?
    ) {
        super.onCreateContextMenu(menu, v, menuInfo)
        //llamamos las opcuines del menú
        val inflater = menuInflater
        inflater.inflate(R.menu.menu_producto, menu)
        //Obtener el id de ArrayListSeleccionado
        val info = menuInfo as AdapterView.AdapterContextMenuInfo
        val id = info.position
        idItemSeleccionado = id
    }

    override fun onContextItemSelected(item: MenuItem): Boolean {
        var documento = arreglo.get(idItemSeleccionado).nameCafe.toString()
        return when (item.itemId){
            R.id.mi_editar  ->{
                val intent = Intent(this, ActualizarTiposCafe::class.java)
                intent.putExtra("nameProducto", "${documento}")
                intent.putExtra("name", "${nombreDocumentoCafeteria}")
                startActivity(intent)

                "${idItemSeleccionado}"
                return true
            }
            R.id.mi_eliminar ->{
                abrirDialogo()
                "${idItemSeleccionado}"
                return true
            }
            else -> super.onContextItemSelected(item)
        }
    }
    fun abrirDialogo(){
        val builder = AlertDialog.Builder(this)
        builder.setTitle("Desea eliminar")
        builder.setPositiveButton(
            "Accept",
            DialogInterface.OnClickListener{ dialog, which ->
                //al aceptar eliminar el registro
                eliminarRegistro(idItemSeleccionado)
            }
        )
        builder.setNegativeButton(
            "Cancelar",
            null
        )
        val dialogo = builder.create()
        dialogo.show()
    }

    fun eliminarRegistro(idItemSelect: Int){
        val db = Firebase.firestore
        var nombreDocumento = arreglo.get(idItemSelect).nameCafe.toString()
        val referencia = db
            .collection("cafeteria")
            .document("${nombreDocumentoCafeteria}")
            .collection("tiposCafe")
        referencia
            .document("${nombreDocumento}")
            .delete()
            .addOnCompleteListener{/*si todo salio bien*/}
            .addOnFailureListener{/*Si algo salio mal*/}
    }

    fun consultar(
        adaptador: ArrayAdapter<TiposCafe>
    ){
        val db = Firebase.firestore
        val producto = db
            .collection("cafeteria")
            .document("${nombreDocumentoCafeteria}")
            .collection("tiposCafe")
        limpiarArreglo()
        adaptador.notifyDataSetChanged()
        producto
            .get()
            .addOnSuccessListener {
                for(cafeteria in it){
                    anadirAArregloProducto(arreglo, cafeteria, adaptador)
                }
            }
    }
    fun anadirAArregloProducto(
        arregloNuevo: ArrayList<TiposCafe>,
        tiposCafe: QueryDocumentSnapshot,
        adaptador: ArrayAdapter<TiposCafe>
    ){
        val nuevoTiposCafe = TiposCafe(
            tiposCafe.data.get("nameCafe") as String?,
            tiposCafe.data.get("numProduct") as Long?,
            tiposCafe.data.get("dateC") as String?,
            tiposCafe.data.get("hasMilk") as String?,
            tiposCafe.data.get("price") as Long?

        )
        arregloNuevo.add(nuevoTiposCafe)
        adaptador.notifyDataSetChanged()
    }

    fun limpiarArreglo(){arreglo.clear()}
}
}