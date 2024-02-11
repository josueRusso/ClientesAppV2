package com.ucne.clientesapp.ui.SeleccionCliente

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ucne.clientesapp.data.remote.dto.ClienteDto
import com.ucne.clientesapp.data.repository.ClienteRepository
import com.ucne.clientesapp.ui.Home.ClienteListState
import com.ucne.clientesapp.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

data class ClienteUiState(
    var cliente: ClienteDto = ClienteDto(
        0,
        "",
        "",
        "",
        "",
        "",
        1

    )
)

@HiltViewModel
class SeleccionClienteViewModelV @Inject constructor(
    val repository: ClienteRepository
) : ViewModel() {

    private val _state = MutableStateFlow(ClienteListState())
    val state = _state.asStateFlow()

    fun getClienteSeleccionado(id: Int) {
        val codigoCliente = state.value.selectedCliente?.codigoCliente ?: id
        viewModelScope.launch {
            repository.getClientesById(codigoCliente).collectLatest { result ->
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