package com.example.proyecto1

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class ForgotPasswordActivity : AppCompatActivity() {

    private lateinit var editTextEmail: EditText
    private lateinit var buttonSendEmail: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_forgot_password)

        editTextEmail = findViewById(R.id.editTextEmail)
        buttonSendEmail = findViewById(R.id.buttonSendEmail)

        buttonSendEmail.setOnClickListener {
            val email = editTextEmail.text.toString().trim()

            if (email.isEmpty()) {
                Toast.makeText(this, "Por favor, ingrese su correo electrónico", Toast.LENGTH_SHORT).show()
            } else if (!isValidEmail(email)) {
                Toast.makeText(this, "Ingrese un correo electrónico válido", Toast.LENGTH_SHORT).show()
            } else {
                // Lógica para enviar el enlace de recuperación
                sendRecoveryEmail(email)
            }
        }
    }

    private fun isValidEmail(email: String): Boolean {
        return android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()
    }

    private fun sendRecoveryEmail(email: String) {
        // Aquí agregarías la lógica para enviar un correo electrónico
        // Esto puede incluir un servicio backend que maneje la recuperación de contraseña
        Toast.makeText(this, "Enlace de recuperación enviado a $email", Toast.LENGTH_SHORT).show()
        // Regresar a la actividad anterior
        finish()
    }
}