package com.onitsura12.cfttest.fragments

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.onitsura12.cfttest.network.ApiService
import com.onitsura12.cfttest.data.models.CardDetails
import com.onitsura12.cfttest.data.models.CardDetailsDatabaseModel
import com.onitsura12.cfttest.data.room.DetailsDatabase
import com.onitsura12.cfttest.data.room.DetailsRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailsViewModel @Inject constructor(
    private val apiService: ApiService,
    @ApplicationContext context: Context
) : ViewModel() {

    private val _cardDetails: MutableLiveData<CardDetails> = MutableLiveData()
    val cardDetails: LiveData<CardDetails> = _cardDetails
    val number: MutableLiveData<Long> = MutableLiveData()
    private val repository: DetailsRepository
    private val readAllData: Flow<List<CardDetailsDatabaseModel>>

    init {
        val dao = DetailsDatabase.getDatabase(context = context).detailsDao()
        repository = DetailsRepository(dao = dao)
        readAllData = repository.readAllData
    }


    fun addDetails(cardDetails: CardDetailsDatabaseModel) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.addDetails(cardDetails = cardDetails)
        }
    }


    fun getInfo() {
        viewModelScope.launch {
            getCardDetails(number.value!!).flowOn(Dispatchers.IO).collect {
                _cardDetails.value = it
            }
        }
    }


    private suspend fun getCardDetails(cardNumber: Long): Flow<CardDetails> {
        return getDetailsByNumber(cardNumber).asFlow()
    }


    private suspend fun getDetailsByNumber(cardNumber: Long): CardDetails {
        return apiService.getDetailsByNumber(cardNumber = cardNumber)
    }


}

private fun CardDetails.asFlow(): Flow<CardDetails> {
    val list = mutableListOf(this)
    return flow {
        emitAll(list.asFlow())
    }.flowOn(Dispatchers.IO)
}
