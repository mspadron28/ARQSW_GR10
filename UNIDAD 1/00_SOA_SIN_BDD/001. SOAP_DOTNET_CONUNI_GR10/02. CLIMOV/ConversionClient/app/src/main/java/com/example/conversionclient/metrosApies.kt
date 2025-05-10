package com.example.conversionclient

import android.os.AsyncTask
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import org.w3c.dom.Element
import java.io.BufferedInputStream
import java.io.ByteArrayInputStream
import java.io.ByteArrayOutputStream
import java.io.InputStream
import java.net.HttpURLConnection
import java.net.URL
import javax.xml.parsers.DocumentBuilderFactory

class centimetrosApies : AppCompatActivity() {

    private lateinit var editTextCentimeters: EditText
    private lateinit var buttonConvert: Button
    private lateinit var textViewResult: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.centimeterstofeets)

        editTextCentimeters = findViewById(R.id.editTextCentimeters)
        buttonConvert = findViewById(R.id.buttonConvert)
        textViewResult = findViewById(R.id.textViewResult)

        buttonConvert.setOnClickListener {
            val centimeters = editTextCentimeters.text.toString().toDoubleOrNull()
            if (centimeters != null) {
                val methodName = "CentimetersToFeet"
                val soapAction = "http://tempuri.org/IConversionService/$methodName"
                val url = "http://10.40.13.165:8733/Design_Time_Addresses/ConversionUnidades_SOAP/Service1/"

                val xmlInput = """
                    <?xml version="1.0" encoding="utf-8"?>
                    <soap:Envelope xmlns:soap="http://schemas.xmlsoap.org/soap/envelope/" xmlns:tem="http://tempuri.org/">
                        <soap:Body>
                            <tem:$methodName>
                                <tem:centimeters>$centimeters</tem:centimeters>
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

    private inner class AsyncTaskHandleSOAP : AsyncTask<String, Void, Pair<String?, String?>>() {

        override fun doInBackground(vararg params: String?): Pair<String?, String?> {
            val urlString = params[0]
            val soapAction = params[1]
            val xmlInput = params[2]

            if (urlString == null || soapAction == null || xmlInput == null) {
                return Pair(null, "Error: Uno o más parámetros son nulos")
            }

            try {
                val url = URL(urlString)
                val connection = url.openConnection() as HttpURLConnection
                connection.requestMethod = "POST"
                connection.setRequestProperty("Content-Type", "text/xml;charset=UTF-8")
                connection.setRequestProperty("SOAPAction", soapAction)
                connection.doOutput = true
                connection.connectTimeout = 10000 // Timeout de 10 segundos
                connection.readTimeout = 10000

                val outputStream = connection.outputStream
                outputStream.write(xmlInput.toByteArray(Charsets.UTF_8))
                outputStream.flush()

                val inputStream: InputStream = if (connection.responseCode == HttpURLConnection.HTTP_OK) {
                    BufferedInputStream(connection.inputStream)
                } else {
                    BufferedInputStream(connection.errorStream)
                }

                val response = readStream(inputStream)
                Log.d("SOAP_RESPONSE", response) // Imprimir respuesta XML para depuración

                inputStream.close()
                outputStream.close()
                connection.disconnect()

                return Pair(response, null)
            } catch (e: Exception) {
                e.printStackTrace()
                return Pair(null, "Error en la solicitud: ${e.message}")
            }
        }

        override fun onPostExecute(result: Pair<String?, String?>) {
            super.onPostExecute(result)

            val (response, error) = result
            if (error != null) {
                textViewResult.text = error
                Log.e("SOAP_ERROR", error)
                return
            }

            if (response == null) {
                textViewResult.text = "Error: No se recibió respuesta"
                Log.e("SOAP_ERROR", "No se recibió respuesta")
                return
            }

            try {
                val xmlDoc = DocumentBuilderFactory.newInstance().apply {
                    isNamespaceAware = true // Habilitar manejo de namespaces
                }.newDocumentBuilder().parse(ByteArrayInputStream(response.toByteArray(Charsets.UTF_8)))
                xmlDoc.documentElement.normalize()

                // Buscar el nodo con namespace
                val resultNodeList = xmlDoc.getElementsByTagNameNS("http://tempuri.org/", "CentimetersToFeetResult")
                if (resultNodeList.length > 0) {
                    val resultNode = resultNodeList.item(0) as Element
                    val conversionResult = resultNode.textContent.toDoubleOrNull()
                    if (conversionResult != null) {
                        textViewResult.text = "Centímetros a Pies: $conversionResult"
                    } else {
                        textViewResult.text = "Error: El valor recibido no es un número válido"
                        Log.e("XML_PARSE", "Valor no numérico: ${resultNode.textContent}")
                    }
                } else {
                    // Verificar si hay un error SOAP
                    val faultNodeList = xmlDoc.getElementsByTagName("soap:Fault")
                    if (faultNodeList.length > 0) {
                        val faultNode = faultNodeList.item(0) as Element
                        val faultString = faultNode.getElementsByTagName("faultstring").item(0)?.textContent
                        textViewResult.text = "Error del servidor: $faultString"
                        Log.e("SOAP_FAULT", "Error SOAP: $faultString")
                    } else {
                        textViewResult.text = "Error: Nodo CentimetersToFeetResult no encontrado"
                        Log.e("XML_PARSE", "Nodo CentimetersToFeetResult no encontrado en la respuesta")
                    }
                }
            } catch (e: Exception) {
                e.printStackTrace()
                textViewResult.text = "Error al parsear XML: ${e.message}"
                Log.e("XML_PARSE", "Error al parsear XML: ${e.message}")
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