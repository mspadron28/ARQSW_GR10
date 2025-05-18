package ec.edu.monster.servicio;

import ec.edu.monster.util.SoapConstants;
import org.ksoap2.SoapEnvelope;
import org.ksoap2.serialization.PropertyInfo;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapPrimitive;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.HttpTransportSE;

import android.util.Log;

public class CONUNIService {

    private static final String TAG = "CONUNIService";

    public boolean login(String usuario, String contraseña) throws Exception {
        SoapObject request = new SoapObject(SoapConstants.NAMESPACE, SoapConstants.LOGIN_METHOD);
        request.addProperty("usuario", usuario);
        request.addProperty("contraseña", contraseña);

        SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
        envelope.dotNet = true;
        envelope.setOutputSoapObject(request);

        HttpTransportSE transport = new HttpTransportSE(SoapConstants.URL, 10000);
        try {
            transport.debug = true;
            transport.call(SoapConstants.SOAP_ACTION_PREFIX + SoapConstants.LOGIN_METHOD, envelope);
            Log.d(TAG, "Login Request: " + transport.requestDump);
            Log.d(TAG, "Login Response: " + transport.responseDump);
            SoapPrimitive response = (SoapPrimitive) envelope.getResponse();
            return Boolean.parseBoolean(response.toString());
        } catch (Exception e) {
            Log.e(TAG, "Error en login: " + e.getMessage(), e);
            throw new Exception("Error en login: " + e.getMessage(), e);
        }
    }

    public double pulgadasACentimetros(double pulgadas) throws Exception {
        SoapObject request = new SoapObject(SoapConstants.NAMESPACE, SoapConstants.PULGADAS_A_CENTIMETROS_METHOD);
        PropertyInfo property = new PropertyInfo();
        property.setName("pulgadas");
        property.setValue(String.valueOf(pulgadas)); // Enviar como String
        property.setType(String.class);
        request.addProperty(property);

        SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
        envelope.dotNet = true;
        envelope.setOutputSoapObject(request);

        HttpTransportSE transport = new HttpTransportSE(SoapConstants.URL, 10000);
        try {
            transport.debug = true;
            transport.call(SoapConstants.SOAP_ACTION_PREFIX + SoapConstants.PULGADAS_A_CENTIMETROS_METHOD, envelope);
            Log.d(TAG, "pulgadasACentimetros Request: " + transport.requestDump);
            Log.d(TAG, "pulgadasACentimetros Response: " + transport.responseDump);
            Object response = envelope.getResponse();
            if (response instanceof SoapPrimitive) {
                return Double.parseDouble(response.toString());
            } else if (response instanceof SoapObject) {
                // Manejar posible SOAP Fault
                SoapObject fault = (SoapObject) response;
                String faultString = fault.getPropertyAsString("faultstring");
                throw new Exception("SOAP Fault: " + faultString);
            } else {
                throw new Exception("Respuesta inesperada: " + response);
            }
        } catch (Exception e) {
            Log.e(TAG, "Error en pulgadasACentimetros: " + e.getMessage(), e);
            e.printStackTrace();
            throw new Exception("Error en pulgadasACentimetros: " + e.getMessage(), e);
        }
    }

    public double centimetrosAPulgadas(double centimetros) throws Exception {
        SoapObject request = new SoapObject(SoapConstants.NAMESPACE, SoapConstants.CENTIMETROS_A_PULGADAS_METHOD);
        PropertyInfo property = new PropertyInfo();
        property.setName("centimetros");
        property.setValue(String.valueOf(centimetros));
        property.setType(String.class);
        request.addProperty(property);

        SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
        envelope.dotNet = true;
        envelope.setOutputSoapObject(request);

        HttpTransportSE transport = new HttpTransportSE(SoapConstants.URL, 10000);
        try {
            transport.debug = true;
            transport.call(SoapConstants.SOAP_ACTION_PREFIX + SoapConstants.CENTIMETROS_A_PULGADAS_METHOD, envelope);
            Log.d(TAG, "centimetrosAPulgadas Request: " + transport.requestDump);
            Log.d(TAG, "centimetrosAPulgadas Response: " + transport.responseDump);
            Object response = envelope.getResponse();
            if (response instanceof SoapPrimitive) {
                return Double.parseDouble(response.toString());
            } else if (response instanceof SoapObject) {
                SoapObject fault = (SoapObject) response;
                String faultString = fault.getPropertyAsString("faultstring");
                throw new Exception("SOAP Fault: " + faultString);
            } else {
                throw new Exception("Respuesta inesperada: " + response);
            }
        } catch (Exception e) {
            Log.e(TAG, "Error en centimetrosAPulgadas: " + e.getMessage(), e);
            e.printStackTrace();
            throw new Exception("Error en centimetrosAPulgadas: " + e.getMessage(), e);
        }
    }

    public double metrosAPies(double metros) throws Exception {
        SoapObject request = new SoapObject(SoapConstants.NAMESPACE, SoapConstants.METROS_A_PIES_METHOD);
        PropertyInfo property = new PropertyInfo();
        property.setName("metros");
        property.setValue(String.valueOf(metros));
        property.setType(String.class);
        request.addProperty(property);

        SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
        envelope.dotNet = true;
        envelope.setOutputSoapObject(request);

        HttpTransportSE transport = new HttpTransportSE(SoapConstants.URL, 10000);
        try {
            transport.debug = true;
            transport.call(SoapConstants.SOAP_ACTION_PREFIX + SoapConstants.METROS_A_PIES_METHOD, envelope);
            Log.d(TAG, "metrosAPies Request: " + transport.requestDump);
            Log.d(TAG, "metrosAPies Response: " + transport.responseDump);
            Object response = envelope.getResponse();
            if (response instanceof SoapPrimitive) {
                return Double.parseDouble(response.toString());
            } else if (response instanceof SoapObject) {
                SoapObject fault = (SoapObject) response;
                String faultString = fault.getPropertyAsString("faultstring");
                throw new Exception("SOAP Fault: " + faultString);
            } else {
                throw new Exception("Respuesta inesperada: " + response);
            }
        } catch (Exception e) {
            Log.e(TAG, "Error en metrosAPies: " + e.getMessage(), e);
            e.printStackTrace();
            throw new Exception("Error en metrosAPies: " + e.getMessage(), e);
        }
    }

    public double piesAMetros(double pies) throws Exception {
        SoapObject request = new SoapObject(SoapConstants.NAMESPACE, SoapConstants.PIES_A_METROS_METHOD);
        PropertyInfo property = new PropertyInfo();
        property.setName("pies");
        property.setValue(String.valueOf(pies));
        property.setType(String.class);
        request.addProperty(property);

        SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
        envelope.dotNet = true;
        envelope.setOutputSoapObject(request);

        HttpTransportSE transport = new HttpTransportSE(SoapConstants.URL, 10000);
        try {
            transport.debug = true;
            transport.call(SoapConstants.SOAP_ACTION_PREFIX + SoapConstants.PIES_A_METROS_METHOD, envelope);
            Log.d(TAG, "piesAMetros Request: " + transport.requestDump);
            Log.d(TAG, "piesAMetros Response: " + transport.responseDump);
            Object response = envelope.getResponse();
            if (response instanceof SoapPrimitive) {
                return Double.parseDouble(response.toString());
            } else if (response instanceof SoapObject) {
                SoapObject fault = (SoapObject) response;
                String faultString = fault.getPropertyAsString("faultstring");
                throw new Exception("SOAP Fault: " + faultString);
            } else {
                throw new Exception("Respuesta inesperada: " + response);
            }
        } catch (Exception e) {
            Log.e(TAG, "Error en piesAMetros: " + e.getMessage(), e);
            e.printStackTrace();
            throw new Exception("Error en piesAMetros: " + e.getMessage(), e);
        }
    }

    public double metrosAYardas(double metros) throws Exception {
        SoapObject request = new SoapObject(SoapConstants.NAMESPACE, SoapConstants.METROS_A_YARDAS_METHOD);
        PropertyInfo property = new PropertyInfo();
        property.setName("metros");
        property.setValue(String.valueOf(metros));
        property.setType(String.class);
        request.addProperty(property);

        SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
        envelope.dotNet = true;
        envelope.setOutputSoapObject(request);

        HttpTransportSE transport = new HttpTransportSE(SoapConstants.URL, 10000);
        try {
            transport.debug = true;
            transport.call(SoapConstants.SOAP_ACTION_PREFIX + SoapConstants.METROS_A_YARDAS_METHOD, envelope);
            Log.d(TAG, "metrosAYardas Request: " + transport.requestDump);
            Log.d(TAG, "metrosAYardas Response: " + transport.responseDump);
            Object response = envelope.getResponse();
            if (response instanceof SoapPrimitive) {
                return Double.parseDouble(response.toString());
            } else if (response instanceof SoapObject) {
                SoapObject fault = (SoapObject) response;
                String faultString = fault.getPropertyAsString("faultstring");
                throw new Exception("SOAP Fault: " + faultString);
            } else {
                throw new Exception("Respuesta inesperada: " + response);
            }
        } catch (Exception e) {
            Log.e(TAG, "Error en metrosAYardas: " + e.getMessage(), e);
            e.printStackTrace();
            throw new Exception("Error en metrosAYardas: " + e.getMessage(), e);
        }
    }

    public double yardasAMetros(double yardas) throws Exception {
        SoapObject request = new SoapObject(SoapConstants.NAMESPACE, SoapConstants.YARDAS_A_METROS_METHOD);
        PropertyInfo property = new PropertyInfo();
        property.setName("yardas");
        property.setValue(String.valueOf(yardas));
        property.setType(String.class);
        request.addProperty(property);

        SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
        envelope.dotNet = true;
        envelope.setOutputSoapObject(request);

        HttpTransportSE transport = new HttpTransportSE(SoapConstants.URL, 10000);
        try {
            transport.debug = true;
            transport.call(SoapConstants.SOAP_ACTION_PREFIX + SoapConstants.YARDAS_A_METROS_METHOD, envelope);
            Log.d(TAG, "yardasAMetros Request: " + transport.requestDump);
            Log.d(TAG, "yardasAMetros Response: " + transport.responseDump);
            Object response = envelope.getResponse();
            if (response instanceof SoapPrimitive) {
                return Double.parseDouble(response.toString());
            } else if (response instanceof SoapObject) {
                SoapObject fault = (SoapObject) response;
                String faultString = fault.getPropertyAsString("faultstring");
                throw new Exception("SOAP Fault: " + faultString);
            } else {
                throw new Exception("Respuesta inesperada: " + response);
            }
        } catch (Exception e) {
            Log.e(TAG, "Error en yardasAMetros: " + e.getMessage(), e);
            e.printStackTrace();
            throw new Exception("Error en yardasAMetros: " + e.getMessage(), e);
        }
    }
}