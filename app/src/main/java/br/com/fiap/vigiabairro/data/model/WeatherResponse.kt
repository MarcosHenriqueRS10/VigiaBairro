package br.com.fiap.vigiabairro.data.model

import com.google.gson.annotations.SerializedName

data class WeatherResponse(
    val current: CurrentWeather?,
    val daily: DailyWeather?
)

data class CurrentWeather(
    @SerializedName("temperature_2m")
    val temperature: Double,

    val precipitation: Double
)

data class DailyWeather(
    @SerializedName("precipitation_probability_max")
    val precipitationProbability: List<Int>
)