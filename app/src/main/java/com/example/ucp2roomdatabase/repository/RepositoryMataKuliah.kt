package com.example.ucp2roomdatabase.repository

import com.example.ucp2roomdatabase.data.dao.MataKuliahDao
import com.example.ucp2roomdatabase.data.entity.MataKuliah
import kotlinx.coroutines.flow.Flow

interface RepositoryMataKuliah {
    //Suspend Untuk operasi berat create,update,delete
    suspend fun insertMatKul(mataKuliah: MataKuliah)

    fun getAllMatKul() : Flow<List<MataKuliah>>

    fun getMatKul(kode : String) : Flow<MataKuliah>

    suspend fun deleteMatKul(mataKuliah: MataKuliah)

    suspend fun updateMatKul(mataKuliah: MataKuliah)
}