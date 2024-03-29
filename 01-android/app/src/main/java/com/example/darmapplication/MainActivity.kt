package com.example.darmapplication

import android.app.Activity
import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.ContactsContract
import android.util.Log
import android.widget.Button
import androidx.activity.result.contract.ActivityResultContracts

class MainActivity : AppCompatActivity() {

    val contenidoIntentExplicito =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()){
            result->
            if(result.resultCode == Activity.RESULT_OK){
                if(result.data != null){
                    val data = result.data
                    Log.i("intent-epn", "${data?.getStringExtra("nombreModificado")}")
                }
            }
        }
    val contenidoIntentImplicito = registerForActivityResult(
        ActivityResultContracts.StartActivityForResult()
    ){
        result ->
        if(result.resultCode == RESULT_OK){
            if(result.data != null){
                if (result.data !!.data != null){
                    val uri: Uri = result.data!!.data!!
                    val cursor = contentResolver.query(
                        uri,
                        null,
                        null,
                        null,
                        null,
                        null
                    )
                    cursor?.moveToFirst()
                    val indiceTelefono = cursor?.getColumnIndex(
                        ContactsContract.CommonDataKinds.Phone.NUMBER
                    )
                    val telefono = cursor?.getString(
                        indiceTelefono!!
                    )

                    cursor?.close()
                    Log.i("intent-epn", "Telefono ${telefono}")
                }
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //Base de datos SQLite
        EBaseDeDatos.tablaEntrenador = ESqliteHelperEntrenador(this)

        // Identificador carpeta recursos
        val botonACicloVida = findViewById<Button>(R.id.btn_ciclo_vida)
        // Listener
        botonACicloVida
            .setOnClickListener {
            irActividad(ACicloVida::class.java)
        }

        // Boton List View
        val botonListView = findViewById<Button>(R.id.btn_ir_list_view)
        botonListView
            .setOnClickListener {
                irActividad((BListView::class.java))
            }

        // Intent Implicito
        val botonIntentImplicito = findViewById<Button>(R.id.btn_ir_intent_implicito)
        botonIntentImplicito
            .setOnClickListener {
                val intentConRespuesta = Intent(
                    Intent.ACTION_PICK,
                    ContactsContract.CommonDataKinds.Phone.CONTENT_URI
                )
                contenidoIntentImplicito.launch(intentConRespuesta)
            }

        // SQLite
        val botonSqlite = findViewById<Button>(R.id.btn_sqlite)
        botonSqlite
            .setOnClickListener {
                irActividad(ECrudEntrenador::class.java)
            }

        // Recycler View
        val botonRecyclerView = findViewById<Button>(R.id.btn_recycler_view)
        botonRecyclerView
            .setOnClickListener {
                irActividad(GRecyclerView::class.java)
            }
    }

    fun abrirActividadConParametros(
        clase: Class<*>
    ){
        val intentExplicito = Intent(this, clase)
        // Enviar parametros (solamente varibles primitivas)
        intentExplicito.putExtra("nombre","Adrian")
        intentExplicito.putExtra("apellido", "Eguez")
        intentExplicito.putExtra("edad", "33")
        intentExplicito.putExtra(
            "entrenadorPrincipal",
        BEntrenador(1,"Adrian", "Paleta")
        )
        contenidoIntentExplicito.launch(intentExplicito)
    }

    fun irActividad(
        clase: Class<*>
    ){
        val intent = Intent(this, clase)
        startActivity(intent)
    }
}
