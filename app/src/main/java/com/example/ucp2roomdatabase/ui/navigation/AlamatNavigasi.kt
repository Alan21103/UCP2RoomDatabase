package com.example.ucp2roomdatabase.ui.navigation

interface AlamatNavigasi {
    val route: String
}

object DestinasiHome : AlamatNavigasi{
    override val route = "home"
}

object DestinasiDosen : AlamatNavigasi {
    override val route = "dosen"
}

object DestinasiDosenDetail : AlamatNavigasi {
    override val route = "dosendetail"
    const val NIDN = "nidn"
    val routesWithArg = "$route/{$NIDN}"
}

object DestinasiDetail : AlamatNavigasi{
    override val route = "detail"
    const val KODE = "kode"
    val routesWithArg = "$route/{$KODE}"
}

object DestinasiUpdate : AlamatNavigasi{
    override val route = "update"
    const val KODE = "kode"
    val routesWithArg = "$route/{$KODE}"
}