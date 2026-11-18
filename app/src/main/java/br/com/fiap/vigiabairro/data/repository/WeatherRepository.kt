package br.com.fiap.vigiabairro.data.repository

import br.com.fiap.vigiabairro.data.remote.RetrofitClient

class WeatherRepository {

    suspend fun getWeather() =
        RetrofitClient.api.getWeather(
            latitude = -23.5505,
            longitude = -46.6333
        )
}