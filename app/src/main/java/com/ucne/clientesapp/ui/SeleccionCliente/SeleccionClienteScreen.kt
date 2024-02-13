package com.ucne.clientesapp.ui.SeleccionCliente

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.remember
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import com.ucne.clientesapp.data.remote.dto.ClienteDto
import com.ucne.clientesapp.ui.theme.ClientesAppTheme


@Composable
fun SeleccionClienteScreen(
   id: Int = 0,
   viewModel: SeleccionClienteViewModelV = hiltViewModel(),


) {
    remember {
        viewModel.getClienteSeleccionado(id)
        0
    }

    val uiState by viewModel.state.collectAsState()

    uiState.cliente?.let { Base(it) }

}

@Composable
private fun Contenido(cliente : ClienteDto){

    ElevatedCard(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            horizontalAlignment = Alignment.Start
        ) {

            Text(
                text = "# " + cliente.codigoCliente.toString(),
                fontSize = 30.sp
            )
            Spacer(modifier = Modifier.padding(0.dp,10.dp))

            Text(
                    text = "Nombres: " + cliente.nombres,
                    fontSize = 25.sp
            )

            Spacer(modifier = Modifier.padding(0.dp,10.dp))

            Text(
                text = "Direccion: " + cliente.direccion,
                fontSize = 25.sp
            )
            Spacer(modifier = Modifier.padding(0.dp,10.dp))

            Text(
                text = "Telefono: " + cliente.telefono,
                fontSize = 25.sp
            )

            Spacer(modifier = Modifier.padding(0.dp,10.dp))

            Text(
                text = "Celular: " + cliente.celular,
                fontSize = 25.sp
            )
            Spacer(modifier = Modifier.padding(0.dp,10.dp))

            Text(
                text = "Cedula: " + cliente.cedula,
                fontSize = 25.sp
            )

            Spacer(modifier = Modifier.padding(0.dp,10.dp))


        }
    }
}

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun Base(cliente : ClienteDto) {
    Scaffold(
        topBar = {
            TopAppBar(
                colors = TopAppBarDefaults.topAppBarColors(containerColor = Color(0xFF39393A)),
                title = {
                    Text(
                        "ClienteApp",
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis
                    )
                },

                )
        }

    ){

        Column(
            modifier = Modifier.fillMaxSize()
        ) {
            Spacer(modifier = Modifier.padding(0.dp, 30.dp))
            Contenido(cliente = cliente)
        }

    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun GreetingPreview() {
    ClientesAppTheme {
        val cliente = ClienteDto(
            1,
            "Josue",
            "27 de febrero",
            "829-602-0880",
            "829-602-0880",
            "1003339292",
            1

        )
        Base(cliente)
    }
}
