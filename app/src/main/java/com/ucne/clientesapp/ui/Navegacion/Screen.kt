package com.ucne.clientesapp.ui.Navegacion

sealed class Screen (val route: String){
    object Home: Screen("Home")
    object RegistroClienteScreen: Screen("RegistroClienteScreen")
    object SeleccionClienteScreen: Screen("SeleccionTicketScreen")

}