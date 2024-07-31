package io.dexnet.marketkit.storage

import androidx.room.*
import io.dexnet.marketkit.models.*

@Dao
interface TokenEntityDao {

    @Query("SELECT * FROM TokenEntity")
    fun getAll(): List<TokenEntity>

}
