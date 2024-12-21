package com.example.ucp2roomdatabase.ui.view.Splash

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.ucp2roomdatabase.R

@Composable
fun SplashView(
    onDosenClick: () -> Unit,
    onMataKuliahClick: () -> Unit,
    modifier: Modifier = Modifier
){
    Column (
        modifier = Modifier
            .fillMaxSize()
            .background(color = colorResource(
                id = R.color.teal_700
            )
            ),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ){
        Image(
            painter = painterResource(
                id = R.drawable.umy3
            ),
            contentDescription = "",
            modifier = Modifier.size(150.dp)
        )
        Spacer(modifier = Modifier.padding(16.dp))
        // Tombol untuk "Dosen"
        Button(
            onClick = { onDosenClick() },
            modifier = Modifier
                .fillMaxWidth()
                .padding(20.dp)
                .height(56.dp), // Menggunakan height untuk proporsionalitas
            colors = ButtonDefaults.buttonColors(
                containerColor = Color(0xFF276AA3),
                contentColor = Color.White
            )
        ) {
            Icon(
                imageVector = Icons.Default.Person, // Ikon yang sesuai, bisa diganti
                contentDescription = "Dosen Icon",
                modifier = Modifier.padding(end = 8.dp) // Memberi jarak antara ikon dan teks
            )
            Text(
                text = "Dosen",
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold
            )
        }

// Spacer untuk jarak antar tombol
        Spacer(modifier = Modifier.height(16.dp))

// Tombol untuk "MataKuliah"
        Button(
            onClick = { onMataKuliahClick() },
            modifier = Modifier
                .fillMaxWidth()
                .padding(20.dp)
                .height(56.dp), // Konsisten dengan tombol pertama
            colors = ButtonDefaults.buttonColors(
                containerColor = Color(0xFF276AA3),
                contentColor = Color.White
            )
        ) {
            Icon(
                imageVector = Icons.Default.DateRange, // Ikon yang sesuai, bisa diganti
                contentDescription = "MataKuliah Icon",
                modifier = Modifier.padding(end = 8.dp)
            )
            Text(
                text = "MataKuliah",
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold
            )
        }
    }
}