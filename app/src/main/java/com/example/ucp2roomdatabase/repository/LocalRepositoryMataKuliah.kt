package com.example.ucp2roomdatabase.repository

import com.example.ucp2roomdatabase.data.dao.MataKuliahDao
import com.example.ucp2roomdatabase.data.entity.MataKuliah
import kotlinx.coroutines.flow.Flow

class LocalRepositoryMataKuliah (
    private val mataKuliahDao: MataKuliahDao
) : RepositoryMataKuliah {
    override suspend fun insertMatKul(mataKuliah: MataKuliah) {
        mataKuliahDao.insertMatkul(mataKuliah)
    }

    override fun getAllMatKul(): Flow<List<MataKuliah>> {
        return mataKuliahDao.getAllMatKul()
    }

    override fun getMatKul(nidn: String): Flow<MataKuliah> {
        return mataKuliahDao.getMatKul(nidn)
    }

    override suspend fun deleteMatKul(mataKuliah: MataKuliah) {
        mataKuliahDao.deleteMatkul(mataKuliah)
    }

    override suspend fun updateMatKul(mataKuliah: MataKuliah) {
        mataKuliahDao.updateMatkul(mataKuliah)
    }
}