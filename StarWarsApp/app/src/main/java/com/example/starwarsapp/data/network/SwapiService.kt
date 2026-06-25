package com.example.starwarsapp.data.network

import com.example.starwarsapp.data.model.PeopleResponse
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

// ── Retrofit interface ────────────────────────────────────────────────────────

interface SwapiService {

    @GET("people/")
    suspend fun getPeople(
        @Query("page") page: Int = 1
    ): PeopleResponse

    companion object {
        private const val BASE_URL = "https://swapi.dev/api/"

        fun create(): SwapiService {
            val logger = HttpLoggingInterceptor().apply {
                level = HttpLoggingInterceptor.Level.BASIC
            }
            val client = OkHttpClient.Builder()
                .addInterceptor(logger)
                .build()

            return Retrofit.Builder()
                .baseUrl(BASE_URL)
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(SwapiService::class.java)
        }
    }
}
