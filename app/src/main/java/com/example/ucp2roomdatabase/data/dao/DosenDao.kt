package com.example.ucp2roomdatabase.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.ucp2roomdatabase.data.entity.Dosen
import kotlinx.coroutines.flow.Flow

@Dao
interface DosenDao {
    @Insert
    suspend fun insertDosen(
        dosen: Dosen
    )

    @Query("SELECT * FROM dosen ORDER BY nama ASC")
    fun getAllDosen () : Flow<List<Dosen>>

    @Query("SELECT * FROM dosen WHERE nidn = :nidn")
    fun getDosen (nidn: String) : Flow<Dosen>
}