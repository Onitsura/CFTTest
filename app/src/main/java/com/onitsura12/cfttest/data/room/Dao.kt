package com.onitsura12.cfttest.data.room

import androidx.room.Insert
import androidx.room.Dao
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.onitsura12.cfttest.data.models.CardDetails
import com.onitsura12.cfttest.data.models.CardDetailsDatabaseModel
import kotlinx.coroutines.flow.Flow

@Dao
interface Dao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addDetails(details: CardDetailsDatabaseModel)

    @Query(value = "SELECT * FROM details_table ORDER BY id ASC")
    fun readAllData(): Flow<List<CardDetailsDatabaseModel>>


}