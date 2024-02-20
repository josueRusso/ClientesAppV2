package com.ucne.clientesapp.ui.RegistroCliente

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ucne.clientesapp.data.remote.dto.ClienteDto
import com.ucne.clientesapp.data.repository.ClienteRepository
import com.ucne.clientesapp.ui.Home.ClienteListState
import com.ucne.clientesapp.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class RegistroClienteViewModel @Inject constructor(
    private val clienteRepository: ClienteRepository
) : ViewModel() {

    private val _state = MutableStateFlow(ClienteState())
    val state = _state.asStateFlow()

    fun postCliente() {
        viewModelScope.launch {
            clienteRepository.postCliente(_state.value.cliente).collectLatest  { result ->
                when (result) {
                    is Resource.Loading -> {
                        _state.update { it.copy(isLoading = true) }
                    }

                    is Resource.Success -> {
                        _state.update {
                            it.copy(
                                successMessage = "Guardado correctamente",
                                isLoading = false
                            )
                        }
                    }

                    is Resource.Error -> {
                        _state.update {
                            it.copy(
                                error = "Ocurrio un error, intentelo de nuevo",
                                isLoading = false
                            )
                        }
                    }
                }
            }
        }
    }
    fun onEvent(event: ClienteEvent){
        when(event){
            is ClienteEvent.NombreChanged -> {
                _state.update{
                    it.copy(
                        cliente = it.cliente.copy(nombres = event.nombre)
                    )
                }
            }

            is ClienteEvent.DireccionChanged -> {
                _state.update{
                    it.copy(
                        cliente = it.cliente.copy(direccion = event.direccion)
                    )
                }
            }

            is ClienteEvent.TelefonoChanged -> {
                _state.update{
                    it.copy(
                        cliente = it.cliente.copy(telefono = event.telefono)
                    )
                }
            }

            is ClienteEvent.CelularChanged -> {
                _state.update{
                    it.copy(
                        cliente = it.cliente.copy(celular = event.celular)
                    )
                }
            }

            is ClienteEvent.CedulaChanged -> {
                _state.update{
                    it.copy(
                        cliente = it.cliente.copy(cedula = event.cedula)
                    )
                }
            }

            is ClienteEvent.TipoComprobanteChanged -> {
                _state.update{
                    it.copy(
                        cliente = it.cliente.copy(tipoComprobante = event.tipoComprobante.toIntOrNull()?:0)
                    )
                }
            }



            ClienteEvent.onSave -> {
                postCliente()
                onEvent(ClienteEvent.onLimpiar)
            }

            ClienteEvent.onLimpiar -> {
                _state.update{
                    it.copy(
                        cliente = ClienteDto(),
                        successMessage = null,
                        error = null,
                        isLoading = false
                    )
                }
            }

        }
    }
}


data class ClienteState(
    val isLoading: Boolean = false,
    val cliente: ClienteDto = ClienteDto(),
    val selectedCliente: ClienteDto? = null,
    val error: String? = null,
    val successMessage: String? = null,
)


sealed class ClienteEvent{
    data class NombreChanged(val nombre: String): ClienteEvent()
    data class DireccionChanged(val direccion: String): ClienteEvent()
    data class TelefonoChanged(val telefono: String): ClienteEvent()
    data class CelularChanged(val celular: String): ClienteEvent()
    data class CedulaChanged(val cedula: String): ClienteEvent()
    data class TipoComprobanteChanged(val tipoComprobante: String): ClienteEvent()

    object onSave: ClienteEvent()
    object onLimpiar: ClienteEvent()
}
