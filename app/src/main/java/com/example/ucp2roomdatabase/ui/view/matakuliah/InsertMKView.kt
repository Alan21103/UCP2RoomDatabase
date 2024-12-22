package com.example.ucp2roomdatabase.ui.view.matakuliah

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.RadioButton
import androidx.compose.material3.RadioButtonDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.ucp2roomdatabase.R
import com.example.ucp2roomdatabase.data.entity.Dosen
import com.example.ucp2roomdatabase.ui.customewidget.TopAppBar
import com.example.ucp2roomdatabase.ui.navigation.AlamatNavigasi
import com.example.ucp2roomdatabase.ui.viewmodel.matakuliah.FormErrorState
import com.example.ucp2roomdatabase.ui.viewmodel.matakuliah.MKUIState
import com.example.ucp2roomdatabase.ui.viewmodel.matakuliah.MataKuliahEvent
import com.example.ucp2roomdatabase.ui.viewmodel.matakuliah.MataKuliahViewModel
import com.example.ucp2roomdatabase.ui.customewidget.DynamicSelectedTextField
import com.example.ucp2roomdatabase.ui.viewmodel.dosen.HomeDosenViewModel
import com.example.ucp2roomdatabase.ui.viewmodel.dosen.HomeUiState
import com.example.ucp2roomdatabase.ui.viewmodel.dosen.PenyediaDosenViewModel
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
    viewModelDsn: HomeDosenViewModel =viewModel(factory = PenyediaDosenViewModel.Factory)
) {
    val uiState = viewModel.uiState
    val snackbarHostState = remember { SnackbarHostState() }
    val coroutineScope = rememberCoroutineScope()
    val dsnList by viewModelDsn.homeUiState.collectAsState()

    LaunchedEffect(uiState.snackBarMessage) {
        uiState.snackBarMessage?.let { message ->
            coroutineScope.launch {
                snackbarHostState.showSnackbar(message)
                viewModel.resetSnackBarMessage()
            }
        }
    }

    Scaffold(
        modifier = modifier
            .background(color = colorResource(id = R.color.purple_500))
            .fillMaxSize(),
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
                .background(color = colorResource(id = R.color.purple_500))
                .fillMaxSize()
                .padding(padding)
                .padding(16.dp)
        ) {
            InsertBodyMK(
                uiState = uiState,
                listDosen = dsnList,
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
    onClick: () -> Unit,
    listDosen: HomeUiState
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
            modifier = Modifier.fillMaxWidth(),
            listDsn = listDosen.listDsn
        )
        Button(
            onClick = onClick,
            modifier = Modifier.fillMaxWidth()
        ){
            Text("Simpan")
        }
    }
}


@Composable
fun FormMataKuliah(
    mataKuliahEvent: MataKuliahEvent = MataKuliahEvent(),
    onValueChange: (MataKuliahEvent) -> Unit = {},
    errorState: FormErrorState = FormErrorState(),
    modifier: Modifier = Modifier,
    listDsn: List<Dosen>
){
    var chosenDropdown by remember {
        mutableStateOf(
            ""
        )
    }
    val jenis = listOf("Wajib", "Peminatan")
    val namaDosenList = listDsn.map { it.nama }

    Column(
        modifier = modifier.fillMaxWidth()
    ){
        OutlinedTextField(
            modifier = Modifier.fillMaxWidth(),
            value = mataKuliahEvent.kode,
            onValueChange = {
                onValueChange(mataKuliahEvent.copy(kode = it))
            },
            label = { Text("Kode MK", color = Color.White) },
            isError = errorState.kode !=null,
            textStyle = TextStyle(color = Color.White),
            placeholder = { Text("Masukkan Kode MataKuliah", color = Color.White) },
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
            label = { Text("Nama", color = Color.White) },
            isError = errorState.nama !=null,
            textStyle = TextStyle(color = Color.White),
            placeholder = { Text("Masukkan Nama MataKuliah", color = Color.White) },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text)
        )
        Text(
            text = errorState.nama ?:"",
            color = Color.Red
        )

        Spacer(modifier = Modifier.height(10.dp))

        OutlinedTextField(
            modifier = Modifier.fillMaxWidth(),
            value = mataKuliahEvent.sks,
            onValueChange = {
                onValueChange(mataKuliahEvent.copy(sks = it))
            },
            label = { Text("SKS", color = Color.White) },
            textStyle = TextStyle(color = Color.White),
            isError = errorState.sks !=null,
            placeholder = { Text("Masukkan SKS MataKuliah", color = Color.White) },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
        )
        Text(
            text = errorState.sks ?:"",
            color = Color.Red
        )

        Spacer(modifier = Modifier.height(10.dp))

        Text(text = "Jenis", color = Color.White)
        Row(
            modifier = Modifier.fillMaxWidth()
        ) {
            jenis.forEach { jenisOption ->
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Start
                ) {
                    RadioButton(
                        colors = RadioButtonDefaults.colors(colorResource(id = R.color.white)) ,
                        selected = mataKuliahEvent.jenis == jenisOption,
                        onClick = {
                            onValueChange(mataKuliahEvent.copy(jenis = jenisOption))
                        },
                    )
                    Text(text = jenisOption, color = Color.White)
                }
            }
        }
        Text(
            text = errorState.jenis ?: "",
            color = Color.Red
        )

        Spacer(modifier = Modifier.height(10.dp))

        OutlinedTextField(
            modifier = Modifier.fillMaxWidth(),
            value = mataKuliahEvent.semester,
            onValueChange = {
                onValueChange(mataKuliahEvent.copy(semester = it))
            },
            label = { Text("Semester", color = Color.White) },
            isError = errorState.semester !=null,
            textStyle = TextStyle(color = Color.White),
            placeholder = { Text("Masukkan Semester MataKuliah", color = Color.White) },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text)
        )
        Text(
            text = errorState.semester ?:"",
            color = Color.Red
        )

        Spacer(modifier = Modifier.height(10.dp))

        Text(text = "Dosen Pengampu", color = Color.White)
        DynamicSelectedTextField(
            modifier = Modifier,
            selectedValue = mataKuliahEvent.dosenPengampu,
            options = namaDosenList,
            label = "Pilih Dosen Pengampu",
            onValueChangedEvent = {
                onValueChange(mataKuliahEvent.copy(dosenPengampu = it))
            }
        )
        Text(
            text = errorState.dosenPengampu ?: "",
            color = Color.Red
        )
    }
}

