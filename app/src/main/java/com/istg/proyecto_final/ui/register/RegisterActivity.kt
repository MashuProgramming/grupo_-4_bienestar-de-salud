package com.istg.proyecto_final.ui.register

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.istg.proyecto_final.DBHelper   // <- Import corregido
import com.istg.proyecto_final.R
import com.istg.proyecto_final.ui.login.LoginActivity // <- Asegúrate de que LoginActivity es tu pantalla de login

class RegisterActivity : AppCompatActivity() {

    private lateinit var dbHelper: DBHelper
    private lateinit var inputEmail: EditText
    private lateinit var inputNombre: EditText
    private lateinit var inputFechaNacimiento: EditText
    private lateinit var inputPassword: EditText
    private lateinit var btnRegister: Button
    private lateinit var tvLoginLink: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        // Enlazar vistas
        inputEmail = findViewById(R.id.inputEmail)
        inputNombre = findViewById(R.id.inputNombre)
        inputFechaNacimiento = findViewById(R.id.inputFechaNacimiento)
        inputPassword = findViewById(R.id.inputPassword)
        btnRegister = findViewById(R.id.btnRegister)
        tvLoginLink = findViewById(R.id.tvLoginLink)

        // Inicializar DBHelper
        dbHelper = DBHelper(this)

        // Botón de registrar
        btnRegister.setOnClickListener {
            val email = inputEmail.text.toString().trim()
            val nombre = inputNombre.text.toString().trim()
            val fecha = inputFechaNacimiento.text.toString().trim()
            val pass = inputPassword.text.toString().trim()

            if (email.isEmpty() || nombre.isEmpty() || fecha.isEmpty() || pass.isEmpty()) {
                Toast.makeText(this, "Completa todos los campos", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            val registrado = dbHelper.insertUser(email, pass)
            if (registrado) {
                Toast.makeText(this, "Registro exitoso", Toast.LENGTH_SHORT).show()
                startActivity(Intent(this, LoginActivity::class.java))
                finish()
            } else {
                Toast.makeText(this, "Error al registrar", Toast.LENGTH_SHORT).show()
            }
        }

        // Ir al login
        tvLoginLink.setOnClickListener {
            startActivity(Intent(this, LoginActivity::class.java))
            finish()
        }
    }
}
