package com.example.ucp2roomdatabase.ui.view.matakuliah

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.ucp2roomdatabase.data.`object`.Sks
import com.example.ucp2roomdatabase.data.`object`.Semester
import com.example.ucp2roomdatabase.ui.customewidget.TopAppBar
import com.example.ucp2roomdatabase.ui.navigation.AlamatNavigasi
import com.example.ucp2roomdatabase.ui.viewmodel.matakuliah.FormErrorState
import com.example.ucp2roomdatabase.ui.viewmodel.matakuliah.MKUIState
import com.example.ucp2roomdatabase.ui.viewmodel.matakuliah.MataKuliahEvent
import com.example.ucp2roomdatabase.ui.viewmodel.matakuliah.MataKuliahViewModel
import com.example.ucp2roomdatabase.ui.customewidget.DynamicSelectedTextField
import com.example.ucp2roomdatabase.ui.viewmodel.dosen.PenyediaDosenViewModel
import com.example.ucp2roomdatabase.ui.viewmodel.matakuliah.HomeMKViewModel
import com.example.ucp2roomdatabase.ui.viewmodel.matakuliah.PenyediaMKViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

object DestinasiInsertMK : AlamatNavigasi {
    override val route: String = "insert_mk"
}
@Composable
fun InsertMKView(
    onBack: () -> Unit,
    onNavigate: () -> Unit,
    modifier: Modifier = Modifier,
    viewModel: MataKuliahViewModel = viewModel(factory = PenyediaMKViewModel.Factory),
    viewModelDsn: HomeMKViewModel =viewModel(factory = PenyediaDosenViewModel.Factory)
) {
    val uiState = viewModel.uiState
    val snackbarHostState = remember { SnackbarHostState() }
    val coroutineScope = rememberCoroutineScope()

    LaunchedEffect(uiState.snackBarMessage) {
        uiState.snackBarMessage?.let { message ->
            coroutineScope.launch {
                snackbarHostState.showSnackbar(message)
                viewModel.resetSnackBarMessage()
            }
        }
    }

    Scaffold(
        modifier = modifier,
        snackbarHost = { SnackbarHost(hostState = snackbarHostState) },
        topBar = {
            TopAppBar(
                onBack = onBack,
                showBackButton = true,
                judul = "Tambah MataKuliah",
                modifier = modifier
            )
        }
    ) { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .padding(16.dp)
        ) {
            InsertBodyMK(
                uiState = uiState,
                onValueChange = { updateEvent ->
                    viewModel.updateState(updateEvent)
                },
                onClick = {
                    coroutineScope.launch {
                        if (viewModel.validateFields()) {
                            viewModel.saveData()
                            delay(500)
                            withContext(Dispatchers.Main) {
                                onNavigate()
                            }
                        }
                    }
                }
            )
        }
    }
}

@Composable
fun InsertBodyMK(
    modifier: Modifier = Modifier,
    onValueChange: (MataKuliahEvent) -> Unit,
    uiState: MKUIState,
    onClick: () -> Unit
) {
    Column(
        modifier = modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ){
        FormMataKuliah(
            mataKuliahEvent = uiState.mataKuliahEvent,
            onValueChange = onValueChange,
            errorState = uiState.isEntryValid,
            modifier = Modifier.fillMaxWidth()
        )
        Button(
            onClick = onClick,
            modifier = Modifier.fillMaxWidth()
        ){
            Text("Simpan")
        }
    }
}


@Preview(showBackground = true)
@Composable
fun FormMataKuliah(
    mataKuliahEvent: MataKuliahEvent = MataKuliahEvent(),
    onValueChange: (MataKuliahEvent) -> Unit = {},
    errorState: FormErrorState = FormErrorState(),
    modifier: Modifier = Modifier
){
    var chosenDropdown by remember {
        mutableStateOf(
            ""
        )
    }

    Column(
        modifier = modifier.fillMaxWidth()
    ){
        OutlinedTextField(
            modifier = Modifier.fillMaxWidth(),
            value = mataKuliahEvent.kode,
            onValueChange = {
                onValueChange(mataKuliahEvent.copy(kode = it))
            },
            label = { Text("Kode MK") },
            isError = errorState.kode !=null,
            placeholder = { Text("Masukkan Kode MataKuliah") },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text)
        )
        Text(
            text = errorState.kode ?:"",
            color = Color.Red
        )

        OutlinedTextField(
            modifier = Modifier.fillMaxWidth(),
            value = mataKuliahEvent.nama,
            onValueChange = {
                onValueChange(mataKuliahEvent.copy(nama = it))
            },
            label = { Text("Nama") },
            isError = errorState.nama !=null,
            placeholder = { Text("Masukkan Nama MataKuliah") },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text)
        )
        Text(
            text = errorState.nama ?:"",
            color = Color.Red
        )

        Spacer(modifier = Modifier.height(10.dp))
        DynamicSelectedTextField(
            selectedValue = chosenDropdown,
            options = Sks.options,
            label = "SKS",
            onValueChangedEvent = {
                chosenDropdown = it
            }
        )

        Spacer(modifier = Modifier.height(10.dp))
        OutlinedTextField(
            modifier = Modifier.fillMaxWidth(),
            value = mataKuliahEvent.jenis,
            onValueChange = {
                onValueChange(mataKuliahEvent.copy(jenis = it))
            },
            label = { Text("Jenis") },
            isError = errorState.jenis !=null,
            placeholder = { Text("Masukkan Jenis") },
        )
        Text(
            text = errorState.jenis ?:"",
            color = Color.Red
        )

        Spacer(modifier = Modifier.height(10.dp))
        DynamicSelectedTextField(
            selectedValue = chosenDropdown,
            options = Semester.options,
            label = "Semester",
            onValueChangedEvent = {
                chosenDropdown = it
            }
        )
        Spacer(modifier = Modifier.height(10.dp))
        OutlinedTextField(
            modifier = Modifier.fillMaxWidth(),
            value = mataKuliahEvent.dosenPengampu,
            onValueChange = {
                onValueChange(mataKuliahEvent.copy(dosenPengampu = it))
            },
            label = { Text("Dosen Pengampu") },
            isError = errorState.dosenPengampu !=null,
            placeholder = { Text("Pilih Dosen Pengampu") },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text)
        )
        Text(
            text = errorState.dosenPengampu ?:"",
            color = Color.Red
        )
    }
}

