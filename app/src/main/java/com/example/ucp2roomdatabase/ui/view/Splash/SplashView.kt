package com.example.ucp2roomdatabase.ui.view.Splash

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
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
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.ucp2roomdatabase.R

@Composable
fun SplashView(
    onDosenClick: () -> Unit,
    onMataKuliahClick: () -> Unit,
    modifier: Modifier = Modifier
){
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = colorResource(id = R.color.purple_500)
            )
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // Teks Judul
        Text(
            text = "Welcome to Krs UMY",
            fontSize = 28.sp,
            fontWeight = FontWeight.ExtraBold,
            color = Color.White,
            textAlign = TextAlign.Center,
            modifier = Modifier.padding(bottom = 24.dp)
        )

        // Gambar
        Image(
            painter = painterResource(id = R.drawable.umy3),
            contentDescription = "UMY Logo",
            modifier = Modifier
                .size(150.dp)
                .clip(CircleShape)
                .border(3.dp, Color.White, CircleShape)
        )

        Spacer(modifier = Modifier.height(40.dp))

        // Row untuk Tombol
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            // Tombol Dosen
            Button(
                onClick = onDosenClick,
                modifier = Modifier
                    .weight(1f) // Membuat tombol proporsional dalam Row
                    .height(56.dp), // Bentuk kotak
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color.White,
                    contentColor = Color(0xFF276AA3)
                )
            ) {
                Icon(
                    imageVector = Icons.Default.Person,
                    contentDescription = "Dosen Icon",
                    tint = Color(0xFF276AA3),
                    modifier = Modifier.padding(end = 8.dp)
                )

                Text(
                    text = "Dosen",
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold
                )
            }

            Spacer(modifier = Modifier.width(16.dp)) // Jarak antar tombol

            // Tombol MataKuliah
            Button(
                onClick = onMataKuliahClick,
                modifier = Modifier
                    .weight(1f) // Membuat tombol proporsional dalam Row
                    .height(56.dp), // Bentuk kotak
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color.White,
                    contentColor = Color(0xFF276AA3)
                )
            ) {
                Icon(
                    imageVector = Icons.Default.DateRange,
                    contentDescription = "MataKuliah Icon",
                    tint = Color(0xFF276AA3),
                    modifier = Modifier.padding(end = 8.dp)
                )

                Text(
                    text = "MataKuliah",
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold
                )
            }
        }
    }
}