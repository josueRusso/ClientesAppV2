package com.ucne.clientesapp.data.repository

import android.os.Build
import androidx.annotation.RequiresExtension
import com.ucne.clientesapp.data.remote.ClienteApi
import com.ucne.clientesapp.data.remote.dto.ClienteDto
import com.ucne.clientesapp.util.Resource
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.Flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class ClienteRepository @Inject constructor(
    val clienteApi: ClienteApi
) {

    @RequiresExtension(extension = Build.VERSION_CODES.S, version = 7)
    fun getCliente(): Flow<Resource<List<ClienteDto>>> = flow {
        try {
            emit(Resource.Loading())

            val cliente = clienteApi.getClientes()

            emit(Resource.Success(cliente))

        } catch (e: HttpException) {
            //error general HTTP
            emit(Resource.Error(e.message ?: "Error HTTP GENERAL"))
        } catch (e: IOException) {
            //debe verificar tu conexion a internet
            emit(Resource.Error(e.message ?: "verificar tu conexion a internet"))
        }
    }

    fun getClientesById(codigoCliente: Int): Flow<Resource<ClienteDto>> = flow {
        try {
            emit(Resource.Loading())

            val clientes = clienteApi.getClientesById(codigoCliente)

            emit(Resource.Success(clientes))

        } catch (e: HttpException) {
            //error general HTTP
            emit(Resource.Error(e.message ?: "Error HTTP GENERAL"))
        } catch (e: IOException) {
            //debe verificar tu conexion a internet
            emit(Resource.Error(e.message ?: "verificar tu conexion a internet"))
        }
        catch (e: Exception) {
            //debe verificar tu conexion a internet
            emit(Resource.Error(e.message ?: "verificar tu conexion a internet"))
        }
    }

    suspend fun postCliente(cliente: ClienteDto): Resource<ClienteDto> {
        return try {
            val response = clienteApi.postClientes(cliente)

            if (response.isSuccessful) {
                val responseBody = response.body()
                if (responseBody != null) {
                    Resource.Success(responseBody)
                } else {
                    Resource.Error("Respuesta vacía del servidor")
                }
            } else {
                Resource.Error("Error en la solicitud: ${response.code()} ${response.message()}")
            }
        } catch (e: IOException) {
            // Error de conexión a Internet
            Resource.Error("Verificar tu conexión a internet")
        } catch (e: Exception) {
            // Otros errores
            Resource.Error("Error desconocido: ${e.message ?: "verificar tu conexión a internet"}")
        }
    }

}