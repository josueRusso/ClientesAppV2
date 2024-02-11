package com.ucne.clientesapp.ui.RegistroCliente

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Button
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.ucne.clientesapp.data.remote.dto.ClienteDto
import com.ucne.clientesapp.ui.theme.ClientesAppTheme


@Composable
fun RegistroClienteScreen(
    //ticketId: Int,
    //onPressCancel: () -> Unit,
    viewModel: RegistroClienteViewModel = hiltViewModel()
) {

    Base()
}

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun Base() {
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

                )
        }

    ){

        RegistroCliente()
    }
}


@Composable
private fun RegistroCliente() {
    var cliente : ClienteDto

    var codigoCliente by remember { mutableStateOf("") }
    var nombres by remember { mutableStateOf("") }
    var direccion by remember { mutableStateOf("") }
    var telefono by remember { mutableStateOf("") }
    var celular by remember { mutableStateOf("") }
    var cedula by remember { mutableStateOf("") }
    var tipoComprobante by remember { mutableStateOf("") }

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
            Spacer(modifier = Modifier.padding(0.dp,40.dp))
            Text(
                text = "Registro de Cliente",
                fontWeight = FontWeight.Bold,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(0.dp, 0.dp, 0.dp, 16.dp)
            )

            OutlinedTextField(
                value = codigoCliente,
                onValueChange = { codigoCliente = it },
                label = { Text(text = "Codigo del Cliente ") },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(5.dp, 0.dp)
            )

            OutlinedTextField(
                value = nombres,
                onValueChange = { nombres = it },
                label = { Text(text = "Nombres") },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(5.dp, 0.dp)
            )

            OutlinedTextField(
                value = direccion,
                onValueChange = { direccion = it },
                label = { Text(text = "Direccion") },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(5.dp, 0.dp)
            )

            OutlinedTextField(
                value = telefono,
                onValueChange = { telefono = it },
                label = { Text(text = "Telefono") },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(5.dp, 0.dp)
            )

            OutlinedTextField(
                value = celular,
                onValueChange = { celular = it },
                label = { Text(text = "Celular") },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(5.dp, 0.dp)
            )

            OutlinedTextField(
                value = cedula,
                onValueChange = { cedula = it },
                label = { Text(text = "Cedula") },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(5.dp, 0.dp)
            )
            OutlinedTextField(
                value = tipoComprobante,
                onValueChange = { tipoComprobante = it },
                label = { Text(text = "tipo Comprobante") },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(5.dp, 0.dp)
            )

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(5.dp, 16.dp),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Button(
                    onClick = {
                        // Acción para el botón "Guardar"
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
                cliente = ClienteDto(codigoCliente.toInt(),nombres,direccion,telefono,celular,cedula, tipoComprobante.toInt() )
            }
        }
    }
}


@Preview(showBackground = true, showSystemUi = true)
@Composable
fun Preview() {
    ClientesAppTheme {
        Base()
    }
}