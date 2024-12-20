package com.example.ucp2roomdatabase.ui.view.dosen

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.ucp2roomdatabase.ui.viewmodel.dosen.DetailDosenViewModel
import com.example.ucp2roomdatabase.ui.viewmodel.dosen.PenyediaDosenViewModel
import com.example.ucp2roomdatabase.ui.viewmodel.matakuliah.DetailMKViewModel
import com.example.ucp2roomdatabase.ui.viewmodel.matakuliah.PenyediaMKViewModel

@Composable
fun DetailDosenView (
    modifier: Modifier = Modifier,
    viewModel: DetailDosenViewModel = viewModel (factory = PenyediaDosenViewModel.Factory),
    onBack: () -> Unit = { },
    onEditClick: (String) -> Unit = { },
    onDeleteClick: () -> Unit = { }
){

}