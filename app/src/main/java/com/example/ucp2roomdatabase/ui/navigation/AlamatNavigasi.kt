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

object DestinasiMataKuliah : AlamatNavigasi {
    override val route = "matakuliah"
}

object DestinasiMKDetail : AlamatNavigasi{
    override val route = "MKdetail"
    const val KODE = "kode"
    val routesWithArg = "$route/{$KODE}"
}

object DestinasiMKUpdate : AlamatNavigasi{
    override val route = "MKupdate"
    const val KODE = "kode"
    val routesWithArg = "$route/{$KODE}"
}