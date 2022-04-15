package com.karyadika.cekongkir.ui.tabcekongkir.cost

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.karyadika.cekongkir.data.CekOngkirRepository
import com.karyadika.cekongkir.utils.Resource
import com.karyadika.cekongkir.data.local.preferences.PreferencesModel
import com.karyadika.cekongkir.data.remote.response.CostResponse
import kotlinx.coroutines.launch
import timber.log.Timber

class CostViewModel(private val repository: CekOngkirRepository) : ViewModel() {

    val preferences: MutableLiveData<List<PreferencesModel>> = MutableLiveData()
    val costResponse: MutableLiveData<Resource<CostResponse>> = MutableLiveData()

    fun getPreferences(){
        preferences.value = repository.getPreferences()
    }

    fun fetchCost(origin: String, originType: String, destination: String, destinationType: String, weight: String, courier: String) = viewModelScope.launch {
        costResponse.value = Resource.Loading()
        try {
            val response = repository.fetchCost(origin, originType, destination, destinationType, weight, courier)
            costResponse.value = Resource.Success(response.body()!!)
        } catch (e: Exception){
            costResponse.value = Resource.Error(e.message.toString())
            Timber.e(e)
        }
    }

}