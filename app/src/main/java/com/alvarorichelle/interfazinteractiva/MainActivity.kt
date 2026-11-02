package com.alvarorichelle.interfazinteractiva

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import android.content.Intent
import android.widget.Button
import android.widget.CheckBox
import android.widget.RadioGroup
import android.widget.EditText
import android.widget.Toast

class MainActivity : AppCompatActivity() {
    // Claves del Intent para ambas pantallas
    companion object {
        const val EXTRA_NOMBRE = "nombre"
        const val EXTRA_EDAD = "edad"
        const val EXTRA_CURSOS = "cursos"
        const val EXTRA_TURNO = "turno"
    }
    // Referencia a los componentes del XML
    private lateinit var etNombre: EditText
    private lateinit var etEdad: EditText
    private lateinit var cbJava: CheckBox
    private lateinit var cbKotlin: CheckBox
    private lateinit var rgTurno: RadioGroup
    private lateinit var btnEnviar: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        initComponents()
        setupListeners()
    }
    // Conectamos cada variable con su componente XML
    private fun initComponents() {
        etNombre = findViewById(R.id.etNombre)
        etEdad = findViewById(R.id.etEdad)
        cbJava = findViewById(R.id.cbJava)
        cbKotlin = findViewById(R.id.cbKotlin)
        rgTurno = findViewById(R.id.rgTurno)
        btnEnviar = findViewById(R.id.btnEnviar)
    }
    // Definimos lo que sucede al pulsar el botón:
    private fun setupListeners() {
        btnEnviar.setOnClickListener {
            enviarFormulario()
        }
    }
    // Recoge, valida y envía los datos
    private fun enviarFormulario() {
        val nombre = etNombre.text.toString().trim()
        val edad = etEdad.text.toString().trim()

        if (nombre.isEmpty() || edad.isEmpty()) {
            Toast.makeText(this, R.string.error_campos, Toast.LENGTH_SHORT).show()
            return
        }
        val cursos = obtenerCursos()
        val turno = obtenerTurno()
        val intent = Intent(this, Resultado::class.java)
        intent.putExtra(EXTRA_NOMBRE, nombre)
        intent.putExtra(EXTRA_EDAD, edad)
        intent.putExtra(EXTRA_CURSOS, cursos)
        intent.putExtra(EXTRA_TURNO, turno)
        startActivity(intent)
    }
    // Devuelve "Java", "Kotlin", ambos o ninguno.
    private fun obtenerCursos(): String {
        var cursos = ""
        if (cbJava.isChecked) {
            cursos = "Java"
        }
        if (cbKotlin.isChecked) {
            cursos = if (cursos.isEmpty()) "Kotlin" else "$cursos, Kotlin"
        }
        if (cursos.isEmpty()) {
            cursos = "Ninguno"
        }
        return cursos
    }
    // Devuelve "Mañana" o "Tarde"
    private fun obtenerTurno(): String {
        return if (rgTurno.checkedRadioButtonId == R.id.rbManana) {
            "Mañana"
        } else {
            "Tarde"
        }
    }
}