package com.ucne.clientesapp.data.remote.dto


data class ClienteDto(
    val codigoCliente: Int = 1,
    val nombres: String = "",
    val direccion: String = "",
    val telefono: String = "",
    val celular: String = "",
    val cedula: String = "",
    val tipoComprobante: Int = 1,
)