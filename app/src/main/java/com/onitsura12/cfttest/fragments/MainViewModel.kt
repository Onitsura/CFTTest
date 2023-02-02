package com.onitsura12.cfttest.fragments

import android.content.Context
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.onitsura12.cfttest.data.models.CardDetails
import com.onitsura12.cfttest.data.models.CardDetailsDatabaseModel
import com.onitsura12.cfttest.data.room.DetailsDatabase
import com.onitsura12.cfttest.data.room.DetailsRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(@ApplicationContext context: Context) : ViewModel() {

    private val repository: DetailsRepository
    private val readAllData: Flow<List<CardDetailsDatabaseModel>>
    private val _historyList: MutableLiveData<ArrayList<CardDetailsDatabaseModel>> =
        MutableLiveData()
    val historyList: LiveData<ArrayList<CardDetailsDatabaseModel>> = _historyList

    init {

        val dao = DetailsDatabase.getDatabase(context = context).detailsDao()
        repository = DetailsRepository(dao = dao)
        readAllData = repository.readAllData


    }

    fun getList() {
        viewModelScope.launch {
            readAllData.flowOn(Dispatchers.IO).collect {
                _historyList.value = it as ArrayList<CardDetailsDatabaseModel>
            }
        }
    }


}