package com.example.conversionclient

import android.content.Intent
import android.os.AsyncTask
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import org.w3c.dom.Element
import java.io.BufferedInputStream
import java.io.ByteArrayOutputStream
import java.io.InputStream
import java.net.HttpURLConnection
import java.net.URL
import javax.xml.parsers.DocumentBuilderFactory

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val usernameEditText = findViewById<EditText>(R.id.usernameEditText)
        val passwordEditText = findViewById<EditText>(R.id.passwordEditText)
        val loginButton = findViewById<Button>(R.id.loginButton)

        loginButton.setOnClickListener {
            val usuario = usernameEditText.text.toString().trim()
            val contraseña = passwordEditText.text.toString().trim()

            if (usuario.isEmpty() || contraseña.isEmpty()) {
                Toast.makeText(this, "Por favor, ingrese usuario y contraseña", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            val methodName = "Login"
            val soapAction = "http://tempuri.org/IConversionService/$methodName"
            val url = "http://192.168.100.11:8733/Design_Time_Addresses/ConversionUnidades_SOAP/Service1/"
            val xmlInput = """
                <?xml version="1.0" encoding="utf-8"?>
                <soap:Envelope xmlns:soap="http://schemas.xmlsoap.org/soap/envelope/" xmlns:tem="http://tempuri.org/">
                    <soap:Body>
                        <tem:$methodName>
                            <tem:usuario>$usuario</tem:usuario>
                            <tem:contraseña>$contraseña</tem:contraseña>
                        </tem:$methodName>
                    </soap:Body>
                </soap:Envelope>
            """.trimIndent()

            AsyncTaskHandleSOAP().execute(url, soapAction, xmlInput)
        }
    }

    private fun navigateToNextActivity() {
        val intent = Intent(this, NextActivity::class.java)
        startActivity(intent)
    }

    private inner class AsyncTaskHandleSOAP : AsyncTask<String, Void, String>() {

        override fun doInBackground(vararg params: String?): String {
            val urlString = params[0]
            val soapAction = params[1]
            val xmlInput = params[2]

            if (urlString == null || soapAction == null || xmlInput == null) {
                return "Error: Uno o más parámetros son nulos"
            }

            try {
                val url = URL(urlString)
                val connection = url.openConnection() as HttpURLConnection
                connection.requestMethod = "POST"
                connection.setRequestProperty("Content-Type", "text/xml;charset=UTF-8")
                connection.setRequestProperty("SOAPAction", soapAction)
                connection.doOutput = true

                val outputStream = connection.outputStream
                outputStream.write(xmlInput.toByteArray())
                outputStream.flush()

                val inputStream = BufferedInputStream(connection.inputStream)
                val response = readStream(inputStream)

                inputStream.close()
                outputStream.close()
                connection.disconnect()

                return response
            } catch (e: Exception) {
                e.printStackTrace()
                return "Error: ${e.message}"
            }
        }

        override fun onPostExecute(result: String?) {
            super.onPostExecute(result)

            try {
                if (result != null && !result.startsWith("Error")) {
                    val xmlDoc = DocumentBuilderFactory.newInstance().newDocumentBuilder()
                        .parse(result.byteInputStream())
                    xmlDoc.documentElement.normalize()

                    val resultNode = xmlDoc.getElementsByTagName("LoginResult").item(0) as Element
                    val loginSuccess = resultNode.textContent.toBoolean()

                    if (loginSuccess) {
                        Toast.makeText(this@MainActivity, "¡Bienvenido!", Toast.LENGTH_SHORT).show()
                        navigateToNextActivity()
                    } else {
                        Toast.makeText(this@MainActivity, "Usuario o contraseña incorrectos", Toast.LENGTH_SHORT).show()
                    }
                } else {
                    Toast.makeText(this@MainActivity, "Error en la respuesta del servidor: $result", Toast.LENGTH_SHORT).show()
                }
            } catch (e: Exception) {
                e.printStackTrace()
                Toast.makeText(this@MainActivity, "Error al procesar la respuesta: ${e.message}", Toast.LENGTH_SHORT).show()
            }
        }

        private fun readStream(inputStream: InputStream): String {
            val outputStream = ByteArrayOutputStream()
            val buffer = ByteArray(1024)
            var bytesRead: Int
            while (inputStream.read(buffer).also { bytesRead = it } != -1) {
                outputStream.write(buffer, 0, bytesRead)
            }
            return outputStream.toString("UTF-8")
        }
    }
}