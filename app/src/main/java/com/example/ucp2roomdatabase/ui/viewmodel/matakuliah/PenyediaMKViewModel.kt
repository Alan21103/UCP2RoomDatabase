package com.example.ucp2roomdatabase.ui.viewmodel.matakuliah

import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.createSavedStateHandle
import androidx.lifecycle.viewmodel.CreationExtras
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.ucp2roomdatabase.KrsApp

object PenyediaMKViewModel{

    val Factory = viewModelFactory {
        initializer {
            MataKuliahViewModel(
                krsApp().containerApp.repositoryMataKuliah
            )
        }

        initializer {
            HomeMKViewModel(
                krsApp().containerApp.repositoryMataKuliah
            )
        }

        initializer {
            DetailMKViewModel(
                createSavedStateHandle(),
                krsApp().containerApp.repositoryMataKuliah,
            )
        }

        initializer {
            UpdateMKViewModel(
                createSavedStateHandle(),
                krsApp().containerApp.repositoryMataKuliah,
            )
        }
    }
}

fun CreationExtras.krsApp(): KrsApp =
    (this[ViewModelProvider.AndroidViewModelFactory.APPLICATION_KEY] as KrsApp)

