package com.ucne.clientesapp.ui.RegistroCliente

import android.annotation.SuppressLint
import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.absoluteOffset
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.compose.viewModel
import com.ucne.clientesapp.data.remote.dto.ClienteDto
import com.ucne.clientesapp.ui.theme.ClientesAppTheme
import kotlinx.coroutines.launch


@Composable
fun RegistroClienteScreen(
    viewModel: RegistroClienteViewModel = hiltViewModel()
) {
    Base(viewModel = viewModel)
}

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun Base(viewModel: RegistroClienteViewModel) {
    val uiState by viewModel.state.collectAsState()
    Scaffold(
        topBar = {
            TopAppBar(
                colors = TopAppBarDefaults.topAppBarColors(containerColor = Color(0xFF39393A)),
                title = {
                    Text(
                        "ClientesApp",
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis
                    )
                },
            )
        }
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                //.verticalScroll(rememberScrollState(),enabled = true)
        ) {
            if (uiState.isLoading) {
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .absoluteOffset(),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                ) {
                    CircularProgressIndicator(
                        modifier = Modifier
                            .size(80.dp)
                            .padding(0.dp, 50.dp),
                        strokeWidth = 8.dp
                    )
                }

            }
            Spacer(modifier = Modifier.padding(0.dp,30.dp))
            RegistroCliente(viewModel = viewModel)
        }

    }
}

@Composable
private fun RegistroCliente(viewModel: RegistroClienteViewModel) {
    val stateVertical = rememberScrollState(0)
    val state by viewModel.state.collectAsStateWithLifecycle()
    val cliente = state.cliente

    ElevatedCard(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            Text(
                text = "Registro de Cliente",
                fontWeight = FontWeight.Bold,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(0.dp, 0.dp, 0.dp, 16.dp)
            )

            OutlinedTextField(
                value = cliente.nombres,
                onValueChange = { viewModel.onEvent(ClienteEvent.NombreChanged(it)) },
                label = { Text(text = "Nombre") },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(5.dp, 0.dp)
            )

            OutlinedTextField(
                value = cliente.direccion,
                onValueChange = { viewModel.onEvent(ClienteEvent.DireccionChanged(it)) },
                label = { Text(text = "Direccion") },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(5.dp, 0.dp)
            )

            OutlinedTextField(
                value = cliente.telefono,
                onValueChange = { viewModel.onEvent(ClienteEvent.TelefonoChanged(it)) },
                label = { Text(text = "Telefono") },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(5.dp, 0.dp),
                keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Number,
                    imeAction = ImeAction.Next)

            )

            OutlinedTextField(
                value = cliente.celular,
                onValueChange = { viewModel.onEvent(ClienteEvent.CelularChanged(it)) },
                label = { Text(text = "Celular") },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(5.dp, 0.dp),
                keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Number,
                    imeAction = ImeAction.Next)
            )

            OutlinedTextField(
                value = cliente.cedula,
                onValueChange = { viewModel.onEvent(ClienteEvent.CedulaChanged(it)) },
                label = { Text(text = "Cedula") },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(5.dp, 0.dp),
                keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Number,
                    imeAction = ImeAction.Next)
            )
            OutlinedTextField(
                value = cliente.tipoComprobante.toString(),
                onValueChange = { viewModel.onEvent(ClienteEvent.TipoComprobanteChanged(it)) },
                label = { Text(text = "tipo Comprobante") },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(5.dp, 0.dp),
                keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Number,
                    imeAction = ImeAction.Next)
            )

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(5.dp, 16.dp),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Button(
                    onClick = {
                        viewModel.onEvent(ClienteEvent.onSave)
                    },
                    modifier = Modifier
                        .weight(1f)
                        .padding(4.dp)
                ) {
                    Row(
                        horizontalArrangement = Arrangement.Center,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Icon(imageVector = Icons.Default.Add, contentDescription = "Guardar")
                        Spacer(modifier = Modifier.width(4.dp))
                    }
                }

                Button(
                    onClick = {
                        viewModel.onEvent(ClienteEvent.onLimpiar)
                    },
                    modifier = Modifier
                        .weight(1f)
                        .padding(4.dp)
                ) {
                    Row(
                        horizontalArrangement = Arrangement.Center,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Icon(imageVector = Icons.Default.Refresh, contentDescription = "Limpiar")
                        Spacer(modifier = Modifier.width(4.dp))

                    }
                }
            }
        }
    }
}


