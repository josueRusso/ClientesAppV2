package com.ucne.clientesapp.data.remote

import com.ucne.clientesapp.data.remote.dto.ClienteDto
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.POST
import retrofit2.http.Path


interface ClienteApi {
    @GET("api/Clientes")
    @Headers("X-API-Key: test")
    suspend fun getClientes():List<ClienteDto>

    @GET("api/Clientes/{id}")
    @Headers("X-API-Key: test")
    suspend fun getClientesById(@Path("id") id: Int): ClienteDto

    @POST("api/Clientes")
    @Headers("X-API-key: test")
    suspend fun postClientes(@Body clienteDto: ClienteDto ) : Response<ClienteDto>

}