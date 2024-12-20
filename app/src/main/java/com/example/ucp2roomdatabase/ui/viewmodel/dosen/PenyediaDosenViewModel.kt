package com.example.ucp2roomdatabase.ui.viewmodel.dosen

import androidx.lifecycle.createSavedStateHandle
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.ucp2roomdatabase.KrsApp

object PenyediaDosenViewModel{

    val Factory = viewModelFactory {
        initializer {
            DosenViewModel(
                KrsApp().containerApp.repositoryDosen,
            )
        }
        initializer {
            HomeDosenViewModel(
                KrsApp().containerApp.repositoryDosen,
            )
        }
        initializer {
            DetailDosenViewModel(
                createSavedStateHandle(),
                KrsApp().containerApp.repositoryDosen,
            )
        }
    }
}