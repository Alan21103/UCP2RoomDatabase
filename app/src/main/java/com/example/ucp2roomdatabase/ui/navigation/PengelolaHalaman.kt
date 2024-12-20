package com.example.ucp2roomdatabase.ui.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.ucp2roomdatabase.ui.view.Splash.SplashView
import com.example.ucp2roomdatabase.ui.view.dosen.DestinasiDosenInsert
import com.example.ucp2roomdatabase.ui.view.dosen.DetailDosenView
import com.example.ucp2roomdatabase.ui.view.dosen.HomeDosenView
import com.example.ucp2roomdatabase.ui.view.dosen.InsertDosenView
import com.example.ucp2roomdatabase.ui.view.matakuliah.DestinasiInsertMK
import com.example.ucp2roomdatabase.ui.view.matakuliah.DetailMKView
import com.example.ucp2roomdatabase.ui.view.matakuliah.HomeMKView
import com.example.ucp2roomdatabase.ui.view.matakuliah.InsertMKView
import com.example.ucp2roomdatabase.ui.view.matakuliah.UpdateMKView

@Composable
fun PengelolaHalaman(
    navController: NavHostController = rememberNavController(),
    modifier: Modifier = Modifier
){
    NavHost(
        navController = navController,
        startDestination = DestinasiHome.route
    ) {
        composable(route = DestinasiHome.route) {
            SplashView(
                onDosenClick = {
                    navController.navigate(DestinasiDosen.route)
                },
                onMataKuliahClick = {
                    navController.navigate(DestinasiMataKuliah.route)
                },
                modifier = modifier
            )
        }
        // Dosen List Screen
        composable(route = DestinasiDosen.route) {
            HomeDosenView(
                onDetailClick = { nidn ->
                    navController.navigate("${DestinasiDosenDetail.route}/$nidn")
                    println("PengelolaHalaman = $nidn")
                },
                onBack = { navController.popBackStack() },
                onAddDosen = {
                    navController.navigate(DestinasiDosenInsert.route)
                },
                modifier = modifier
            )
        }

        // Insert Dosen Screen
        composable(
            route = DestinasiDosenInsert.route
        ) {
            InsertDosenView(
                onBack = { navController.popBackStack() },
                onNavigate = { navController.popBackStack() },
                modifier = modifier
            )
        }

        // Detail Dosen Screen
        composable(
            DestinasiDosenDetail.routesWithArg,
            arguments = listOf(
                navArgument(DestinasiDosenDetail.NIDN) {
                    type = NavType.StringType
                }
            )
        ) {
            val nidn = it.arguments?.getString(DestinasiDosenDetail.NIDN)
            nidn?.let { nidn ->
                DetailDosenView(
                    onBack = {
                        navController.popBackStack()
                    },
                    modifier = modifier
                )
            }
        }

        composable(
            route = DestinasiMataKuliah.route
        ) {
            HomeMKView(
                onDetailClick = {kode ->
                    navController.navigate("${DestinasiMKDetail.route}/$kode")
                    println("PengelolaHalaman: nim = $kode")
                },
                onBack = {
                    navController.popBackStack()
                },
                onAddMK = {
                    navController.navigate(DestinasiInsertMK.route)
                },
                modifier = modifier
            )
        }

        composable (
            route = DestinasiInsertMK.route
        ) {
            InsertMKView(
                onBack = {
                    navController.popBackStack()
                },
                onNavigate = {
                    navController.popBackStack()
                },
                modifier = modifier
            )
        }

        composable (
            DestinasiMKDetail.routesWithArg,
            arguments = listOf(
                navArgument(DestinasiMKDetail.KODE) {
                    type = NavType.StringType
                }
            )
        ){
            val kode = it.arguments?.getString(DestinasiMKDetail.KODE)

            kode?.let { kode ->
                DetailMKView(
                    onBack = {
                        navController.popBackStack()
                    },
                    onEditClick = {
                        navController.navigate("${DestinasiMKUpdate.route}/$kode")
                    },
                    modifier = modifier,
                    onDeleteClick = {
                        navController.popBackStack()
                    }
                )
            }
        }

        composable(
            DestinasiMKUpdate.routesWithArg,
            arguments = listOf(
                navArgument (DestinasiMKUpdate.KODE) {
                    type = NavType.StringType
                }
            )
        ) {
            UpdateMKView(
                onBack = {
                    navController.popBackStack()
                },
                onNavigate = {
                    navController.popBackStack()
                },
                modifier = modifier,
            )
        }
    }
}
