package com.example.ucp2roomdatabase.ui.viewmodel.dosen

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.ucp2roomdatabase.repository.RepositoryDosen
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow

class DetailDosenViewModel(
    savedStateHandle: SavedStateHandle,
    private val repositoryDosen: RepositoryDosen,
) :ViewModel(){

    // Mendapatkan NIDN dari SavedStateHandle
    private val _nidn: String = checkNotNull(savedStateHandle[DestinasiDosenDetail.NIDN])

    // Mendapatkan detail mahasiswa dan mengelola UI state
    val detailUiState: StateFlow<DetailUiState> = repositoryDosen.getDosen(_nidn)
        .filterNotNull() // Pastikan data tidak null
        .map {
            DetailUiState(
                detailUiEvent = it.toDetailUiEvent(),
                isLoading = false,
            )
        }
        .onStart {
            emit(DetailUiState(isLoading = true)) // Set status loading saat mulai
            delay(600) // Simulasi delay jika diperlukan
        }
        .catch {
            emit(
                DetailUiState(
                    isLoading = false,
                    isError = true,
                    errorMessage = it.message ?: "Terjadi Kesalahan"
                )
            )
        }
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(2000),
            initialValue = DetailUiState(
                isLoading = true,
            ),
        )
}