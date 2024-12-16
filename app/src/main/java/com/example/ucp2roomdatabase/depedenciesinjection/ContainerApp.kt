package com.example.ucp2roomdatabase.depedenciesinjection

import android.content.Context
import com.example.ucp2roomdatabase.data.database.KrsDatabase
import com.example.ucp2roomdatabase.repository.LocalRepositoryDosen
import com.example.ucp2roomdatabase.repository.LocalRepositoryMataKuliah
import com.example.ucp2roomdatabase.repository.RepositoryDosen
import com.example.ucp2roomdatabase.repository.RepositoryMataKuliah

interface InterfaceContainerApp{
    val repositoryDosen: RepositoryDosen
    val repositoryMataKuliah: RepositoryMataKuliah
}

class ContainerApp(private val context: Context) : InterfaceContainerApp{
    override val repositoryDosen: RepositoryDosen by lazy {
        LocalRepositoryDosen(KrsDatabase.getDatabase(context).dosenDao())
    }

    override val repositoryMataKuliah: RepositoryMataKuliah by lazy {
        LocalRepositoryMataKuliah(KrsDatabase.getDatabase(context).mataKuliahDao())
    }
}