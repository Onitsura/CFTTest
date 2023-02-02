package com.onitsura12.cfttest.data.room


import com.onitsura12.cfttest.data.models.CardDetailsDatabaseModel
import kotlinx.coroutines.flow.Flow

class DetailsRepository(private val dao: Dao) {

    val readAllData: Flow<List<CardDetailsDatabaseModel>> = dao.readAllData()

    suspend fun addDetails(cardDetails: CardDetailsDatabaseModel) {
        dao.addDetails(cardDetails)
    }


}