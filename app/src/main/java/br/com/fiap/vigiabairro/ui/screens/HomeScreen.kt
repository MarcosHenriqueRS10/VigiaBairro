package br.com.fiap.vigiabairro.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import br.com.fiap.vigiabairro.viewmodel.WeatherViewModel

@Composable
fun HomeScreen(weatherViewModel: WeatherViewModel = viewModel()) {
    val weather by weatherViewModel.weather.collectAsStateWithLifecycle()
    val loading by weatherViewModel.loading.collectAsStateWithLifecycle()
    val error by weatherViewModel.error.collectAsStateWithLifecycle()

    LaunchedEffect(Unit) { weatherViewModel.loadWeather() }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(20.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        Text("Olá, seja bem-vindo!", fontSize = 25.sp)
        Text("VigiaBairro • São Paulo")

        Spacer(modifier = Modifier.height(8.dp))
        Text("Condições meteorológicas", style = MaterialTheme.typography.titleLarge)

        Card(modifier = Modifier.fillMaxWidth()) {
            Column(
                modifier = Modifier.padding(20.dp),
                verticalArrangement = Arrangement.spacedBy(10.dp)
            ) {
                when {
                    loading -> {
                        CircularProgressIndicator()
                        Text("Carregando informações do clima...")
                    }
                    weather != null -> {
                        val current = weather?.current
                        val probability = weather?.daily?.precipitationProbability?.firstOrNull()
                        Text("🌡️ ${current?.temperature ?: "--"} °C", fontSize = 30.sp)
                        Text("Precipitação atual: ${current?.precipitation ?: "--"} mm")
                        Text("Probabilidade máxima de chuva: ${probability ?: "--"}%")
                    }
                    else -> {
                        Text(error ?: "Dados meteorológicos indisponíveis.")
                    }
                }
                OutlinedButton(
                    onClick = { weatherViewModel.loadWeather() },
                    enabled = !loading,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text("Atualizar informações")
                }
            }
        }

        Spacer(modifier = Modifier.height(8.dp))
        Text("Nossa missão", style = MaterialTheme.typography.titleLarge)
        Text(
            "Conectar moradores e incentivar a prevenção de riscos urbanos " +
                "através da tecnologia."
        )
        Text("⚠️ Em períodos de chuva, fique atento às condições das ruas.")
    }
}
