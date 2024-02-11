package com.ucne.clientesapp

import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.RequiresExtension
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.ucne.clientesapp.ui.Home.HomeScreen
import com.ucne.clientesapp.ui.Navegacion.Screen
import com.ucne.clientesapp.ui.RegistroCliente.RegistroClienteScreen
import com.ucne.clientesapp.ui.SeleccionCliente.SeleccionClienteScreen
import com.ucne.clientesapp.ui.theme.ClientesAppTheme
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    @RequiresExtension(extension = Build.VERSION_CODES.S, version = 7)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ClientesAppTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {

                    val navController = rememberNavController()
                    Menu(navController = navController)

                }
            }
        }
    }
}


@RequiresExtension(extension = Build.VERSION_CODES.S, version = 7)
@Composable
private fun Menu(navController: NavHostController) {
    NavHost(
        navController = navController,
        startDestination = Screen.Home.route
    ) {

        composable(Screen.Home.route) {
            HomeScreen(
              registroNavigation = { navController.navigate(Screen.RegistroClienteScreen.route) }
            ){
                navController.navigate(Screen.SeleccionClienteScreen.route + "/$it")
            }
        }

        composable(
            Screen.SeleccionClienteScreen.route + "/{id}",
            arguments = listOf(navArgument("id") {type = NavType.IntType})
        ) {
            val id = it.arguments?.getInt("id") ?: 0
            SeleccionClienteScreen(
                id = id
            )
        }

        composable(Screen.RegistroClienteScreen.route) {
           RegistroClienteScreen()
        }
    }
}