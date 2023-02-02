package com.onitsura12.cfttest.fragments

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.onitsura12.cfttest.network.ApiService
import com.onitsura12.cfttest.network.models.CardDetails
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailsViewModel@Inject constructor(private val apiService: ApiService) : ViewModel() {

    private val _cardDetails: MutableLiveData<CardDetails> = MutableLiveData()
    val cardDetails: LiveData<CardDetails> = _cardDetails
    private val number: MutableLiveData<Long> = MutableLiveData()


    init {

    }

    fun getInfo(cardNumber: Long) {
        Log.i("Card", cardNumber.toString())
        number.value = cardNumber.also { getCardDetails(number.value!!) }
    }


    private fun getCardDetails(cardNumber: Long) = CoroutineScope(Dispatchers.IO).launch {

            try {
                apiService.getDetailsByNumber(cardNumber = cardNumber)
                Log.i("Card", apiService.getDetailsByNumber(cardNumber = cardNumber).toString())
            }
            catch (e: Exception) {
        Log.e("sendNotification", e.toString())
    }
    }


}