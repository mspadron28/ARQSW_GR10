package ec.edu.monster.servicio;
import android.util.Log;
import org.json.JSONException;
import org.json.JSONObject;
import ec.edu.monster.util.RestConstants; // Actualizado de SoapConstants a RestConstants import okhttp3.OkHttpClient; import okhttp3.Request; import okhttp3.Response; import org.json.JSONException; import org.json.JSONObject; import android.util.Log;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import java.io.IOException;

public class CONUNIService {
    private static final String TAG = "CONUNIService";
    private static final OkHttpClient client = new OkHttpClient();

    public boolean login(String usuario, String contraseña) throws Exception {
        String url = RestConstants.BASE_URL + "login?usuario=" + java.net.URLEncoder.encode(usuario, "UTF-8") + "&contraseña=" + java.net.URLEncoder.encode(contraseña, "UTF-8");
        Request request = new Request.Builder().url(url).build();

        try (Response response = client.newCall(request).execute()) {
            if (response.isSuccessful()) {
                String responseBody = response.body().string();
                Log.d(TAG, "Login Response: " + responseBody);
                // Interpretar directamente como booleano
                return Boolean.parseBoolean(responseBody.trim());
            } else {
                throw new Exception("Error en login: " + response.code() + " - " + response.message());
            }
        } catch (IOException e) {
            Log.e(TAG, "Error en login: " + e.getMessage(), e);
            throw new Exception("Error en login: " + e.getMessage(), e);
        }
    }


    public double pulgadasACentimetros(double pulgadas) throws Exception {
        String url = RestConstants.BASE_URL + "pulgadas-a-centimetros?pulgadas=" + pulgadas;
        Request request = new Request.Builder().url(url).build();

        try (Response response = client.newCall(request).execute()) {
            if (response.isSuccessful()) {
                String responseBody = response.body().string();
                Log.d(TAG, "pulgadasACentimetros Response: " + responseBody);
                // Parsear directamente como Double, reemplazando espacios por puntos si es necesario
                return Double.parseDouble(responseBody.trim().replace(" ", "."));
            } else {
                throw new Exception("Error en pulgadasACentimetros: " + response.code() + " - " + response.message());
            }
        } catch (IOException | NumberFormatException e) {
            Log.e(TAG, "Error en pulgadasACentimetros: " + e.getMessage(), e);
            throw new Exception("Error en pulgadasACentimetros: " + e.getMessage(), e);
        }
    }

    public double centimetrosAPulgadas(double centimetros) throws Exception {
        String url = RestConstants.BASE_URL + "centimetros-a-pulgadas?centimetros=" + centimetros;
        Request request = new Request.Builder().url(url).build();

        try (Response response = client.newCall(request).execute()) {
            if (response.isSuccessful()) {
                String responseBody = response.body().string();
                Log.d(TAG, "centimetrosAPulgadas Response: " + responseBody);
                return Double.parseDouble(responseBody.trim().replace(" ", "."));
            } else {
                throw new Exception("Error en centimetrosAPulgadas: " + response.code() + " - " + response.message());
            }
        } catch (IOException | NumberFormatException e) {
            Log.e(TAG, "Error en centimetrosAPulgadas: " + e.getMessage(), e);
            throw new Exception("Error en centimetrosAPulgadas: " + e.getMessage(), e);
        }
    }

    public double metrosAPies(double metros) throws Exception {
        String url = RestConstants.BASE_URL + "metros-a-pies?metros=" + metros;
        Request request = new Request.Builder().url(url).build();

        try (Response response = client.newCall(request).execute()) {
            if (response.isSuccessful()) {
                String responseBody = response.body().string();
                Log.d(TAG, "metrosAPies Response: " + responseBody);
                return Double.parseDouble(responseBody.trim().replace(" ", "."));
            } else {
                throw new Exception("Error en metrosAPies: " + response.code() + " - " + response.message());
            }
        } catch (IOException | NumberFormatException e) {
            Log.e(TAG, "Error en metrosAPies: " + e.getMessage(), e);
            throw new Exception("Error en metrosAPies: " + e.getMessage(), e);
        }
    }

    public double piesAMetros(double pies) throws Exception {
        String url = RestConstants.BASE_URL + "pies-a-metros?pies=" + pies;
        Request request = new Request.Builder().url(url).build();

        try (Response response = client.newCall(request).execute()) {
            if (response.isSuccessful()) {
                String responseBody = response.body().string();
                Log.d(TAG, "piesAMetros Response: " + responseBody);
                return Double.parseDouble(responseBody.trim().replace(" ", "."));
            } else {
                throw new Exception("Error en piesAMetros: " + response.code() + " - " + response.message());
            }
        } catch (IOException | NumberFormatException e) {
            Log.e(TAG, "Error en piesAMetros: " + e.getMessage(), e);
            throw new Exception("Error en piesAMetros: " + e.getMessage(), e);
        }
    }

    public double metrosAYardas(double metros) throws Exception {
        String url = RestConstants.BASE_URL + "metros-a-yardas?metros=" + metros;
        Request request = new Request.Builder().url(url).build();

        try (Response response = client.newCall(request).execute()) {
            if (response.isSuccessful()) {
                String responseBody = response.body().string();
                Log.d(TAG, "metrosAYardas Response: " + responseBody);
                return Double.parseDouble(responseBody.trim().replace(" ", "."));
            } else {
                throw new Exception("Error en metrosAYardas: " + response.code() + " - " + response.message());
            }
        } catch (IOException | NumberFormatException e) {
            Log.e(TAG, "Error en metrosAYardas: " + e.getMessage(), e);
            throw new Exception("Error en metrosAYardas: " + e.getMessage(), e);
        }
    }

    public double yardasAMetros(double yardas) throws Exception {
        String url = RestConstants.BASE_URL + "yardas-a-metros?yardas=" + yardas;
        Request request = new Request.Builder().url(url).build();

        try (Response response = client.newCall(request).execute()) {
            if (response.isSuccessful()) {
                String responseBody = response.body().string();
                Log.d(TAG, "yardasAMetros Response: " + responseBody);
                return Double.parseDouble(responseBody.trim().replace(" ", "."));
            } else {
                throw new Exception("Error en yardasAMetros: " + response.code() + " - " + response.message());
            }
        } catch (IOException | NumberFormatException e) {
            Log.e(TAG, "Error en yardasAMetros: " + e.getMessage(), e);
            throw new Exception("Error en yardasAMetros: " + e.getMessage(), e);
        }
    }
}