package com.alvarorichelle.interfazinteractiva

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import android.widget.Button
import android.widget.TextView

class Resultado : AppCompatActivity() {
    // Referenciamos a los componentes del XML
    private lateinit var tvNombre: TextView
    private lateinit var tvEdad: TextView
    private lateinit var tvCursos: TextView
    private lateinit var tvTurno: TextView
    private lateinit var btnVolver: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_resultado)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        initComponents()
        mostrarDatos()
        setupListeners()
    }
    // Conectamos cada variable con su componente XML
    private fun initComponents() {
        tvNombre = findViewById(R.id.tvNombre)
        tvEdad = findViewById(R.id.tvEdad)
        tvCursos = findViewById(R.id.tvCursos)
        tvTurno = findViewById(R.id.tvTurno)
        btnVolver = findViewById(R.id.btnVolver)
    }
    // Leemos los datos del Intent y los muestra
    private fun mostrarDatos() {
        val nombre = intent.getStringExtra(MainActivity.EXTRA_NOMBRE)
        val edad = intent.getStringExtra(MainActivity.EXTRA_EDAD)
        val cursos = intent.getStringExtra(MainActivity.EXTRA_CURSOS)
        val turno = intent.getStringExtra(MainActivity.EXTRA_TURNO)

        tvNombre.text = getString(R.string.res_nombre, nombre)
        tvEdad.text = getString(R.string.res_edad, edad)
        tvCursos.text = getString(R.string.res_cursos, cursos)
        tvTurno.text = getString(R.string.res_turno, turno)
    }
    private fun setupListeners() {
        btnVolver.setOnClickListener {
            finish()
        }
    }
}