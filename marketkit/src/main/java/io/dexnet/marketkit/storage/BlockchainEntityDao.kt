package io.dexnet.marketkit.storage

import androidx.room.*
import io.dexnet.marketkit.models.*

@Dao
interface BlockchainEntityDao {

    @Query("SELECT * FROM BlockchainEntity")
    fun getAll(): List<BlockchainEntity>

}
