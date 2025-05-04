package ec.edu.monster.servicio;

import ec.edu.monster.util.SoapConstants;
import org.ksoap2.SoapEnvelope;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapPrimitive;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.HttpTransportSE;

public class CONUNIService {

    public boolean login(String usuario, String contraseña) throws Exception {
        SoapObject request = new SoapObject(SoapConstants.NAMESPACE, SoapConstants.LOGIN_METHOD);
        request.addProperty("usuario", usuario);
        request.addProperty("contraseña", contraseña);

        SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
        envelope.setOutputSoapObject(request);

        HttpTransportSE transport = new HttpTransportSE(SoapConstants.URL);
        transport.call(SoapConstants.SOAP_ACTION_PREFIX + SoapConstants.LOGIN_METHOD, envelope);

        SoapPrimitive response = (SoapPrimitive) envelope.getResponse();
        return Boolean.parseBoolean(response.toString());
    }

    public double pulgadasACentimetros(double pulgadas) throws Exception {
        SoapObject request = new SoapObject(SoapConstants.NAMESPACE, SoapConstants.PULGADAS_A_CENTIMETROS_METHOD);
        // Convertir el double a String para evitar problemas de serialización
        request.addProperty("pulgadas", String.valueOf(pulgadas));

        SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
        envelope.setOutputSoapObject(request);

        HttpTransportSE transport = new HttpTransportSE(SoapConstants.URL);
        transport.call(SoapConstants.SOAP_ACTION_PREFIX + SoapConstants.PULGADAS_A_CENTIMETROS_METHOD, envelope);

        SoapPrimitive response = (SoapPrimitive) envelope.getResponse();
        return Double.parseDouble(response.toString());
    }

    public double centimetrosAPulgadas(double centimetros) throws Exception {
        SoapObject request = new SoapObject(SoapConstants.NAMESPACE, SoapConstants.CENTIMETROS_A_PULGADAS_METHOD);
        // Convertir el double a String para evitar problemas de serialización
        request.addProperty("centimetros", String.valueOf(centimetros));

        SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
        envelope.setOutputSoapObject(request);

        HttpTransportSE transport = new HttpTransportSE(SoapConstants.URL);
        transport.call(SoapConstants.SOAP_ACTION_PREFIX + SoapConstants.CENTIMETROS_A_PULGADAS_METHOD, envelope);

        SoapPrimitive response = (SoapPrimitive) envelope.getResponse();
        return Double.parseDouble(response.toString());
    }
}