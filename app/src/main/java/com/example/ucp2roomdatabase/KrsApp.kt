package com.example.ucp2roomdatabase

import android.app.Application
import com.example.ucp2roomdatabase.depedenciesinjection.ContainerApp

class KrsApp : Application(){
    // Fungsinya untuk menyimpan instance Container
    lateinit var containerApp: ContainerApp

    override fun onCreate() {
        super.onCreate()
        // Membuat instance ContainerApp
        containerApp = ContainerApp(this)
        //Instance adalah object yang dibuat dari kelas
    }
}