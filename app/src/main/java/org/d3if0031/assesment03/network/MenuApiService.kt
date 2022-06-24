package org.d3if0031.assesment03.network

import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import org.d3if0031.assesment03.model.Makanan
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET

private const val BASE_URL = "https://raw.githubusercontent.com/Rayhan-ok/static-api/main/"

private val moshi = Moshi.Builder()
    .add(KotlinJsonAdapterFactory())
    .build()

private val retrofit = Retrofit.Builder()
    .addConverterFactory(MoshiConverterFactory.create(moshi))
    .baseUrl(BASE_URL)
    .build()

interface MenuApiService {
    @GET("static-api.json")
    suspend fun getMakanan(): List<Makanan>
}
object MakananApi {
    val service: MenuApiService by lazy {
        retrofit.create(MenuApiService::class.java)
    }
    fun getMakananUrl(nama: String): String {
        return "$BASE_URL$nama.jpg"
    }
}

enum class ApiStatus
{
    LOADING, SUCCESS, FAILED
}