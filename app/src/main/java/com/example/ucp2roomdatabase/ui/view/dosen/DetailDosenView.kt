package com.example.ucp2roomdatabase.ui.view.dosen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
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

@Composable
fun ComponentDetailDosen (
    modifier: Modifier = Modifier,
    judul: String,
    isinya: String,
) {
    Column(
        modifier = modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.Start
    ) {
        Text(
            text = "$judul : ",
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold,
            color = Color.Gray

        )
        Text(
            text = isinya, fontSize = 20.sp,
            fontWeight = FontWeight.Bold,
        )
    }
}