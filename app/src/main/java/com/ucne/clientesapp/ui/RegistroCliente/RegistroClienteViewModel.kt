package com.ucne.clientesapp.ui.RegistroCliente

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ucne.clientesapp.data.remote.dto.ClienteDto
import com.ucne.clientesapp.data.repository.ClienteRepository
import com.ucne.clientesapp.ui.SeleccionCliente.ClienteUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import java.time.LocalDate
import javax.inject.Inject


@HiltViewModel
class RegistroClienteViewModel @Inject constructor(

    private val clienteRepository: ClienteRepository
) : ViewModel() {

    var clienteUiState = MutableStateFlow(ClienteUiState())
        private set





}