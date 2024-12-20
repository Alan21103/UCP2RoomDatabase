package com.example.ucp2roomdatabase.ui.viewmodel.dosen

import com.example.ucp2roomdatabase.data.entity.Dosen


data class HomeUiState(
    val listDsn: List<Dosen> = listOf(),
    val isLoading: Boolean = false,
    val isError: Boolean = false,
    val errorMessage: String = ""
)