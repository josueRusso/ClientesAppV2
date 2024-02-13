package com.ucne.clientesapp.ui.Home

import android.os.Build
import androidx.annotation.RequiresExtension
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ucne.clientesapp.data.remote.dto.ClienteDto
import com.ucne.clientesapp.data.repository.ClienteRepository
import com.ucne.clientesapp.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.emptyFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@RequiresExtension(extension = Build.VERSION_CODES.S, version = 7)
@HiltViewModel
class HomeViewModel @Inject constructor(
    private val clienteRepository: ClienteRepository,
) : ViewModel() {
    private val _state = MutableStateFlow(ClienteListState())
    val state = _state.asStateFlow()

    init {
        getClientes()
    }

    private fun getClientes() {
        viewModelScope.launch {
            clienteRepository.getCliente().collectLatest { result ->
                when (result) {
                    is Resource.Loading -> {
                        _state.update { it.copy(isLoading = true) }
                    }

                    is Resource.Success -> {
                        _state.update { it.copy(clientes = result.data, isLoading = false) }
                    }

                    is Resource.Error -> {
                        _state.update {
                            it.copy(
                                error = result.message,
                                clientes = emptyList(),
                                isLoading = false
                            )
                        }
                    }
                }
            }
        }
    }

    fun selectCliente(cliente: ClienteDto?) {
        _state.update { it.copy(selectedCliente = cliente) }
    }

    fun getClienteSeleccionado() {
        val codigoCliente = state.value.selectedCliente?.codigoCliente ?: 0
        viewModelScope.launch {
            clienteRepository.getClientesById(codigoCliente).collectLatest { result ->
                when (result) {
                    is Resource.Success -> {
                        _state.update {
                            it.copy(
                                cliente = result.data,
                                isLoading = false
                            )
                        }
                    }

                    else -> {

                    }
                }
            }
        }
    }
}


data class ClienteListState(
    val isLoading: Boolean = false,
    val clientes: List<ClienteDto>? = emptyList(),
    val cliente: ClienteDto? = ClienteDto(
        0,
        "",
        "",
        "",
        "",
        "",
        0

    ),
    val selectedCliente: ClienteDto? = null,
    val error: String? = null,
)