
package br.com.fiap.vigiabairro.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun OnboardingScreen(
    onFinish: (Boolean) -> Unit
) {
    var currentPage by remember { mutableIntStateOf(0) }
    var dontShowAgain by remember { mutableStateOf(false) }

    val titles = listOf(
        "Bem-vindo ao VigiaBairro!",
        "Juntos por uma cidade mais segura!"
    )

    val descriptions = listOf(
        "Acompanhe as condições meteorológicas e fique atento a possíveis situações de risco na sua região.",
        "Registre ocorrências, acompanhe informações e compartilhe alertas para ajudar a proteger sua comunidade."
    )

    val icons = listOf("🏙️", "🤝")

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(25.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = icons[currentPage],
            fontSize = 75.sp
        )

        Spacer(modifier = Modifier.height(25.dp))

        Text(
            text = titles[currentPage],
            style = MaterialTheme.typography.headlineMedium
        )

        Spacer(modifier = Modifier.height(15.dp))

        Text(
            text = descriptions[currentPage],
            style = MaterialTheme.typography.bodyLarge
        )

        Spacer(modifier = Modifier.height(25.dp))

        Text(
            text = "${currentPage + 1} de ${titles.size}",
            style = MaterialTheme.typography.labelLarge
        )

        Spacer(modifier = Modifier.height(25.dp))

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            if (currentPage > 0) {
                OutlinedButton(
                    onClick = { currentPage-- }
                ) {
                    Text("Voltar")
                }
            } else {
                Spacer(modifier = Modifier.width(90.dp))
            }

            if (currentPage < titles.lastIndex) {
                Button(
                    onClick = { currentPage++ }
                ) {
                    Text("Avançar")
                }
            } else {
                Button(
                    onClick = { onFinish(dontShowAgain) }
                ) {
                    Text("Começar a usar")
                }
            }
        }

        Spacer(modifier = Modifier.height(20.dp))

        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            Checkbox(
                checked = dontShowAgain,
                onCheckedChange = { dontShowAgain = it }
            )

            Text("Não mostrar a introdução novamente")
        }
    }
}
