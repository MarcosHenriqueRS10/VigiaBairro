package br.com.fiap.vigiabairro.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import br.com.fiap.vigiabairro.data.model.Occurrence

@Composable
fun OccurrencesScreen(
    occurrences: List<Occurrence>,
    onSelect: (Occurrence) -> Unit
) {

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(20.dp)
    ) {

        Text(
            "Ocorrências do bairro",
            style = MaterialTheme.typography.headlineSmall
        )

        Spacer(modifier = Modifier.height(15.dp))

        if (occurrences.isEmpty()) {

            Text("Ainda não existem ocorrências registradas.")

        } else {

            LazyColumn(
                verticalArrangement = Arrangement.spacedBy(12.dp)
            ) {

                items(occurrences) { occurrence ->

                    Card(
                        modifier = Modifier.fillMaxWidth(),
                        onClick = {
                            onSelect(occurrence)
                        }
                    ) {

                        Column(
                            modifier = Modifier.padding(16.dp)
                        ) {

                            Text(
                                occurrence.title,
                                style = MaterialTheme.typography.titleMedium
                            )

                            Text("Tipo: ${occurrence.type}")

                            Text("Local: ${occurrence.location}")

                            Text("Data: ${occurrence.date}")
                        }
                    }
                }
            }
        }
    }
}