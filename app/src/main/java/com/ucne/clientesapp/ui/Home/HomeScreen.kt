package com.ucne.clientesapp.ui.Home

import android.annotation.SuppressLint
import android.os.Build
import androidx.annotation.RequiresExtension
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.absoluteOffset
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.RadioButton
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
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.ucne.clientesapp.data.remote.dto.ClienteDto
import com.ucne.clientesapp.ui.theme.ClientesAppTheme

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@RequiresExtension(extension = Build.VERSION_CODES.S, version = 7)
@Composable
fun HomeScreen(
    viewModel: HomeViewModel = hiltViewModel(),
    registroNavigation: () -> Unit,
    onClickSeleccionado: (Int) -> Unit
) {
    val uiState by viewModel.state.collectAsState()
    val selectedCliente by rememberUpdatedState(newValue = uiState.selectedCliente)

    ClientesAppTheme {
        Scaffold(
            topBar = {
                TopAppBar(
                    colors = TopAppBarDefaults.topAppBarColors(containerColor = Color(0xFF8BDBF3)),
                    title = {
                        Text(
                            "ClientesApp",
                            maxLines = 1,
                            overflow = TextOverflow.Ellipsis
                        )
                    },
                    actions = {
                        IconButton(onClick = registroNavigation) {
                            Icon(
                                imageVector = Icons.Filled.Add,
                                contentDescription = "Añadir un nuevo registro"
                            )
                        }
                    }
                )
            }
        ) {
            Column(
                modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                if (uiState.isLoading) {
                    Column(
                        modifier = Modifier.fillMaxSize().absoluteOffset(),
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

                Column(
                    Modifier.fillMaxSize()
                ) {
                    Spacer(modifier = Modifier.padding(0.dp, 50.dp))

                    ComboBox(
                        clientes = uiState.clientes ,
                        onClienteSelected = { newSelectedCliente ->
                            viewModel.selectCliente(newSelectedCliente)
                        }

                    )
                    LaunchedEffect(selectedCliente) {
                        viewModel.getClienteSeleccionado()
                    }

                    Spacer(modifier = Modifier.padding(0.dp, 15.dp))

                    selectedCliente?.let { cliente ->
                        CardCliente(cliente = cliente) {
                            onClickSeleccionado(it)
                        }
                    }
                }
            }
        }
    }
}

@Composable
private fun ComboBox(
    clientes: List<ClienteDto>?,
    onClienteSelected: (ClienteDto?) -> Unit
) {
    var expanded by remember { mutableStateOf(false) }

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .height(200.dp)
            .padding(10.dp, 0.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "Seleccion de Clientes",
                fontWeight = FontWeight.Bold,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(0.dp, 0.dp, 0.dp, 16.dp)
            )

            Column(modifier = Modifier.fillMaxWidth()) {
                OutlinedTextField(
                    value = "",
                    onValueChange = {},
                    readOnly = true,
                    enabled = false,
                    label = { Text(text = "Clientes") },
                    trailingIcon = {
                        Icon(
                            imageVector = Icons.Default.ArrowDropDown,
                            contentDescription = null
                        )
                    },
                    modifier = Modifier
                        .clickable {
                            expanded = true
                        }
                        .fillMaxWidth()
                )

                DropdownMenu(
                    expanded = expanded,
                    onDismissRequest = { expanded = false },
                    modifier = Modifier.fillMaxWidth()
                ) {
                    clientes?.forEach { cliente ->
                        DropdownMenuItem(
                            text = { Text(text = cliente.nombres) },
                            onClick = {
                                expanded = false
                                onClienteSelected(cliente)
                            }
                        )
                    }
                }
                Button(
                    onClick = {
                        onClienteSelected(null)
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 8.dp)
                ) {
                    Text(text = "Deseleccionar")
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun CardCliente(cliente: ClienteDto,onClickSeleccionado: (Int) -> Unit) {
    ElevatedCard(
        onClick = {onClickSeleccionado(cliente.codigoCliente)},
        modifier = Modifier
            .fillMaxWidth()
            .height(100.dp)
            .padding(10.dp, 0.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
        ) {
            Spacer(modifier = Modifier.padding(0.dp, 5.dp))
            Row(
                modifier = Modifier
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    modifier = Modifier.padding(5.dp,0.dp),
                    text = "# ${cliente.codigoCliente}")
                Text(
                    modifier = Modifier.padding(5.dp,0.dp),
                    text = "Nombre : ${cliente.nombres}")
            }
            Spacer(modifier = Modifier.padding(0.dp, 15.dp))
            Row(
                modifier = Modifier
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    modifier = Modifier.padding(5.dp,0.dp),
                    text = "Telefono : ${cliente.telefono}")
                Text(
                    modifier = Modifier.padding(5.dp,0.dp),
                    text = "Cedula : ${cliente.cedula}")
            }
        }
    }
    Spacer(modifier = Modifier.padding(5.dp))
}