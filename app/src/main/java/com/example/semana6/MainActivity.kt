package com.example.semana6

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material.icons.outlined.AccountCircle
import androidx.compose.material.icons.outlined.Notifications
import androidx.compose.material.icons.outlined.Settings
import androidx.compose.material.icons.rounded.Menu
import androidx.compose.material.icons.rounded.Search
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.semana6.ui.theme.Semana6Theme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Semana6Theme {
                CustomScaffold()
            }
        }
    }
}

// Función Composable que crea un Scaffold personalizado
@Composable
fun CustomScaffold() {
    val navController = rememberNavController()
    var clickCount by remember { mutableIntStateOf(0) }

    Scaffold(
        topBar = { CustomTopBar(navController) },
        bottomBar = { CustomBottomBar(navController) },
        floatingActionButton = { CustomFAB(clickCount) { clickCount++ } },
        content = { padding ->
            NavHost(
                navController = navController,
                startDestination = "home"
            ) {
                composable("home") { CustomContent(padding, clickCount) }
                composable("profile") { ProfileScreen() }
                composable("settings") { SettingsScreen() }
                composable("notifications") { NotificationsScreen() }
            }
        }
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CustomTopBar(navController: NavHostController) {
    TopAppBar(
        navigationIcon = {
            IconButton(onClick = { /* TODO */ }) {
                Icon(imageVector = Icons.Rounded.Menu, contentDescription = null)
            }
        },
        title = { Text(text = "Sample Title") },
        actions = {
            IconButton(onClick = { /* TODO */ }) {
                Icon(
                    imageVector = Icons.Rounded.Search,
                    contentDescription = null
                )
            }
            IconButton(onClick = {
                navController.navigate("profile")
            }) {
                Icon(
                    imageVector = Icons.Outlined.AccountCircle,
                    contentDescription = null
                )
            }
        }
    )
}

@Composable
fun CustomBottomBar(navController: NavHostController) {
    BottomAppBar {
        Row(
            modifier = Modifier.fillMaxSize() // Ocupar todo el espacio disponible
        ) {
            IconButton(
                onClick = { navController.navigate("home") },
                modifier = Modifier.weight(1f) // Cada botón toma el mismo espacio
            ) {
                Icon(Icons.Filled.Home, contentDescription = "Home")
            }
            IconButton(
                onClick = { navController.navigate("settings") },
                modifier = Modifier.weight(1f) // Cada botón toma el mismo espacio
            ) {
                Icon(Icons.Outlined.Settings, contentDescription = "Settings")
            }
            IconButton(
                onClick = { navController.navigate("notifications") },
                modifier = Modifier.weight(1f) // Cada botón toma el mismo espacio
            ) {
                Icon(Icons.Outlined.Notifications, contentDescription = "Notifications")
            }
            IconButton(
                onClick = { navController.navigate("profile") },
                modifier = Modifier.weight(1f) // Cada botón toma el mismo espacio
            ) {
                Icon(Icons.Outlined.AccountCircle, contentDescription = "Profile")
            }
        }
    }
}


@Composable
fun CustomFAB(clickCount: Int, onClick: () -> Unit) {
    FloatingActionButton(onClick = onClick) {
        Text(
            fontSize = 24.sp,
            text = "+ $clickCount"
        )
    }
}

@Composable
fun CustomContent(padding: PaddingValues, clickCount: Int) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(padding)
    ) {
        Text(text = "Ejemplo de funcionamiento")
        Text(text = "Número de clics: $clickCount")
    }
}

@Composable
fun ProfileScreen() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = "Perfil", style = MaterialTheme.typography.bodyLarge)
    }
}

// Pantalla de ajustes
@Composable
fun SettingsScreen() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = "configuraciones", style = MaterialTheme.typography.bodyLarge)
    }
}

// Pantalla de notificaciones
@Composable
fun NotificationsScreen() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = "notificaciones", style = MaterialTheme.typography.bodyLarge)
    }
}