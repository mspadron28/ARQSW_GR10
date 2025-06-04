package ec.edu.monster.servicio

import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

object RetrofitClient {
    private val client = OkHttpClient.Builder()
        .connectTimeout(30, TimeUnit.SECONDS)
        .writeTimeout(30, TimeUnit.SECONDS)
        .readTimeout(30, TimeUnit.SECONDS)
        .build()

    private val retrofit = Retrofit.Builder()
        .baseUrl("http://192.168.1.10:5158/api/") // Nueva URL
        .client(client)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    val apiService: ViajecitosApi = retrofit.create(ViajecitosApi::class.java)
}
