package com.example.ucp2roomdatabase.ui.viewmodel.dosen

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.example.ucp2roomdatabase.data.entity.Dosen
import com.example.ucp2roomdatabase.repository.RepositoryDosen
import com.example.ucp2roomdatabase.ui.viewmodel.matakuliah.FormErrorState

class DosenViewModel(private val repositoryDosen: RepositoryDosen) : ViewModel() {
    var uiState by mutableStateOf(DosenUIState())

    // Memperbarui state berdasarkan input pengguna
    fun updateState(dosenEvent: DosenEvent) {
        uiState = uiState.copy(
            dosenEvent = dosenEvent
        )
    }

    // Validasi data input pengguna
    private fun validateField(): Boolean {
        val event = uiState.dosenEvent
        val errorState = FormErrorState(
            nidn = if (event.nidn.isNotEmpty()) null else "NIDN tidak boleh kosong",
            nama = if (event.nama.isNotEmpty()) null else "Nama tidak boleh kosong",
            jenisKelamin = if (event.jenisKelamin.isNotEmpty()) null else "Jenis kelamin tidak boleh kosong"
        )
        uiState = uiState.copy(isEntryValid = errorState)
        return errorState.isValid()
    }
}

data class DosenUIState(
    val dosenEvent: DosenEvent = DosenEvent(),
    val isEntryValid:FormErrorState = FormErrorState(),
    val snackBarMessage: String? = null,
)
data class FormErrorState(
    val nidn:String? =null,
    val nama:String?= null,
    val jenisKelamin:String?=null
){
    fun isValid():Boolean{
        return nidn ==null && nama ==null &&jenisKelamin==null
    }
}

//menyimpan input form ke entity
fun DosenEvent.toDosenEntity(): Dosen = Dosen(
    nidn = nidn,
    nama = nama,
    jenisKelamin = jenisKelamin
)
//data class variable yang ,menyimpan data input form
data class DosenEvent(
    val nidn:String ="",
    val nama:String="",
    val jenisKelamin:String=""
)