package com.example.ucp2roomdatabase.ui.view.Splash

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
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
        Button(onClick = { onDosenClick() }, modifier = Modifier
            .fillMaxWidth()
            .padding(20.dp)
            .size(40.dp),
        ) {
            Text(text = "Dosen",
                fontSize = 15.sp
            )
        }

        Spacer(modifier = Modifier.padding(16.dp))
        Button(onClick = { onMataKuliahClick() }, modifier = Modifier
            .fillMaxWidth()
            .padding(20.dp)
            .size(40.dp),
        ) {
            Text(text = "MataKuliah",
                fontSize = 15.sp
            )
        }
    }
}