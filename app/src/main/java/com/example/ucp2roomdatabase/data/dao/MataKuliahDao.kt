package com.example.ucp2roomdatabase.data.dao

import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.example.ucp2roomdatabase.data.entity.MataKuliah
import kotlinx.coroutines.flow.Flow

interface MataKuliahDao {
    @Insert
    suspend fun insertMatkul(
        mataKuliah: MataKuliah
    )

    @Query("SELECT * FROM matakuliah ORDER BY nama ASC")
    fun getAllMatKul () : Flow<List<MataKuliah>>

    @Query("SELECT * FROM matakuliah WHERE kode = :kode")
    fun getMatKul (kode: String) : Flow<MataKuliah>

    @Delete
    suspend fun deleteMatkul (mataKuliah: MataKuliah)

    @Update
    suspend fun updateMatkul (mataKuliah: MataKuliah)
}