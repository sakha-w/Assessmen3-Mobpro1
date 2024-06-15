package org.d3if3102.fashionstore.network

import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import org.d3if3102.fashionstore.model.Produk
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET
import retrofit2.http.Multipart
import retrofit2.http.POST
import retrofit2.http.Part

private const val BASE_URL =
    "https://fakestoreapi.com/"

private val moshi = Moshi.Builder()
    .add(KotlinJsonAdapterFactory())
    .build()

private val retrofit = Retrofit.Builder()
    .addConverterFactory(MoshiConverterFactory.create(moshi))
    .baseUrl(BASE_URL)
    .build()

interface ProdukApiService {
    @GET("products?limit=5")
    suspend fun getProduk(
    ): List<Produk>
}

object ProdukApi {
    val service: ProdukApiService by lazy {
        retrofit.create(ProdukApiService::class.java)
    }

    fun getProdukUrl(image: String): String {
        return "${BASE_URL}$image.jpg"
    }
}
