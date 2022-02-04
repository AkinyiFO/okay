package com.ibrajix.directcurrencyconverter.data

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow

@Dao
interface SingleLogDao {

    @Query("SELECT * from singlelogentity ORDER BY amount ASC")
    fun getItems(): Flow<List<SingleLogEntity>>

    @Query("SELECT * from singlelogentity WHERE id = :id")
    fun getItem(id: Int): Flow<SingleLogEntity>

    // Specify the conflict strategy as IGNORE, when the user tries to add an
    // existing Item into the database.
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(singleLogEntity: SingleLogEntity)

    @Update
    suspend fun update(singleLogEntity: SingleLogEntity)

    @Delete
    suspend fun delete(singleLogEntity: SingleLogEntity)
}