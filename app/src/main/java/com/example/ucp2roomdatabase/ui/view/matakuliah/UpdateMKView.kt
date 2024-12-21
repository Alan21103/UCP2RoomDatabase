package com.example.ucp2roomdatabase.ui.view.matakuliah

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarDuration
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.ucp2roomdatabase.ui.customewidget.TopAppBar
import com.example.ucp2roomdatabase.ui.viewmodel.dosen.HomeDosenViewModel
import com.example.ucp2roomdatabase.ui.viewmodel.dosen.PenyediaDosenViewModel
import com.example.ucp2roomdatabase.ui.viewmodel.matakuliah.PenyediaMKViewModel
import com.example.ucp2roomdatabase.ui.viewmodel.matakuliah.UpdateMKViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

@Composable
fun UpdateMKView(
    onBack: () -> Unit,
    onNavigate: () -> Unit,
    modifier: Modifier = Modifier,
    viewModel: UpdateMKViewModel = viewModel(factory = PenyediaMKViewModel.Factory),
    viewModelDsn: HomeDosenViewModel =viewModel(factory = PenyediaDosenViewModel.Factory)
){
    val uiState = viewModel.updateUIState
    val snackbarHostState = remember { SnackbarHostState() }
    val coroutineScope = rememberCoroutineScope()
    val dsnList by viewModelDsn.homeUiState.collectAsState()

    LaunchedEffect(uiState.snackBarMessage) {
        println("LaunchedEffect triggered")
        uiState.snackBarMessage?.let { message ->
            println("Launching coroutine: $message")
            coroutineScope.launch {
                println("Launching coroutine for snackbar")
                snackbarHostState.showSnackbar(
                    message = message,
                    duration = SnackbarDuration.Long
                )
                viewModel.resetSnackBarMessage()
            }
        }
    }
    Scaffold (
        modifier=modifier,
        snackbarHost = { SnackbarHost(hostState = snackbarHostState) },
        topBar = {
            TopAppBar(
                judul = "Edit MataKuliah",
                showBackButton = true,
                onBack = onBack,
                modifier = modifier
            )
        }
    ){ padding ->
        Column (
            modifier= Modifier
                .fillMaxSize()
                .padding(padding)
                .padding(16.dp)
        ){
            //isi BOdy
            InsertBodyMK(
                uiState = uiState,
                listDosen = dsnList,
                onValueChange = { updateEvent ->
                    viewModel.updateState(updateEvent)
                },
                onClick = {
                    coroutineScope.launch {
                        if (viewModel.validateFields()){
                            viewModel.updateData()
                            delay(500)
                            withContext(Dispatchers.Main){
                                onNavigate()
                            }
                        }
                    }
                }
            )
        }
    }
}