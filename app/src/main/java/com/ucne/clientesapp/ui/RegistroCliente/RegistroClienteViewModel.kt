package com.ucne.clientesapp.ui.RegistroCliente

import android.os.Build
import androidx.annotation.RequiresApi

import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.compose.runtime.setValue
import androidx.compose.runtime.getValue
import com.ucne.clientesapp.data.remote.ClienteApi
import com.ucne.clientesapp.data.remote.dto.ClienteDto
import com.ucne.clientesapp.data.repository.ClienteRepository
import com.ucne.clientesapp.ui.Home.ClienteListState
import com.ucne.clientesapp.ui.SeleccionCliente.ClienteUiState
import com.ucne.clientesapp.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import java.io.IOException
import java.time.LocalDate
import javax.inject.Inject


@HiltViewModel
class RegistroClienteViewModel @Inject constructor(
    private val clienteRepository: ClienteRepository
) : ViewModel() {

    private val _state = MutableStateFlow(ClienteListState())
    val state = _state.asStateFlow()

    fun postCliente(cliente: ClienteDto) {
        viewModelScope.launch {
            clienteRepository.postCliente(cliente).collectLatest  { result ->
                when (result) {
                    is Resource.Loading -> {
                        _state.update { it.copy(isLoading = true) }
                    }

                    is Resource.Success -> {
                        _state.update { it.copy(cliente = ClienteDto(
                            0,
                            "",
                            "",
                            "",
                            "",
                            "",
                            0
                            ),
                            isLoading = false,
                            error = result.message
                        )
                        }
                    }

                    is Resource.Error -> {
                        _state.update {
                            it.copy(
                                error = result.message,
                                cliente =   ClienteDto(
                                0,
                                "",
                                "",
                                "",
                                "",
                                "",
                                0
                            ),
                                isLoading = false
                            )
                        }
                    }
                }
            }
        }
    }
}

data class ClienteListState(
    val isLoading: Boolean = false,
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

