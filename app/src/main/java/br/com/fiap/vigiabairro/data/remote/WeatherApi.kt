package br.com.fiap.vigiabairro.data.remote

import br.com.fiap.vigiabairro.data.model.WeatherResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface WeatherApi {

    @GET("v1/forecast")
    suspend fun getWeather(
        @Query("latitude") latitude: Double,
        @Query("longitude") longitude: Double,
        @Query("current") current: String =
            "temperature_2m,precipitation",
        @Query("daily") daily: String =
            "precipitation_probability_max",
        @Query("timezone") timezone: String = "auto"
    ): WeatherResponse
}