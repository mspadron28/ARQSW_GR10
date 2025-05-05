import java.io.BufferedReader
import java.io.InputStreamReader
import java.io.OutputStream
import java.net.HttpURLConnection
import java.net.URL

class SOAPServiceHandler {
    private val SOAP_URL = "http://10.40.20.154:8733/Design_Time_Addresses/ConversionUnidades_SOAP/Service1/?wsdl"

    fun centimetersToFeet(centimeters: Double): String? {
        var connection: HttpURLConnection? = null
        var reader: BufferedReader? = null
        try {
            val url = URL(SOAP_URL)
            connection = url.openConnection() as HttpURLConnection
            connection.requestMethod = "POST"
            connection.setRequestProperty("Content-Type", "text/xml;charset=UTF-8")
            connection.setRequestProperty("SOAPAction", "\"http://tempuri.org/IConversionService/CentimetersToFeet\"")
            connection.doOutput = true

            val xmlInput = """
                <soapenv:Envelope xmlns:soapenv="http://schemas.xmlsoap.org/soap/envelope/" xmlns:tem="http://tempuri.org/">
                   <soapenv:Header/>
                   <soapenv:Body>
                      <tem:CentimetersToFeet>
                         <tem:centimeters>$centimeters</tem:centimeters>
                      </tem:CentimetersToFeet>
                   </soapenv:Body>
                </soapenv:Envelope>
            """.trimIndent()

            val outputStream: OutputStream = connection.outputStream
            outputStream.write(xmlInput.toByteArray())
            outputStream.flush()

            if (connection.responseCode == HttpURLConnection.HTTP_OK) {
                reader = BufferedReader(InputStreamReader(connection.inputStream))
                val response = StringBuilder()
                var line: String?
                while (reader.readLine().also { line = it } != null) {
                    response.append(line)
                }
                return response.toString()
            } else {
                // Handle error response
                return null
            }
        } catch (e: Exception) {
            e.printStackTrace()
            return null
        } finally {
            connection?.disconnect()
            try {
                reader?.close()
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }
}