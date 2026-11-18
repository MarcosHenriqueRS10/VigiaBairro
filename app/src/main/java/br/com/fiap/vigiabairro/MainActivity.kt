package br.com.fiap.vigiabairro

import android.content.Context
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.*
import br.com.fiap.vigiabairro.data.model.Occurrence
import br.com.fiap.vigiabairro.data.repository.OccurrenceRepository
import br.com.fiap.vigiabairro.ui.components.BottomNavigationBar
import br.com.fiap.vigiabairro.ui.screens.*
import br.com.fiap.vigiabairro.ui.theme.VigiaBairroTheme
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            VigiaBairroTheme {
                VigiaBairroApp()
            }
        }
    }
}

@Composable
fun VigiaBairroApp() {
    val navController = rememberNavController()
    val context = androidx.compose.ui.platform.LocalContext.current
    val preferences = remember {
        context.getSharedPreferences("vigia_bairro", Context.MODE_PRIVATE)
    }
    val occurrenceRepository = remember {
        OccurrenceRepository(context.applicationContext)
    }
    val occurrences = remember {
        mutableStateListOf<Occurrence>().apply {
            addAll(occurrenceRepository.getAll())
        }
    }
    var selectedOccurrence by remember { mutableStateOf<Occurrence?>(null) }

    val currentEntry by navController.currentBackStackEntryAsState()
    val route = currentEntry?.destination?.route
    val showBottomBar = route in listOf(
        "home", "occurrences", "register", "prevention"
    )

    Scaffold(
        bottomBar = {
            if (showBottomBar) BottomNavigationBar(navController)
        }
    ) { padding ->
        NavHost(
            navController = navController,
            startDestination = "splash",
            modifier = Modifier.padding(padding)
        ) {
            composable("splash") {
                SplashScreen {
                    val onboardingCompleted = preferences.getBoolean(
                        "onboarding_completed", false
                    )
                    navController.navigate(
                        if (onboardingCompleted) "home" else "onboarding"
                    ) {
                        popUpTo("splash") { inclusive = true }
                    }
                }
            }

            composable("onboarding") {
                OnboardingScreen { dontShowAgain ->
                    preferences.edit()
                        .putBoolean("onboarding_completed", dontShowAgain)
                        .apply()
                    navController.navigate("home") {
                        popUpTo("onboarding") { inclusive = true }
                    }
                }
            }

            composable("home") { HomeScreen() }

            composable("occurrences") {
                OccurrencesScreen(
                    occurrences = occurrences,
                    onSelect = { occurrence ->
                        selectedOccurrence = occurrence
                        navController.navigate("detail")
                    }
                )
            }

            composable("register") {
                RegisterScreen(
                    onRegister = { title, description, location, type ->
                        val date = SimpleDateFormat(
                            "dd/MM/yyyy HH:mm", Locale.getDefault()
                        ).format(Date())
                        val saved = occurrenceRepository.add(
                            title = title,
                            description = description,
                            location = location,
                            type = type,
                            date = date
                        )
                        occurrences.add(saved)
                        navController.navigate("occurrences") {
                            popUpTo("home") { inclusive = false }
                            launchSingleTop = true
                        }
                    }
                )
            }

            composable("prevention") { PreventionScreen() }

            composable("detail") {
                val occurrence = selectedOccurrence
                if (occurrence != null) {
                    Column(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(20.dp),
                        verticalArrangement = Arrangement.spacedBy(12.dp)
                    ) {
                        Text(
                            "Detalhes da ocorrência",
                            style = MaterialTheme.typography.headlineSmall
                        )
                        Card(modifier = Modifier.fillMaxWidth()) {
                            Column(
                                modifier = Modifier.padding(16.dp),
                                verticalArrangement = Arrangement.spacedBy(8.dp)
                            ) {
                                Text(
                                    occurrence.title,
                                    style = MaterialTheme.typography.titleLarge
                                )
                                Text("Tipo: ${occurrence.type}")
                                Text("Local: ${occurrence.location}")
                                Text("Descrição: ${occurrence.description}")
                                Text("Data: ${occurrence.date}")
                            }
                        }
                        OutlinedButton(
                            onClick = { navController.popBackStack() }
                        ) {
                            Text("Voltar para ocorrências")
                        }
                    }
                }
            }
        }
    }
}
