package br.com.fiap.vigiabairro.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun RegisterScreen(
    onRegister: (
        String,
        String,
        String,
        String
    ) -> Unit
) {

    var title by remember { mutableStateOf("") }
    var description by remember { mutableStateOf("") }
    var location by remember { mutableStateOf("") }
    var type by remember { mutableStateOf("Alagamento") }

    val types = listOf(
        "Alagamento",
        "Bueiro entupido",
        "Rua interditada",
        "Árvore caída",
        "Outro"
    )

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(20.dp),
        verticalArrangement = Arrangement.spacedBy(14.dp)
    ) {

        Text(
            "Registrar ocorrência",
            style = MaterialTheme.typography.headlineSmall
        )

        OutlinedTextField(
            value = title,
            onValueChange = { title = it },
            label = { Text("Título") },
            modifier = Modifier.fillMaxWidth()
        )

        OutlinedTextField(
            value = description,
            onValueChange = { description = it },
            label = { Text("Descrição") },
            modifier = Modifier.fillMaxWidth()
        )

        OutlinedTextField(
            value = location,
            onValueChange = { location = it },
            label = { Text("Local da ocorrência") },
            modifier = Modifier.fillMaxWidth()
        )

        Text("Tipo de ocorrência")

        types.forEach { item ->

            Row {
                RadioButton(
                    selected = type == item,
                    onClick = { type = item }
                )

                Text(
                    item,
                    modifier = Modifier.padding(top = 12.dp)
                )
            }
        }

        Button(
            onClick = {
                if (
                    title.isNotBlank() &&
                    description.isNotBlank() &&
                    location.isNotBlank()
                ) {
                    onRegister(
                        title,
                        description,
                        location,
                        type
                    )

                    title = ""
                    description = ""
                    location = ""
                    type = "Alagamento"
                }
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Enviar ocorrência")
        }
    }
}