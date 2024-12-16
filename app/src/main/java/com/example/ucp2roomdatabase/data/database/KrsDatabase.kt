package com.example.ucp2roomdatabase.data.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.ucp2roomdatabase.data.dao.DosenDao
import com.example.ucp2roomdatabase.data.dao.MataKuliahDao
import com.example.ucp2roomdatabase.data.entity.Dosen
import com.example.ucp2roomdatabase.data.entity.MataKuliah

@Database(entities = [Dosen::class], [MataKuliah::class],version = 1, exportSchema = false)
abstract class KrsDatabase: RoomDatabase() {

    abstract fun dosenDao(): DosenDao
    abstract fun mataKuliahDao(): MataKuliahDao

    companion object{
        @Volatile // Memastikan bahwa nilai variable instance selalu sama di semua thread
        private var Instance: KrsDatabase? = null

        fun getDatabase(context: Context): KrsDatabase {
            return (Instance ?: synchronized(this){
                Room.databaseBuilder(
                    context,
                    KrsDatabase::class.java, // Class Database
                    "KrsDatabase" // Nama Database
                )
                    .build().also { Instance= it }
            })
        }
    }
}