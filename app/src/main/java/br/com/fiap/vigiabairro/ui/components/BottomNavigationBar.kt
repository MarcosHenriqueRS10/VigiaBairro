package br.com.fiap.vigiabairro.ui.components

import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState

@Composable
fun BottomNavigationBar(navController: NavController) {

    val items = listOf(
        "home" to "Início",
        "occurrences" to "Ocorrências",
        "register" to "Registrar",
        "prevention" to "Prevenção"
    )

    NavigationBar(
        containerColor = Color(0xFF102A43)
    ) {

        val currentRoute =
            navController.currentBackStackEntryAsState()
                .value?.destination?.route

        items.forEach { (route, title) ->

            NavigationBarItem(
                selected = currentRoute == route,
                onClick = {
                    navController.navigate(route) {
                        launchSingleTop = true
                        popUpTo("home") {
                            saveState = true
                        }
                        restoreState = true
                    }
                },
                icon = {
                    Text(
                        when (route) {
                            "home" -> "🏠"
                            "occurrences" -> "📋"
                            "register" -> "➕"
                            else -> "🛡️"
                        }
                    )
                },
                label = { Text(title) },
                colors = NavigationBarItemDefaults.colors(
                    selectedIconColor = Color.White,
                    selectedTextColor = Color.White,
                    indicatorColor = Color(0xFF176B87),
                    unselectedIconColor = Color.LightGray,
                    unselectedTextColor = Color.LightGray
                )
            )
        }
    }
}