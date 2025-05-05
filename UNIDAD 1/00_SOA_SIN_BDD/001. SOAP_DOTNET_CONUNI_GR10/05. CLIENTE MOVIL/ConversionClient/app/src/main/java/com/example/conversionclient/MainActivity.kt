package com.example.conversionclient

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    private val correctUsername = "MasterMonster"
    private val correctPassword = "Monster9"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val usernameEditText = findViewById<EditText>(R.id.usernameEditText)
        val passwordEditText = findViewById<EditText>(R.id.passwordEditText)
        val loginButton = findViewById<Button>(R.id.loginButton)

        loginButton.setOnClickListener {
            val username = usernameEditText.text.toString()
            val password = passwordEditText.text.toString()

            if (username == correctUsername && password == correctPassword) {
                Toast.makeText(this, "Bienvenido!", Toast.LENGTH_SHORT).show()
                navigateToNextActivity()
            } else {
                Toast.makeText(this, "Usuario o Contraseña Incorrectos", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun navigateToNextActivity() {
        val intent = Intent(this, NextActivity::class.java)
        startActivity(intent)
    }
}
