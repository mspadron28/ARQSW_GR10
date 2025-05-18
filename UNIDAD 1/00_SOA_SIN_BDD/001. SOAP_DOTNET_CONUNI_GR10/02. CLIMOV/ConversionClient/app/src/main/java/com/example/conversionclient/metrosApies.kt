package com.example.conversionclient

import android.os.AsyncTask
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import org.w3c.dom.Element
import java.io.BufferedInputStream
import java.io.ByteArrayOutputStream
import java.io.InputStream
import java.net.HttpURLConnection
import java.net.URL
import javax.xml.parsers.DocumentBuilderFactory

class metrosApies : AppCompatActivity() {

    private lateinit var editTextMeters: EditText
    private lateinit var buttonConvert: Button
    private lateinit var textViewResult: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.centimeterstofeets) // Cambiado a layout existente

        editTextMeters = findViewById(R.id.editTextMeters) // Updated ID
        buttonConvert = findViewById(R.id.buttonConvert)
        textViewResult = findViewById(R.id.textViewResult)

        buttonConvert.setOnClickListener {
            val meters = editTextMeters.text.toString().toDoubleOrNull()
            if (meters != null) {
                val methodName = "metrosAPies"
                val soapAction = "http://tempuri.org/IConversionService/$methodName"
                val url = "http://192.168.100.11:8733/Design_Time_Addresses/ConversionUnidades_SOAP/Service1/"

                val xmlInput = """
                    <?xml version="1.0" encoding="utf-8"?>
                    <soap:Envelope xmlns:soap="http://schemas.xmlsoap.org/soap/envelope/" xmlns:tem="http://tempuri.org/">
                        <soap:Body>
                            <tem:$methodName>
                                <tem:metros>$meters</tem:metros>
                            </tem:$methodName>
                        </soap:Body>
                    </soap:Envelope>
                """.trimIndent()

                AsyncTaskHandleSOAP().execute(url, soapAction, xmlInput)
            } else {
                textViewResult.text = "Entrada inválida. Por favor, ingrese un número válido."
            }
        }
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
                connection.connectTimeout = 10000
                connection.readTimeout = 10000

                val outputStream = connection.outputStream
                outputStream.write(xmlInput.toByteArray(Charsets.UTF_8))
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

                    val resultNode = xmlDoc.getElementsByTagName("metrosAPiesResult").item(0) as Element
                    val conversionResult = resultNode.textContent.toDoubleOrNull()

                    if (conversionResult != null) {
                        textViewResult.text = "Metros a Pies: $conversionResult"
                    } else {
                        textViewResult.text = "Error: El valor recibido no es un número válido"
                    }
                } else {
                    textViewResult.text = "Error en la respuesta del servidor: $result"
                }
            } catch (e: Exception) {
                e.printStackTrace()
                textViewResult.text = "Error al procesar la respuesta XML: ${e.message}"
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