package br.com.fiap.vigiabairro.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun PreventionScreen() {

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(20.dp),
        verticalArrangement = Arrangement.spacedBy(15.dp)
    ) {

        Text(
            "Guia de prevenção",
            style = MaterialTheme.typography.headlineSmall
        )

        Card {
            Column(Modifier.padding(16.dp)) {
                Text("🌧️ Durante chuvas fortes")

                Text(
                    "Evite áreas alagadas, acompanhe os " +
                            "alertas oficiais e procure locais seguros."
                )
            }
        }

        Card {
            Column(Modifier.padding(16.dp)) {
                Text("⚡ Segurança")

                Text(
                    "Não se aproxime de fios elétricos caídos " +
                            "e não tente atravessar correntezas."
                )
            }
        }

        Card {
            Column(Modifier.padding(16.dp)) {
                Text("🏠 Preparação")

                Text(
                    "Mantenha documentos protegidos e " +
                            "conheça as rotas seguras da região."
                )
            }
        }

        Card {
            Column(Modifier.padding(16.dp)) {
                Text("📢 Emergências")

                Text(
                    "Em situações de risco, procure os canais " +
                            "oficiais de emergência da sua região."
                )
            }
        }
    }
}