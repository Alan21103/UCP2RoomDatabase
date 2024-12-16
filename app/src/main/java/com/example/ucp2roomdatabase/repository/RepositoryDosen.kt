package com.example.ucp2roomdatabase.repository

import com.example.ucp2roomdatabase.data.entity.Dosen
import kotlinx.coroutines.flow.Flow

interface RepositoryDosen {

    suspend fun insertDosen(dosen: Dosen)

    fun getAllDosen() : Flow<List<Dosen>>

    fun getDosen(nidn : String) : Flow<Dosen>
}